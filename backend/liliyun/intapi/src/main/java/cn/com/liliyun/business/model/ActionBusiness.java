package cn.com.liliyun.business.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class ActionBusiness extends BaseModel implements Serializable{

	private Integer id;
	
	private String actionid;
	
	private String businessid;

	private String ids;
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActionid() {
		return actionid;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	
	
}
