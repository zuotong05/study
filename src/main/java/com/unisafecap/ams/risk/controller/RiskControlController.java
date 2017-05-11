package com.unisafecap.ams.risk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unisafecap.ams.risk.service.RiskControlService;
import com.unisafecap.framework.common.enums.ServiceErrorCode;
import com.unisafecap.framework.model.dto.RequestData;
import com.unisafecap.framework.model.dto.ResponseData;

/**
 * <P>
 * 风控接口
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0
 */
@Controller
@RequestMapping("/riskControl/")
public class RiskControlController {
	private static final Logger logger = LoggerFactory.getLogger(RiskControlController.class);

	@Autowired
	private RiskControlService riskControlService;

	/**
	 * 客户审批
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/customerAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<?> customerAudit(@RequestBody RequestData requestData) {
		try {
			return riskControlService.customerAudit(requestData);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}

	}

	/**
	 * 客户审批结果查询
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/customerAuditQuery", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<?> customerAuditQuery(@RequestBody RequestData requestData) {
		try {
			return riskControlService.customerAuditQuery(requestData);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
	}

	/**
	 * 放款审批
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/loadAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<?> loadAudit(@RequestBody RequestData requestData) {
		try {
			return riskControlService.loadAudit(requestData);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}

	}

	/**
	 * 放款审批结果查询
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/loadAuditQuery", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<?> riskControlAuditQuery(@RequestBody RequestData requestData) {
		try {
			return riskControlService.loadAuditQuery(requestData);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
	}

}
