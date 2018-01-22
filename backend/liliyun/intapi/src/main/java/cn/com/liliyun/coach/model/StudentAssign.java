package cn.com.liliyun.coach.model;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class StudentAssign extends BaseModel{

	private Integer areaid;
	
	private Integer storeid;
	
	private Integer teachtypeid;
	
	private Integer assigntype;
	
	private Date begindate;
	
	private Date enddate;
	
	
	private int coachid;
	
	@Excel(name="片区")
	private String area;
	@Excel(name="门店")
	private String store;
	@Excel(name="带教类型")
	private String teachtype;
	@Excel(name="教练")
	private String name;
	@Excel(name="学员数量")
	private int count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public Integer getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(Integer teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public Integer getAssigntype() {
		return assigntype;
	}

	public void setAssigntype(Integer assigntype) {
		this.assigntype = assigntype;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
}
