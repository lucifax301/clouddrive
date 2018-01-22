package cn.com.liliyun.user.model;

import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class AllRoleMenu extends BaseModel{
	private static final long serialVersionUID = 1L;

	private String val;
	
	private String txt;
	
	private List<AllRoleMenu> menu;

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public List<AllRoleMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<AllRoleMenu> menu) {
		this.menu = menu;
	}
	
}
