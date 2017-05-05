package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;

/** 
 * <P>
 * 风控审核
 * </P> 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0 
 */

public class RiskControlAuditDto implements Serializable {

	private static final long serialVersionUID = -8112786001511337239L;

	/**
	 * 外部流水号
	 */
	private String outTradeNo;

	/**
	 * 回调地址
	 */
	private String callbackUrl;

	/**
	 * 贷款单信息
	 */
	private RiskLoanApplyDto loanApply;

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

	public RiskLoanApplyDto getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(RiskLoanApplyDto loanApply) {
		this.loanApply = loanApply;
	}

}
