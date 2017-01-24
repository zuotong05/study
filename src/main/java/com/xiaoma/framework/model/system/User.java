package com.xiaoma.framework.model.system;

import java.util.Date;

/**
 * <P>
 * 用户接口
 * </P>
 * 
 * @author xiaoma
 * @since 2017年1月19日
 * @version V1.0
 */
public interface User {

	/**
	 * 用户ID
	 * 
	 * @return Long
	 */
	public Long getId();

	/**
	 * 用户名
	 * 
	 * @return String
	 */
	public String getUsername();

	/**
	 * 密码
	 * 
	 * @return String
	 */
	public String getPassword();

	/**
	 * 姓名
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * 昵称
	 * 
	 * @return String
	 */
	public String getNickname();

	/**
	 * 性别
	 * 
	 * @return Integer
	 */
	public Integer getGender();

	/**
	 * 生日
	 * 
	 * @return java.util.Date
	 */
	public Date getBirthday();

	/**
	 * 头像
	 * 
	 * @return String
	 */
	public String getAvatar();

	/**
	 * 电话
	 * 
	 * @return String
	 */
	public String getPhone();

	/**
	 * 邮箱
	 * 
	 * @return String
	 */
	public String getEmail();

	/**
	 * 是否是游客
	 * 
	 * @return Boolean
	 */
	public Boolean isAnonymous();

}
