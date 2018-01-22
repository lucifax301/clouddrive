package cn.com.liliyun.staff.model;

import java.util.Date;
import java.util.Map;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class Staff extends BaseModel{

	private Integer id;
	@Excel(name="姓名")
	private String name;
	
	
	private String idcard;
	@Excel(name="手机号码")
	private String mobile;
	
	@Excel(name="性别",replace={"男_0","女_1"})
	private Integer sex;
	@Excel(name="职务",replace={"职能员工_1","教练员_2","客服_5","顶班客服_6"})
	private Integer job;
	
	private String address;
	@Excel(name="供职状态",replace={"在职_0","离职_1"})
	private String employstatus;
	@Excel(name="入职日期")
	private String hiredate;
	
	private String leavedate;
	
	private String photo;
	
	private Integer deptid;
	
	private String detail;
	
	private Integer isdel;
	
	private Date createdate;
	
	private Integer cuid;

	private Map detailinfo;
	
	
	
	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public Map getDetailinfo() {
		return detailinfo;
	}

	public void setDetailinfo(Map detailinfo) {
		this.detailinfo = detailinfo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getJob() {
		return job;
	}

	public void setJob(Integer job) {
		this.job = job;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}
	
	
}
