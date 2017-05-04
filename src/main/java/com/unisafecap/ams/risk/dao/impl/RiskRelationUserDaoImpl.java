package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.RiskRelationUserDao;
import com.unisafecap.ams.risk.model.RiskRelationUser;

@Repository("riskRelationUserDaoImpl")
public class RiskRelationUserDaoImpl extends BaseDaoImpl<RiskRelationUser> implements RiskRelationUserDao {

}
