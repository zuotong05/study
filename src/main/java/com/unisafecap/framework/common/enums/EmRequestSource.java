package com.unisafecap.framework.common.enums;

/**
 * <P>
 * 来源渠道枚举
 * </P>
 * 
 * @author zuotong
 * @since 2017年4月28日
 * @version V1.0
 */
public enum EmRequestSource {

	INSIDE(0, "内部触发"), WEB(1, "web");

	// 成员变量
	private Integer value;
	private String name;

	// 构造方法
	private EmRequestSource(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	// 普通方法
	public static EmRequestSource get(final Integer value) {
		for (EmRequestSource enums : EmRequestSource.values()) {
			if (enums.value != null && enums.value.equals(value)) {
				return enums;
			}
		}
		return null;
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
