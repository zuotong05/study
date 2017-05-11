package com.unisafecap.ams.risk.model.dto.request;

import java.io.Serializable;

/**
 * <P>
 * 风控审核查询
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */

public class RiskControlAuditQueryDto implements Serializable {
	private static final long serialVersionUID = -9180766985889359114L;
	/**
	 * 外部流水号
	 */
	private String outTradeNo;

	/**
	 *
	 */
	private String trustProjectCode;
	
	/**
	 * 合同号
	 */
	private String contractNo;

	/**
	 * 证件类型
	 */
	private String certType;

	/**
	 * 证件号码
	 */
	private String certId;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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


	
}
