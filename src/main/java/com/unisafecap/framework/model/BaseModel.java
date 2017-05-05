package com.unisafecap.framework.model;

import java.io.Serializable;

/**
 * <P>
 * Model基础类
 * </P>
 * 
 * @author zuotong
 * @since 2017年4月27日
 * @version V1.0
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -3615506771269532232L;

	/**
	 * 标识
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
