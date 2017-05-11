package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;


/**
 * <P>
 * 关系人信息
 * </P>
 *  
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0  
 */
public class RiskRelationUser extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1864804844142858653L;
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
     * 关系人类型
     */
    private String relationUserType;
    /**
     * 同借款人关系
     */
    private String relationType;
    /**
     * 担保人姓名
     */
    private String name;
    /**
     * 担保人证件类型
     */
    private String certType;
    /**
     * 担保人证件号码
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
     * 担保方式
     */
    private String guaranteeMode;
    /**
     * 担保金额
     */
    private java.math.BigDecimal guaranteeSum;

	public RiskRelationUser() {
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
    public String getRelationUserType() {
        return relationUserType;
    }

    public void setRelationUserType(String relationUserType) {
        this.relationUserType = relationUserType;
    }
    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
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
    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }
    public java.math.BigDecimal getGuaranteeSum() {
        return guaranteeSum;
    }

    public void setGuaranteeSum(java.math.BigDecimal guaranteeSum) {
        this.guaranteeSum = guaranteeSum;
    }

}
