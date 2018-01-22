package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;

public class PartsSettingDto extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer tyremonth;

    private Integer tyremileage;

    private Integer oilmonth;

    private Integer oilmileage;
    
    private Integer beltmonth;

    private Integer beltmileage;
    
    private Integer maintainmonth;

    private Integer maintainmileage;
    
    private Integer batterymonth;

	public Integer getTyremonth() {
		return tyremonth;
	}

	public void setTyremonth(Integer tyremonth) {
		this.tyremonth = tyremonth;
	}

	public Integer getTyremileage() {
		return tyremileage;
	}

	public void setTyremileage(Integer tyremileage) {
		this.tyremileage = tyremileage;
	}

	public Integer getOilmonth() {
		return oilmonth;
	}

	public void setOilmonth(Integer oilmonth) {
		this.oilmonth = oilmonth;
	}

	public Integer getOilmileage() {
		return oilmileage;
	}

	public void setOilmileage(Integer oilmileage) {
		this.oilmileage = oilmileage;
	}

	public Integer getBeltmonth() {
		return beltmonth;
	}

	public void setBeltmonth(Integer beltmonth) {
		this.beltmonth = beltmonth;
	}

	public Integer getBeltmileage() {
		return beltmileage;
	}

	public void setBeltmileage(Integer beltmileage) {
		this.beltmileage = beltmileage;
	}

	public Integer getMaintainmonth() {
		return maintainmonth;
	}

	public void setMaintainmonth(Integer maintainmonth) {
		this.maintainmonth = maintainmonth;
	}

	public Integer getMaintainmileage() {
		return maintainmileage;
	}

	public void setMaintainmileage(Integer maintainmileage) {
		this.maintainmileage = maintainmileage;
	}

	public Integer getBatterymonth() {
		return batterymonth;
	}

	public void setBatterymonth(Integer batterymonth) {
		this.batterymonth = batterymonth;
	}

}