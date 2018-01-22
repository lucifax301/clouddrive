package cn.com.liliyun.flow.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 业务流程
 * @author devil
 * 一项审批业务就产生一条数据
 */
public class Flow extends BaseModel  implements Serializable{

	private int id;
	
	private int templateid;
	
	/**
	 * 全局唯一事务ID
	 */
	private String transactionid;
	
	private String businessid;
	
	/**
	 * 当前
	 */
	private int step;
	
	private String description;
	/**
	 * 0 not finish 1 finish
	 */
	private Integer status;
	
	private Date createdate;
	
	/**
	 * 发起人
	 */
	private int fuserid;
	/**
	 * 当前审批人
	 */
	private Integer curuserid;
	
	private String business;
	
	private String template;
	
	
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Integer getCuruserid() {
		return curuserid;
	}

	public void setCuruserid(Integer curuserid) {
		this.curuserid = curuserid;
	}

	public int getFuserid() {
		return fuserid;
	}

	public void setFuserid(int fuserid) {
		this.fuserid = fuserid;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

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

	

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
