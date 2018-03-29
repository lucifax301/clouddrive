package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CarTax extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8660337231888889209L;

	private Integer id;

    private Integer carId;

    private Integer tax;

    private String cname;

    
    private Date payDate;

    private Date nextTest;

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

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
    

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getNextTest() {
        return nextTest;
    }

    public void setNextTest(Date nextTest) {
        this.nextTest = nextTest;
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