package com.unisafecap.ams.risk.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.manager.BaseManager;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskLoanUserDao;
import com.unisafecap.ams.risk.model.RiskLoanUser;

/**
 * <P>
 * 个人用户信息Manager
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0  
 */
@Service("riskLoanUserManager")
public class RiskLoanUserManager extends BaseManager<RiskLoanUser> {

	@Autowired
	private RiskLoanUserDao riskLoanUserDaoImpl;
	
	@Override
	public BaseDao<RiskLoanUser> getBaseDao() {
		return riskLoanUserDaoImpl;
	}
}
