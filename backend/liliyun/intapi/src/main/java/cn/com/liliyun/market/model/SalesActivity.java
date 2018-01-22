package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class SalesActivity extends BaseModel implements Serializable{

	private Integer id;
	
	private String businessid;
	
	private String transactionid;
	@Excel(name="活动名称")
	private String activityname;
	
	@Excel(name="活动起止时间")
	private String period;
	
	@Excel(name="状态")
	private String statusStr;
	
	private Integer areaid;
	
	private Integer storeid;
	
	private Date begindate;
	
	private Date enddate;
	
	private Integer applyuserid;
	
	private String applyuser;
	
	private int rent;
	
	private Integer staff;
	
	private String address;
	
	private Integer actstatus;
	
	private Integer status;
	
	private String classinfoid;
	
	private String reason;
	
	private String remark;
	
	private Date createdate;
	
	private Integer audituserid;
	
	private String audituser;
	
	private Date auditdate;
	
	private List<SalesActivityClassinfo> classinfo;
	@Excel(name="已招生人数")
	private Integer enrolcount;
	
	private String searchstatus;
	
	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getSearchstatus() {
		return searchstatus;
	}

	public void setSearchstatus(String searchstatus) {
		this.searchstatus = searchstatus;
	}

	public Integer getEnrolcount() {
		return enrolcount;
	}

	public void setEnrolcount(Integer enrolcount) {
		this.enrolcount = enrolcount;
	}

	public List<SalesActivityClassinfo> getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(List<SalesActivityClassinfo> classinfo) {
		this.classinfo = classinfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
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

	public Integer getApplyuserid() {
		return applyuserid;
	}

	public void setApplyuserid(Integer applyuserid) {
		this.applyuserid = applyuserid;
	}

	public String getApplyuser() {
		return applyuser;
	}

	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public Integer getStaff() {
		return staff;
	}

	public void setStaff(Integer staff) {
		this.staff = staff;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getActstatus() {
		return actstatus;
	}

	public void setActstatus(Integer actstatus) {
		this.actstatus = actstatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(String classinfoid) {
		this.classinfoid = classinfoid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getAudituserid() {
		return audituserid;
	}

	public void setAudituserid(Integer audituserid) {
		this.audituserid = audituserid;
	}

	public String getAudituser() {
		return audituser;
	}

	public void setAudituser(String audituser) {
		this.audituser = audituser;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}
	
	
}
