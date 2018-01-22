package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class EnrolDetailParam extends BaseModel implements Serializable{

	/**
	 * 1 area 2 store
	 */
	private int type;
	
	private Integer areaid;
	
	private Integer channelid;
	
	private Integer showzero;
	
	private Date begindate;
	
	private Date enddate;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public Integer getShowzero() {
		return showzero;
	}

	public void setShowzero(Integer showzero) {
		this.showzero = showzero;
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
