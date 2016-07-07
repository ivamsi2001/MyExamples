package com.venkat.annotations.pojos;

import com.venkat.annotations.types.BeanInfo;

@BeanInfo(description = "This JavaBean represents an Article in the On-line Store application")
public class Article {

	private static final String ACTIVE = "active";
	private static final String INACTIVE = "inactive";
	private static final String INVALID = "invalid";
	@BeanInfo(description = "The unique identifier for this Article in the catalog")
	private String id;
	@BeanInfo(description = "The department number where this Article belongs to")
	private int department;
	@BeanInfo(description = "Textual representation of this Article status")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@BeanInfo
	public void activate() {
		setStatus(ACTIVE);
	}

	@BeanInfo
	public void deactivate() {
		setStatus(INACTIVE);
	}

	@BeanInfo(description = "Action to invalidate the Article, given the cause")
	public void invalidate(@BeanInfo(description = "The cause why the Article is invalidated") String cause) {
		setStatus(INVALID + ": " + cause);
	}

	@BeanInfo
	public boolean isActive() {
		return ACTIVE.equals(status);
	}

	@BeanInfo
	public boolean isInactive() {
		return INACTIVE.equals(status);
	}

	@BeanInfo
	public boolean isInvalid() {
		return status != null && status.startsWith(INVALID);
	}
}