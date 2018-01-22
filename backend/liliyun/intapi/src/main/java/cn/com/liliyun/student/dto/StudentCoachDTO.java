package cn.com.liliyun.student.dto;

import java.io.Serializable;
import cn.com.liliyun.common.model.BaseModel;

public class StudentCoachDTO extends BaseModel implements Serializable {
 
	private static final long serialVersionUID = 1L;

	//绑定教练关系
	private Integer coachid;
	private Integer studentid;
	private Integer isvalid;//绑定状态
	private Integer state;
	private String reason;
	private Integer cuid;
	private Integer reviewid;
	private String businessid;
	private String transactionid;
	
	//学员信息
	private String stunum;
	private String name;
	private String idcard;
	private String phone;
	private Integer applyexam;
	private Integer applystatus;
	private Integer classid;
	private Integer areaid;
	private Integer storeid;
	private String flownum;
	
	//教练信息
	private String coachnum;
    private String coachname;
    private String coachmobile;
    private Byte coachsex;
    private Integer coachareaid;
    private String coachclassids;
    private String coachclasses;
    private Integer coachload;
	
	public Integer getApplystatus() {
		return applystatus;
	}
	public void setApplystatus(Integer applystatus) {
		this.applystatus = applystatus;
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
	public Integer getCuid() {
		return cuid;
	}
	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}
	public Integer getReviewid() {
		return reviewid;
	}
	public void setReviewid(Integer reviewid) {
		this.reviewid = reviewid;
	}
	public String getCoachclasses() {
		return coachclasses;
	}
	public void setCoachclasses(String coachclasses) {
		this.coachclasses = coachclasses;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Byte getCoachsex() {
		return coachsex;
	}
	public void setCoachsex(Byte coachsex) {
		this.coachsex = coachsex;
	}
	public Integer getCoachareaid() {
		return coachareaid;
	}
	public void setCoachareaid(Integer coachareaid) {
		this.coachareaid = coachareaid;
	}
	public String getCoachclassids() {
		return coachclassids;
	}
	public void setCoachclassids(String coachclassids) {
		this.coachclassids = coachclassids;
	}
	public Integer getCoachload() {
		return coachload;
	}
	public void setCoachload(Integer coachload) {
		this.coachload = coachload;
	}
	public String getStunum() {
		return stunum;
	}
	public void setStunum(String stunum) {
		this.stunum = stunum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getApplyexam() {
		return applyexam;
	}
	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
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
	public String getFlownum() {
		return flownum;
	}
	public void setFlownum(String flownum) {
		this.flownum = flownum;
	}
	public String getCoachnum() {
		return coachnum;
	}
	public void setCoachnum(String coachnum) {
		this.coachnum = coachnum;
	}
	public String getCoachname() {
		return coachname;
	}
	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}
	public String getCoachmobile() {
		return coachmobile;
	}
	public void setCoachmobile(String coachmobile) {
		this.coachmobile = coachmobile;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCoachid() {
		return coachid;
	}
	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}
	public Integer getStudentid() {
		return studentid;
	}
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	public Integer getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}
	
	
    
}