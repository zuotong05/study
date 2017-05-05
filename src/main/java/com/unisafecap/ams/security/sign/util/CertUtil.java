package com.unisafecap.ams.security.sign.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import com.unisafecap.ams.security.sign.model.CertInfo;

public class CertUtil {
	private static boolean matchUsage(boolean[] keyUsage, int usage) {
		if ((usage == 0) || (keyUsage == null))
			return true;
		for (int i = 0; i < Math.min(keyUsage.length, 32); ++i) {
			if (((usage & 1 << i) != 0) && (keyUsage[i]))
				return false;
		}
		return true;
	}

	private static CertInfo getSigner(String password, byte[] pfxCert) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		pfxCert = Base64.decode(new String(pfxCert));
		InputStream stream = new ByteArrayInputStream(pfxCert);
		keyStore.load(stream, password.toCharArray());
		Enumeration<?> aliases = keyStore.aliases();
		String keyAlias = null;
		while ((aliases != null) && (aliases.hasMoreElements())) {
			keyAlias = (String) aliases.nextElement();
			Certificate[] certs = keyStore.getCertificateChain(keyAlias);
			if (certs == null)
				continue;
			if (certs.length == 0)
				continue;
			X509Certificate cert = (X509Certificate) certs[0];
			if (matchUsage(cert.getKeyUsage(), 1)) {
				try {
					cert.checkValidity();
				}
				catch (CertificateException e) {}

			}

		}

		if (keyAlias == null) {
			throw new GeneralSecurityException("None certificate for sign in this keystore");
		}
		X509Certificate[] certificates = null;
		if (keyStore.isKeyEntry(keyAlias)) {
			Certificate[] certs = keyStore.getCertificateChain(keyAlias);
			for (int i = 0; i < certs.length; ++i) {
				if (!(certs[i] instanceof X509Certificate)) {
					throw new GeneralSecurityException("Certificate[" + i + "] in chain '" + keyAlias + "' is not a X509Certificate.");
				}

			}

			certificates = new X509Certificate[certs.length];
			for (int i = 0; i < certs.length; ++i) {
				certificates[i] = ((X509Certificate) certs[i]);
			}
		} else if (keyStore.isCertificateEntry(keyAlias)) {
			Certificate cert = keyStore.getCertificate(keyAlias);
			if (cert instanceof X509Certificate)
				certificates = new X509Certificate[]{(X509Certificate) cert};
		} else {
			throw new GeneralSecurityException(keyAlias + " is unknown to this keystore");
		}

		PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());

		if (privateKey == null) {
			throw new GeneralSecurityException(keyAlias + " could not be accessed");
		}

		CertInfo cert = new CertInfo();
		cert.setPriKey(privateKey);
		cert.setCert(certificates);
		return cert;
	}

	public static CertInfo getCertInfo(String password, String cert) {
		try {
			return getSigner(password, cert.getBytes());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static X509Certificate getPublicCert(String cert) {
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X509");
			InputStream inStream = new ByteArrayInputStream(Base64.decode(cert));
			X509Certificate x509 = (X509Certificate) cf.generateCertificate(inStream);
			return x509;
		}
		catch (CertificateException e) {
			e.printStackTrace();
		}
		return null;
	}
}