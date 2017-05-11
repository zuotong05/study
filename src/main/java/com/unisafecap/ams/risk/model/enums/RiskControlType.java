package com.unisafecap.ams.risk.model.enums;

public enum RiskControlType {
	CUSTOMER_AUDIT("客户审批"), LOAD_AUDIT("放款审批");

	private String name;

	private RiskControlType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
