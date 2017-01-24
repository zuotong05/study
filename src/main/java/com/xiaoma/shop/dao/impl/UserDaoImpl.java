package com.xiaoma.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoma.framework.dao.impl.BaseDaoImpl;
import com.xiaoma.shop.dao.UserDao;
import com.xiaoma.shop.model.User;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

}
