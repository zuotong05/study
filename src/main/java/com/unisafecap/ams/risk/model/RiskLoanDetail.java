package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;

/**
 * <P>
 * 贷款单信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0
 */
public class RiskLoanDetail extends BaseModel implements Serializable {
	private static final long serialVersionUID = -2030830212230834095L;

	/**
	 * 合作机构编号
	 */
	private String orgCode;
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
	 * 信托项目简码
	 */
	private String trustProjectCode;
	/**
	 * 合同号
	 */
	private String contractNo;
	/**
	 * 申请地点
	 */
	private String applyAddress;
	/**
	 * 渠道来源
	 */
	private String sourceChannel;
	/**
	 * 详细描述
	 */
	private String loanuse;
	/**
	 * 合同金额
	 */
	private java.math.BigDecimal contractSum;
	/**
	 * 实付金额
	 */
	private java.math.BigDecimal actualSum;
	/**
	 * 申请币种
	 */
	private String currency;
	/**
	 * 申请期限(月)
	 */
	private Integer deadline;
	/**
	 * 还款账号类型
	 */
	private String repaymentAccountType;
	/**
	 * 还款账号
	 */
	private String repaymentAccountNo;
	/**
	 * 还款方式
	 */
	private String returnType;
	/**
	 * 扣款日类型
	 */
	private String withholdType;
	/**
	 * 扣款日类别
	 */
	private String withholdDateType;
	/**
	 * 扣款日期
	 */
	private String withholdDate;
	/**
	 * 缴费方式
	 */
	private String payMode;
	/**
	 * 贷款类型
	 */
	private String loanType;
	/**
	 * 借款人类型
	 */
	private String borrowerType;
	/**
	 * 产品编号
	 */
	private String typeNo;
	/**
	 * 产品名称
	 */
	private String typeName;
	/**
	 * 手续费
	 */
	private java.math.BigDecimal operationFee;
	/**
	 * 手续费率
	 */
	private java.math.BigDecimal operationFeeRate;
	/**
	 * 利率
	 */
	private java.math.BigDecimal businessRate;
	/**
	 * 提前还款违约金比率
	 */
	private java.math.BigDecimal deditRate;
	/**
	 * 罚息率(月)
	 */
	private java.math.BigDecimal lateChargeRate;
	/**
	 * 履行担保天数
	 */
	private Integer assureDays;
	/**
	 * 服务费
	 */
	private java.math.BigDecimal serviceFee;
	/**
	 * 服务费率
	 */
	private java.math.BigDecimal serviceFeeRate;
	/**
	 * 担保费
	 */
	private java.math.BigDecimal assureFee;
	/**
	 * 担保费率
	 */
	private java.math.BigDecimal assureFeeRate;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 开户省市
	 */
	private String accountCity;
	/**
	 * 费用一
	 */
	private java.math.BigDecimal fee1;
	/**
	 * 费用二
	 */
	private java.math.BigDecimal fee2;
	/**
	 * 费用三
	 */
	private java.math.BigDecimal fee3;
	/**
	 * 费用四
	 */
	private java.math.BigDecimal fee4;
	/**
	 * 费用五
	 */
	private java.math.BigDecimal fee5;
	/**
	 * 划拨申请书文件名称
	 */
	private String transferFileName;
	/**
	 * 放款日期
	 */
	private String beginDate;
	/**
	 * 到期日期
	 */
	private String endDate;
	/**
	 * 利率类型
	 */
	private String interestRateType;
	/**
	 * 放款账户类型
	 */
	private String putoutAccountType;
	/**
	 * 放款银行代码
	 */
	private String putoutBankCode;
	/**
	 * 放款账户名称
	 */
	private String putoutAccountName;
	/**
	 * 放款账户号码
	 */
	private String putoutAccountNo;
	/**
	 * 放款账户开户支行
	 */
	private String putoutAccountBank;
	/**
	 * 放款账户开户所在省
	 */
	private String putoutAccountProvince;
	/**
	 * 放款账户开户所在市
	 */
	private String putoutAccountCity;
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

	public RiskLoanDetail() {
		super();
	}

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

	public String getApplyAddress() {
		return applyAddress;
	}

	public void setApplyAddress(String applyAddress) {
		this.applyAddress = applyAddress;
	}

	public String getSourceChannel() {
		return sourceChannel;
	}

	public void setSourceChannel(String sourceChannel) {
		this.sourceChannel = sourceChannel;
	}

	public String getLoanuse() {
		return loanuse;
	}

	public void setLoanuse(String loanuse) {
		this.loanuse = loanuse;
	}

	public java.math.BigDecimal getContractSum() {
		return contractSum;
	}

	public void setContractSum(java.math.BigDecimal contractSum) {
		this.contractSum = contractSum;
	}

	public java.math.BigDecimal getActualSum() {
		return actualSum;
	}

