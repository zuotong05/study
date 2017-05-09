package com.unisafecap.ams.risk.dao.impl;

import org.springframework.stereotype.Repository;

import com.unisafecap.framework.dao.impl.BaseDaoImpl;
import com.unisafecap.ams.risk.dao.SecuritySignCertDao;
import com.unisafecap.ams.risk.model.SecuritySignCert;

@Repository("securitySignCertDaoImpl")
public class SecuritySignCertDaoImpl extends BaseDaoImpl<SecuritySignCert> implements SecuritySignCertDao {

}
