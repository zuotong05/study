package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;

/** 
 * <P>
 * 放款账户信息
 * </P> 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0 
 */

public class RiskLoanAccountDto implements Serializable{
	private static final long serialVersionUID = -7777566809754999643L;
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
