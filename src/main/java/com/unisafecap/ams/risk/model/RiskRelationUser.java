package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;


/**
 * <P>
 * 关系人信息
 * </P>
 *  
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0  
 */
public class RiskRelationUser extends BaseModel implements Serializable {
    /**
     * 贷款单ID
     */
    private Long loanDetailId;
    /**
     * 信托项目简码
     */
    private String trustProjectCode;
    /**
     * 合同号
     */
    private String contractNo;
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
	
    public Long getLoanDetailId() {
        return loanDetailId;
    }

    public void setLoanDetailId(Long loanDetailId) {
        this.loanDetailId = loanDetailId;
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
