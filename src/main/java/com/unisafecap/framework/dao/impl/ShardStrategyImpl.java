package com.unisafecap.framework.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.shardbatis.strategy.ShardStrategy;
import com.unisafecap.framework.common.utils.StringUtils;
import com.unisafecap.framework.model.BaseModel;

public class ShardStrategyImpl implements ShardStrategy {
	private static final Logger logger = LoggerFactory.getLogger(ShardStrategyImpl.class);

	@Override
	public String getTargetTableName(String tableName, Object params, String mapperId) {
		logger.debug("tableName->" + tableName + " \tparams->" + params + "\tmapperId->" + mapperId);
		String sTableName = null;
		if (params instanceof Map) {
			Map map = (Map) params;
			sTableName = String.valueOf(map.get("tableNameSuffix"));
			
		} else if (params instanceof BaseModel) {
			BaseModel model = (BaseModel) params;
			sTableName = model.getTableNameSuffix();
		}

		sTableName = StringUtils.isNotBlankStr(sTableName) ? tableName + "_" + sTableName : tableName;

		logger.debug("proced tableName->" + sTableName + " \tparams->" + params + "\tmapperId->" + mapperId);
		return sTableName;
	}

}
