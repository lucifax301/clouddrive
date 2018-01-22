package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class StaffPerformanceStat extends BaseModel implements Serializable{

	private Integer deptid;
	
	private String dept;
	
	private List<StaffItemPerformanceStat> data;

	public StaffPerformanceStat(){
		this.data=new ArrayList();
	}
	
	

	public Integer getDeptid() {
		return deptid;
	}



	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}



	public String getDept() {
		return dept;
	}



	public void setDept(String dept) {
		this.dept = dept;
	}



	public List<StaffItemPerformanceStat> getData() {
		return data;
	}



	public void setData(List<StaffItemPerformanceStat> data) {
		this.data = data;
	}


	
	
}
