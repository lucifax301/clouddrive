package cn.com.liliyun.car.model;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CarParts extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer carid;

    @Excel(name="车牌号")
    private String carno;

    private Integer areaid;

    private Integer storeid;

    private Integer partstype;

    @Excel(name="更换日期",exportFormat="yyyy-MM-dd")
    private Date handletime;

    @Excel(name="更换时里程")
    private Integer mileage;

    @Excel(name="是否当前记录",replace={"是_1","否_0"})
    private Byte iscurrent;
    
    private Byte islatest;

    private String remark;

    private Date ctime;

    @Excel(name="操作者")
    private String cname;

    private Integer cuid;
    
    //筛选参数
    private Date handletimetop;
    
    private Date handletimelow;

    //导出参数
    @Excel(name="配件名称")
    private String partstypestr;
    
    public Date getHandletimetop() {
		return handletimetop;
	}

	public void setHandletimetop(Date handletimetop) {
		this.handletimetop = handletimetop;
	}

	public Date getHandletimelow() {
		return handletimelow;
	}

	public void setHandletimelow(Date handletimelow) {
		this.handletimelow = handletimelow;
	}

	public String getPartstypestr() {
		return partstypestr;
	}

	public void setPartstypestr(String partstypestr) {
		this.partstypestr = partstypestr;
	}

	public Byte getIslatest() {
		return islatest;
	}

	public void setIslatest(Byte islatest) {
		this.islatest = islatest;
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

    public Integer getPartstype() {
        return partstype;
    }

    public void setPartstype(Integer partstype) {
        this.partstype = partstype;
    }

    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Byte getIscurrent() {
        return iscurrent;
    }

    public void setIscurrent(Byte iscurrent) {
        this.iscurrent = iscurrent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }
}