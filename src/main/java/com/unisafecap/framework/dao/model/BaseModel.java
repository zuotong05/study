package com.unisafecap.framework.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <P>
 * </P>
 * 
 * @author zuotong
 * @since 2017年4月27日
 * @version V1.0
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 创建日期
	 */
	private Date createdTime;

	/**
	 * 创建人
	 */
	protected String createdBy;

	/**
	 * 更新日期
	 */
	private Date updatedTime;

	/**
	 * 更新人
	 */
	private String updatedBy;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 版本号
	 */
	private Long version;

	private Integer yn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

}
