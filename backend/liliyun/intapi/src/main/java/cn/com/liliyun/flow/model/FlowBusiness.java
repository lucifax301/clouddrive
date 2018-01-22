package cn.com.liliyun.flow.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * @author devil
 *
 */
public class FlowBusiness extends BaseModel implements Serializable{

	private int id;
	
	private int templateid;
	
	private int bussinessid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTemplateid() {
		return templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}

	public int getBussinessid() {
		return bussinessid;
	}

	public void setBussinessid(int bussinessid) {
		this.bussinessid = bussinessid;
	}
	
	
}
