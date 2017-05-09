package com.unisafecap.ams.risk.model;

import java.io.Serializable;
import com.unisafecap.framework.model.BaseModel;


/**
 * <P>
 * 签名证书
 * </P>
 *  
 * @author zuotong
 * @since 2017年05月09日
 * @version V1.0  
 */
public class SecuritySignCert extends BaseModel implements Serializable {
    /**
     * 合作方代码
     */
    private String orgCode;
    /**
     * 合作方证书路径
     */
    private String orgCertPath;
    /**
     * 合作方证书密码
     */
    private String orgCertPwd;
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
    public String getOrgCertPath() {
        return orgCertPath;
    }

    public void setOrgCertPath(String orgCertPath) {
        this.orgCertPath = orgCertPath;
    }
    public String getOrgCertPwd() {
        return orgCertPwd;
    }

    public void setOrgCertPwd(String orgCertPwd) {
        this.orgCertPwd = orgCertPwd;
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
