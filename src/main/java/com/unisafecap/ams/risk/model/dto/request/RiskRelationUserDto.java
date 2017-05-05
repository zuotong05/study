package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;

/**
 * <P>
 * 关系人信息
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0
 */
public class RiskRelationUserDto implements Serializable {
	private static final long serialVersionUID = -4407811660506457666L;
	
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
