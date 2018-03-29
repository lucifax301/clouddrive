package cn.com.liliyun.car.model;


import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CarAnnual extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8785417817191326980L;

	private Integer id;

    private Integer carId;

    private Date annualIndate;

    private Integer annualCost;

    private Date reimDate;

    private Integer ygSign;

    private Integer ygCost;

    private Integer detectAddress;

    private Date rohsDate;

    private Integer detectCost;

    private Date dcostDate;

    private String cname;

    private Date assertDate;

    private String remark;

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

    public Date getAnnualIndate() {
        return annualIndate;
    }

    public void setAnnualIndate(Date annualIndate) {
        this.annualIndate = annualIndate;
    }

    public Integer getAnnualCost() {
        return annualCost;
    }

    public void setAnnualCost(Integer annualCost) {
        this.annualCost = annualCost;
    }

    public Date getReimDate() {
        return reimDate;
    }

    public void setReimDate(Date reimDate) {
        this.reimDate = reimDate;
    }

    public Integer getYgSign() {
        return ygSign;
    }

    public void setYgSign(Integer ygSign) {
        this.ygSign = ygSign;
    }

    public Integer getYgCost() {
        return ygCost;
    }

    public void setYgCost(Integer ygCost) {
        this.ygCost = ygCost;
    }

    public Integer getDetectAddress() {
        return detectAddress;
    }

    public void setDetectAddress(Integer detectAddress) {
        this.detectAddress = detectAddress;
    }

    public Date getRohsDate() {
        return rohsDate;
    }

    public void setRohsDate(Date rohsDate) {
        this.rohsDate = rohsDate;
    }

    public Integer getDetectCost() {
        return detectCost;
    }

    public void setDetectCost(Integer detectCost) {
        this.detectCost = detectCost;
    }

    public Date getDcostDate() {
        return dcostDate;
    }

    public void setDcostDate(Date dcostDate) {
        this.dcostDate = dcostDate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

        public Date getAssertDate() {
        return assertDate;
    }

    public void setAssertDate(Date assertDate) {
        this.assertDate = assertDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}