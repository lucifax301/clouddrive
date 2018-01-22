package cn.com.liliyun.trainorg.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

public class Classinfo extends BaseModel implements Serializable {
    private Integer id;

    private String name;

    private String opentime;

    private Integer type;

    private Integer amount=0;
    
    private Integer c1amount;
    
    private Integer c2amount;
    
    private Integer c1flag;
    
    private Integer c2flag;
    
    private Integer highflag;
    
    private Integer localflag;
    
    private Integer classtypeid;
    
    private Integer c1houramount;
    
    private Integer c2houramount;

//    private Integer singleamount;
//
//    private Integer personnum;
//
//    private Integer gradnum;
//
//    private String passrate;
//
//    private String period;

    private String classdesc;

    private Integer cuid;

    private Integer muid;

    private Date ctime;

    private Date mtime;

    private Integer status;
    
    private String classtype;
    
    

    public Integer getC1houramount() {
		return c1houramount;
	}

	public void setC1houramount(Integer c1houramount) {
		this.c1houramount = c1houramount;
	}

	public Integer getC2houramount() {
		return c2houramount;
	}

	public void setC2houramount(Integer c2houramount) {
		this.c2houramount = c2houramount;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public Integer getC1flag() {
		return c1flag;
	}

	public void setC1flag(Integer c1flag) {
		this.c1flag = c1flag;
	}

	public Integer getC2flag() {
		return c2flag;
	}

	public void setC2flag(Integer c2flag) {
		this.c2flag = c2flag;
	}

	public Integer getC1amount() {
		return c1amount;
	}

	public void setC1amount(Integer c1amount) {
		this.c1amount = c1amount;
	}

	public Integer getC2amount() {
		return c2amount;
	}

	public void setC2amount(Integer c2amount) {
		this.c2amount = c2amount;
	}

	public Integer getClasstypeid() {
		return classtypeid;
	}

	public void setClasstypeid(Integer classtypeid) {
		this.classtypeid = classtypeid;
	}

	private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime == null ? null : opentime.trim();
    }

    

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

//    public Integer getSingleamount() {
//        return singleamount;
//    }
//
//    public void setSingleamount(Integer singleamount) {
//        this.singleamount = singleamount;
//    }
//
//    public Integer getPersonnum() {
//        return personnum;
//    }
//
//    public void setPersonnum(Integer personnum) {
//        this.personnum = personnum;
//    }
//
//    public Integer getGradnum() {
//        return gradnum;
//    }
//
//    public void setGradnum(Integer gradnum) {
//        this.gradnum = gradnum;
//    }
//
//    public String getPassrate() {
//        return passrate;
//    }
//
//    public void setPassrate(String passrate) {
//        this.passrate = passrate == null ? null : passrate.trim();
//    }
//
//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period == null ? null : period.trim();
//    }
    

    public String getClassdesc() {
        return classdesc;
    }

    public Integer getHighflag() {
		return highflag;
	}

	public void setHighflag(Integer highflag) {
		this.highflag = highflag;
	}

	public Integer getLocalflag() {
		return localflag;
	}

	public void setLocalflag(Integer localflag) {
		this.localflag = localflag;
	}

	public void setClassdesc(String classdesc) {
        this.classdesc = classdesc == null ? null : classdesc.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    
}