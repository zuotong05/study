package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.ams.risk.dao.SecuritySignCertDao;
import com.unisafecap.ams.risk.model.SecuritySignCert;
import com.unisafecap.ams.security.sign.SignEnvelopService;
import com.unisafecap.ams.security.sign.SignEnvelopServiceImpl;
import com.unisafecap.ams.security.sign.util.Base64;
import com.unisafecap.ams.security.sign.util.FileUtil;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.framework.service.BaseService;

/**
 * <P>
 * 签名证书Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月09日
 * @version V1.0
 */
@Service("securitySignCertService")
public class SecuritySignCertService extends BaseService<SecuritySignCert> {

	@Autowired
	private SecuritySignCertDao securitySignCertDaoImpl;

	@Override
	public BaseDao<SecuritySignCert> getBaseDao() {
		return securitySignCertDaoImpl;
	}

	/**
	 * 加密	 
	 * @param orgCode 合作方
	 * @param srcData 加密数据
	 * @return String
	 */
	public String encryptData( String orgCode ,String srcData) {
		SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("orgCode", orgCode);
		if (null == signcert) {
			return null;
		}
		
		String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
		String pubCert = Base64.encode(FileUtil.readFile(signcert.getOrgCertPath()));
			
		String password = signcert.getOrgCertPwd();
		SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
		return signEnvelopService.signEnvelop(priCert, password, pubCert, srcData.getBytes());

	}
	

	/** 
	 * 加密	 
	 * @param orgCode 合作方
	 * @param envelopData 解密数据
	 * @return byte[]
	 */
	public byte[] decryptData(String orgCode, String envelopData) {
		SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("orgCode", orgCode);
		if (null == signcert) {
			return null;
		}
		SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
		String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
		return signEnvelopService.verifyEnvelop(priCert, signcert.getBhxtCertPwd(), envelopData);
	}
	
	
}
