package cn.com.liliyun.finance.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceFee extends BaseModel implements Serializable {
    private String tableid;

    private Integer subject1;
    
    private Integer subject2;

    private Integer applyexam;

    private Integer total;

    private BigDecimal money;

    private BigDecimal totalmoney;

    private BigDecimal paymoney;

    private Integer paytotal;

    private Integer areaid;

    private Integer storeid;

    private Integer cuid;

    private Date ctime;

    private String cname;

    private Integer muid;

    private Date mtime;

    private String mname;
    
    private Date stime;
    
    private Date etime;

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

	private static final long serialVersionUID = 1L;

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid == null ? null : tableid.trim();
    }

    public Integer getSubject1() {
        return subject1;
    }

    public void setSubject1(Integer subject1) {
        this.subject1 = subject1;
    }

    public Integer getSubject2() {
		return subject2;
	}

	public void setSubject2(Integer subject2) {
		this.subject2 = subject2;
	}

	public Integer getApplyexam() {
        return applyexam;
    }

    public void setApplyexam(Integer applyexam) {
        this.applyexam = applyexam;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public Integer getPaytotal() {
        return paytotal;
    }

    public void setPaytotal(Integer paytotal) {
        this.paytotal = paytotal;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
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

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }
}