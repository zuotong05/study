package com.unisafecap.ams.risk.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.manager.BaseManager;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskLoanApplyDao;
import com.unisafecap.ams.risk.model.RiskLoanApply;

/**
 * <P>
 * 贷款单信息Manager
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0  
 */
@Service("riskLoanApplyManager")
public class RiskLoanApplyManager extends BaseManager<RiskLoanApply> {

	@Autowired
	private RiskLoanApplyDao riskLoanApplyDaoImpl;
	
	@Override
	public BaseDao<RiskLoanApply> getBaseDao() {
		return riskLoanApplyDaoImpl;
	}
}
