package cn.com.liliyun.user.model;

import cn.com.liliyun.common.model.BaseModel;

public class RoleUser extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	private int roleId;
	
	private int userId;
	
	/**
	 * 多个用户id集合 1,2,3
	 */
	private String ids;
	
	public RoleUser(){}
	
	public RoleUser(int roleId,int userId){
		this.roleId=roleId;
		this.userId=userId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
