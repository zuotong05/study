package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;


/**
 * <P>
 * 签名证书
 * </P>
 *  
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0  
 */
public class SecuritySignCert extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1131304982486983193L;
	/**
     * 合作机构编号
     */
    private String orgCode;
    /**
     * 信托项目简码
     */
    private String trustProjectCode;
    /**
     * 合作方信托项目证书路径
     */
    private String trustProjectCertPath;
    /**
     * 合作方信托项目证书密码
     */
    private String trustProjectCertPwd;
    /**
     * 渤海信托证书路径
     */
    private String bhxtCertPath;
    /**
     * 渤海信托证书密码
     */
    private String bhxtCertPwd;

	public SecuritySignCert() {
		super();
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
    public String getTrustProjectCertPath() {
        return trustProjectCertPath;
    }

    public void setTrustProjectCertPath(String trustProjectCertPath) {
        this.trustProjectCertPath = trustProjectCertPath;
    }
    public String getTrustProjectCertPwd() {
        return trustProjectCertPwd;
    }

    public void setTrustProjectCertPwd(String trustProjectCertPwd) {
        this.trustProjectCertPwd = trustProjectCertPwd;
    }
    public String getBhxtCertPath() {
        return bhxtCertPath;
    }

    public void setBhxtCertPath(String bhxtCertPath) {
        this.bhxtCertPath = bhxtCertPath;
    }
    public String getBhxtCertPwd() {
        return bhxtCertPwd;
    }

    public void setBhxtCertPwd(String bhxtCertPwd) {
        this.bhxtCertPwd = bhxtCertPwd;
    }

}
