package com.unisafecap.ams.security.sign.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
	public static byte[] readFile(String filename) {
		File f = new File(filename);
		if (!(f.exists())) {
			return null;
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			byte[] arrayOfByte = bos.toByteArray();
			return arrayOfByte;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (null != in) {
					in.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != bos) {
					bos.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}