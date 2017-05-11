package com.unisafecap.ams.risk.model.dto.response;

import java.io.Serializable;

/**
 * <P>
 * 风控审核返回结果
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月5日
 * @version V1.0
 */

public class RiskControlAuditResult<T> implements Serializable {

	private static final long serialVersionUID = -5528466187307850592L;

	/**
	 * 内部流水号
	 */
	private String tradeNo;
	/**
	 * 错误信息
	 */
	private String errorMessages;

	/**
	 * 审批信息
	 */
	private T auditDetail;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public T getAuditDetail() {
		return auditDetail;
	}

	public void setAuditDetail(T auditDetail) {
		this.auditDetail = auditDetail;
	}

}
