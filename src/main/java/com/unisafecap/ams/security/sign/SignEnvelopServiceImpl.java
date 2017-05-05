package com.unisafecap.ams.security.sign;

import com.unisafecap.ams.security.sign.common.SignService;
import com.unisafecap.ams.security.sign.util.Base64;

public class SignEnvelopServiceImpl implements SignEnvelopService {
	private SignService getSignService() {
		return SignService.getInstance();
	}

	public String signEnvelop(String signCert, String password, String envelopCert, byte[] bOrgData) {
		byte[] signData = getSignService().attachSign(signCert, password, bOrgData);
		byte[] envelop = getSignService().encryptEnvelop(envelopCert, signData);
		return Base64.encode(envelop);
	}

	public byte[] verifyEnvelop(String envelopCert, String password, String envelopData) {
		byte[] envelop = Base64.decode(envelopData);
		byte[] signData = getSignService().decryptEnvelop(envelopCert, password, envelop);
		return getSignService().verifyAttachSign(signData);
	}
}