package com.unisafecap.loan.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisafecap.framework.manager.BaseManager;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.loan.dao.LoanUserDao;
import com.unisafecap.loan.model.LoanUser;

/**
 * <P>
 * 个人用户信息Manager
 * </P>
 * 
 * @author zuotong
 * @since 2017年04月28日
 * @version V1.0  
 */
@Service("loanUserManager")
public class LoanUserManager extends BaseManager<LoanUser> {

	@Autowired
	private LoanUserDao loanUserDaoImpl;
	
	@Override
	public BaseDao<LoanUser> getBaseDao() {
		return loanUserDaoImpl;
	}
}
