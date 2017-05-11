package com.unisafecap.ams.risk.model.dto.response;

import java.io.Serializable;

public class RiskLoanUserResult implements Serializable {
	private static final long serialVersionUID = 4682970402627062162L;

	/**
	 * 外部流水号
	 */
	private String outTradeNo;

	/**
	 * 内部流水号
	 */
	private String tradeNo;

	/**
	 * 信托项目简码
	 */
	private String trustProjectCode;

	/**
	 * 证件类型
	 */
	private String certType;

	/**
	 * 证件号码
	 */
	private String certId;

	/**
	 * 审核状态
	 */
	private String auditStatus;
	/**
	 * 审核信息
	 */
	private String auditMessages;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTrustProjectCode() {
		return trustProjectCode;
	}

	public void setTrustProjectCode(String trustProjectCode) {
		this.trustProjectCode = trustProjectCode;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
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

}
