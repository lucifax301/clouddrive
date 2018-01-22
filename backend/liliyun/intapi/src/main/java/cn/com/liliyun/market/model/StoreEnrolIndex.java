package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * @author devil
 * 门店指标
 *
 */
public class StoreEnrolIndex extends AreaEnrolIndex implements Serializable{

	private Integer storeid;

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	public StoreEnrolIndex(){
		
	}
	
	
	
}
