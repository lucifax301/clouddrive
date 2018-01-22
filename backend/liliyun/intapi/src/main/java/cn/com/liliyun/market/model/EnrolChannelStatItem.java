package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class EnrolChannelStatItem extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer channelid;
	
	private Integer count;
	
	private Integer storeid;

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	
}
