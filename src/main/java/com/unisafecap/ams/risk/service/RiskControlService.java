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
		ResponseData<Object> response = new ResponseData<Object>();
		RiskControlAuditDto auditDto = new RiskControlAuditDto();
		response = riskControlAudit(requestData, auditDto);

		if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
			return response;
		}

		RiskLoanUser loanUser = auditDto.getLoanUser();
		RiskLoanAccount loanAccount = auditDto.getLoanAccount();
		if (StringUtils.isBlank(loanUser.getCustomerName()) || StringUtils.isBlank(loanUser.getCertType()) || StringUtils.isBlank(loanUser.getCertId()) || StringUtils.isBlank(loanUser.getPhone()) || StringUtils.isBlank(loanAccount.getPutoutAccountNo())) {
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
			response.setServiceErrorCode(ServiceErrorCode.CONTRACT_NO_REPEAT);
			return response;
		}

		BeanUtils.copyProperties(auditDto, riskControlInfo);
		riskControlInfo.setRiskControlType(RiskControlType.CUSTOMER_AUDIT.ordinal());
		IdWorker18 idWorker = new IdWorker18(0, 0);
		String tradeNo = auditDto.getTrustProjectCode() + idWorker.nextId();
		riskControlInfo.setTradeNo(tradeNo);
		AuditStatus auditStatus = AuditStatus.PASS;// 默认 通过
		riskControlInfo.setAuditStatus(String.valueOf(auditStatus.getValue()));
		int result = riskControlInfoService.create4Selective(riskControlInfo);
		if (result < 1)
			throw new RuntimeException("保存风控信息失败");

		BeanUtils.copyProperties(riskControlInfo, loanUser);
		loanUser.setRiskControlId(riskControlInfo.getId());
		result = riskLoanUserService.create4Selective(loanUser);
		if (result < 1)
			throw new RuntimeException("个人用户信息失败");

		BeanUtils.copyProperties(riskControlInfo, loanAccount);
		loanAccount.setRiskControlId(riskControlInfo.getId());
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

		String bizContent = securitySignCertService.encryptData(auditDto.getTrustProjectCode(), JSON.toJSONString(auditResult));
		if (null == bizContent) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			response.setMsg("签名证书过期");
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
		ResponseData<Object> response = new ResponseData<Object>();
		RiskControlAuditDto auditDto = new RiskControlAuditDto();
		response = riskControlAudit(requestData, auditDto);

		if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
			return response;
		}

		RiskLoanDetail loanDetail = auditDto.getLoanApply();
		RiskLoanUser loanUser = auditDto.getLoanUser();
		RiskLoanAccount loanAccount = auditDto.getLoanAccount();

		if (StringUtils.isBlank(loanDetail.getContractNo()) || StringUtils.isBlank(loanUser.getCustomerName()) || StringUtils.isBlank(loanUser.getCertType()) || StringUtils.isBlank(loanUser.getCertId()) || StringUtils.isBlank(loanUser.getPhone()) || StringUtils.isBlank(loanAccount.getPutoutAccountNo())) {
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

		BeanUtils.copyProperties(auditDto, riskControlInfo);
		riskControlInfo.setRiskControlType(RiskControlType.LOAD_AUDIT.ordinal());
		IdWorker18 idWorker = new IdWorker18(0, 0);
		String tradeNo = auditDto.getTrustProjectCode() + idWorker.nextId();
		riskControlInfo.setTradeNo(tradeNo);
		AuditStatus auditStatus = AuditStatus.PASS;// 默认 通过
		riskControlInfo.setAuditStatus(String.valueOf(auditStatus.getValue()));

		int result = riskControlInfoService.create4Selective(riskControlInfo);
		if (result < 1)
			throw new RuntimeException("保存风控信息失败");

		BeanUtils.copyProperties(riskControlInfo, loanDetail);
		loanDetail.setRiskControlId(riskControlInfo.getId());

		result = riskLoanDetailService.create4Selective(loanDetail);
		if (result < 1)
			throw new RuntimeException("保存贷款单信息失败");

		BeanUtils.copyProperties(riskControlInfo, loanUser);
		loanUser.setRiskControlId(riskControlInfo.getId());

		result = riskLoanUserService.create4Selective(loanUser);
		if (result < 1)
			throw new RuntimeException("个人用户信息失败");

		List<RiskRelationUser> relationUsers = auditDto.getRelationUsers();
		for (Iterator<RiskRelationUser> iterator = relationUsers.iterator(); iterator.hasNext();) {
			RiskRelationUser relationUser = new RiskRelationUser();
			BeanUtils.copyProperties(riskControlInfo, relationUser);
			relationUser.setRiskControlId(riskControlInfo.getId());

			result = riskRelationUserService.create4Selective(relationUser);
			if (result < 1)
				throw new RuntimeException("关系人信息失败");
		}

		BeanUtils.copyProperties(riskControlInfo, loanAccount);
		loanAccount.setRiskControlId(riskControlInfo.getId());
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

		String bizContent = securitySignCertService.encryptData(auditDto.getTrustProjectCode(), JSON.toJSONString(auditResult));
		if (null == bizContent) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			response.setMsg("签名证书过期");
			return response;
		}
		response.setBizContent(bizContent);

		return response;

	}

	private ResponseData<Object> riskControlAudit(RequestData requestData, RiskControlAuditDto auditDto) throws Exception {
		ResponseData<Object> response = new ResponseData<Object>();
		if (StringUtils.isBlank(requestData.getTrustProjectCode()) || StringUtils.isBlank(requestData.getTimestamp()) || StringUtils.isBlank(requestData.getBizContent())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}

		byte[] cleartxt = securitySignCertService.decryptData(requestData.getTrustProjectCode(), requestData.getBizContent());
		if (null == cleartxt) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			response.setMsg("签名信封数据有误");
			return response;
		}

		auditDto = JSON.parseObject(new String(cleartxt), RiskControlAuditDto.class);

		if (StringUtils.isBlank(auditDto.getOutTradeNo())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}
		auditDto.setTrustProjectCode(requestData.getTrustProjectCode());
		auditDto.setTimestamp(requestData.getTimestamp());

		response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
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
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			RiskControlAuditQueryDto auditQueryDto = new RiskControlAuditQueryDto();
			response = queryRiskControlAudit(requestData, auditQueryDto);

			if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
				return response;
			}

			if (StringUtils.isBlank(auditQueryDto.getCertType()) || StringUtils.isBlank(auditQueryDto.getCertId())) {
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

				String bizContent = securitySignCertService.encryptData(auditQueryDto.getTrustProjectCode(), JSON.toJSONString(auditResult));
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

	/**
	 * 放款审批結果查询
	 * 
	 * @param requestData
	 *            请求数据
	 * @return ResponseData 响应数据
	 */
	
	@Transactional(readOnly = true)
	public ResponseData<?> loadAuditQuery(RequestData requestData) {
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			RiskControlAuditQueryDto auditQueryDto = new RiskControlAuditQueryDto();
			response = queryRiskControlAudit(requestData, auditQueryDto);
			if (!ServiceErrorCode.SUCCESS.getCode().equals(response.getCode())) {
				return response;
			}

			if (StringUtils.isBlank(auditQueryDto.getContractNo())) {
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
				String bizContent = securitySignCertService.encryptData(auditQueryDto.getTrustProjectCode(), JSON.toJSONString(auditResult));
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

	private ResponseData<Object> queryRiskControlAudit(RequestData requestData, RiskControlAuditQueryDto auditQueryDto) {
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			if (StringUtils.isBlank(requestData.getTrustProjectCode()) || StringUtils.isBlank(requestData.getTimestamp()) || StringUtils.isBlank(requestData.getBizContent())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}

			byte[] cleartxt = securitySignCertService.decryptData(requestData.getTrustProjectCode(), requestData.getBizContent());
			if (null == cleartxt) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				response.setMsg("签名证书过期");
				return response;
			}

			auditQueryDto = JSON.parseObject(new String(cleartxt), RiskControlAuditQueryDto.class);
			if (StringUtils.isBlank(auditQueryDto.getOutTradeNo())) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}
			response.setServiceErrorCode(ServiceErrorCode.SUCCESS);

			return response;

		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			response.setServiceErrorCode(ServiceErrorCode.UNKNOWN_EXCEPTION);
			return response;
		}

	}

}
