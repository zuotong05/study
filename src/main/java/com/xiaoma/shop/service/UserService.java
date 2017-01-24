package com.xiaoma.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoma.framework.service.BaseService;
import com.xiaoma.framework.dao.BaseDao;
import com.xiaoma.shop.dao.UserDao;
import com.xiaoma.shop.model.User;

/**
 * @Title:钱包明细Service
 * @Description: TODO
 * @author xiaoma
 * @since 2017年01月24日
 * @version V1.0  
 */
@Service("userService")
public class UserService extends BaseService<User> {

	@Autowired
	private UserDao userDaoImpl;
	
	@Override
	public BaseDao<User> getBaseDao() {
		return userDaoImpl;
	}
}
