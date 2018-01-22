package cn.com.liliyun.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.common.util.UserLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.staff.model.Staff;

public class User extends BaseModel {
	
	private Integer id;

	private String username;

	@JsonIgnore
	private String password;

	private String realname;

	private String mobile;

	private Integer roleid;

	private Byte issuper;

	private Byte status;

	private Date ctime;
	
	private Date mtime;

	private Byte isdel;

	private String invitecode;
	
	private Integer areaid;
	
	private Integer storeid;
	
	//角色数据
	private Byte level;
	
	private Byte isGroup;
	
	private Byte isAdmin;
	
	private Integer pid;
	
	private String rolename;
	
	private Byte roletype;
	//1--启用，0--停用
	private Byte enable;
	
	private String remark;
	
	private String creator;
	
	private String updator;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int privilegeCount;
	
	private int userCount;

	private List<RolePrivilege> rolePrivileges;
	
	private String privilegestr;
		
	private Integer deptid;
	
	private Integer deptflag;
	
	private String storename;

	private String areaname;
	
	private String storenum;
	
	private String areanum;
	
	private static final long serialVersionUID = 1L;

	private Integer staffid;
	
	private Staff staff;

	private int firstlogin;
	
	private String oldpassword;
	
	 private String rongToken;

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public int getFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(int firstlogin) {
		this.firstlogin = firstlogin;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public Integer getDeptflag() {
		return deptflag;
	}

	public void setDeptflag(Integer deptflag) {
		this.deptflag = deptflag;
	}

	public Byte getRoletype() {
		return roletype;
	}

	public void setRoletype(Byte roletype) {
		this.roletype = roletype;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Byte getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Byte isGroup) {
		this.isGroup = isGroup;
	}

	public Byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Byte getEnable() {
		return enable;
	}

	public void setEnable(Byte enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPrivilegeCount() {
		return privilegeCount;
	}

	public void setPrivilegeCount(int privilegeCount) {
		this.privilegeCount = privilegeCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public List<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(List<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	public String getPrivilegestr() {
		return privilegestr;
	}

	public void setPrivilegestr(String privilegestr) {
		this.privilegestr = privilegestr;
	}

	private List<Privilege> privileges;
	
	public User(){
		
	}
	
	public User(int id){
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Byte getIssuper() {
		return issuper;
	}

	public void setIssuper(Byte issuper) {
		this.issuper = issuper;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Byte getIsdel() {
		return isdel;
	}

	public void setIsdel(Byte isdel) {
		this.isdel = isdel;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getStorenum() {
		return storenum;
	}

	public void setStorenum(String storenum) {
		this.storenum = storenum;
	}

	public String getAreanum() {
		return areanum;
	}

	public void setAreanum(String areanum) {
		this.areanum = areanum;
	}

	public String getBatchId() {
		String id = new SimpleDateFormat("yyMMddmmss").format(new Date());
        if (level != null) {
            if (UserLevel.SCHOOL.getStatus() == level) {
                return "PZ" + id;
            } else if (UserLevel.AREA.getStatus() == level) {
                return areanum + id;
            } else if (UserLevel.STORE.getStatus() == level) {
                return areanum + storenum + id;
            }
        }
		return id;
	}

    public String getRongToken() {
		return rongToken;
	}

	public void setRongToken(String rongToken) {
		this.rongToken = rongToken;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
            ", realname='" + realname + '\'' +
            ", areaid=" + areaid +
            ", storeid=" + storeid +
            ", rolename='" + rolename + '\'' +
            ", storename='" + storename + '\'' +
            ", areaname='" + areaname + '\'' +
            ", storenum='" + storenum + '\'' +
            ", areanum='" + areanum + '\'' +
            ", rongToken='" + rongToken + '\'' +
            ", dblink='" + super.getDblink() + '\'' +
            '}';
}
}