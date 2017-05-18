package com.unisafecap.ams.risk.model.enums;

public enum EmDataSource {
	/**
	 *默认数据源
	 */
	DATA_SOURCE_01("Default", "dataSource01"), 
	/**
	 *趣店数据源
	 */
	DATA_SOURCE_02("LS016", "dataSource02");

	private String code;
	private String name;

	private EmDataSource(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static EmDataSource get(final String code) {
		for (EmDataSource enums : EmDataSource.values()) {
			if (enums.code != null && enums.code.equals(code)) {
				return enums;
			}
		}
		return DATA_SOURCE_01;
	}


	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
