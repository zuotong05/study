package com.unisafecap.framework.model.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.unisafecap.framework.common.enums.ServiceErrorCode;
import com.unisafecap.framework.common.utils.StringUtils;

/**
 * <P>
 * 响应数据
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */
public class ResponseData<T> implements Serializable {
	private static final long serialVersionUID = 615870725836711077L;
	/**
	 * 业务返回码
	 */
	private String code;

	/**
	 * 业务返回描述
	 */
	private String msg;

	/**
	 * 业务数据-加密
	 */
	private String bizContent;

	/**
	 * 业务数据
	 */
	private T datas;

	// @JsonIgnore
	@JSONField(serialize = false)
	private ServiceErrorCode serviceErrorCode;

	public ResponseData<T> code(String code) {
		setCode(code);
		return this;
	}

	public ResponseData<T> msg(String msg) {
		setMsg(msg);
		return this;
	}

	public ResponseData<T> bizContent(String bizContent) {
		setBizContent(bizContent);
		return this;
	}

	public ResponseData<T> datas(T datas) {
		setDatas(datas);
		return this;
	}

	public ResponseData<T> serviceErrorCode(ServiceErrorCode errorCode) {
		setServiceErrorCode(errorCode);
		return this;
	}

	public String getCode() {
		if (null != serviceErrorCode && StringUtils.isBlank(code)) {
			return serviceErrorCode.getCode();
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		if (null != serviceErrorCode && StringUtils.isBlank(msg)) {
			return serviceErrorCode.getMessage();
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBizContent() {
		return bizContent;
	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

	public ServiceErrorCode getServiceErrorCode() {
		return serviceErrorCode;
	}

	public void setServiceErrorCode(ServiceErrorCode serviceErrorCode) {
		this.serviceErrorCode = serviceErrorCode;
	}

}
