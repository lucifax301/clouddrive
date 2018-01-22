package cn.com.liliyun.student.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class StudentPauseApply extends BaseModel  implements Serializable{

	private int id;
	
	private String businessid;
	
	private String transactionid;
	
	private int type;
	
	private int studentid;
	
	
	private Date createdate;
	
	private Date begindate;
	  
	private Date enddate;
	
	private String reason;
	
	private int status;
	
	private int applyuserid;
	
	private String applyuser;
	
	private int pauseid;
	
	//µ±Ç°ÉóºËÈË
	private Integer audituserid;
	
	private String audituser;
	
	private String remark;

	private String area;
	
	private int areaid;
	
	private String store;
	
	private String storeid;
	
	private String studentname;
	
	private String idcard;
	
	private String phone;
	
	private int applyexam;
	
	private Integer modapplystat;
	
	
	
	public Integer getModapplystat() {
		return modapplystat;
	}

	public void setModapplystat(Integer modapplystat) {
		this.modapplystat = modapplystat;
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

	public int getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(int applyexam) {
		this.applyexam = applyexam;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getApplyuserid() {
		return applyuserid;
	}

	public void setApplyuserid(int applyuserid) {
		this.applyuserid = applyuserid;
	}

	public int getPauseid() {
		return pauseid;
	}

	public void setPauseid(int pauseid) {
		this.pauseid = pauseid;
	}

	

	public Integer getAudituserid() {
		return audituserid;
	}

	public void setAudituserid(Integer audituserid) {
		this.audituserid = audituserid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
