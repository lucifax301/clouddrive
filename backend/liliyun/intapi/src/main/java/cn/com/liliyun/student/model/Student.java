package cn.com.liliyun.student.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class Student extends BaseModel implements Serializable {
    private Integer id;

	private String stunum;

	private String inscode;

	private String cardtype;

	@Excel(name="学员姓名") //生成EXCEL表头顺序按字段的顺序排列
	private String name;

	@Excel(name="性别",replace={"男_1","女_2"}) //码值转换
	private String sex;
	
	@Excel(name="证件号码")
	private String idcard;

	@Excel(name="联系电话")
	private String phone;
	
	private String qq;

	private Integer height;

	private Integer nationality;

	private Integer residence;

	private Date residencedate;

	private String address;

	private Integer photo;

	private String photoback;

	private Date birthday;

	private Integer fingerprint;

	private String busitype;

	private String drilicnum;

	private String perdritype;

	private Date fstdrilicdate;

	@Excel(name="报考车型")
	private String traintype;

	@Excel(name="报名时间",exportFormat="yyyy-MM-dd")
	private Date applydate;

	private Integer completetime;

	private Integer recordtype;

	private Integer applyexam;

	private Integer applystatus;

	private Integer applytype;

	private String contract;

	private Integer operate;

	private Integer errorcode;

	private String message;

	private Date utime;

	private Date ltime;

	private Integer cuid;
	
	private String cname;

	private Integer muid;
	
	private String mname;

	private Date ctime;

	private Date mtime;

	private String photourl;

	private String fingerprinturl;

	private String trainmethod;

	private Integer classid;

	private Integer payway;

	private Integer paytype;

	private String payremark;

	private Date paydate;

	private Integer paystatus;

	private BigDecimal signmoney;

	private BigDecimal contractmoney;

	private BigDecimal cashmoney;

	private BigDecimal posmoney;

	private BigDecimal submoney;

	private String couponnum;

	private BigDecimal couponmoney;

	private Integer isvalid;

	private Integer areaid;

	private Integer storeid;

	private Date chacktime;

	private Integer checkstatus;

	private Integer bussource;

	private Integer isinternal;

	private Integer infosource;

	private String remark;

	private String billname;

	private String billnum;

	private Integer billtype;

	private Integer posid;

	private String posnum;

	private Integer xid;

	private Date carddate;

	private Integer recuid;

	private String recname;
	
	@Excel(name="流水号")
	private String flownum;
	
	private Integer islocal;
	
	private Date stabletime;
	
	private Date etabletime;

	//0 normal 1 pause
	private Integer learnstatus;
	
	//理论课用,没有对应字段
	private Integer theoryid;
	
	private Date stime;
	
	private Date etime;
	
	@Excel(name="所属片区")
	private String areaname;
	
	@Excel(name="所属门店")
	private String storename;
	
	@Excel(name="所属班别")
	private String classname;
	
	@Excel(name="学车状态")
	private String applyexamname;
	
	/**
	 * 分配教练操作人
	 */
	private String assignuser;
	
	private Integer assignuserid;
	/**
	 * 分配时间
	 */
	private Date assigntime;

	private Integer activityid;
	
	private String activity;
	
	private Integer reccoachid;
	
	private String reccoachname;
	
	private String iccard;
	
	private Integer channel;
	
	private String learncard;
	
	private String coachname;
	
	

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public String getReccoachname() {
		return reccoachname;
	}

	public void setReccoachname(String reccoachname) {
		this.reccoachname = reccoachname;
	}

	public Integer getActivityid() {
		return activityid;
	}

	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getAssignuserid() {
		return assignuserid;
	}

	public void setAssignuserid(Integer assignuserid) {
		this.assignuserid = assignuserid;
	}

	public String getAssignuser() {
		return assignuser;
	}

	public void setAssignuser(String assignuser) {
		this.assignuser = assignuser;
	}

	public Date getAssigntime() {
		return assigntime;
	}

	public void setAssigntime(Date assigntime) {
		this.assigntime = assigntime;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public Integer getTheoryid() {
		return theoryid;
	}

	public void setTheoryid(Integer theoryid) {
		this.theoryid = theoryid;
	}

	private static final long serialVersionUID = 1L;

	public String getApplyexamname() {
		return applyexamname;
	}

	public BigDecimal getSignmoney() {
		return signmoney;
	}

	public void setSignmoney(BigDecimal signmoney) {
		this.signmoney = signmoney;
	}

	public void setApplyexamname(String applyexamname) {
		this.applyexamname = applyexamname;
	}

	public Integer getLearnstatus() {
		return learnstatus;
	}

	public void setLearnstatus(Integer learnstatus) {
		this.learnstatus = learnstatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStunum() {
		return stunum;
	}

	public void setStunum(String stunum) {
		this.stunum = stunum == null ? null : stunum.trim();
	}

	public String getInscode() {
		return inscode;
	}

	public void setInscode(String inscode) {
		this.inscode = inscode == null ? null : inscode.trim();
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype == null ? null : cardtype.trim();
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getResidence() {
		return residence;
	}

	public void setResidence(Integer residence) {
		this.residence = residence;
	}

	public Date getResidencedate() {
		return residencedate;
	}

	public void setResidencedate(Date residencedate) {
		this.residencedate = residencedate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getPhoto() {
		return photo;
	}

	public void setPhoto(Integer photo) {
		this.photo = photo;
	}

	public String getPhotoback() {
		return photoback;
	}

	public void setPhotoback(String photoback) {
		this.photoback = photoback == null ? null : photoback.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(Integer fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype == null ? null : busitype.trim();
	}

	public String getDrilicnum() {
		return drilicnum;
	}

	public void setDrilicnum(String drilicnum) {
		this.drilicnum = drilicnum == null ? null : drilicnum.trim();
	}

	public String getPerdritype() {
		return perdritype;
	}

	public void setPerdritype(String perdritype) {
		this.perdritype = perdritype == null ? null : perdritype.trim();
	}

	public Date getFstdrilicdate() {
		return fstdrilicdate;
	}

	public void setFstdrilicdate(Date fstdrilicdate) {
		this.fstdrilicdate = fstdrilicdate;
	}

	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype == null ? null : traintype.trim();
	}

	public Date getApplydate() {
		return applydate;
	}

	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}

	public Integer getCompletetime() {
		return completetime;
	}

	public void setCompletetime(Integer completetime) {
		this.completetime = completetime;
	}

	public Integer getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(Integer recordtype) {
		this.recordtype = recordtype;
	}

	public Integer getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}

	public Integer getApplytype() {
		return applytype;
	}

	public void setApplytype(Integer applytype) {
		this.applytype = applytype;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract == null ? null : contract.trim();
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Date getLtime() {
		return ltime;
	}

	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
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

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl == null ? null : photourl.trim();
	}

	public String getFingerprinturl() {
		return fingerprinturl;
	}

	public void setFingerprinturl(String fingerprinturl) {
		this.fingerprinturl = fingerprinturl == null ? null : fingerprinturl.trim();
	}

	public String getTrainmethod() {
		return trainmethod;
	}

	public void setTrainmethod(String trainmethod) {
		this.trainmethod = trainmethod == null ? null : trainmethod.trim();
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getPayremark() {
		return payremark;
	}

	public void setPayremark(String payremark) {
		this.payremark = payremark == null ? null : payremark.trim();
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public Integer getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
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

	public Date getChacktime() {
		return chacktime;
	}

	public void setChacktime(Date chacktime) {
		this.chacktime = chacktime;
	}

	public Integer getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}

	public Integer getBussource() {
		return bussource;
	}

	public void setBussource(Integer bussource) {
		this.bussource = bussource;
	}

	public Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(Integer isinternal) {
		this.isinternal = isinternal;
	}

	public Integer getInfosource() {
		return infosource;
	}

	public void setInfosource(Integer infosource) {
		this.infosource = infosource;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getBillname() {
		return billname;
	}

	public void setBillname(String billname) {
		this.billname = billname == null ? null : billname.trim();
	}

	public String getBillnum() {
		return billnum;
	}

	public void setBillnum(String billnum) {
		this.billnum = billnum == null ? null : billnum.trim();
	}

	public Integer getBilltype() {
		return billtype;
	}

	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	public String getPosnum() {
		return posnum;
	}

	public void setPosnum(String posnum) {
		this.posnum = posnum == null ? null : posnum.trim();
	}

	public Integer getXid() {
		return xid;
	}

	public void setXid(Integer xid) {
		this.xid = xid;
	}

	public Date getCarddate() {
		return carddate;
	}

	public void setCarddate(Date carddate) {
		this.carddate = carddate;
	}

	public String getRecname() {
		return recname;
	}

	public void setRecname(String recname) {
		this.recname = recname == null ? null : recname.trim();
	}

	public String getFlownum() {
		return flownum;
	}

	public void setFlownum(String flownum) {
		this.flownum = flownum;
	}

	public Integer getIslocal() {
		return islocal;
	}

	public void setIslocal(Integer islocal) {
		this.islocal = islocal;
	}

	public Date getStabletime() {
		return stabletime;
	}

	public void setStabletime(Date stabletime) {
		this.stabletime = stabletime;
	}

	public Date getEtabletime() {
		return etabletime;
	}

	public void setEtabletime(Date etabletime) {
		this.etabletime = etabletime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getReccoachid() {
		return reccoachid;
	}

	public void setReccoachid(Integer reccoachid) {
		this.reccoachid = reccoachid;
	}

	public String getIccard() {
		return iccard;
	}

	public void setIccard(String iccard) {
		this.iccard = iccard;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getLearncard() {
		return learncard;
	}

	public void setLearncard(String learncard) {
		this.learncard = learncard;
	}

	public BigDecimal getContractmoney() {
		return contractmoney;
	}

	public void setContractmoney(BigDecimal contractmoney) {
		this.contractmoney = contractmoney;
	}

	public BigDecimal getCashmoney() {
		return cashmoney;
	}

	public void setCashmoney(BigDecimal cashmoney) {
		this.cashmoney = cashmoney;
	}

	public BigDecimal getPosmoney() {
		return posmoney;
	}

	public void setPosmoney(BigDecimal posmoney) {
		this.posmoney = posmoney;
	}

	public BigDecimal getSubmoney() {
		return submoney;
	}

	public void setSubmoney(BigDecimal submoney) {
		this.submoney = submoney;
	}

	public String getCouponnum() {
		return couponnum;
	}

	public void setCouponnum(String couponnum) {
		this.couponnum = couponnum;
	}

	public BigDecimal getCouponmoney() {
		return couponmoney;
	}

	public void setCouponmoney(BigDecimal couponmoney) {
		this.couponmoney = couponmoney;
	}

	public Integer getRecuid() {
		return recuid;
	}

	public void setRecuid(Integer recuid) {
		this.recuid = recuid;
	}

	public Integer getPosid() {
		return posid;
	}

	public void setPosid(Integer posid) {
		this.posid = posid;
	}

	public Integer getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(Integer applystatus) {
		this.applystatus = applystatus;
	}
}