package com.unisafecap.loan.model;

import java.io.Serializable;
import com.unisafecap.framework.dao.model.BaseModel;


/**
 * <P>
 * 个人用户信息
 * </P>
 *  
 * @author zuotong
 * @since 2017年04月28日
 * @version V1.0  
 */
public class LoanUser extends BaseModel implements Serializable {
    /**
     * 姓名
     */
    private String name;
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
    private String telephone;
    /**
     * 移动电话
     */
    private String mobile;
    /**
     * 邮政编码
     */
    private String postcode;
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
    private String maritalStatus;
    /**
     * 学历
     */
    private String education;
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
    private java.math.BigDecimal monthlyIncome;
    /**
     * 家庭住址
     */
    private String homeAdd;
    /**
     * 家庭邮编
     */
    private String homePostcode;
    /**
     * 住宅电话
     */
    private String homeTelephone;
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
    private java.math.BigDecimal realEstateType;

	public LoanUser() {
		super();
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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
    public java.math.BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(java.math.BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }
    public String getHomePostcode() {
        return homePostcode;
    }

    public void setHomePostcode(String homePostcode) {
        this.homePostcode = homePostcode;
    }
    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
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
    public java.math.BigDecimal getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(java.math.BigDecimal realEstateType) {
        this.realEstateType = realEstateType;
    }

}
