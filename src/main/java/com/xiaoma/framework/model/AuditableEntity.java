package com.xiaoma.framework.model;

import java.util.Date;

/**
 * <P>
 * 含审计信息的Entity基类.
 * </P>
 * 
 * @author zuotong
 * @since 2017年1月18日
 * @version V1.0
 */

public abstract class AuditableEntity extends BaseEntity {
	protected String createdBy;// 创建人
	protected Date createdDate;// 创建时间

	protected Date updatedBy;// 更新人
	protected String updatedDate;// 更新时间

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Date updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
