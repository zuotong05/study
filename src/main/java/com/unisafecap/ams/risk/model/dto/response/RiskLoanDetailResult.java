package com.unisafecap.ams.risk.model.dto.response;

import java.io.Serializable;

/**
 * <P>
 * 风控审核返回结果-贷款单信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月5日
 * @version V1.0
 */

public class RiskLoanDetailResult implements Serializable {

	private static final long serialVersionUID = -8146796141624830962L;

	/**
	 * 外部流水号
	 */
	private String outTradeNo;

	/**
	 * 信托项目简码
	 */
	private String trustProjectCode;
	/**
	 * 合同号
	 */
	private String contractNo;
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

	public String getTrustProjectCode() {
		return trustProjectCode;
	}

	public void setTrustProjectCode(String trustProjectCode) {
		this.trustProjectCode = trustProjectCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
