package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;

/**
 * <P>
 * 个人用户信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0
 */

public class RiskLoanUserDto implements Serializable {
	private static final long serialVersionUID = 4516670523933849498L;

	/**
	 * 姓名
	 */
	private String customerName;
	/**
	 * 证件类型
	 */
	private String certType;
	/**
	 * 证件号码
	 */
	private String certId;
	/**
	 * 联系电话
	 */
	private String handPhone;
	/**
	 * 移动电话
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private String postalZip;
	/**
	 * 通讯地址
	 */
	private String commAdd;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 婚姻状况
	 */
	private String marriage;
	/**
	 * 学历
	 */
	private String eduexperience;
	/**
	 * 户籍
	 */
	private String nativePlace;
	/**
	 * 户籍地址
	 */
	private String nativePlaceAdd;
	/**
	 * 个人月收入
	 */
	private java.math.BigDecimal selfMonthIncome;
	/**
	 * 家庭住址
	 */
	private String familyAdd;
	/**
	 * 家庭邮编
	 */
	private String familyZip;
	/**
	 * 住宅电话
	 */
	private String familyTel;
	/**
	 * 职业
	 */
	private String peroccupation;
	/**
	 * 单位名称
	 */
	private String workCorp;
	/**
	 * 单位所属行业
	 */
	private String unitKind;
	/**
	 * 单位地址
	 */
	private String workAdd;
	/**
	 * 单位邮政编码
	 */
	private String workZip;
	/**
	 * 本单位工作起始年份
	 */
	private String workBeginDate;
	/**
	 * 职务
	 */
	private String occupation;
	/**
	 * 职称
	 */
	private String position;
	/**
	 * 家庭净资产
	 */
	private java.math.BigDecimal userNetAssets;
	/**
	 * 住房价值
	 */
	private java.math.BigDecimal userHouseBalue;
	/**
	 * 住房面积
	 */
	private java.math.BigDecimal userHouseArea;
	/**
	 * 居住类型
	 */
	private String realEstateType;
	/**
	 * 备注1:芝麻信用
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getHandPhone() {
		return handPhone;
	}

	public void setHandPhone(String handPhone) {
		this.handPhone = handPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalZip() {
		return postalZip;
	}

	public void setPostalZip(String postalZip) {
		this.postalZip = postalZip;
	}

	public String getCommAdd() {
		return commAdd;
	}

	public void setCommAdd(String commAdd) {
		this.commAdd = commAdd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getEduexperience() {
		return eduexperience;
	}

	public void setEduexperience(String eduexperience) {
		this.eduexperience = eduexperience;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNativePlaceAdd() {
		return nativePlaceAdd;
	}

	public void setNativePlaceAdd(String nativePlaceAdd) {
		this.nativePlaceAdd = nativePlaceAdd;
	}

	public java.math.BigDecimal getSelfMonthIncome() {
		return selfMonthIncome;
	}

	public void setSelfMonthIncome(java.math.BigDecimal selfMonthIncome) {
		this.selfMonthIncome = selfMonthIncome;
	}

	public String getFamilyAdd() {
		return familyAdd;
	}

	public void setFamilyAdd(String familyAdd) {
		this.familyAdd = familyAdd;
	}

	public String getFamilyZip() {
		return familyZip;
	}

	public void setFamilyZip(String familyZip) {
		this.familyZip = familyZip;
	}

	public String getFamilyTel() {
		return familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	public String getPeroccupation() {
		return peroccupation;
	}

	public void setPeroccupation(String peroccupation) {
		this.peroccupation = peroccupation;
	}

	public String getWorkCorp() {
		return workCorp;
	}

	public void setWorkCorp(String workCorp) {
		this.workCorp = workCorp;
	}

	public String getUnitKind() {
		return unitKind;
	}

	public void setUnitKind(String unitKind) {
		this.unitKind = unitKind;
	}

	public String getWorkAdd() {
		return workAdd;
	}

	public void setWorkAdd(String workAdd) {
		this.workAdd = workAdd;
	}

	public String getWorkZip() {
		return workZip;
	}

	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	public String getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(String workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public java.math.BigDecimal getUserNetAssets() {
		return userNetAssets;
	}

	public void setUserNetAssets(java.math.BigDecimal userNetAssets) {
		this.userNetAssets = userNetAssets;
	}

	public java.math.BigDecimal getUserHouseBalue() {
		return userHouseBalue;
	}

	public void setUserHouseBalue(java.math.BigDecimal userHouseBalue) {
		this.userHouseBalue = userHouseBalue;
	}

	public java.math.BigDecimal getUserHouseArea() {
		return userHouseArea;
	}

	public void setUserHouseArea(java.math.BigDecimal userHouseArea) {
		this.userHouseArea = userHouseArea;
	}

	public String getRealEstateType() {
		return realEstateType;
	}

	public void setRealEstateType(String realEstateType) {
		this.realEstateType = realEstateType;
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
