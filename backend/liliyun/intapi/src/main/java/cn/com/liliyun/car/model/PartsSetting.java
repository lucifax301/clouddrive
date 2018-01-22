package cn.com.liliyun.car.model;

import java.util.Calendar;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class PartsSetting extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer partstype;

    private Integer month;

    private Integer mileage;
    
    private Date monthdate;

    public Date getMonthdate() {
		return monthdate;
	}

	public void setMonthdate(Date monthdate) {
		this.monthdate = monthdate;
	}

	public Integer getPartstype() {
        return partstype;
    }

    public void setPartstype(Integer partstype) {
        this.partstype = partstype;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
        if (month != null) {
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(new Date());
        	calendar.add(Calendar.MONTH, -month);
        	calendar.set(Calendar.HOUR_OF_DAY, 0);
        	calendar.set(Calendar.MINUTE, 0);
        	calendar.set(Calendar.SECOND, 0);
        	calendar.set(Calendar.MILLISECOND, 0);
        	monthdate = calendar.getTime();
        } else {
        	monthdate = null;
        }
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}