package com.unisafecap.framework.spring.interceptor;

import org.aopalliance.intercept.MethodInterceptor ;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;

public class DataSourceMethodInterceptor  implements MethodInterceptor, InitializingBean  {

	@Override
	public void afterPropertiesSet() throws Exception {		
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		return null;
	}



}
