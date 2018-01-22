package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class AreaEnrolIndex extends BaseModel implements Serializable{

	private Integer id;
	
	private Integer areaid;
	
	
	private Integer year;
	
	private Integer month;
	
	
	
	private Integer highrate;
	
	private List<StoreEnrolIndex> data;
	
	
	private Integer beginyear;
	
	private Integer beginmonth;
	
	private Integer endyear;
	
	private Integer endmonth;
	@Excel(name="片区")
	private String area;
	@Excel(name="时间")
	private String datestr;
	@Excel(name="招生指标")
	private Integer enrolindex;
	
	public String getHighratestr() {
		return highratestr;
	}

	public void setHighratestr(String highratestr) {
		this.highratestr = highratestr;
	}

	@Excel(name="高端班比例")
	private String highratestr;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public Integer getBeginyear() {
		return beginyear;
	}

	public void setBeginyear(Integer beginyear) {
		this.beginyear = beginyear;
	}

	public Integer getBeginmonth() {
		return beginmonth;
	}

	public void setBeginmonth(Integer beginmonth) {
		this.beginmonth = beginmonth;
	}

	public Integer getEndyear() {
		return endyear;
	}

	public void setEndyear(Integer endyear) {
		this.endyear = endyear;
	}

	public Integer getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(Integer endmonth) {
		this.endmonth = endmonth;
	}

	public List<StoreEnrolIndex> getData() {
		return data;
	}

	public void setData(List<StoreEnrolIndex> data) {
		this.data = data;
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

	

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getEnrolindex() {
		return enrolindex;
	}

	public void setEnrolindex(Integer enrolindex) {
		this.enrolindex = enrolindex;
	}

	public Integer getHighrate() {
		return highrate;
	}

	public void setHighrate(Integer highrate) {
		this.highrate = highrate;
	}
	
	
}
