package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 按教练进行统计
 * @author devil
 *
 */
public class HeadCoachStatParam extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer headcoachid;

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getHeadcoachid() {
		return headcoachid;
	}

	public void setHeadcoachid(Integer headcoachid) {
		this.headcoachid = headcoachid;
	}
	
	
}
