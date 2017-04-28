package com.unisafecap.framework.common.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <P>
 * 身份证工具类
 * </P>
 * 
 * @author 左通
 * @since 2017年1月11日
 * @version V1.0
 */
public class IDCardUtils {

	/** 中国公民身份证号码最小长度。 */
	public static final int CHINA_ID_MIN_LENGTH = 15;

	/** 中国公民身份证号码最大长度。 */
	public static final int CHINA_ID_MAX_LENGTH = 18;

	public static Map<String, String> cityCodes = new HashMap<String, String>();
	static {
		cityCodes.put("11", "北京");
		cityCodes.put("12", "天津");
		cityCodes.put("13", "河北");
		cityCodes.put("14", "山西");
		cityCodes.put("15", "内蒙古");
		cityCodes.put("21", "辽宁");
		cityCodes.put("22", "吉林");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("31", "上海");
		cityCodes.put("32", "江苏");
		cityCodes.put("33", "浙江");
		cityCodes.put("34", "安徽");
		cityCodes.put("35", "福建");
		cityCodes.put("36", "江西");
		cityCodes.put("37", "山东");
		cityCodes.put("41", "河南");
		cityCodes.put("42", "湖北");
		cityCodes.put("43", "湖南");
		cityCodes.put("44", "广东");
		cityCodes.put("45", "广西");
		cityCodes.put("46", "海南");
		cityCodes.put("50", "重庆");
		cityCodes.put("51", "四川");
		cityCodes.put("52", "贵州");
		cityCodes.put("53", "云南");
		cityCodes.put("54", "西藏");
		cityCodes.put("61", "陕西");
		cityCodes.put("62", "甘肃");
		cityCodes.put("63", "青海");
		cityCodes.put("64", "宁夏");
		cityCodes.put("65", "新疆");
		cityCodes.put("71", "台湾");
		cityCodes.put("81", "香港");
		cityCodes.put("82", "澳门");
		cityCodes.put("91", "国外");

	}

	/**
	 * 判断是否是身份证
	 * 
	 * @param idcard
	 *            身份编号
	 * @return boolean
	 */
	public static boolean isIDCard(String idCard) {		
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		if (idCard.length() != CHINA_ID_MAX_LENGTH) {
			return false;
		}
		// 获取输入身份证上的最后一位，它是校验码
		String checkDigit = idCard.substring(17, 18);
		// 比较获取的校验码与本方法生成的校验码是否相等
		if (checkDigit.equals(getCheckDigit(idCard))) {
			return true;
		}
		return false;
	}

	/**
	 * 将15位身份证号码转换为18位
	 * 
	 * @param idCard
	 *            15位身份编码
	 * @return String 18位身份编码
	 */
	public static String conver15CardTo18(String idCard) {
		// 15位身份证上的生日中的年份没有19，要加上
		String eighteenCardID = idCard.substring(0, 6);
		eighteenCardID = eighteenCardID + "19";
		eighteenCardID = eighteenCardID + idCard.substring(6, 15);
		eighteenCardID = eighteenCardID + getCheckDigit(eighteenCardID);
		return eighteenCardID;
	}

	/**
	 * 根据身份编号获取年龄
	 * 
	 * @param idCard
	 *            身份编号
	 * @return int 年龄
	 */
	public static int getAgeByIDCard(String idCard) {
		int iAge = 0;
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String year = idCard.substring(6, 10);
		Calendar cal = Calendar.getInstance();
		int iCurrYear = cal.get(Calendar.YEAR);
		iAge = iCurrYear - Integer.valueOf(year);
		return iAge;
	}

	/**
	 * 根据身份编号获取生日 ，格式：yyyyMMdd
	 * 
	 * @param idCard
	 *            身份编号
	 * @return String 生日
	 */
	public static String getBirthByIDCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return idCard.substring(6, 14);
	}

	/**
	 * 根据身份编号获取生日月
	 * 
	 * @param idCard
	 *            身份编号
	 * @return String 生日(MM)
	 */
	public static String getMonthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return idCard.substring(10, 12);
	}

	/**
	 * 根据身份编号获取生日天
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(dd)
	 */
	public static String getDateByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return idCard.substring(12, 14);
	}

	/**
	 * 根据身份编号获取性别
	 * 
	 * @param idCard
	 *            身份编码
	 * @return String 性别(M-男，F-女，N-未知)
	 */
	public static String getGenderByIDCard(String idCard) {
		String sGender = "N";
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "M";
		} else {
			sGender = "F";
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取户籍省份
	 * 
	 * @param idCard
	 *            身份编码
	 * @return String 省份名称
	 */
	public static String getProvinceByIdCard(String idCard) {
		int len = idCard.length();
		String sProvince = null;
		String sProvinNum = "";
		if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
			sProvinNum = idCard.substring(0, 2);
		}
		sProvince = cityCodes.get(sProvinNum);
		return sProvince;
	}

	private static String getCheckDigit(String eighteenCardID) {
		int[] weight = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};// 加权因子
		int[] checkDigit = new int[]{1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};// 第18位校验码
		int remaining = 0;
		if (eighteenCardID.length() == 18) {
			eighteenCardID = eighteenCardID.substring(0, 17);
		}

		if (eighteenCardID.length() == 17) {
			int sum = 0;
			int[] a = new int[17];
			// 先对前17位数字的权求和
			for (int i = 0; i < 17; i++) {
				String k = eighteenCardID.substring(i, i + 1);
				a[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + weight[i] * a[i];
			}
			// 再与11取模
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(checkDigit[remaining]);
	}

	public static void main(String[] args) {
		System.out.println(isIDCard("AAAAAAAAAAAAAAAAAAAA"));
	}

}
