package com.unisafecap.framework.exceptions;

import com.unisafecap.framework.common.enums.ServiceErrorCode;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -552974525359666324L;

	private ServiceErrorCode errorCode;
	private String message;

	public ServiceException(ServiceErrorCode errorCode) {
		message = errorCode.getMessage();
		this.errorCode = errorCode;
	}

	public ServiceException(ServiceErrorCode errorCode, Object... param) {
		super(String.format(errorCode.getMessage(), param));
		message = String.format(errorCode.getMessage(), param);
		this.errorCode = errorCode;
	}

	public ServiceException(Throwable cause, ServiceErrorCode errorCode, Object... param) {
		super(String.format(errorCode.getMessage(), param), cause);
		message = String.format(errorCode.getMessage(), param);
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode.getCode();
	}

	public String getMessage() {
		return message;
	}

	public ServiceErrorCode getErrorCode() {
		return errorCode;
	}

}
