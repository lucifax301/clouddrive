package cn.com.liliyun.user.model;

import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class Privilege extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer pid;
	
	private String url;
	
	private Integer enable;
	
	private String remark;
	
	private Integer menuorder;
	
	private Integer menulevel;
	
	private String icon;
	
	private Integer type;
	
	private String ajax;
	
	private Integer must;
	
	private List<Privilege> children;
	
	private boolean layoutChecked;
	
	private String privilege;
	
	
	
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public boolean isLayoutChecked() {
		return layoutChecked;
	}

	public void setLayoutChecked(boolean layoutChecked) {
		this.layoutChecked = layoutChecked;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Privilege> getChildren() {
		return children;
	}

	public void setChildren(List<Privilege> children) {
		this.children = children;
	}

	public Integer getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(Integer menulevel) {
		this.menulevel = menulevel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}

	public Integer getMust() {
		return must;
	}

	public void setMust(Integer must) {
		this.must = must;
	}
	
}
