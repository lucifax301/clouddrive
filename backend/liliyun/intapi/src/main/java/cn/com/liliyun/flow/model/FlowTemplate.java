package cn.com.liliyun.flow.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 业务流模板
 * @author devil
 *
 */
public class FlowTemplate extends BaseModel implements Serializable{

	private Integer id;
	
	private String name;
	
	/**
	 * roleid;roleid2;roleid3
	 * 表示一个审批流有3个角色审批节点
	 */
	private String rule;
	/**
	 * 0表示可用，1不可用
	 */
	private Integer status;
	
	private Date createdate;
	
	/**
	 * 绑定业务
	 */
	private String businessid;
	
	private String ids;
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
}
