package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

public class CarMileage extends BaseModel implements Serializable {
    private Integer id;

    private Integer areaid;

    private Integer carid;

    private String carno;

    private Integer lastmile;

    private Integer thismile;

    private String name;

    private Integer actualmile;

    private Date fetchtime;

    private String remark;

    private Integer cuid;

    private String cname;

    private Date ctime;
    
    private Date stime;
    
    private Date etime;
    
    private Date fstime;
    
    private Date fetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
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

    public Integer getLastmile() {
        return lastmile;
    }

    public void setLastmile(Integer lastmile) {
        this.lastmile = lastmile;
    }

    public Integer getThismile() {
        return thismile;
    }

    public void setThismile(Integer thismile) {
        this.thismile = thismile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getActualmile() {
        return actualmile;
    }

    public void setActualmile(Integer actualmile) {
        this.actualmile = actualmile;
    }

    public Date getFetchtime() {
        return fetchtime;
    }

    public void setFetchtime(Date fetchtime) {
        this.fetchtime = fetchtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
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

	public Date getFstime() {
		return fstime;
	}

	public void setFstime(Date fstime) {
		this.fstime = fstime;
	}

	public Date getFetime() {
		return fetime;
	}

	public void setFetime(Date fetime) {
		this.fetime = fetime;
	}
    
}