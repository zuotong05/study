package com.unisafecap.ams.security.sign.util;

public class Base64 {

	public static String encode(byte[] data) {
		return org.bouncycastle.util.encoders.Base64.toBase64String(data);
	}

	public static byte[] decode(String data) {
		return org.bouncycastle.util.encoders.Base64.decode(data);

	}
}