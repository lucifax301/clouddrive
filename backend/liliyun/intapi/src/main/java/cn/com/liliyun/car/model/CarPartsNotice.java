package cn.com.liliyun.car.model;

import java.util.Calendar;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CarPartsNotice extends BaseModel{
	private static final long serialVersionUID = 1L;

    @Excel(name="车牌号")
    private String carno;

    private Integer areaid;

    private Integer partstype;

    @Excel(name="上次更换日期",exportFormat="yyyy-MM-dd")
    private Date handletime;

    private Integer mileage;
    
    private Integer thismile;
    
    @Excel(name="使用时长（月）")
    private Float usetime;
    
    @Excel(name="行驶里程（公里）")
    private Integer drivemileage;
    
    //导出参数
    @Excel(name="配件名称")
    private String partstypestr;
    
    @Excel(name="片区")
    private String areastr;

	public Integer getThismile() {
		return thismile;
	}

	public void setThismile(Integer thismile) {
		this.thismile = thismile;
		setDrivemileage(thismile!=null?thismile-mileage:null);
	}

	public String getAreastr() {
		return areastr;
	}

	public void setAreastr(String areastr) {
		this.areastr = areastr;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getPartstype() {
		return partstype;
	}

	public void setPartstype(Integer partstype) {
		this.partstype = partstype;
	}

	public Date getHandletime() {
		return handletime;
	}

	public void setHandletime(Date handletime) {
		this.handletime = handletime;
		if (handletime != null) {
			Calendar calendar = Calendar.getInstance();
        	calendar.setTime(new Date());
        	calendar.set(Calendar.HOUR_OF_DAY, 0);
        	calendar.set(Calendar.MINUTE, 0);
        	calendar.set(Calendar.SECOND, 0);
        	calendar.set(Calendar.MILLISECOND, 0);
        	Long day = (calendar.getTime().getTime() - handletime.getTime()) / (24 * 60 * 60 * 1000);
        	Float time = (float) Math.ceil((float)day / 3);
        	setUsetime(time / 10);
		} else {
			setUsetime(null);
		}
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Float getUsetime() {
		return usetime;
	}

	public void setUsetime(Float usetime) {
		this.usetime = usetime;
	}

	public Integer getDrivemileage() {
		return drivemileage;
	}

	public void setDrivemileage(Integer drivemileage) {
		this.drivemileage = drivemileage;
	}

	public String getPartstypestr() {
		return partstypestr;
	}

	public void setPartstypestr(String partstypestr) {
		this.partstypestr = partstypestr;
	}
    
}