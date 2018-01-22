package cn.com.liliyun.coach.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class Coach extends BaseModel implements Serializable {
    private Integer coachid;

    private Integer instid;

    private String inscode;

    private String coachnum;
    @Excel(name="教练姓名")
    private String name;

    private String sex;
    @Excel(name="身份证号")
    private String idcard;
    @Excel(name="手机号码")
    private String mobile;

    private String address;

    private String photo;

    private Integer fingerprint;

    private String photo_url;

    private String fingerprint_url;

    private String drilicence;

    private String fstdrilicdate;

    private String occupationno;

    private String occupationlevel;

    private String dripermitted;

    private String teachpermitted;

    @Excel(name="供职状态",replace={"在职_0","离职_1"})
    private String employstatus;

    private String hiredate;

    private String leavedate;

    private Byte operate;

    private Integer errorcode;

    private String message;

    private Date utime;

    private Date ltime;

    private Integer cuid;

    private Integer muid;

    private Date ctime;

    private Date mtime;
    
    //add by devil
    private Integer trainareaid;
    
    private Integer areaid;
    @Excel(name="片区")
    private String area;
    
    private Integer headcoachid;
    
    private Integer teachtypeid;
    @Excel(name="带教类型")
    private String teachtype;
    
    private Integer jobid;
    @Excel(name="带教职务")
    private String job;
    @Excel(name="教练车")
    private String carno;
    @Excel(name="带教车型")
    private String teachcartype;
    @Excel(name="带教状态",replace={"正常分配_1","暂停分配_2"})
    private Integer teachstate;
    
    private Integer studentload;
    @Excel(name="负荷学员")
    private Integer curstudentload;
    
    private Integer mainstoreid;
    
    private String expireDate;
    
    //
    private String teachStartDate;//起教时间
    private String workType;//供职方式
    private String teaching;//教龄
    private String driving;//驾龄
    private String imgeArray;//图片key
    
    //
    private Integer carid;//教练车只是传参用
    private String isbind;//是否绑定了该教练 1已绑定 0未绑定
    
    private Integer xid; //与app端关联id
    
    private String storeids;
    
    private String areaids;
    
    private String step2areaids;
    
    private String step3areaids;
    
    private Integer headcoachflag;
    
    //带教班别
    private Integer classid;
    
    private String coachclassids;
    
    private String coachclasses;
    
    private Integer modapplystat;
    
    //筛选字段，属于某个特定门店
    private Integer storeid;
    
    //筛选字段，分配教练列表使用，不显示当前studentid学员的教练
    private Integer studentid;
    
	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getCoachclassids() {
		return coachclassids;
	}
    
    public Integer getModapplystat() {
		return modapplystat;
	}
	public void setCoachclassids(String coachclassids) {
		this.coachclassids = coachclassids;
	}

	public String getCoachclasses() {
		return coachclasses;
	}

	public void setCoachclasses(String coachclasses) {
		this.coachclasses = coachclasses;
	}

	public void setModapplystat(Integer modapplystat) {
		this.modapplystat = modapplystat;
	}

	public Coach(){
    	
    }
    
    public Coach(Integer coachid){
    	this.coachid=coachid;
    }

	public Integer getHeadcoachflag() {
		return headcoachflag;
	}

	public void setHeadcoachflag(Integer headcoachflag) {
		this.headcoachflag = headcoachflag;
	}

	public String getStoreids() {
		return storeids;
	}

	public void setStoreids(String storeids) {
		this.storeids = storeids;
	}

	public String getAreaids() {
		return areaids;
	}

	public void setAreaids(String areaids) {
		this.areaids = areaids;
	}

	public String getStep2areaids() {
		return step2areaids;
	}

	public void setStep2areaids(String step2areaids) {
		this.step2areaids = step2areaids;
	}

	public String getStep3areaids() {
		return step3areaids;
	}

	public void setStep3areaids(String step3areaids) {
		this.step3areaids = step3areaids;
	}

	public Integer getMainstoreid() {
		return mainstoreid;
	}

	public void setMainstoreid(Integer mainstoreid) {
		this.mainstoreid = mainstoreid;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getIsbind() {
		return isbind;
	}

	public void setIsbind(String isbind) {
		this.isbind = isbind;
	}

	public String getImgeArray() {
		return imgeArray;
	}

	public void setImgeArray(String imgeArray) {
		this.imgeArray = imgeArray;
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public String getTeachStartDate() {
		return teachStartDate;
	}

	public void setTeachStartDate(String teachStartDate) {
		this.teachStartDate = teachStartDate;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getTeaching() {
		return teaching;
	}

	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}

	public String getDriving() {
		return driving;
	}

	public void setDriving(String driving) {
		this.driving = driving;
	}

	private static final long serialVersionUID = 1L;



    public Integer getInstid() {
        return instid;
    }

    public void setInstid(Integer instid) {
        this.instid = instid;
    }

    public String getInscode() {
        return inscode;
    }

    public void setInscode(String inscode) {
        this.inscode = inscode == null ? null : inscode.trim();
    }

    public String getCoachnum() {
        return coachnum;
    }

    public void setCoachnum(String coachnum) {
        this.coachnum = coachnum == null ? null : coachnum.trim();
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
		this.sex = sex;
	}

	public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    

    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Integer fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url == null ? null : photo_url.trim();
    }

    public String getFingerprint_url() {
        return fingerprint_url;
    }

    public void setFingerprint_url(String fingerprint_url) {
        this.fingerprint_url = fingerprint_url == null ? null : fingerprint_url.trim();
    }

    public String getDrilicence() {
        return drilicence;
    }

    public void setDrilicence(String drilicence) {
        this.drilicence = drilicence == null ? null : drilicence.trim();
    }

    public String getFstdrilicdate() {
        return fstdrilicdate;
    }

    public void setFstdrilicdate(String fstdrilicdate) {
        this.fstdrilicdate = fstdrilicdate == null ? null : fstdrilicdate.trim();
    }

    public String getOccupationno() {
        return occupationno;
    }

    public void setOccupationno(String occupationno) {
        this.occupationno = occupationno == null ? null : occupationno.trim();
    }

    public String getOccupationlevel() {
        return occupationlevel;
    }

    public void setOccupationlevel(String occupationlevel) {
        this.occupationlevel = occupationlevel == null ? null : occupationlevel.trim();
    }

    public String getDripermitted() {
        return dripermitted;
    }

    public void setDripermitted(String dripermitted) {
        this.dripermitted = dripermitted == null ? null : dripermitted.trim();
    }

    public String getTeachpermitted() {
        return teachpermitted;
    }

    public void setTeachpermitted(String teachpermitted) {
        this.teachpermitted = teachpermitted == null ? null : teachpermitted.trim();
    }

    public String getEmploystatus() {
        return employstatus;
    }

    public void setEmploystatus(String employstatus) {
        this.employstatus = employstatus == null ? null : employstatus.trim();
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate == null ? null : hiredate.trim();
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate == null ? null : leavedate.trim();
    }

    public Byte getOperate() {
        return operate;
    }

    public void setOperate(Byte operate) {
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

	public Integer getTrainareaid() {
		return trainareaid;
	}

	public void setTrainareaid(Integer trainareaid) {
		this.trainareaid = trainareaid;
	}

	public Integer getXid() {
		return xid;
	}

	public void setXid(Integer xid) {
		this.xid = xid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getHeadcoachid() {
		return headcoachid;
	}

	public void setHeadcoachid(Integer headcoachid) {
		this.headcoachid = headcoachid;
	}

	public Integer getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(Integer teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}

	public String getTeachcartype() {
		return teachcartype;
	}

	public void setTeachcartype(String teachcartype) {
		this.teachcartype = teachcartype;
	}

	public Integer getTeachstate() {
		return teachstate;
	}

	public void setTeachstate(Integer teachstate) {
		this.teachstate = teachstate;
	}

	public Integer getStudentload() {
		return studentload;
	}

	public void setStudentload(Integer studentload) {
		this.studentload = studentload;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public Integer getCurstudentload() {
		return curstudentload;
	}

	public void setCurstudentload(Integer curstudentload) {
		this.curstudentload = curstudentload;
	}
    
	
}