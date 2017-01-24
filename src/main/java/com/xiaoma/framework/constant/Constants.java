package com.xiaoma.framework.constant;

/**
 * <P>
 * 常量类
 * </P>
 * 
 * @author 左通
 * @since 2017年1月12日
 * @version V1.0
 */
public class Constants {
	/**
	 * <P>
	 * 排序方式
	 * </P>
	 * 
	 * @author zuotong
	 * @since 2017年1月12日
	 * @version V1.0
	 */
	public enum SortBy {
		ASC(), DESC();
	}

	/**
	 * <P>
	 * 操作类型
	 * </P>
	 * 
	 * @author zuotong
	 * @since 2017年1月20日
	 * @version V1.0
	 */
	public enum OperationType {
		CREATE("新增"), RETRIEVE("查询"), UPDATE("修改"), DELETE("删除");
		private String name;

		OperationType(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
