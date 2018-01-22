package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiClassStat extends BaseModel implements Serializable{

	
	
	private int classid;
	
	private String classname;
	
	private List<KpiClassAreaStat> data;
	
	public KpiClassStat(){
		this.data=new ArrayList();
	}

	

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}



	public List<KpiClassAreaStat> getData() {
		return data;
	}



	public void setData(List<KpiClassAreaStat> data) {
		this.data = data;
	}

	
	
}
