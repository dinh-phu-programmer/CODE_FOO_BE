package com.codefoo.app.authority;

import static com.codefoo.app.authority.AppUserPermission.*;

public enum AppUserRole {
	USER(new String[] { READ_POST.getPermission(), CREATE_COMMENT.getPermission(), EDIT_COMMENT.getPermission(),
			DELETE_COMMENT.getPermission() }),
	AUTHOR(new String[] { READ_POST.getPermission(), CREATE_POST.getPermission(), EDIT_POST.getPermission(),
			DELETE_POST.getPermission(), CREATE_COMMENT.getPermission(), EDIT_COMMENT.getPermission(),
			DELETE_COMMENT.getPermission() }),

	ADMIN(new String[] { ALL_ACTION.getPermission() });

	private String[] permissions;

	private AppUserRole(String[] permissions) {
		this.permissions = permissions;
	}

	public String[] getPermissions() {
		return permissions;
	}

}
