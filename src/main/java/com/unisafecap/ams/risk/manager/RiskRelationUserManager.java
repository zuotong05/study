package com.unisafecap.ams.risk.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.manager.BaseManager;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.ams.risk.dao.RiskRelationUserDao;
import com.unisafecap.ams.risk.model.RiskRelationUser;

/**
 * <P>
 * 关系人信息Manager
 * </P>
 * 
 * @author zuotong
 * @since 2017年05月04日
 * @version V1.0  
 */
@Service("riskRelationUserManager")
public class RiskRelationUserManager extends BaseManager<RiskRelationUser> {

	@Autowired
	private RiskRelationUserDao riskRelationUserDaoImpl;
	
	@Override
	public BaseDao<RiskRelationUser> getBaseDao() {
		return riskRelationUserDaoImpl;
	}
}
