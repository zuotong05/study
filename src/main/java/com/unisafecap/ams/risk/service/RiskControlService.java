package com.unisafecap.ams.risk.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.unisafecap.ams.risk.model.RiskControlInfo;
import com.unisafecap.ams.risk.model.RiskLoanAccount;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.ams.risk.model.RiskLoanUser;
import com.unisafecap.ams.risk.model.RiskRelationUser;
import com.unisafecap.ams.risk.model.dto.request.RiskControlAuditDto;
import com.unisafecap.ams.risk.model.dto.request.RiskControlAuditQueryDto;
import com.unisafecap.ams.risk.model.dto.response.RiskControlAuditResult;
import com.unisafecap.ams.risk.model.dto.response.RiskLoanDetailResult;
import com.unisafecap.ams.risk.model.dto.response.RiskLoanUserResult;
import com.unisafecap.ams.risk.model.enums.AuditStatus;
import com.unisafecap.ams.risk.model.enums.RiskControlType;
import com.unisafecap.framework.common.enums.ServiceErrorCode;
import com.unisafecap.framework.common.utils.IdWorker18;
import com.unisafecap.framework.common.utils.StringUtils;
import com.unisafecap.framework.model.dto.RequestData;
import com.unisafecap.framework.model.dto.ResponseData;

/**
 * <P>
 * 风控审核
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */

@Service("riskControlService")
public class RiskControlService {

	private static final Logger logger = LoggerFactory.getLogger(SecuritySignCertService.class);

	@Autowired
	private RiskControlInfoService riskControlInfoService;

	@Autowired
	private RiskLoanDetailService riskLoanDetailService;

	@Autowired
	private RiskLoanUserService riskLoanUserService;

	@Autowired
	private RiskRelationUserService riskRelationUserService;

	@Autowired
	private RiskLoanAccountService riskLoanAccountService;

	@Autowired
	private SecuritySignCertService securitySignCertService;

