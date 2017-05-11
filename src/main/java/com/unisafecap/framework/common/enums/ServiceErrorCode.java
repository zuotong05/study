package com.unisafecap.framework.common.enums;

/** 
 * <P>
 * 服务错误代码枚举
 * </P> 
 * @author zuotong
 * @since 2017年5月5日
 * @version V1.0 
 */
public enum ServiceErrorCode {
	SUCCESS("CF0000", "成功"), HANDLING("CF0001", "受理成功，处理中"),

	UNKNOWN_EXCEPTION("CF9999", "未知异常"), PARAMETER_ERROR("CF0002", "数据参数错误"), 
	LOAN_NON_EXISTENT("CF0004", "贷款单不存在"), OUT_TRADE_NO_REPEAT("CF0006", "流水号重复"),
	CONTRACT_NO_REPEAT("CF0105", "贷款单合同号重复"), SIGNATURE_EXPIRED("CF7777", "签名过期"),FAIL("CF8888", "业务处理失败");

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
