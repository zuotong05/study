package com.unisafecap.ams.risk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private static final Logger logger = LoggerFactory.getLogger(SecuritySignCertService.class);
	@Autowired
	private SecuritySignCertDao securitySignCertDaoImpl;

	@Override
	public BaseDao<SecuritySignCert> getBaseDao() {
		return securitySignCertDaoImpl;
	}

	/**
	 * 加密
	 * 
	 * @param orgCode
	 *            合作方
	 * @param srcData
	 *            加密数据
	 * @return String
	 */
	@Transactional(readOnly = true)
	public String encryptData(String trustProjectCode, String srcData) {
		try {
			SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("trustProjectCode", trustProjectCode);
			if (null == signcert) {
				return null;
			}

			String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
			String pubCert = Base64.encode(FileUtil.readFile(signcert.getTrustProjectCertPath()));
			SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
			return signEnvelopService.signEnvelop(priCert, signcert.getTrustProjectCertPwd(), pubCert, srcData.getBytes());
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}

	}

	/**
	 * 加密
	 * 
	 * @param orgCode
	 *            合作方
	 * @param envelopData
	 *            解密数据
	 * @return byte[]
	 */
	@Transactional(readOnly = true)
	public byte[] decryptData(String trustProjectCode, String envelopData) {
		try {
			SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("trustProjectCode", trustProjectCode);
			if (null == signcert) {
				return null;
			}
			SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
			String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
			return signEnvelopService.verifyEnvelop(priCert, signcert.getBhxtCertPwd(), envelopData);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}

}
