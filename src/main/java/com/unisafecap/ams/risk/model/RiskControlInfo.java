package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;

/**
 * <P>
 * 风控信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0
 */
public class RiskControlInfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 8864288174830295899L;
	/**
	 * 合作机构编号
	 */
	private String orgCode;
	/**
	 * 信托项目简码
	 */
	private String trustProjectCode;
	/**
	 * 请求发送时间
	 */
	private String timestamp;
	/**
	 * 外部流水号
	 */
	private String outTradeNo;
	/**
	 * 回调地址
	 */
	private String callbackUrl;
	/**
	 * 0=客户、放款单
	 */
	private Integer riskControlType;
	/**
	 * 内部流水号
	 */
	private String tradeNo;
	/**
	 * 审核状态
	 */
	private String auditStatus;
	/**
	 * 审核信息
	 */
	private String auditMessages;
	/**
	 * 发送状态
	 */
	private String sendStatus;
	/**
	 * 发送时间
	 */
	private java.util.Date sendTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createdTime;
	/**
	 * 创建人
	 */
	private String createdBy;
	/**
	 * 更新时间
	 */
	private java.util.Date updatedTime;
	/**
	 * 更新人
	 */
	private String updatedBy;
	/**
	 * 备注1
	 */
	private String remark1;
	/**
	 * 备注2
	 */
	private String remark2;
	/**
	 * 备注3
	 */
	private String remark3;

	public RiskControlInfo() {
		super();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

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

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public Integer getRiskControlType() {
		return riskControlType;
	}

	public void setRiskControlType(Integer riskControlType) {
		this.riskControlType = riskControlType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditMessages() {
		return auditMessages;
	}

	public void setAuditMessages(String auditMessages) {
		this.auditMessages = auditMessages;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public java.util.Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	public java.util.Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public java.util.Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(java.util.Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

}
