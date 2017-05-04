package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.RiskLoanApplyDao;
import com.unisafecap.ams.risk.model.RiskLoanApply;

@Repository("riskLoanApplyDaoImpl")
public class RiskLoanApplyDaoImpl extends BaseDaoImpl<RiskLoanApply> implements RiskLoanApplyDao {

}
