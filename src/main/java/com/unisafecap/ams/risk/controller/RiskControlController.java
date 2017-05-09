package com.unisafecap.ams.risk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	 * 风控审核
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/riskControlAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData<?> riskControlAudit(@RequestBody RequestData requestData) {
		try {
			return riskControlService.riskControlAudit(requestData);		
			}
		catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}

	}

	/**
	 * 风控审核查询
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "/riskControlAuditQuery", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> riskControlAuditQuery(@RequestBody RequestData requestData) {
		return ResponseEntity.ok(riskControlService.riskControlAuditQuery(requestData));
	}

}
