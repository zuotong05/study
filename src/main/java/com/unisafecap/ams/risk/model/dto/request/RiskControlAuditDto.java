package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;
import java.util.List;

import com.unisafecap.ams.risk.model.RiskLoanAccount;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.ams.risk.model.RiskLoanUser;
import com.unisafecap.ams.risk.model.RiskRelationUser;

/**
 * <P>
 * 风控审核
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */

public class RiskControlAuditDto implements Serializable {

	private static final long serialVersionUID = -8112786001511337239L;
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
	 * 贷款单信息
	 */
	private RiskLoanDetail loanApply;

	/**
	 * 个人用户信息
	 */
	private RiskLoanUser loanUser;

	/**
	 * 关系人信息
	 */
	private List<RiskRelationUser> relationUsers;

	/**
	 * 放款账户信息
	 */
	private RiskLoanAccount loanAccount;

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

	public RiskLoanDetail getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(RiskLoanDetail loanApply) {
		this.loanApply = loanApply;
	}

	public RiskLoanUser getLoanUser() {
		return loanUser;
	}

	public void setLoanUser(RiskLoanUser loanUser) {
		this.loanUser = loanUser;
	}

	public List<RiskRelationUser> getRelationUsers() {
		return relationUsers;
	}

	public void setRelationUsers(List<RiskRelationUser> relationUsers) {
		this.relationUsers = relationUsers;
	}

	public RiskLoanAccount getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(RiskLoanAccount loanAccount) {
		this.loanAccount = loanAccount;
	}

}
