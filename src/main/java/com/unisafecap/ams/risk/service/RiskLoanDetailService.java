package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.ams.risk.dao.RiskLoanDetailDao;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.framework.service.BaseService;

/**
 * <P>
 * 贷款单信息Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0
 */
@Service("riskLoanDetailService")
public class RiskLoanDetailService extends BaseService<RiskLoanDetail> {

	@Autowired
	private RiskLoanDetailDao riskLoanDetailDaoImpl;

	@Override
	public BaseDao<RiskLoanDetail> getBaseDao() {
		return riskLoanDetailDaoImpl;
	}

}