	/**
	 * 客户审批
	 * 
	 * @param requestData
	 *            请求数据
	 * @return ResponseData 响应数据
	 * @throws Exception
	 */
	public ResponseData<?> customerAudit(RequestData requestData) throws Exception {

		ResponseData<RiskControlAuditDto> response = riskControlAudit(requestData);

		if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
			response.setDatas(null);
			return response;
		}
		RiskControlAuditDto auditDto = response.getDatas();
		response.setDatas(null);
		RiskLoanUser loanUser = auditDto.getLoanUser();
		RiskLoanAccount loanAccount = auditDto.getLoanAccount();
		if (null== loanUser|| null == loanAccount 
				|| StringUtils.isBlank(auditDto.getTrustProjectCode()) 
				|| StringUtils.isBlank(loanUser.getCustomerName()) 
				|| StringUtils.isBlank(loanUser.getCertType()) 
				|| StringUtils.isBlank(loanUser.getCertId()) 
				|| StringUtils.isBlank(loanUser.getPhone()) 
				|| StringUtils.isBlank(loanAccount.getPutoutAccountNo())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("outTradeNo", auditDto.getOutTradeNo());
		List<RiskControlInfo> list = riskControlInfoService.findByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.OUT_TRADE_NO_REPEAT);
			return response;
		}

		RiskControlInfo riskControlInfo = new RiskControlInfo();
		riskControlInfo.setRemark1(loanUser.getCertType());
		riskControlInfo.setRemark2(loanUser.getCertId());
		map = new HashMap<String, Object>();
		map.put("trustProjectCode", auditDto.getTrustProjectCode());
		map.put("remark1", riskControlInfo.getRemark1());
		map.put("remark2", riskControlInfo.getRemark2());

		list = riskControlInfoService.findByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.CERT_ID_NO_REPEAT);
			return response;
		}

		riskControlInfo.setOrgCode(auditDto.getOrgCode());
		riskControlInfo.setTrustProjectCode(auditDto.getTrustProjectCode());
		riskControlInfo.setTimestamp(auditDto.getTimestamp());
		riskControlInfo.setOutTradeNo(auditDto.getOutTradeNo());
		riskControlInfo.setCallbackUrl(auditDto.getCallbackUrl());
		riskControlInfo.setRiskControlType(RiskControlType.CUSTOMER_AUDIT.ordinal());

		IdWorker18 idWorker = new IdWorker18(0, 0);
		String tradeNo = auditDto.getTrustProjectCode() + idWorker.nextId();
		riskControlInfo.setTradeNo(tradeNo);
		AuditStatus auditStatus = AuditStatus.PASS;// 默认 通过
		riskControlInfo.setAuditStatus(String.valueOf(auditStatus.getValue()));
		int result = riskControlInfoService.create4Selective(riskControlInfo);
		if (result < 1)
			throw new RuntimeException("保存风控信息失败");

		loanUser.setRiskControlId(riskControlInfo.getId());
		loanUser.setRiskControlType(riskControlInfo.getRiskControlType());
		loanUser.setOrgCode(riskControlInfo.getOrgCode());
		loanUser.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
		result = riskLoanUserService.create4Selective(loanUser);
		if (result < 1)
			throw new RuntimeException("个人用户信息失败");

		loanAccount.setRiskControlId(riskControlInfo.getId());
		loanAccount.setRiskControlType(riskControlInfo.getRiskControlType());
		loanAccount.setOrgCode(riskControlInfo.getOrgCode());
		loanAccount.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
		loanAccount.setPutoutAccountName(StringUtils.isNotBlank(loanAccount.getPutoutAccountName()) ? loanAccount.getPutoutAccountName() : loanUser.getCustomerName());
		result = riskLoanAccountService.create4Selective(loanAccount);
		if (result < 1)
			throw new RuntimeException("放款账户信息失败");

		RiskControlAuditResult<RiskLoanUserResult> auditResult = new RiskControlAuditResult<RiskLoanUserResult>();
		auditResult.setTradeNo(tradeNo);

		if (auditStatus.getValue().equals(AuditStatus.PASS.getValue())) {// 判断审核是否通过，以后预留用
			response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
			RiskLoanUserResult loanUserResult = new RiskLoanUserResult();
			BeanUtils.copyProperties(riskControlInfo, loanUserResult);
			loanUserResult.setCertType(riskControlInfo.getRemark1());
			loanUserResult.setCertId(riskControlInfo.getRemark2());
			auditResult.setAuditDetail(loanUserResult);
		} else {
			response.setServiceErrorCode(ServiceErrorCode.HANDLING);
		}

		String bizContent = securitySignCertService.encryptData(auditDto.getOrgCode(), JSON.toJSONString(auditResult));
		if (null == bizContent) {
			response.setServiceErrorCode(ServiceErrorCode.SIGNATURE_EXPIRED);			
			return response;
		}
		response.setBizContent(bizContent);

		return response;
	}

	/**
	 * 放款审批
	 * 
	 * @param requestData
	 *            请求数据
	 * @return ResponseData 响应数据
	 * @throws Exception
	 */
	public ResponseData<?> loadAudit(RequestData requestData) throws Exception {
		ResponseData<RiskControlAuditDto> response = riskControlAudit(requestData);

		if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
			response.setDatas(null);
			return response;
		}
		
		RiskControlAuditDto auditDto = response.getDatas();
		response.setDatas(null);
		
		RiskLoanDetail loanDetail = auditDto.getLoanApply();
		RiskLoanUser loanUser = auditDto.getLoanUser();
		RiskLoanAccount loanAccount = auditDto.getLoanAccount();

		if (null==loanDetail || null== loanUser|| null == loanAccount 
				|| StringUtils.isBlank(auditDto.getTrustProjectCode())
				|| StringUtils.isBlank(loanDetail.getContractNo()) 
				|| StringUtils.isBlank(loanUser.getCustomerName()) 
				|| StringUtils.isBlank(loanUser.getCertType()) 
				|| StringUtils.isBlank(loanUser.getCertId()) 
				|| StringUtils.isBlank(loanUser.getPhone()) 
				|| StringUtils.isBlank(loanAccount.getPutoutAccountNo())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("outTradeNo", auditDto.getOutTradeNo());
		List<RiskControlInfo> list = riskControlInfoService.findByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.OUT_TRADE_NO_REPEAT);
			return response;
		}

		RiskControlInfo riskControlInfo = new RiskControlInfo();
		riskControlInfo.setRemark1("-1");
		riskControlInfo.setRemark2(loanDetail.getContractNo());

		map = new HashMap<String, Object>();
		map.put("trustProjectCode", auditDto.getTrustProjectCode());
		map.put("remark1", riskControlInfo.getRemark1());
		map.put("remark2", riskControlInfo.getRemark2());

		list = riskControlInfoService.findByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.CONTRACT_NO_REPEAT);
			return response;
		}

		riskControlInfo.setOrgCode(auditDto.getOrgCode());
		riskControlInfo.setTrustProjectCode(auditDto.getTrustProjectCode());
		riskControlInfo.setTimestamp(auditDto.getTimestamp());
		riskControlInfo.setOutTradeNo(auditDto.getOutTradeNo());
		riskControlInfo.setCallbackUrl(auditDto.getCallbackUrl());
		riskControlInfo.setRiskControlType(RiskControlType.LOAD_AUDIT.ordinal());

		IdWorker18 idWorker = new IdWorker18(0, 0);
		String tradeNo = auditDto.getTrustProjectCode() + idWorker.nextId();
		riskControlInfo.setTradeNo(tradeNo);
		AuditStatus auditStatus = AuditStatus.PASS;// 默认 通过
		riskControlInfo.setAuditStatus(String.valueOf(auditStatus.getValue()));

		int result = riskControlInfoService.create4Selective(riskControlInfo);
		if (result < 1)
			throw new RuntimeException("保存风控信息失败");

		loanDetail.setRiskControlId(riskControlInfo.getId());
		loanDetail.setRiskControlType(riskControlInfo.getRiskControlType());
		loanDetail.setOrgCode(riskControlInfo.getOrgCode());
		loanDetail.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
		result = riskLoanDetailService.create4Selective(loanDetail);
		if (result < 1)
			throw new RuntimeException("保存贷款单信息失败");

		loanUser.setRiskControlId(riskControlInfo.getId());
		loanUser.setRiskControlType(riskControlInfo.getRiskControlType());
		loanUser.setOrgCode(riskControlInfo.getOrgCode());
		loanUser.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
		result = riskLoanUserService.create4Selective(loanUser);
		if (result < 1)
			throw new RuntimeException("个人用户信息失败");

		List<RiskRelationUser> relationUsers = auditDto.getRelationUsers();
		if(relationUsers!=null&&relationUsers.size()>0){
			for (Iterator<RiskRelationUser> iterator = relationUsers.iterator(); iterator.hasNext();) {
				RiskRelationUser relationUser = iterator.next();
				relationUser.setRiskControlId(riskControlInfo.getId());
				relationUser.setRiskControlType(riskControlInfo.getRiskControlType());
				relationUser.setOrgCode(riskControlInfo.getOrgCode());
				relationUser.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
				result = riskRelationUserService.create4Selective(relationUser);
				if (result < 1)
					throw new RuntimeException("关系人信息失败");
			}
		}
		

		loanAccount.setRiskControlId(riskControlInfo.getId());
		loanAccount.setRiskControlType(riskControlInfo.getRiskControlType());
		loanAccount.setOrgCode(riskControlInfo.getOrgCode());
		loanAccount.setTrustProjectCode(riskControlInfo.getTrustProjectCode());
		loanAccount.setPutoutAccountName(StringUtils.isNotBlank(loanAccount.getPutoutAccountName()) ? loanAccount.getPutoutAccountName() : loanUser.getCustomerName());
		result = riskLoanAccountService.create4Selective(loanAccount);
		if (result < 1)
			throw new RuntimeException("放款账户信息失败");

		RiskControlAuditResult<RiskLoanDetailResult> auditResult = new RiskControlAuditResult<RiskLoanDetailResult>();
		auditResult.setTradeNo(tradeNo);

		if (auditStatus.getValue().equals(AuditStatus.PASS.getValue())) {// 判断审核是否通过，以后预留用
			response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
			RiskLoanDetailResult loanDetailResult = new RiskLoanDetailResult();
			BeanUtils.copyProperties(riskControlInfo, loanDetailResult);
			loanDetailResult.setContractNo(riskControlInfo.getRemark2());
			auditResult.setAuditDetail(loanDetailResult);
		} else {
			response.setServiceErrorCode(ServiceErrorCode.HANDLING);
		}

		String bizContent = securitySignCertService.encryptData(auditDto.getOrgCode(), JSON.toJSONString(auditResult));
		if (null == bizContent) {
			response.setServiceErrorCode(ServiceErrorCode.SIGNATURE_EXPIRED);		
			return response;
		}
		response.setBizContent(bizContent);

		return response;

	}

	private ResponseData<RiskControlAuditDto> riskControlAudit(RequestData requestData) throws Exception {
		ResponseData<RiskControlAuditDto> response = new ResponseData<RiskControlAuditDto>();
		if (StringUtils.isBlank(requestData.getOrgCode()) 
				|| StringUtils.isBlank(requestData.getTimestamp()) 
				|| StringUtils.isBlank(requestData.getBizContent())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}

		byte[] cleartxt = securitySignCertService.decryptData(requestData.getOrgCode(), requestData.getBizContent());
		if (null == cleartxt) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			response.setMsg("签名信封数据有误");
			return response;
		}

		RiskControlAuditDto auditDto = JSON.parseObject(new String(cleartxt), RiskControlAuditDto.class);
		
		if (StringUtils.isBlank(auditDto.getOutTradeNo())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}				
		auditDto.setOrgCode(requestData.getOrgCode());
		auditDto.setTimestamp(requestData.getTimestamp());
		response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
		response.setDatas(auditDto);
		return response;

	}

	/**
	 * 客户审批結果查询
	 * 
	 * @param requestData
	 *            请求数据
	 * @return ResponseData 响应数据
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public ResponseData<?> customerAuditQuery(RequestData requestData) {
		ResponseData<RiskControlAuditQueryDto> response = new ResponseData<RiskControlAuditQueryDto>();
		try {

			response = queryRiskControlAudit(requestData);

			if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
				response.setDatas(null);
				return response;
			}
			RiskControlAuditQueryDto auditQueryDto = response.getDatas();
			response.setDatas(null);

			if (StringUtils.isBlank(auditQueryDto.getOutTradeNo()) 
					||StringUtils.isBlank(auditQueryDto.getTrustProjectCode()) 
					||StringUtils.isBlank(auditQueryDto.getCertType()) 
					|| StringUtils.isBlank(auditQueryDto.getCertId())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("outTradeNo", auditQueryDto.getOutTradeNo());
			map.put("trustProjectCode", auditQueryDto.getTrustProjectCode());
			map.put("remark1", auditQueryDto.getCertType());
			map.put("remark2", auditQueryDto.getCertId());
			RiskControlInfo riskControlInfo = riskControlInfoService.findUniqueByMap(map);
			if (null == riskControlInfo || null == riskControlInfo.getId()) {
				response.setServiceErrorCode(ServiceErrorCode.LOAN_NON_EXISTENT);
				return response;
			} else {
				response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
				RiskControlAuditResult<RiskLoanUserResult> auditResult = new RiskControlAuditResult<RiskLoanUserResult>();
				RiskLoanUserResult loanUserResult = new RiskLoanUserResult();
				BeanUtils.copyProperties(riskControlInfo, loanUserResult);
				loanUserResult.setCertType(riskControlInfo.getRemark1());
				loanUserResult.setCertId(riskControlInfo.getRemark2());
				auditResult.setAuditDetail(loanUserResult);

				String bizContent = securitySignCertService.encryptData(requestData.getOrgCode(), JSON.toJSONString(auditResult));
				if (null == bizContent) {
					response.setServiceErrorCode(ServiceErrorCode.SIGNATURE_EXPIRED);				
					return response;
				}
				response.setBizContent(bizContent);
				return response;
			}
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			response.setServiceErrorCode(ServiceErrorCode.UNKNOWN_EXCEPTION);
			return response;
		}
	}

	/**
	 * 放款审批結果查询
	 * 
	 * @param requestData
	 *            请求数据
	 * @return ResponseData 响应数据
	 */

	@Transactional(readOnly = true)
	public ResponseData<?> loadAuditQuery(RequestData requestData) {
		ResponseData<RiskControlAuditQueryDto> response = new ResponseData<RiskControlAuditQueryDto>();
		try {

			response = queryRiskControlAudit(requestData);
			if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
				response.setDatas(null);
				return response;
			}
			RiskControlAuditQueryDto auditQueryDto = response.getDatas();
			response.setDatas(null);

			if (StringUtils.isBlank(auditQueryDto.getOutTradeNo()) 
					||StringUtils.isBlank(auditQueryDto.getTrustProjectCode()) 
					||StringUtils.isBlank(auditQueryDto.getContractNo())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("outTradeNo", auditQueryDto.getOutTradeNo());
			map.put("trustProjectCode", auditQueryDto.getTrustProjectCode());
			map.put("remark1", "-1");
			map.put("remark2", auditQueryDto.getContractNo());
			RiskControlInfo riskControlInfo = riskControlInfoService.findUniqueByMap(map);
			if (null == riskControlInfo || null == riskControlInfo.getId()) {
				response.setServiceErrorCode(ServiceErrorCode.LOAN_NON_EXISTENT);
				return response;
			} else {
				response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
				RiskControlAuditResult<RiskLoanDetailResult> auditResult = new RiskControlAuditResult<RiskLoanDetailResult>();
				RiskLoanDetailResult loanDetailResult = new RiskLoanDetailResult();
				BeanUtils.copyProperties(riskControlInfo, loanDetailResult);
				loanDetailResult.setContractNo(riskControlInfo.getRemark2());
				auditResult.setAuditDetail(loanDetailResult);
				String bizContent = securitySignCertService.encryptData(requestData.getOrgCode(), JSON.toJSONString(auditResult));
				if (null == bizContent) {
					response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
					response.setMsg("签名证书过期");
					return response;
				}
				response.setBizContent(bizContent);
				return response;
			}
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			response.setServiceErrorCode(ServiceErrorCode.UNKNOWN_EXCEPTION);
			return response;
		}
	}

	private ResponseData<RiskControlAuditQueryDto> queryRiskControlAudit(RequestData requestData) {
		ResponseData<RiskControlAuditQueryDto> response = new ResponseData<RiskControlAuditQueryDto>();
		try {
			if (StringUtils.isBlank(requestData.getOrgCode()) || StringUtils.isBlank(requestData.getTimestamp()) || StringUtils.isBlank(requestData.getBizContent())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}

			byte[] cleartxt = securitySignCertService.decryptData(requestData.getOrgCode(), requestData.getBizContent());
			if (null == cleartxt) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				response.setMsg("签名证书过期");
				return response;
			}

			RiskControlAuditQueryDto auditQueryDto = JSON.parseObject(new String(cleartxt), RiskControlAuditQueryDto.class);			
			if (StringUtils.isBlank(auditQueryDto.getOutTradeNo())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}
			response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
			response.setDatas(auditQueryDto);

			return response;

		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			response.setServiceErrorCode(ServiceErrorCode.UNKNOWN_EXCEPTION);
			return response;
		}

	}

}
