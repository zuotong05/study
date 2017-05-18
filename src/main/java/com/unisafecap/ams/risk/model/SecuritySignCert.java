package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;


/**
 * <P>
 * 签名证书
 * </P>
 *  
 * @author zuotong
 * @since 2017年05月18日
 * @version V1.0  
 */
public class SecuritySignCert extends BaseModel implements Serializable {
    /**
     * 合作机构编号
     */
    private String orgCode;
    /**
     * 合作机构名称
     */
    private String orgName;
    /**
     * 信托项目简码
     */
    private String trustProjectCode;
    /**
     * 合作机构证书路径
     */
    private String orgCodeCertPath;
    /**
     * 合作机构证书密码
     */
    private String orgCodeCertPwd;
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
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getTrustProjectCode() {
        return trustProjectCode;
    }

    public void setTrustProjectCode(String trustProjectCode) {
        this.trustProjectCode = trustProjectCode;
    }
    public String getOrgCodeCertPath() {
        return orgCodeCertPath;
    }

    public void setOrgCodeCertPath(String orgCodeCertPath) {
        this.orgCodeCertPath = orgCodeCertPath;
    }
    public String getOrgCodeCertPwd() {
        return orgCodeCertPwd;
    }

    public void setOrgCodeCertPwd(String orgCodeCertPwd) {
        this.orgCodeCertPwd = orgCodeCertPwd;
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
