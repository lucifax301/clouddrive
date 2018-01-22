package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class MarketActivity extends BaseModel implements Serializable{

	private Integer id;
	
	private String businessid;
	
	private String transactionid;
	@Excel(name="活动名称")
	private String activityname;
	
	private Integer areaid;
	@Excel(name="申请片区")
	private String area;
	
	private Integer storeid;
	@Excel(name="申请门店")
	private String store;
	@Excel(name="活动起止时间")
	private String period;
	@Excel(name="活动天数")
	private Integer day;
	
	private Date begindate;
	
	private Date enddate;
	
	private Integer applyuserid;
	
	private String applyuser;
	
	
	@Excel(name="活动地点")
	private String address;
	
	@Excel(name="当日场地租金")
	private int rent;
	
	@Excel(name="租金总计")
	private int totalrent;
	
	@Excel(name="现场工作人员")
	private Integer staff;
	
	private Date feedbackdate;
	
	private Integer status;
	@Excel(name="审核状态")
	private String statusStr;
	
	private String attach;
	
	private String attachid;
	
	private String reason;
	
	private String remark;
	
	private Date createdate;
	
	private Integer audituserid;
	
	private String audituser;
	
	private Date auditdate;
	
	private Integer modapplystat;
	
	private Object extend;
	
	
	
	public Object getExtend() {
		return extend;
	}

	public void setExtend(Object extend) {
		this.extend = extend;
	}

	public Integer getModapplystat() {
		return modapplystat;
	}

	public void setModapplystat(Integer modapplystat) {
		this.modapplystat = modapplystat;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public int getTotalrent() {
		return totalrent;
	}

	public void setTotalrent(int totalrent) {
		this.totalrent = totalrent;
	}

	public MarketActivity(){
		
	}
	
	public MarketActivity(Integer id){
		this.id=id;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
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

	public Date getFeedbackdate() {
		return feedbackdate;
	}

	public void setFeedbackdate(Date feedbackdate) {
		this.feedbackdate = feedbackdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getAttachid() {
		return attachid;
	}

	public void setAttachid(String attachid) {
		this.attachid = attachid;
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
	
	
}
