package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CarInsurance extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6189632877594416669L;

	private Integer id;

    private Integer carId;
    @Excel(name="车牌号")
    private String carNo;
    
    @Excel(name="负责人")
    private String cname;
    
    private String carOwner;

    private String type;

    private Integer nature;

    private String paid;
    @Excel(name="险种")
    private String paidName;

    private Date busrisksDate;
    
    private String busriskStartDate;//商业险投保开始日期 仅做查询参数
    
    private String busriskEndDate;//商业险投保结束日期 仅做查询参数
    @Excel(name="商业保单号")
    private String busrisksNo;
    @Excel(name="商业险保费")
    private Integer busrisksFee;

    private Integer busrisksRates;

    private Integer busrisksRfee;

    private Integer busrisksPay;

    
    private Date saliDate;
    
    private String saliStartDate;//交强险投保开始日期 仅做查询参数
    
    private String sailEndDate;//交强险投保结束日期 仅做查询参数

    private String saliNo;

    private Integer saliFee;

    private Integer saliRates;

    private Integer saliRfee;

    private Integer saliPay;

    private Date saliStime;

    private Date saliEtime;

    private Integer taxFee;

    private Integer insureCompanyid;
    @Excel(name="保险公司")
    private String insureCompany;
    
    @Excel(name="开始日期")
    private Date busrisksStime;
    @Excel(name="结束日期")
    private Date busrisksEtime;

    private Integer insurePay;

    private String remark;

    private Date accountDate;
    
    private String accountStartDate;//报账开始日期  仅做查询参数
    
    private String accountEndDate;//报账结束日期 仅做查询参数

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid == null ? null : paid.trim();
    }

    public String getPaidName() {
        return paidName;
    }

    public void setPaidName(String paidName) {
        this.paidName = paidName == null ? null : paidName.trim();
    }

    public Date getBusrisksDate() {
        return busrisksDate;
    }

    public void setBusrisksDate(Date busrisksDate) {
        this.busrisksDate = busrisksDate;
    }

    public String getBusrisksNo() {
        return busrisksNo;
    }

    public void setBusrisksNo(String busrisksNo) {
        this.busrisksNo = busrisksNo == null ? null : busrisksNo.trim();
    }

    public Integer getBusrisksFee() {
        return busrisksFee;
    }

    public void setBusrisksFee(Integer busrisksFee) {
        this.busrisksFee = busrisksFee;
    }

    public Integer getBusrisksRates() {
        return busrisksRates;
    }

    public void setBusrisksRates(Integer busrisksRates) {
        this.busrisksRates = busrisksRates;
    }

    public Integer getBusrisksRfee() {
        return busrisksRfee;
    }

    public void setBusrisksRfee(Integer busrisksRfee) {
        this.busrisksRfee = busrisksRfee;
    }

    public Integer getBusrisksPay() {
        return busrisksPay;
    }

    public void setBusrisksPay(Integer busrisksPay) {
        this.busrisksPay = busrisksPay;
    }

    public Date getBusrisksStime() {
        return busrisksStime;
    }

    public void setBusrisksStime(Date busrisksStime) {
        this.busrisksStime = busrisksStime;
    }

    public Date getBusrisksEtime() {
        return busrisksEtime;
    }

    public void setBusrisksEtime(Date busrisksEtime) {
        this.busrisksEtime = busrisksEtime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getSaliDate() {
        return saliDate;
    }

    public void setSaliDate(Date saliDate) {
        this.saliDate = saliDate;
    }

    public String getSaliNo() {
        return saliNo;
    }

    public void setSaliNo(String saliNo) {
        this.saliNo = saliNo == null ? null : saliNo.trim();
    }

    public Integer getSaliFee() {
        return saliFee;
    }

    public void setSaliFee(Integer saliFee) {
        this.saliFee = saliFee;
    }

    public Integer getSaliRates() {
        return saliRates;
    }

    public void setSaliRates(Integer saliRates) {
        this.saliRates = saliRates;
    }

    public Integer getSaliRfee() {
        return saliRfee;
    }

    public void setSaliRfee(Integer saliRfee) {
        this.saliRfee = saliRfee;
    }

    public Integer getSaliPay() {
        return saliPay;
    }

    public void setSaliPay(Integer saliPay) {
        this.saliPay = saliPay;
    }

    public Date getSaliStime() {
        return saliStime;
    }

    public void setSaliStime(Date saliStime) {
        this.saliStime = saliStime;
    }

    public Date getSaliEtime() {
        return saliEtime;
    }

    public void setSaliEtime(Date saliEtime) {
        this.saliEtime = saliEtime;
    }

    public Integer getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(Integer taxFee) {
        this.taxFee = taxFee;
    }

    public Integer getInsureCompanyid() {
        return insureCompanyid;
    }

    public void setInsureCompanyid(Integer insureCompanyid) {
        this.insureCompanyid = insureCompanyid;
    }

    public String getInsureCompany() {
        return insureCompany;
    }

    public void setInsureCompany(String insureCompany) {
        this.insureCompany = insureCompany == null ? null : insureCompany.trim();
    }

    public Integer getInsurePay() {
        return insurePay;
    }

    public void setInsurePay(Integer insurePay) {
        this.insurePay = insurePay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getBusriskStartDate() {
		return busriskStartDate;
	}

	public void setBusriskStartDate(String busriskStartDate) {
		this.busriskStartDate = busriskStartDate;
	}

	public String getBusriskEndDate() {
		return busriskEndDate;
	}

	public void setBusriskEndDate(String busriskEndDate) {
		this.busriskEndDate = busriskEndDate;
	}

	public String getSaliStartDate() {
		return saliStartDate;
	}

	public void setSaliStartDate(String saliStartDate) {
		this.saliStartDate = saliStartDate;
	}

	public String getSailEndDate() {
		return sailEndDate;
	}

	public void setSailEndDate(String sailEndDate) {
		this.sailEndDate = sailEndDate;
	}

	public String getAccountStartDate() {
		return accountStartDate;
	}

	public void setAccountStartDate(String accountStartDate) {
		this.accountStartDate = accountStartDate;
	}

	public String getAccountEndDate() {
		return accountEndDate;
	}

	public void setAccountEndDate(String accountEndDate) {
		this.accountEndDate = accountEndDate;
	}
	
	
	
	
    
}