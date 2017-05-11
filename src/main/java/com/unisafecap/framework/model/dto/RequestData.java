package com.unisafecap.framework.model.dto;

import java.io.Serializable;

/**
 * <P>
 * 请求数据
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */
public class RequestData implements Serializable {
	private static final long serialVersionUID = -3372911626170445418L;
	/**
	 * 信托项目简码
	 */
	private String trustProjectCode;

	/**
	 * 请求发送时间
	 */
	private String timestamp;
	/**
	 * 业务数据
	 */
	private String bizContent;

	public String getTrustProjectCode() {
		return trustProjectCode;
	}

	public void setTrustProjectCode(String trustProjectCode) {
		this.trustProjectCode = trustProjectCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getBizContent() {
		return bizContent;
	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}

}
