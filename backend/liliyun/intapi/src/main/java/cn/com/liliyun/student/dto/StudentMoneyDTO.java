package cn.com.liliyun.student.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class StudentMoneyDTO extends BaseModel implements Serializable {
    private Integer studentid;
    
    private String name;
    
    private String idcard;
    
    private Date applydate;

    private Integer storeid;
    
    private Integer areaid;

    private BigDecimal money;

    private BigDecimal contractmoney;

    private BigDecimal paymoney;

    private BigDecimal submoney;

    private BigDecimal owesubmoney;

    private BigDecimal oweresitmoney;

    private BigDecimal owetrainmoney;

   private BigDecimal owemoney;

    private Integer owestatus;

    private Integer returnstatus;
    
    private Integer type;
    
    private Date stime;
    
    private Date etime;

    private static final long serialVersionUID = 1L;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setContractmoney(BigDecimal contractmoney) {
        this.contractmoney = contractmoney;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public BigDecimal getSubmoney() {
        return submoney;
    }

    public void setSubmoney(BigDecimal submoney) {
        this.submoney = submoney;
    }

    public BigDecimal getOwesubmoney() {
        return owesubmoney;
    }

    public void setOwesubmoney(BigDecimal owesubmoney) {
        this.owesubmoney = owesubmoney;
    }

    public BigDecimal getOweresitmoney() {
        return oweresitmoney;
    }

    public void setOweresitmoney(BigDecimal oweresitmoney) {
        this.oweresitmoney = oweresitmoney;
    }

    public BigDecimal getOwemoney() {
        return owemoney;
    }

    public void setOwemoney(BigDecimal owemoney) {
        this.owemoney = owemoney;
    }

    public Integer getOwestatus() {
        return owestatus;
    }

    public void setOwestatus(Integer owestatus) {
        this.owestatus = owestatus;
    }

    public Integer getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(Integer returnstatus) {
        this.returnstatus = returnstatus;
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

	public Date getApplydate() {
		return applydate;
	}

	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

    public BigDecimal getContractmoney() {
        return contractmoney;
    }

    public BigDecimal getOwetrainmoney() {
        return owetrainmoney;
    }

    public void setOwetrainmoney(BigDecimal owetrainmoney) {
        this.owetrainmoney = owetrainmoney;
    }
}