package com.unisafecap.ams.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.service.BaseService;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskRelationUserDao;
import com.unisafecap.ams.risk.model.RiskRelationUser;

/**
 * <P>
 * 关系人信息Service
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月05日
 * @version V1.0  
 */
@Service("riskRelationUserService")
public class RiskRelationUserService extends BaseService<RiskRelationUser> {

	@Autowired
	private RiskRelationUserDao riskRelationUserDaoImpl;
	
	@Override
	public BaseDao<RiskRelationUser> getBaseDao() {
		return riskRelationUserDaoImpl;
	}
}
