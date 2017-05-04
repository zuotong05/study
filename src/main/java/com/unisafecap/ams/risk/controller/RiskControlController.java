package com.unisafecap.ams.risk.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * <P>
 * 风控审核
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

	@RequestMapping(value = "/riskControlAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> riskControlAudit() {
		return ResponseEntity.ok(new JSONObject().fluentPut("message", "好！"));
	}

	@RequestMapping(value = "/riskControlAuditQuery", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> riskControlAuditQuery() {
		return ResponseEntity.ok(new JSONObject().fluentPut("message", "好！"));
	}

}
