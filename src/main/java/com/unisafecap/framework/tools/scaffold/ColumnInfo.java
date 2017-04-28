package com.unisafecap.framework.tools.scaffold;

import java.util.HashMap;
import java.util.Map;

public class ColumnInfo {

	public static Map<String, String> dbTypeToJdbcType = new HashMap<String, String>();

	private String name;
	public String dbType;
	public String jdbcType;
	public String javaType;
	private int size;
	private int digits;
	private boolean nullable;
	private String comment;
	private final WordsParser wordsParser;

	static {
		dbTypeToJdbcType.put("int", "integer");
	}

	public ColumnInfo(String name, String dbType, int size, int decimalDigits, int nullable, String comment) {
		this.name = name;
		this.dbType = dbType;
		this.size = size;
		this.digits = decimalDigits;
		if (nullable == 1)
			this.nullable = true;
		this.comment = comment;
		if (ScaffoldUtil.isUpperCase(name) || name.contains(ScaffoldUtil.UNDER_LINE)) {
			this.wordsParser = new UnderlineSplitWordsParser();
		} else {
			this.wordsParser = new UncapitalizeWordsParser();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getJdbcType() {
		jdbcType = dbTypeToJdbcType.get(dbType.toLowerCase());
		return jdbcType == null ? dbType : jdbcType.toUpperCase();
	}

	public String getJavaType() {
		javaType = TypeUtils.fromDbType2JavaType(dbType);
		return javaType;
	}

	public WordsParser getWordsParser() {
		return wordsParser;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String parseFieldName() {
		return wordsParser.parseWords(name);
	}

	@Override
	public String toString() {
		return name + " " + dbType + " " + size + " " + digits + " " + nullable + " " + comment;
	}
}
