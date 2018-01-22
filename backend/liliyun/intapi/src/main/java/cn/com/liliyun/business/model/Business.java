package cn.com.liliyun.business.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 业务
 * @author devil
 *
 */
public class Business extends BaseModel implements Serializable{

	private Integer id;
	
	/*
	 * 业务编号, 如 SPJLXG 审批教练修改 , SPXYXG 审批学员修改 SPXYZL 审批学员资料
	 */
	private String businessid;
	
	private String name;
	
	private String description;

	private String ids;
	
	private String template;
	
	

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
