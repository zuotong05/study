package com.xiaoma.framework.model;

/**
 * <P>
 * 统一定义id的entity基类
 * </P>
 * 
 * @author 左通
 * @since 2017年1月12日
 * @version V1.0
 */

public abstract class BaseEntity {

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
