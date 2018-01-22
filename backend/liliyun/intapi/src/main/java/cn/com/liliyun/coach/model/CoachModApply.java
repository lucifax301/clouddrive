package cn.com.liliyun.coach.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CoachModApply extends BaseModel{

	private int id;
	
	private String businessid;
	
	private String transactionid;
	
	private int coachid;
	
	private int areaid;
	
	private int applyuserid;
	
	private int audituserid;
	
	private int status;
	
	private String remark;
	
	private String detail;
	
	private Date createdate;
	
	private String coachname;
	
	private String applyuser;
	
	private String audituser;
	
	private String area;
	
	private Date auditdate;
	
	private Integer modapplystat;
	
	

	public Integer getModapplystat() {
		return modapplystat;
	}

	public void setModapplystat(Integer modapplystat) {
		this.modapplystat = modapplystat;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public String getApplyuser() {
		return applyuser;
	}

	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}

	public String getAudituser() {
		return audituser;
	}

	public void setAudituser(String audituser) {
		this.audituser = audituser;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

	public int getApplyuserid() {
		return applyuserid;
	}

	public void setApplyuserid(int applyuserid) {
		this.applyuserid = applyuserid;
	}

	public int getAudituserid() {
		return audituserid;
	}

	public void setAudituserid(int audituserid) {
		this.audituserid = audituserid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