	public void setActualSum(java.math.BigDecimal actualSum) {
		this.actualSum = actualSum;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public String getRepaymentAccountType() {
		return repaymentAccountType;
	}

	public void setRepaymentAccountType(String repaymentAccountType) {
		this.repaymentAccountType = repaymentAccountType;
	}

	public String getRepaymentAccountNo() {
		return repaymentAccountNo;
	}

	public void setRepaymentAccountNo(String repaymentAccountNo) {
		this.repaymentAccountNo = repaymentAccountNo;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getWithholdType() {
		return withholdType;
	}

	public void setWithholdType(String withholdType) {
		this.withholdType = withholdType;
	}

	public String getWithholdDateType() {
		return withholdDateType;
	}

	public void setWithholdDateType(String withholdDateType) {
		this.withholdDateType = withholdDateType;
	}

	public String getWithholdDate() {
		return withholdDate;
	}

	public void setWithholdDate(String withholdDate) {
		this.withholdDate = withholdDate;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getBorrowerType() {
		return borrowerType;
	}

	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public java.math.BigDecimal getOperationFee() {
		return operationFee;
	}

	public void setOperationFee(java.math.BigDecimal operationFee) {
		this.operationFee = operationFee;
	}

	public java.math.BigDecimal getOperationFeeRate() {
		return operationFeeRate;
	}

	public void setOperationFeeRate(java.math.BigDecimal operationFeeRate) {
		this.operationFeeRate = operationFeeRate;
	}

	public java.math.BigDecimal getBusinessRate() {
		return businessRate;
	}

	public void setBusinessRate(java.math.BigDecimal businessRate) {
		this.businessRate = businessRate;
	}

	public java.math.BigDecimal getDeditRate() {
		return deditRate;
	}

	public void setDeditRate(java.math.BigDecimal deditRate) {
		this.deditRate = deditRate;
	}

	public java.math.BigDecimal getLateChargeRate() {
		return lateChargeRate;
	}

	public void setLateChargeRate(java.math.BigDecimal lateChargeRate) {
		this.lateChargeRate = lateChargeRate;
	}

	public Integer getAssureDays() {
		return assureDays;
	}

	public void setAssureDays(Integer assureDays) {
		this.assureDays = assureDays;
	}

	public java.math.BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(java.math.BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public java.math.BigDecimal getServiceFeeRate() {
		return serviceFeeRate;
	}

	public void setServiceFeeRate(java.math.BigDecimal serviceFeeRate) {
		this.serviceFeeRate = serviceFeeRate;
	}

	public java.math.BigDecimal getAssureFee() {
		return assureFee;
	}

	public void setAssureFee(java.math.BigDecimal assureFee) {
		this.assureFee = assureFee;
	}

	public java.math.BigDecimal getAssureFeeRate() {
		return assureFeeRate;
	}

	public void setAssureFeeRate(java.math.BigDecimal assureFeeRate) {
		this.assureFeeRate = assureFeeRate;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountCity() {
		return accountCity;
	}

	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}

	public java.math.BigDecimal getFee1() {
		return fee1;
	}

	public void setFee1(java.math.BigDecimal fee1) {
		this.fee1 = fee1;
	}

	public java.math.BigDecimal getFee2() {
		return fee2;
	}

	public void setFee2(java.math.BigDecimal fee2) {
		this.fee2 = fee2;
	}

	public java.math.BigDecimal getFee3() {
		return fee3;
	}

	public void setFee3(java.math.BigDecimal fee3) {
		this.fee3 = fee3;
	}

	public java.math.BigDecimal getFee4() {
		return fee4;
	}

	public void setFee4(java.math.BigDecimal fee4) {
		this.fee4 = fee4;
	}

	public java.math.BigDecimal getFee5() {
		return fee5;
	}

	public void setFee5(java.math.BigDecimal fee5) {
		this.fee5 = fee5;
	}

	public String getTransferFileName() {
		return transferFileName;
	}

	public void setTransferFileName(String transferFileName) {
		this.transferFileName = transferFileName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInterestRateType() {
		return interestRateType;
	}

	public void setInterestRateType(String interestRateType) {
		this.interestRateType = interestRateType;
	}

	public String getPutoutAccountType() {
		return putoutAccountType;
	}

	public void setPutoutAccountType(String putoutAccountType) {
		this.putoutAccountType = putoutAccountType;
	}

	public String getPutoutBankCode() {
		return putoutBankCode;
	}

	public void setPutoutBankCode(String putoutBankCode) {
		this.putoutBankCode = putoutBankCode;
	}

	public String getPutoutAccountName() {
		return putoutAccountName;
	}

	public void setPutoutAccountName(String putoutAccountName) {
		this.putoutAccountName = putoutAccountName;
	}

	public String getPutoutAccountNo() {
		return putoutAccountNo;
	}

	public void setPutoutAccountNo(String putoutAccountNo) {
		this.putoutAccountNo = putoutAccountNo;
	}

	public String getPutoutAccountBank() {
		return putoutAccountBank;
	}

	public void setPutoutAccountBank(String putoutAccountBank) {
		this.putoutAccountBank = putoutAccountBank;
	}

	public String getPutoutAccountProvince() {
		return putoutAccountProvince;
	}

	public void setPutoutAccountProvince(String putoutAccountProvince) {
		this.putoutAccountProvince = putoutAccountProvince;
	}

	public String getPutoutAccountCity() {
		return putoutAccountCity;
	}

	public void setPutoutAccountCity(String putoutAccountCity) {
		this.putoutAccountCity = putoutAccountCity;
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

}
