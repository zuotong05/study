package com.unisafecap.framework.common.enums;

/**
 * <P>
 * 服务错误代码枚举
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月5日
 * @version V1.0
 */
public enum ServiceErrorCode {
	SUCCESS("CF0000", "成功"), 
	HANDLING("CF0001", "受理成功，处理中"),	
	PARAMETER_ERROR("CF0002", "数据参数错误"), 
	LOAN_NON_EXISTENT("CF0004", "贷款单不存在"), 
	CERT_ID_NON_EXISTENT("CF0005", "证件号码不存在"), 
	OUT_TRADE_NO_REPEAT("CF0006", "流水号重复"), 
	//CERT_ID_NO_REPEAT("CF0007", "证件号码重复"), 
	CONTRACT_NO_REPEAT("CF0008", "贷款单合同号重复"), 
	SIGNATURE_PROBLEM("CF7777", "证书问题"), 
	FAIL("CF8888", "业务处理失败"), 
	UNKNOWN_EXCEPTION("CF9999", "未知异常");

	private String code;

	private String message;

	private ServiceErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
