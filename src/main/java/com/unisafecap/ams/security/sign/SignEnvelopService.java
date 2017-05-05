package com.unisafecap.ams.security.sign;

public interface SignEnvelopService {
	
	public String signEnvelop(String signCert, String password, String envelopCert, byte[] bOrgData);
	
	public byte[] verifyEnvelop(String envelopCert, String password, String envelopData);
}