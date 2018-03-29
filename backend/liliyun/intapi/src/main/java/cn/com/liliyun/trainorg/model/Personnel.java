package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * 驾培机构：人员管理
 * @author Administrator
 *
 */
public class Personnel extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private long instid;
	private String inscode; //机构编号
	private String examnum;//人员编号
	private String name;
	private String sex;
	private String idcard;
	private String mobile;
	private String address;
	private long photo;//照片文件 ID
	private String photo_url;
	private String fingerprint_url;
	private long fingerprint;
	private String drilicence;//驾驶证号
	private String fstdrilicdate;//驾驶证初领日期YYYYMMDD
	private String occupationno;//职业资格证号
	private String occupationlevel;//职业资格等级(一级二级三级四级)
	private String dripermitted;//准驾车型
	private String teachpermitted;//准教车型
	private String employstatus;//供职状态(0在职1离职)
	private String hiredate; // 入职日期
	private String leavedate;// 离职日期
	private String post;//职务 1安全员、2考核员 //规范上没有
	private String recordType;//备案状态 //规范上没有
	
	///
	//新增
	private int operate;
	private int errorcode;
	private String message;
	private Timestamp  utime;
	private Timestamp ltime;
	
	private Timestamp ctime;
	private Timestamp mtime;

	
	
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getFingerprint_url() {
		return fingerprint_url;
	}
	public void setFingerprint_url(String fingerprint_url) {
		this.fingerprint_url = fingerprint_url;
	}
	public String getExamnum() {
		return examnum;
	}
	public void setExamnum(String examnum) {
		this.examnum = examnum;
	}
	public long getInstid() {
		return instid;
	}
	public void setInstid(long instid) {
		this.instid = instid;
	}
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	public Timestamp getLtime() {
		return ltime;
	}
	public void setLtime(Timestamp ltime) {
		this.ltime = ltime;
	}
	
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public Timestamp getMtime() {
		return mtime;
	}
	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getInscode() {
		return inscode;
	}
	public void setInscode(String inscode) {
		this.inscode = inscode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
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
	public long getPhoto() {
		return photo;
	}
	public void setPhoto(long photo) {
		this.photo = photo;
	}
	public long getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(long fingerprint) {
		this.fingerprint = fingerprint;
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
	public String getOccupationno() {
		return occupationno;
	}
	public void setOccupationno(String occupationno) {
		this.occupationno = occupationno;
	}
	public String getOccupationlevel() {
		return occupationlevel;
	}
	public void setOccupationlevel(String occupationlevel) {
		this.occupationlevel = occupationlevel;
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
	
	public Personnel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
