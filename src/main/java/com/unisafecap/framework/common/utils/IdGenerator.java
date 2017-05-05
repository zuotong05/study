package com.unisafecap.framework.common.utils;

import java.util.UUID;

/**
 * <P>
 * UUID生成器
 * </P>
 * 
 * @author 左通
 * @since 2017年1月11日
 * @version V1.0
 */
public class IdGenerator {
	/**
	 * 获得长度36位数UUID
	 * 
	 * @return String
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 获得长度32位数UUID
	 * 
	 * @return String
	 */
	public static String getUUID32() {
		return getUUID().replaceAll("-", "");
	}

	/**
	 * 获得长度32位数数字UUID,a转化成1,b转化成2,c转化成3,d转化成4,e转化成5,f转化成6
	 * 
	 * @return String
	 */
	public static String getUUIDNumber() {
		String uuid = getUUID32();
		return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
	}

	

}
