package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceSubcharge extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer areaid;

    private Integer storeid;

    @Excel(name="代收类别", replace={"代收陪驾费_1","代收学员费用_2"})
    private Byte chargetype;

    @Excel(name="代收人")
    private String charger;

    @Excel(name="代收金额")
    private BigDecimal total;

    @Excel(name="代收日期", exportFormat="yyyy-MM-dd")
    private Date chargedate;

    @Excel(name="现金金额")
    private BigDecimal cashmoney;

    @Excel(name="刷卡金额")
    private BigDecimal cardmoney;

    private Integer posid;

    @Excel(name="Pos机号")
    private String posnum;

    @Excel(name="刷卡银行")
    private String bankname;

    private Integer studentid;

    @Excel(name="学员姓名")
    private String name;

    @Excel(name="身份证号")
    private String idcard;

    private String classinfo;

    private String traintype;

    private BigDecimal classhour;

    private Byte receipttype;

    private String receiptname;

    private String remark;

    private Byte isdel;

    @Excel(name="录入时间", exportFormat="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private Integer cuid;

    @Excel(name="录入人")
    private String cname;

    private Date mtime;

    private Integer muid;

    private String mname;
    
    //导出字段
    @Excel(name="片区")
    private String areastr;
    
    @Excel(name="门店")
    private String storestr;
    
    //筛选字段
    private Date chargedatelow;
    
    private Date chargedatetop;

	public Date getChargedatelow() {
		return chargedatelow;
	}

	public void setChargedatelow(Date chargedatelow) {
		this.chargedatelow = chargedatelow;
	}

	public Date getChargedatetop() {
		return chargedatetop;
	}

	public void setChargedatetop(Date chargedatetop) {
		this.chargedatetop = chargedatetop;
	}

	public String getAreastr() {
		return areastr;
	}

	public void setAreastr(String areastr) {
		this.areastr = areastr;
	}

	public String getStorestr() {
		return storestr;
	}

	public void setStorestr(String storestr) {
		this.storestr = storestr;
	}

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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Byte getChargetype() {
        return chargetype;
    }

    public void setChargetype(Byte chargetype) {
        this.chargetype = chargetype;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger == null ? null : charger.trim();
    }

    public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getChargedate() {
        return chargedate;
    }

    public void setChargedate(Date chargedate) {
        this.chargedate = chargedate;
    }

    public BigDecimal getCashmoney() {
        return cashmoney;
    }

    public void setCashmoney(BigDecimal cashmoney) {
        this.cashmoney = cashmoney;
    }

    public BigDecimal getCardmoney() {
        return cardmoney;
    }

    public void setCardmoney(BigDecimal cardmoney) {
        this.cardmoney = cardmoney;
    }

    public Integer getPosid() {
        return posid;
    }

    public void setPosid(Integer posid) {
        this.posid = posid;
    }

    public String getPosnum() {
        return posnum;
    }

    public void setPosnum(String posnum) {
        this.posnum = posnum == null ? null : posnum.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getClassinfo() {
        return classinfo;
    }

    public void setClassinfo(String classinfo) {
        this.classinfo = classinfo == null ? null : classinfo.trim();
    }

    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype == null ? null : traintype.trim();
    }

    public BigDecimal getClasshour() {
        return classhour;
    }

    public void setClasshour(BigDecimal classhour) {
        this.classhour = classhour;
    }

    public Byte getReceipttype() {
        return receipttype;
    }

    public void setReceipttype(Byte receipttype) {
        this.receipttype = receipttype;
    }

    public String getReceiptname() {
        return receiptname;
    }

    public void setReceiptname(String receiptname) {
        this.receiptname = receiptname == null ? null : receiptname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
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

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }
}