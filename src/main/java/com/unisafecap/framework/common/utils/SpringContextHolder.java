package com.unisafecap.framework.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/** 
 *<P>
 *获取spring容器，以访问容器中定义的其他bean
 *</P>    
 * @author 左通
 * @since 2017年1月24日
 * @version V1.0 
 */
public class SpringContextHolder implements ApplicationContextAware {

	

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象 这里重写了bean方法，起主要作用
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name)   {
		checkApplicationContext();
        return (T) applicationContext.getBean(name);
	}
	
	 /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 如果有多个Bean符合Class, 取出第一个.
     */
    public static <T> T getBean(Class<T> requiredType) {     
        return applicationContext.getBean(requiredType);
    }
    
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
