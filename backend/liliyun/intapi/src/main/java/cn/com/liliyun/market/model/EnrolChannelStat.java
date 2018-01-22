package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.com.liliyun.common.model.BaseModel;

public class EnrolChannelStat extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer storeid;
	
	private String area;
	
	private Map<Integer,Integer> channelinfo;
	
	private int enrolindex;
	
	private int enrolsum;
	
	private int returnsum;
	
	private int realenrolsum;
	
	private String enrolrate;
	
	private int avgprice;
	
	private int outavgprice;
	
	private String highrate;

	public EnrolChannelStat(){
		this.channelinfo=new HashMap();
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Map<Integer, Integer> getChannelinfo() {
		return channelinfo;
	}

	public void setChannelinfo(Map<Integer, Integer> channelinfo) {
		this.channelinfo = channelinfo;
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
	
	
}
