package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.service.BaseService;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskControlInfoDao;
import com.unisafecap.ams.risk.model.RiskControlInfo;

/**
 * <P>
 * 风控信息Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0  
 */
@Service("riskControlInfoService")
public class RiskControlInfoService extends BaseService<RiskControlInfo> {

	@Autowired
	private RiskControlInfoDao riskControlInfoDaoImpl;
	
	@Override
	public BaseDao<RiskControlInfo> getBaseDao() {
		return riskControlInfoDaoImpl;
	}
	
	/*public RiskControlInfo findPartByMap(Map<String, Object> map) {
		return riskControlInfoDaoImpl.findPartByMap(map);
	}*/
}
