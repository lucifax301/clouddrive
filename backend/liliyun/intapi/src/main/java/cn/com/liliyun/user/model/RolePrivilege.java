package cn.com.liliyun.user.model;

import cn.com.liliyun.common.model.BaseModel;

public class RolePrivilege extends BaseModel {

	private static final long serialVersionUID = 1L;

	private int roleId;
	
	private int privilegeId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	
}
