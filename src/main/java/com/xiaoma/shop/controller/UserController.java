package com.xiaoma.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoma.shop.service.UserService;
 
/**
 * @Title:钱包明细Controller
 * @Description: TODO
 * @author xiaoma
 * @since 2017年01月24日
 * @version V1.0  
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

}
