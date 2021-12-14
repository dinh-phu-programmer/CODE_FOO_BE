package com.codefoo.app.authority;

public enum AppUserPermission {
	READ_POST("user:read"), CREATE_POST("user:create_post"), UPLOAD_POST("user:upload_post"),
	DELETE_POST("user:delete_post"), EDIT_POST("user:edit_post"), CREATE_COMMENT("user:create_comment"),
	DELETE_COMMENT("user:create_comment"), EDIT_COMMENT("user:create_comment"), ALL_ACTION("user:all_action");

	private String permission;

	private AppUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
