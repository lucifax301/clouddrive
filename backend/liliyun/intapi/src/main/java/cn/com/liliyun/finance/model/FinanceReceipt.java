package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceReceipt extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer areaid;

    private Integer storeid;

    private Integer studentid;

    @Excel(name = "学员姓名")
    private String stuname;

    @Excel(name = "身份证号")
    private String stuidcard;

    @Excel(name = "班别")
    private String classinfo;

    @Excel(name = "车型")
    private String traintype;

    private Date signupdate;

    @Excel(name = "实际报名费用")
    private BigDecimal signupcost;

    private Integer type;

    @Excel(name = "收据编号")
    private String receiptnum;

    @Excel(name = "收据日期", exportFormat = "yyyy-MM-dd")
    private Date receiptdate;

    @Excel(name = "收据金额")
    private BigDecimal receiptmoney;

    private BigDecimal cashmoney;

    private Integer posid;

    private String posnum;
    
    private String poscompany;

    private BigDecimal posmoney;

    private Byte invoicetype;

    @Excel(name = "发票名")
    private String invoicename;

    @Excel(name = "发票号")
    private String invoicenum;

    @Excel(name = "开票金额")
    private BigDecimal invoicemoney;

    @Excel(name = "发票日期")
    private Date invoicedate;
    
    private Byte isinvoice;

    private Byte invoicestate;

    private Integer state1uid;

    private Date state1date;

    private String state1name;

    private Integer state2uid;

    private Date state2date;

    private String state2name;

    private Integer state3uid;

    private Date state3date;

    private String state3name;

    private Integer state4uid;

    private Date state4date;

    private String state4name;

    @Excel(name = "批次号")
    private String batchnum;

    @Excel(name = "修改申请状态", replace = {"未申请_0","已申请未处理_1","已同意未修改_2","已处理未同意_3","已同意已修改_4"})
    private Byte modifystate;

    private Integer applierid;

    private String applier;

    private Date applydate;

    private String applyreason;

    private Integer reviewerid;

    private String reviewer;

    private Date reviewdate;
    
    private String reviewremark;

    @Excel(name = "是否结转", replace = {"是_1", "否_0"})
    private Byte isconfirm;

    @Excel(name = "结转日期", exportFormat = "yyyy-MM-dd")
    private Date confirmdate;

    private Integer confirmuid;

    @Excel(name = "结转人")
    private String confirmname;

    private String remark;

    private Date ctime;

    private Integer cuid;

    private String cname;

    private Date mtime;

    private Integer muid;

    private String mname;
    
    private String businessid;
    
    private String transactionid;
    
    //筛选字段
    private Date receiptdatetop;
    
    private Date receiptdatelow;
    
    //导出字段
    @Excel(name = "片区")
    private String areastr;
    
    @Excel(name = "门店")
    private String storestr;
    
    @Excel(name = "费用类型")
    private String typestr;
    
    //额外字段
    private Boolean updateable;
    
    private String newbatchnum; //设置新批次号
    
    private String posbankname;
    
    private Byte isdiff;
    
	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public Byte getIsdiff() {
		return isdiff;
	}

	public void setIsdiff(Byte isdiff) {
		this.isdiff = isdiff;
	}

	public String getPosbankname() {
		return posbankname;
	}

	public void setPosbankname(String posbankname) {
		this.posbankname = posbankname;
	}

	public String getPoscompany() {
		return poscompany;
	}

	public void setPoscompany(String poscompany) {
		this.poscompany = poscompany;
	}

	public String getNewbatchnum() {
		return newbatchnum;
	}

	public void setNewbatchnum(String newbatchnum) {
		this.newbatchnum = newbatchnum;
	}

	public String getReviewremark() {
		return reviewremark;
	}

	public void setReviewremark(String reviewremark) {
		this.reviewremark = reviewremark;
	}

	public Boolean getUpdateable() {
    	if (updateable != null) {
    		return updateable;
    	}
    	updateable = true;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if (this.ctime == null || this.modifystate == null || (!sdf.format(this.ctime).equals(sdf.format(new Date())) && this.modifystate != 2)) {
    		this.updateable = false;
    	}
    	return updateable;
	}

	public void setUpdateable(Boolean updateable) {
		this.updateable = updateable;
	}

	public String getTypestr() {
		return typestr;
	}

	public void setTypestr(String typestr) {
		this.typestr = typestr;
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

    public Date getReceiptdatetop() {
		return receiptdatetop;
	}

	public void setReceiptdatetop(Date receiptdatetop) {
		this.receiptdatetop = receiptdatetop;
	}

	public Date getReceiptdatelow() {
		return receiptdatelow;
	}

	public void setReceiptdatelow(Date receiptdatelow) {
		this.receiptdatelow = receiptdatelow;
	}

	public Byte getIsinvoice() {
		return isinvoice;
	}

	public void setIsinvoice(Byte isinvoice) {
		this.isinvoice = isinvoice;
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

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public String getStuidcard() {
        return stuidcard;
    }

    public void setStuidcard(String stuidcard) {
        this.stuidcard = stuidcard == null ? null : stuidcard.trim();
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

    public Date getSignupdate() {
        return signupdate;
    }

    public void setSignupdate(Date signupdate) {
        this.signupdate = signupdate;
    }

    public BigDecimal getSignupcost() {
        return signupcost;
    }

    public void setSignupcost(BigDecimal signupcost) {
        this.signupcost = signupcost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReceiptnum() {
        return receiptnum;
    }

    public void setReceiptnum(String receiptnum) {
        this.receiptnum = receiptnum == null ? null : receiptnum.trim();
    }

    public Date getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Date receiptdate) {
        this.receiptdate = receiptdate;
    }

    public BigDecimal getReceiptmoney() {
        return receiptmoney;
    }

    public void setReceiptmoney(BigDecimal receiptmoney) {
        this.receiptmoney = receiptmoney;
    }

    public BigDecimal getCashmoney() {
        return cashmoney;
    }

    public void setCashmoney(BigDecimal cashmoney) {
        this.cashmoney = cashmoney;
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

    public BigDecimal getPosmoney() {
        return posmoney;
    }

    public void setPosmoney(BigDecimal posmoney) {
        this.posmoney = posmoney;
    }

    public Byte getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(Byte invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename == null ? null : invoicename.trim();
    }

    public String getInvoicenum() {
        return invoicenum;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum == null ? null : invoicenum.trim();
    }

    public BigDecimal getInvoicemoney() {
        return invoicemoney;
    }

    public void setInvoicemoney(BigDecimal invoicemoney) {
        this.invoicemoney = invoicemoney;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Byte getInvoicestate() {
        return invoicestate;
    }

    public void setInvoicestate(Byte invoicestate) {
        this.invoicestate = invoicestate;
    }

    public Integer getState1uid() {
        return state1uid;
    }

    public void setState1uid(Integer state1uid) {
        this.state1uid = state1uid;
    }

    public Date getState1date() {
        return state1date;
    }

    public void setState1date(Date state1date) {
        this.state1date = state1date;
    }

    public String getState1name() {
        return state1name;
    }

    public void setState1name(String state1name) {
        this.state1name = state1name == null ? null : state1name.trim();
    }

    public Integer getState2uid() {
        return state2uid;
    }

    public void setState2uid(Integer state2uid) {
        this.state2uid = state2uid;
    }

    public Date getState2date() {
        return state2date;
    }

    public void setState2date(Date state2date) {
        this.state2date = state2date;
    }

    public String getState2name() {
        return state2name;
    }

    public void setState2name(String state2name) {
        this.state2name = state2name == null ? null : state2name.trim();
    }

    public Integer getState3uid() {
        return state3uid;
    }

    public void setState3uid(Integer state3uid) {
        this.state3uid = state3uid;
    }

    public Date getState3date() {
        return state3date;
    }

    public void setState3date(Date state3date) {
        this.state3date = state3date;
    }

    public String getState3name() {
        return state3name;
    }

    public void setState3name(String state3name) {
        this.state3name = state3name == null ? null : state3name.trim();
    }

    public Integer getState4uid() {
        return state4uid;
    }

    public void setState4uid(Integer state4uid) {
        this.state4uid = state4uid;
    }

    public Date getState4date() {
        return state4date;
    }

    public void setState4date(Date state4date) {
        this.state4date = state4date;
    }

    public String getState4name() {
        return state4name;
    }

    public void setState4name(String state4name) {
        this.state4name = state4name == null ? null : state4name.trim();
    }

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum == null ? null : batchnum.trim();
    }

    public Byte getModifystate() {
        return modifystate;
    }

    public void setModifystate(Byte modifystate) {
        this.modifystate = modifystate;
    }

    public Integer getApplierid() {
        return applierid;
    }

    public void setApplierid(Integer applierid) {
        this.applierid = applierid;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier == null ? null : applier.trim();
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public String getApplyreason() {
        return applyreason;
    }

    public void setApplyreason(String applyreason) {
        this.applyreason = applyreason == null ? null : applyreason.trim();
    }

    public Integer getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(Integer reviewerid) {
        this.reviewerid = reviewerid;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Date getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Byte getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(Byte isconfirm) {
        this.isconfirm = isconfirm;
    }

    public Date getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(Date confirmdate) {
        this.confirmdate = confirmdate;
    }

    public Integer getConfirmuid() {
        return confirmuid;
    }

    public void setConfirmuid(Integer confirmuid) {
        this.confirmuid = confirmuid;
    }

    public String getConfirmname() {
        return confirmname;
    }

    public void setConfirmname(String confirmname) {
        this.confirmname = confirmname == null ? null : confirmname.trim();
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