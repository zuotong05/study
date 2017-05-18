package com.unisafecap.ams.risk.model.enums;

/** 
 * <P>
 * 审核状态枚举
 * </P> 
 * @author zuotong
 * @since 2017年5月5日
 * @version V1.0 
 */
public enum AuditStatus {

	PASS(10, "通过"), MANUAL_AUDIT(15, "人工审核中"), VETO(20, "否决");

	private Integer value;
	private String name;

	private AuditStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
