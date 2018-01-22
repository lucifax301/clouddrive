package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 按片区统计入参
 * @author devil
 *
 */
public class AreaStatParam extends BaseModel implements Serializable{

	private String teachcartype;
	
	private Integer teachtypeid;
	
	private Integer areaid;

	

	public String getTeachcartype() {
		return teachcartype;
	}

	public void setTeachcartype(String teachcartype) {
		this.teachcartype = teachcartype;
	}

	public Integer getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(Integer teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	
	
	
}
