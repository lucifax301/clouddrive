package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.com.liliyun.common.model.BaseModel;

public class EnrolClassStat extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer storeid;
	
	private String area;
	
	private Map<Integer,Integer> classinfo;
	
	private int enrolindex;
	
	private int enrolsum;
	
	private int returnsum;
	
	private int realenrolsum;
	
	private String enrolrate;
	
	private int avgprice;
	
	private int outavgprice;
	
	/**
	 * 高端班人数
	 */
	private int highcount;
	
	public EnrolClassStat(){
		this.classinfo=new HashMap();
	}
	
	public int getHighcount() {
		return highcount;
	}

	public void setHighcount(int highcount) {
		this.highcount = highcount;
	}

	/**
	 * 高端班占比
	 */
	private String highrate;
	
	/**
	 * 高端班指标
	 */
	private int highindex;
	
	/**
	 * 高端班完成率
	 */
	private String highindexrate;
	/**
	 * c1 人数
	 */
	private int c1;
	/*
	 * c2人数
	 */
	private int c2;
	/**
	 * c2占比
	 */
	private String c2rate;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Map<Integer, Integer> getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Map<Integer, Integer> classinfo) {
		this.classinfo = classinfo;
	}

	public int getEnrolindex() {
		return enrolindex;
	}

	public void setEnrolindex(int enrolindex) {
		this.enrolindex = enrolindex;
	}

	public int getEnrolsum() {
		return enrolsum;
	}

	public void setEnrolsum(int enrolsum) {
		this.enrolsum = enrolsum;
	}

	public int getReturnsum() {
		return returnsum;
	}

	public void setReturnsum(int returnsum) {
		this.returnsum = returnsum;
	}

	public int getRealenrolsum() {
		return realenrolsum;
	}

	public void setRealenrolsum(int realenrolsum) {
		this.realenrolsum = realenrolsum;
	}

	public String getEnrolrate() {
		return enrolrate;
	}

	public void setEnrolrate(String enrolrate) {
		this.enrolrate = enrolrate;
	}

	public int getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(int avgprice) {
		this.avgprice = avgprice;
	}

	public int getOutavgprice() {
		return outavgprice;
	}

	public void setOutavgprice(int outavgprice) {
		this.outavgprice = outavgprice;
	}

	public String getHighrate() {
		return highrate;
	}

	public void setHighrate(String highrate) {
		this.highrate = highrate;
	}

	public int getHighindex() {
		return highindex;
	}

	public void setHighindex(int highindex) {
		this.highindex = highindex;
	}

	public String getHighindexrate() {
		return highindexrate;
	}

	public void setHighindexrate(String highindexrate) {
		this.highindexrate = highindexrate;
	}

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public String getC2rate() {
		return c2rate;
	}

	public void setC2rate(String c2rate) {
		this.c2rate = c2rate;
	}
	
	
}
