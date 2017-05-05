package com.unisafecap.ams.risk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unisafecap.ams.risk.dao.RiskLoanDetailDao;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.framework.dao.impl.BaseDaoImpl;

@Repository("riskLoanDetailDaoImpl")
public class RiskLoanDetailDaoImpl extends BaseDaoImpl<RiskLoanDetail> implements RiskLoanDetailDao {

	public static final String POSTFIX_SELECT_BY_CONDITION = ".selectPartByMap";

	public List<RiskLoanDetail> findPartByMap(Map<String, Object> map) {
		return getSqlSession().selectList(getSqlMapNamespace() + POSTFIX_SELECT_BY_CONDITION, map);
	}

}
