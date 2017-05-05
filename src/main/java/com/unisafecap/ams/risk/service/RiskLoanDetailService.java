package com.unisafecap.ams.risk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.service.BaseService;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskLoanDetailDao;
import com.unisafecap.ams.risk.model.RiskLoanDetail;

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
	
	public List<RiskLoanDetail> findPartByMap(Map<String, Object> map) {
		return riskLoanDetailDaoImpl.findPartByMap(map);
	}
}
