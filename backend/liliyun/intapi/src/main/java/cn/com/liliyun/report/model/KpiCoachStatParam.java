package cn.com.liliyun.report.model;

import java.io.Serializable;

/**
 * 
 * @author devil
 *
 */
public class KpiCoachStatParam extends KpiAreaStatParam implements Serializable{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
