package com.unisafecap.framework.common.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
	private static Logger log = Logger.getLogger(PropertiesUtils.class);
	private static ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
	public static Properties readPro(String configPath) {
		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(configPath));
			pro.load(in);
		} catch (Exception e) {
			log.error("读取配置文件失败：" + configPath, e);
		}  finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return pro;
	}

	/**
	 * 在class路径下读取文件
	 *
	 * @param clazzPathFile
	 * @return
	 */
	public static Properties readClazzPath(String clazzPathFile) {
		Properties properties = new Properties();
		InputStream is = classLoader.getResourceAsStream(clazzPathFile);
		try {
			properties.load(is);
		} catch (Exception cause) {
			log.error("加载文件发生异常,文件:" + clazzPathFile, cause);
			new RuntimeException("加载文件发生异常,文件:" + clazzPathFile, cause);
		} finally {
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		}

		return properties;
	}


}
