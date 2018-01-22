package cn.com.liliyun.car.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CarAccident extends BaseModel {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer carid;
    @Excel(name="车牌号")
    private String carno;
    
    private Integer brandid;
    
    private Integer areaid;
    @Excel(name="片区")
    private String area;
    
    private Integer storeid;
    @Excel(name="事故编号")
    private String accidentnum;
    
    private Date accidentdate;
    @Excel(name="事故日期")
    private String accidentdatestr;
    
    private Byte accidenttype;
    @Excel(name="类型")
    private String accidenttypestr;

    private String accidentaddr;

    private String caruser;
    
    private Byte liabletype;
    @Excel(name="责任")
    private String liabletypestr;

    private String personliable;

    private Integer personliableid;

    private String thirdpersonliable;

    private BigDecimal medicalcost;

    private BigDecimal repaircost;

    private BigDecimal othercost;

    private BigDecimal financecost;

    @Excel(name="总损失")
    private BigDecimal totalcost;

    @Excel(name="保险总金额")
    private BigDecimal insuranceothercost;

    private BigDecimal thirdcarcost;

    private BigDecimal insuranceassesscost;

    private BigDecimal repairinsuranceassesscost;

    private BigDecimal insurancetotalcost;

    private BigDecimal submitinsurancetotalcost;

    private Date submitclaiminfodate;

    private BigDecimal claimtotal;

    private Date claimdate;

    @Excel(name="第三者赔款")
    private BigDecimal thirdindemnity;

    @Excel(name="个人扣款")
    private BigDecimal personalindemnity;

    private BigDecimal returnfinancecost;

    private Byte handlestate;

    private Date handledate;

    private String reamrk;

    private String images;

    private Integer cuid;

    private Date ctime;

    private Integer muid;

    private Date mtime;
    
    //筛选字段，事故日期
    private Date accidentdatetop; 
    
    private Date accidentdatelow;

    public String getAccidentdatestr() {
		return accidentdatestr;
	}

	public void setAccidentdatestr(String accidentdatestr) {
		this.accidentdatestr = accidentdatestr;
	}

	public String getLiabletypestr() {
		return liabletypestr;
	}

	public void setLiabletypestr(String liabletypestr) {
		this.liabletypestr = liabletypestr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAccidenttypestr() {
		return accidenttypestr;
	}

	public void setAccidenttypestr(String accidenttypestr) {
		this.accidenttypestr = accidenttypestr;
	}

	

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
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

	public Date getAccidentdatetop() {
		return accidentdatetop;
	}

	public void setAccidentdatetop(Date accidentdatetop) {
		this.accidentdatetop = accidentdatetop;
	}

	public Date getAccidentdatelow() {
		return accidentdatelow;
	}

	public void setAccidentdatelow(Date accidentdatelow) {
		this.accidentdatelow = accidentdatelow;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno == null ? null : carno.trim();
    }

    public String getAccidentnum() {
        return accidentnum;
    }

    public void setAccidentnum(String accidentnum) {
        this.accidentnum = accidentnum == null ? null : accidentnum.trim();
    }

    public Byte getAccidenttype() {
        return accidenttype;
    }

    public void setAccidenttype(Byte accidenttype) {
        this.accidenttype = accidenttype;
    }

    public Date getAccidentdate() {
        return accidentdate;
    }

    public void setAccidentdate(Date accidentdate) {
        this.accidentdate = accidentdate;
    }

    public String getAccidentaddr() {
        return accidentaddr;
    }

    public void setAccidentaddr(String accidentaddr) {
        this.accidentaddr = accidentaddr == null ? null : accidentaddr.trim();
    }

    public String getCaruser() {
        return caruser;
    }

    public void setCaruser(String caruser) {
        this.caruser = caruser == null ? null : caruser.trim();
    }

    public Byte getLiabletype() {
        return liabletype;
    }

    public void setLiabletype(Byte liabletype) {
        this.liabletype = liabletype;
    }

    public String getPersonliable() {
        return personliable;
    }

    public void setPersonliable(String personliable) {
        this.personliable = personliable == null ? null : personliable.trim();
    }

    public Integer getPersonliableid() {
        return personliableid;
    }

    public void setPersonliableid(Integer personliableid) {
        this.personliableid = personliableid;
    }

    public String getThirdpersonliable() {
        return thirdpersonliable;
    }

    public void setThirdpersonliable(String thirdpersonliable) {
        this.thirdpersonliable = thirdpersonliable == null ? null : thirdpersonliable.trim();
    }

    public BigDecimal getMedicalcost() {
        return medicalcost;
    }

    public void setMedicalcost(BigDecimal medicalcost) {
        this.medicalcost = medicalcost;
    }

    public BigDecimal getRepaircost() {
        return repaircost;
    }

    public void setRepaircost(BigDecimal repaircost) {
        this.repaircost = repaircost;
    }

    public BigDecimal getOthercost() {
        return othercost;
    }

    public void setOthercost(BigDecimal othercost) {
        this.othercost = othercost;
    }

    public BigDecimal getFinancecost() {
        return financecost;
    }

    public void setFinancecost(BigDecimal financecost) {
        this.financecost = financecost;
    }

    public BigDecimal getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }

    public BigDecimal getInsuranceothercost() {
        return insuranceothercost;
    }

    public void setInsuranceothercost(BigDecimal insuranceothercost) {
        this.insuranceothercost = insuranceothercost;
    }

    public BigDecimal getThirdcarcost() {
        return thirdcarcost;
    }

    public void setThirdcarcost(BigDecimal thirdcarcost) {
        this.thirdcarcost = thirdcarcost;
    }

    public BigDecimal getInsuranceassesscost() {
        return insuranceassesscost;
    }

    public void setInsuranceassesscost(BigDecimal insuranceassesscost) {
        this.insuranceassesscost = insuranceassesscost;
    }

    public BigDecimal getRepairinsuranceassesscost() {
        return repairinsuranceassesscost;
    }

    public void setRepairinsuranceassesscost(BigDecimal repairinsuranceassesscost) {
        this.repairinsuranceassesscost = repairinsuranceassesscost;
    }

    public BigDecimal getInsurancetotalcost() {
        return insurancetotalcost;
    }

    public void setInsurancetotalcost(BigDecimal insurancetotalcost) {
        this.insurancetotalcost = insurancetotalcost;
    }

    public BigDecimal getSubmitinsurancetotalcost() {
        return submitinsurancetotalcost;
    }

    public void setSubmitinsurancetotalcost(BigDecimal submitinsurancetotalcost) {
        this.submitinsurancetotalcost = submitinsurancetotalcost;
    }

    public Date getSubmitclaiminfodate() {
        return submitclaiminfodate;
    }

    public void setSubmitclaiminfodate(Date submitclaiminfodate) {
        this.submitclaiminfodate = submitclaiminfodate;
    }

    public BigDecimal getClaimtotal() {
        return claimtotal;
    }

    public void setClaimtotal(BigDecimal claimtotal) {
        this.claimtotal = claimtotal;
    }

    public Date getClaimdate() {
        return claimdate;
    }

    public void setClaimdate(Date claimdate) {
        this.claimdate = claimdate;
    }

    public BigDecimal getThirdindemnity() {
        return thirdindemnity;
    }

    public void setThirdindemnity(BigDecimal thirdindemnity) {
        this.thirdindemnity = thirdindemnity;
    }

    public BigDecimal getPersonalindemnity() {
        return personalindemnity;
    }

    public void setPersonalindemnity(BigDecimal personalindemnity) {
        this.personalindemnity = personalindemnity;
    }

    public BigDecimal getReturnfinancecost() {
        return returnfinancecost;
    }

    public void setReturnfinancecost(BigDecimal returnfinancecost) {
        this.returnfinancecost = returnfinancecost;
    }

    public Byte getHandlestate() {
        return handlestate;
    }

    public void setHandlestate(Byte handlestate) {
        this.handlestate = handlestate;
    }

    public Date getHandledate() {
        return handledate;
    }

    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    public String getReamrk() {
        return reamrk;
    }

    public void setReamrk(String reamrk) {
        this.reamrk = reamrk == null ? null : reamrk.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}