package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.RiskLoanAccountDao;
import com.unisafecap.ams.risk.model.RiskLoanAccount;

@Repository("riskLoanAccountDaoImpl")
public class RiskLoanAccountDaoImpl extends BaseDaoImpl<RiskLoanAccount> implements RiskLoanAccountDao {

}
