package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.service.BaseService;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskLoanAccountDao;
import com.unisafecap.ams.risk.model.RiskLoanAccount;

/**
 * <P>
 * 放款账户信息Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月11日
 * @version V1.0  
 */
@Service("riskLoanAccountService")
public class RiskLoanAccountService extends BaseService<RiskLoanAccount> {

	@Autowired
	private RiskLoanAccountDao riskLoanAccountDaoImpl;
	
	@Override
	public BaseDao<RiskLoanAccount> getBaseDao() {
		return riskLoanAccountDaoImpl;
	}
}
