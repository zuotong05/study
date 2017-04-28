package com.unisafecap.framework.api.result;

import java.io.Serializable;

/**
 * <P>
 * </P>
 * 
 * @author zuotong
 * @since 2017年4月28日
 * @version V1.0
 */
public class BooleanResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean success = false;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
