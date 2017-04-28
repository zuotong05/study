package com.unisafecap.framework.api.impl.utils;

import org.hibernate.service.spi.ServiceException;

import com.unisafecap.framework.common.enums.EmRequestSource;

/**
 * <P>
 * 业务功能
 * </P>
 * 
 * @author zuotong
 * @since 2017年4月28日
 * @version V1.0
 */
public interface IServiceFuncExec<T> {

	public T process(Integer ver, Integer systemId, EmRequestSource channel, String jsonPara) throws ServiceException;

	public void valid(Integer ver, Integer systemId, EmRequestSource channel, String jsonPara) throws ServiceException;

}
