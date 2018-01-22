package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 销售渠道
 * @author devil
 *
 */
public class SalesChannel extends BaseModel implements Serializable{

	private Integer id;
	
	private String channel;
	
	private Integer userid;
	
	private String cuser;
	
	private Integer status;
	/**
	 * 一级渠道id, 为0表示当前为一级渠道
	 */
	private Integer parentid;
	
	private Date createdate;
	
	/**
	 * 1 标识有这属性
	 */
	private Integer coachflag;
	/**
	 * 1 标识有这属性
	 */
	private Integer staffflag;
	
	private List<SalesChannel> data;
	
	

	public Integer getCoachflag() {
		return coachflag;
	}

	public void setCoachflag(Integer coachflag) {
		this.coachflag = coachflag;
	}

	public Integer getStaffflag() {
		return staffflag;
	}

	public void setStaffflag(Integer staffflag) {
		this.staffflag = staffflag;
	}

	public List<SalesChannel> getData() {
		return data;
	}

	public void setData(List<SalesChannel> data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
}
