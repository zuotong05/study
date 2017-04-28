package com.unisafecap.framework.common.enums;

/**
 * <P>
 * 操作类型
 * </P>
 * 
 * @author zuotong
 * @since 2017年1月20日
 * @version V1.0
 */
public enum OperationType {
	CREATE("新增"), RETRIEVE("查询"), UPDATE("修改"), DELETE("删除");
	private String name;

	OperationType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}