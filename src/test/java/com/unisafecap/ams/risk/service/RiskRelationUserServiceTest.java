package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test*.xml"})
public class RiskRelationUserServiceTest {
	@Autowired
	private RiskRelationUserService riskRelationUserService;
	
	
	
}
