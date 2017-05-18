package com.unisafecap.ams.risk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.unisafecap.ams.risk.model.RiskControlInfo;
import com.unisafecap.ams.risk.model.enums.EmDataSource;
import com.unisafecap.framework.common.enums.Order;
import com.unisafecap.framework.common.utils.PageBean;
import com.unisafecap.framework.spring.support.CustomerContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/application-context.xml", "classpath:spring/spring-mybatis.xml"})
@ActiveProfiles("test")
// @Transactional("jpaTransactionManager")
public class RiskControlInfoServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(RiskControlInfoServiceTest.class);

	@Autowired
	private RiskControlInfoService riskControlInfoService;

	// @Test
	public void testGet() {
		RiskControlInfo result = riskControlInfoService.get(37L);

		org.junit.Assert.assertNotNull(result);

	}

	// @Test
	public void testGet2() {
		RiskControlInfo result = riskControlInfoService.get(40L, "_0");

		org.junit.Assert.assertNotNull(result);

	}

	 @Test
	public void testCreate() {
		//CustomerContextHolder.setCustomerType(EmDataSource.DATA_SOURCE_02.getName()); 
		RiskControlInfo info = new RiskControlInfo();

		//info.setTableNameSuffix("_0");
		info.setOrgCode("1009000");
		info.setTimestamp("2017-06-12");
		info.setOutTradeNo("10002");
		info.setTrustProjectCode("20002");
		info.setRemark2("30002");
		int result = riskControlInfoService.create(info);

		org.junit.Assert.assertEquals(1, result);
		CustomerContextHolder.clearCustomerType();
	}

	// @Test
	public void testCreate4Selective() {
		RiskControlInfo info = new RiskControlInfo();

		info.setTableNameSuffix("_0");
		info.setOrgCode("100060");
		info.setTimestamp("2017-06-12");
		info.setOutTradeNo("10002");
		info.setTrustProjectCode("20002");
		info.setRemark2("30002");
		int result = riskControlInfoService.create4Selective(info);

		org.junit.Assert.assertEquals(1, result);
	}

	// @Test
	public void testUpdate() {
		RiskControlInfo info = new RiskControlInfo();

		// info.setTableNameSuffix("_0");
		info.setId(41L);
		info.setOrgCode("100060");
		info.setTimestamp("2017-06-12");
		info.setOutTradeNo("10002");
		info.setTrustProjectCode("20002");
		info.setRemark2("30002");
		info.setUpdatedBy("zuotong");

		int result = riskControlInfoService.update(info);

		org.junit.Assert.assertEquals(1, result);
	}

	// @Test
	public void testUpdate4Selective() {
		RiskControlInfo info = new RiskControlInfo();

		info.setTableNameSuffix("_0");
		info.setId(41L);

		info.setUpdatedBy("tongzi");

		int result = riskControlInfoService.update4Selective(info);

		org.junit.Assert.assertEquals(1, result);
	}

	// @Test
	public void testDelete() {

		int result = riskControlInfoService.delete(41L, "_0");

		org.junit.Assert.assertEquals(1, result);
	}

	// @Test
	public void testDeleteT() {
		RiskControlInfo info = new RiskControlInfo();
		info.setTableNameSuffix("_0");
		info.setOrgCode("100060");
		int result = riskControlInfoService.delete(info);

		org.junit.Assert.assertNotEquals(0, result);

	}

	// @Test
	public void testFindAll() {
		List<RiskControlInfo> result = riskControlInfoService.findAll("_0");
		org.junit.Assert.assertNotNull(result);
	}

	// @Test
	public void testFindBy() {
		List<RiskControlInfo> result = riskControlInfoService.findBy("orgCode", "10050", "_0");
		org.junit.Assert.assertNotNull(result);
	}

	// @Test
	public void testFindByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", "10050");
		// map.put("tableNameSuffix", "_0");
		List<RiskControlInfo> result = riskControlInfoService.findByMap(map);
		org.junit.Assert.assertNotNull(result);
	}

	// @Test
	public void testFindUniqueBy() {
		RiskControlInfo result = riskControlInfoService.findUniqueBy("outTradeNo", "1005048744732381151232", "_0");
		org.junit.Assert.assertNotNull(result);
	}

	 @Test
	public void testFindByMapTopN() {
		CustomerContextHolder.setCustomerType(EmDataSource.DATA_SOURCE_02.getName());   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", "1005000");
		List<RiskControlInfo> result = riskControlInfoService.findByMapTopN(map, false, "id", Order.DESC, 10);
		org.junit.Assert.assertNotNull(result);
	}

	 //@Test
	public void find4Page() {
		
		PageBean<RiskControlInfo> page = new PageBean<RiskControlInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", "10050");
		map.put("tableNameSuffix", "_0");
		riskControlInfoService.find4Page(page, map, false);
		org.junit.Assert.assertNotNull(page.getResult());
	}

	public void testFindByIds() {

		List<RiskControlInfo> result = riskControlInfoService.findByIds("37,38");
		org.junit.Assert.assertNotNull(result);

	}

}
