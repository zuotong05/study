package com.unisafecap.ams.risk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unisafecap.ams.risk.model.enums.EmDataSource;
import com.unisafecap.ams.risk.service.RiskControlService;
import com.unisafecap.framework.common.enums.ServiceErrorCode;
import com.unisafecap.framework.model.dto.RequestData;
import com.unisafecap.framework.model.dto.ResponseData;
import com.unisafecap.framework.spring.support.CustomerContextHolder;

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
			logger.debug("*****************客户审批 start*******************");
			CustomerContextHolder.setCustomerType(EmDataSource.get(requestData.getOrgCode()).getName());
			return riskControlService.customerAudit(requestData);
		}
		catch (Exception e) {
			logger.error("客户审批:" + e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
		finally {
			CustomerContextHolder.clearCustomerType();
			logger.debug("*****************客户审批 end *******************");
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
			logger.debug("*****************客户审批结果查询 start*******************");
			CustomerContextHolder.setCustomerType(EmDataSource.get(requestData.getOrgCode()).getName());
			return riskControlService.customerAuditQuery(requestData);
		}
		catch (Exception e) {
			logger.error("客户审批结果查询:" + e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
		finally {
			CustomerContextHolder.clearCustomerType();
			logger.debug("*****************客户审批结果查询 end*******************");
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
			logger.debug("*****************放款审批 start*******************");
			CustomerContextHolder.setCustomerType(EmDataSource.get(requestData.getOrgCode()).getName());
			return riskControlService.loadAudit(requestData);
		}
		catch (Exception e) {
			logger.error("放款审批:" + e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
		finally {
			CustomerContextHolder.clearCustomerType();
			logger.debug("*****************放款审批 end*******************");
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
			logger.debug("*****************放款审批结果查询 start*******************");
			CustomerContextHolder.setCustomerType(EmDataSource.get(requestData.getOrgCode()).getName());
			return riskControlService.loadAuditQuery(requestData);
		}
		catch (Exception e) {
			logger.error("放款审批结果查询:" + e.getMessage());
			return new ResponseData<Object>().serviceErrorCode(ServiceErrorCode.FAIL);
		}
		finally {
			CustomerContextHolder.clearCustomerType();
			logger.debug("*****************放款审批结果查询 end*******************");
		}
	}

}
