package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CarDetect extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7944033363729785354L;

	private Integer id;

    private Integer carId;

    private Integer detectType;

    private String cname;

    private String detectYear;

    private Date detectDate;

    private Date nextDetect;

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

    public Integer getDetectType() {
        return detectType;
    }

    public void setDetectType(Integer detectType) {
        this.detectType = detectType;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
    

    public String getDetectYear() {
        return detectYear;
    }

    public void setDetectYear(String detectYear) {
        this.detectYear = detectYear;
    }

    public Date getDetectDate() {
        return detectDate;
    }

    public void setDetectDate(Date detectDate) {
        this.detectDate = detectDate;
    }

    public Date getNextDetect() {
        return nextDetect;
    }

    public void setNextDetect(Date nextDetect) {
        this.nextDetect = nextDetect;
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