package com.unisafecap.loan.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.loan.dao.LoanUserDao;
import com.unisafecap.loan.model.LoanUser;

@Repository("loanUserDaoImpl")
public class LoanUserDaoImpl extends BaseDaoImpl<LoanUser> implements LoanUserDao {

}
