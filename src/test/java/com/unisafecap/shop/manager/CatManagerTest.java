package com.unisafecap.shop.manager;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test*.xml"})
public class CatManagerTest {
	@Autowired
	private CatManager catManager;
	
	
	
}