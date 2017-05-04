package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.RiskLoanUserDao;
import com.unisafecap.ams.risk.model.RiskLoanUser;

@Repository("riskLoanUserDaoImpl")
public class RiskLoanUserDaoImpl extends BaseDaoImpl<RiskLoanUser> implements RiskLoanUserDao {

}
