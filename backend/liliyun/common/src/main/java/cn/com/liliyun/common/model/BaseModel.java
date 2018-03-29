package cn.com.liliyun.common.model;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer schoolid;

	private String schoolname;
	@JsonIgnore
	private Integer pageNo = 1;
	@JsonIgnore
	private Integer pageSize = 10;

	//默认不走管理库
	@JsonIgnore
	private boolean mgrdb = false;
	//所属数据库
	@JsonIgnore
	private String dblink;
	//排序
	@JsonIgnore
	private String orders;

	private String ids;

	//查询关键字或 excel验证消息
	private String condition;

	private Domain domain=new Domain();

	private Integer cuid;
	
	private Integer muid;
	
	
	
	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	@JsonIgnore
	public Domain getDomain() {
		return domain;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getDblink() {
		return dblink;
	}

	public void setDblink(String dblink) {
		this.dblink = dblink;
	}

	public boolean getMgrdb() {
		return mgrdb;
	}

	public void setMgrdb(boolean mgrdb) {
		this.mgrdb = mgrdb;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.JSON_STYLE);
	}
	
	@SuppressWarnings("rawtypes")
	public Map toMap() {
		return new BeanMap(this);
	}
	
//	/**
//	 * 金额，Integer类型转Float类型
//	 * @param Integer
//	 * @return
//	 */
//	protected Float int2Float(Integer integer) {
//		if (integer != null) {
//			return (float)integer / 100;
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 金额，Float类型转Integer类型
//	 * @param Float
//	 * @return
//	 */
//	protected Integer float2int(Float f) {
//		if (f != null) {
//			return (int) (f * 100);
//		} else {
//			return null;
//		}
//	}
}
