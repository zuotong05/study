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
	public String encryptData(String orgCode, String srcData) {
		try {
			logger.debug("合作方[" + orgCode + "]加密前的数据 ：" + srcData);
			SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("orgCode", orgCode);
			if (null == signcert) {
				logger.info("合作方[" + orgCode + "]加密证书不存在!");
				return null;
			}
			logger.debug("渤海信托证书路径：" + signcert.getBhxtCertPath());
			logger.debug("合作方证书路径：" + signcert.getOrgCodeCertPath());
			String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
			String pubCert = Base64.encode(FileUtil.readFile(signcert.getOrgCodeCertPath()));
			SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
			String signedEvpData = signEnvelopService.signEnvelop(priCert, signcert.getBhxtCertPwd(), pubCert, srcData.getBytes());

			logger.debug("合作方[" + orgCode + "]加密后的数据：" + signedEvpData);
			return signedEvpData;
		}
		catch (Exception e) {
			logger.error("合作方[" + orgCode + "]证书加密" + e.getMessage());
			return null;
		}

	}

	/**
	 * 解密
	 * 
	 * @param orgCode
	 *            合作方
	 * @param envelopData
	 *            解密数据
	 * @return byte[]
	 */
	@Transactional(readOnly = true)
	public byte[] decryptData(String orgCode, String envelopData) {
		try {
			logger.debug("合作方[" + orgCode + "]加密前的数据 ：" + envelopData);
			SecuritySignCert signcert = securitySignCertDaoImpl.findUniqueBy("orgCode", orgCode);
			if (null == signcert) {
				logger.info("合作方[" + orgCode + "]加密证书不存在!");
				return null;
			}
			logger.debug("渤海信托证书路径：" + signcert.getBhxtCertPath());
			SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();
			String priCert = Base64.encode(FileUtil.readFile(signcert.getBhxtCertPath()));
			byte[] cleartxt = signEnvelopService.verifyEnvelop(priCert, signcert.getBhxtCertPwd(), envelopData);
			logger.debug("合作方[" + orgCode + "]解密后的数据：" + (null == cleartxt ? null : new String(cleartxt)));
			return cleartxt;
		}
		catch (Exception e) {
			logger.error("合作方[" + orgCode + "]证书解密" + e.getMessage());
			return null;
		}
	}

}
