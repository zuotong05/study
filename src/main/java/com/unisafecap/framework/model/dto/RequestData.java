package com.unisafecap.framework.model.dto;

import java.io.Serializable;

/** 
 * <P>
 *	请求数据
 * </P> 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0 
 */
public class RequestData implements Serializable {
	private static final long serialVersionUID = -3372911626170445418L;
	/**
	 * 合作机构编号
	 */
	private String orgCode;
	/**
	 * 请求发送时间
	 */
	private String timestamp;
	/**
	 * 业务数据
	 */
	private String bizContent;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
