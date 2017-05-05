package com.unisafecap.ams.security.sign.common;

import com.unisafecap.ams.security.sign.model.CertInfo;
import com.unisafecap.ams.security.sign.util.CertUtil;

import java.io.ByteArrayOutputStream;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientInformationStore;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SignService {
	private static SignService signService = null;

	public static SignService getInstance() {
		if (null == signService) {
			signService = new SignService();
		}
		return signService;
	}

	private byte[] sign(String cert, String password, byte[] data, boolean isDetach) {
		byte[] signedData = (byte[]) null;
		try {
			CertInfo CertInfo = CertUtil.getCertInfo(password, cert);
			X509Certificate cerx509 = CertInfo.getCert()[0];

			List certList = new ArrayList();
			CMSTypedData msg = new CMSProcessableByteArray(data);
			certList.add(cerx509);
			Store certs = new JcaCertStore(certList);
			CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
			ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(CertInfo.getPriKey());
			gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, cerx509));
			gen.addCertificates(certs);
			CMSSignedData sigData = gen.generate(msg, isDetach);
			signedData = sigData.getEncoded();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return signedData;
	}

	public byte[] detachSign(String cert, String password, byte[] data) {
		return sign(cert, password, data, false);
	}

	public int verifyDetachSign(byte[] data, byte[] signData) {
		try {
			CMSProcessable content = new CMSProcessableByteArray(data);
			CMSSignedData s = new CMSSignedData(content, signData);
			Store certStore = s.getCertificates();
			SignerInformationStore signers = s.getSignerInfos();
			Collection c = signers.getSigners();
			Iterator it = c.iterator();
			int verified = 0;
			int size = 0;
			while (it.hasNext()) {
				++size;
				SignerInformation signer = (SignerInformation) it.next();
				Collection certCollection = certStore.getMatches(signer.getSID());
				Iterator certIt = certCollection.iterator();
				X509CertificateHolder cert = (X509CertificateHolder) certIt.next();
				if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
					++verified;
				}
			}
			if (size == verified)
				return 1;
		}
		catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public byte[] attachSign(String cert, String password, byte[] data) {
		return sign(cert, password, data, true);
	}

	public byte[] verifyAttachSign(byte[] signData) {
		try {
			byte[] data = null;
			CMSSignedData s = new CMSSignedData(signData);
			Store certStore = s.getCertificates();
			SignerInformationStore signers = s.getSignerInfos();
			Collection c = signers.getSigners();
			Iterator it = c.iterator();
			int verified = 0;
			int size = 0;

			while (it.hasNext()) {
				++size;
				SignerInformation signer = (SignerInformation) it.next();
				Collection certCollection = certStore.getMatches(signer.getSID());
				Iterator certIt = certCollection.iterator();
				X509CertificateHolder cert = (X509CertificateHolder) certIt.next();

				if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
					++verified;
					CMSTypedData cmsData = s.getSignedContent();
					data = (byte[]) (byte[]) cmsData.getContent();
				}
			}
			if (size == verified)
				return data;
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}

	public byte[] decryptEnvelop(String cert, String password, byte[] bEnvelop) {
		byte[] sign = null;
		try {
			CMSEnvelopedData enveloped = new CMSEnvelopedData(bEnvelop);
			RecipientInformationStore ris = enveloped.getRecipientInfos();
			CertInfo CertInfo = CertUtil.getCertInfo(password, cert);

			if (ris == null) {
				sign = null;
			}

			Collection c = ris.getRecipients();
			Iterator it = c.iterator();

			while (it.hasNext()) {
				RecipientInformation recipient = (RecipientInformation) it.next();
				sign = recipient.getContent(new JceKeyTransEnvelopedRecipient(CertInfo.getPriKey()).setProvider("BC"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}

	public byte[] encryptEnvelop(String cert, byte[] bOrgData) {
		byte[] envelopData = null;
		X509Certificate x509 = CertUtil.getPublicCert(cert);
		CMSEnvelopedDataGenerator gen = new CMSEnvelopedDataGenerator();
		try {
			CMSProcessableByteArray data = new CMSProcessableByteArray(bOrgData);
			gen.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(x509).setProvider("BC"));
			CMSEnvelopedData enveloped = gen.generate(data, new JceCMSContentEncryptorBuilder(CMSAlgorithm.DES_EDE3_CBC).setProvider("BC").build());

			ContentInfo a = enveloped.toASN1Structure();
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			DEROutputStream dOut = new DEROutputStream(bOut);
			dOut.writeObject(a);
			envelopData = bOut.toByteArray();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return envelopData;
	}

	static {
		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);
	}
}