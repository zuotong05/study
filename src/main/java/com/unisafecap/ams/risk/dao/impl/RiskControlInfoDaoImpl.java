package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.RiskControlInfoDao;
import com.unisafecap.ams.risk.model.RiskControlInfo;

@Repository("riskControlInfoDaoImpl")
public class RiskControlInfoDaoImpl extends BaseDaoImpl<RiskControlInfo> implements RiskControlInfoDao {

}
