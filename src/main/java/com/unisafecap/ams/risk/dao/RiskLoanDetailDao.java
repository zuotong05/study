package com.unisafecap.ams.risk.dao;

import java.util.List;
import java.util.Map;

import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.framework.dao.BaseDao;

/**
 * <P>
 * 贷款单信息Dao
 * </P> 
 * 
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0  
 */
public interface RiskLoanDetailDao extends BaseDao<RiskLoanDetail>{
	public List<RiskLoanDetail> findPartByMap(Map<String, Object> map);
}

