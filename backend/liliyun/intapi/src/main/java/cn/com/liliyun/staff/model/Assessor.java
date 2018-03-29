package cn.com.liliyun.staff.model;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class Assessor extends BaseModel{

	private Integer id;
	@Excel(name="姓名")
	private String name;
	
	
	private String idcard;
	@Excel(name="手机号码")
	private String mobile;
	
	@Excel(name="性别",replace={"男_0","女_1"})
	private Integer sex;
	
	
	private String address;
	@Excel(name="供职状态",replace={"在职_0","离职_1"})
	private String employstatus;
	@Excel(name="入职日期")
	private String hiredate;
	
	private String leavedate;
	
	private String photo;
	
	private String fingerprint;
	
	private Integer isdel;
	
	private String drilicence;
	
	private String fstdrilicdate;
	
	private String expiredate;
	
	private String dripermitted;
	
	private String teachpermitted;
	
	
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer staffid;
	
	private String procertificate;
	
	private Integer prolevel;
	
	

	public String getProcertificate() {
		return procertificate;
	}

	public void setProcertificate(String procertificate) {
		this.procertificate = procertificate;
	}

	public Integer getProlevel() {
		return prolevel;
	}

	public void setProlevel(Integer prolevel) {
		this.prolevel = prolevel;
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmploystatus() {
		return employstatus;
	}

	public void setEmploystatus(String employstatus) {
		this.employstatus = employstatus;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getLeavedate() {
		return leavedate;
	}

	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getDrilicence() {
		return drilicence;
	}

	public void setDrilicence(String drilicence) {
		this.drilicence = drilicence;
	}

	public String getFstdrilicdate() {
		return fstdrilicdate;
	}

	public void setFstdrilicdate(String fstdrilicdate) {
		this.fstdrilicdate = fstdrilicdate;
	}

	public String getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}

	public String getDripermitted() {
		return dripermitted;
	}

	public void setDripermitted(String dripermitted) {
		this.dripermitted = dripermitted;
	}

	public String getTeachpermitted() {
		return teachpermitted;
	}

	public void setTeachpermitted(String teachpermitted) {
		this.teachpermitted = teachpermitted;
	}

	

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}
	
	
}
