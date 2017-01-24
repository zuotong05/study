package com.xiaoma.framework.model;

public interface StatusEnabled {

	public static final boolean STATUS_ACTIVE = true;

	public static final boolean STATUS_INACTIVE = false;

	public boolean isEnabled();

	public void setEnabled(boolean enabled);
}
