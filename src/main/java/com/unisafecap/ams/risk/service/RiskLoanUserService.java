package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.service.BaseService;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskLoanUserDao;
import com.unisafecap.ams.risk.model.RiskLoanUser;

/**
 * <P>
 * 个人用户信息Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0  
 */
@Service("riskLoanUserService")
public class RiskLoanUserService extends BaseService<RiskLoanUser> {

	@Autowired
	private RiskLoanUserDao riskLoanUserDaoImpl;
	
	@Override
	public BaseDao<RiskLoanUser> getBaseDao() {
		return riskLoanUserDaoImpl;
	}
}
