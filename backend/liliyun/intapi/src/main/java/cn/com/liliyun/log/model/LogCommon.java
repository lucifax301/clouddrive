package cn.com.liliyun.log.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class LogCommon extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer schoolId;
	
	private Integer id;

	/**
	 * 对应权限的id
	 */
    private Integer menuid;

    private String menuname;

    private String url;

    /**
     * 操作类型 0 其他, 1 增加，2修改，3删除
     */
    private Integer action;

    private String username;

    private String userid;

    private Date operatetime;
    

    private String ip;

    /**
     * 操作记录关联表ID
     */
    private String relateid;

    private Integer tableid;

    private String relatetable;

    /**
     * 操作状态：1成功，2失败
     */
    private Integer status;

    /**
     * WEB用户端只需要看到这个备注即可
     */
    private String remark;
    
    private String detail;
    
    

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	
	
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public String getRelateid() {
		return relateid;
	}

	public void setRelateid(String relateid) {
		this.relateid = relateid;
	}

	public Integer getTableid() {
		return tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	public String getRelatetable() {
		return relatetable;
	}

	public void setRelatetable(String relatetable) {
		this.relatetable = relatetable;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
    
    
}
