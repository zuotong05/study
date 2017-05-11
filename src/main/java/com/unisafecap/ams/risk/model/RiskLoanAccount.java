package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;

/**
 * <P>
 * 放款账户信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0
 */
public class RiskLoanAccount extends BaseModel implements Serializable {
	private static final long serialVersionUID = 4205148095258461921L;
	/**
	 * 风控ID
	 */
	private Long riskControlId;
	/**
	 * 风控类型
	 */
	private Integer riskControlType;
	/**
	 * 合作机构编号
	 */
	private String orgCode;
	/**
	 * 信托项目简码
	 */
	private String trustProjectCode;
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

	public RiskLoanAccount() {
		super();
	}

	public Long getRiskControlId() {
		return riskControlId;
	}

	public void setRiskControlId(Long riskControlId) {
		this.riskControlId = riskControlId;
	}

	public Integer getRiskControlType() {
		return riskControlType;
	}

	public void setRiskControlType(Integer riskControlType) {
		this.riskControlType = riskControlType;
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

}
