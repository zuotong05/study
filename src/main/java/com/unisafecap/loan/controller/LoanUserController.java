package com.unisafecap.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unisafecap.loan.manager.LoanUserManager;

/**
 * <P>
 * 个人用户信息Controller
 * </P> 
 * 
 * @author zuotong
 * @since 2017年04月28日
 * @version V1.0  
 */
@Controller
@RequestMapping("/loanUser")
public class LoanUserController {

	private static final Logger logger = LoggerFactory.getLogger(LoanUserController.class);
	
	@Autowired
	private LoanUserManager loanUserManager;

}
