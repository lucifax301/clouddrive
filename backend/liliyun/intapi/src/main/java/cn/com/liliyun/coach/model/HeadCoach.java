package cn.com.liliyun.coach.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 教学组长
 * @author devil
 *
 */
public class HeadCoach extends Coach implements Serializable  {

	//private Integer coachid;
	
	
	
	private int overcoach;
	
	private int overcoachcar;
	
	private Date ctime;
	
	private Date mtime;
	
	private int muserid;
	
	private String muser;
	
	

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//		
//	}

	public String getMuser() {
		return muser;
	}

	public void setMuser(String muser) {
		this.muser = muser;
	}

//	public Integer getCoachid() {
//		return coachid;
//	}
//
//	public void setCoachid(Integer coachid) {
//		this.coachid = coachid;
//	}

	public int getOvercoach() {
		return overcoach;
	}

	public void setOvercoach(int overcoach) {
		this.overcoach = overcoach;
	}

	public int getOvercoachcar() {
		return overcoachcar;
	}

	public void setOvercoachcar(int overcoachcar) {
		this.overcoachcar = overcoachcar;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public int getMuserid() {
		return muserid;
	}

	public void setMuserid(int muserid) {
		this.muserid = muserid;
	}
	
	
}
