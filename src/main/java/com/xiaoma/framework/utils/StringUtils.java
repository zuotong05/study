package com.xiaoma.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P>
 * 字符串工具类
 * <P>
 * 
 * @author 左通
 * @since 2017年1月11日
 * @version V1.0
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * 判断字符串是否是手机号
	 * <p>
	 * 移动：134、135、136、137、138、139、150、151、152、157、158、159、178、182、183、184、187、188
	 * <p>
	 * 联通：130、131、132、155、156、176、185、186
	 * <p>
	 * 电信：133、153、177、180、181、189
	 * <p>
	 * 虚拟运营商：170
	 * 
	 * @param mobile
	 *            字符串
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0,6-8])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(str);
			return m.matches();
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是邮箱
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		try {
			Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
			Matcher matcher = regex.matcher(str);
			return matcher.matches();
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否全部为汉字
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isChinese(String str) {
		try {
			Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+");
			Matcher m = p.matcher(str);
			return m.matches();
		}
		catch (Exception e) {
			return false;
		}
	}

}