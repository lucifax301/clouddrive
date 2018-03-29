package cn.com.liliyun.user.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;

@SuppressWarnings("rawtypes")
public interface PrivilegeMapper {

	public List<Privilege> getAllPrivilege(BaseModel model);
	
	public List<Privilege> getAllDevPrivilege(BaseModel model);
	
	public int insertPrivilege(Privilege privilege);
	
	public int delPrivilege(Privilege privilege);
	
	public int updatePrivilege(Privilege privilege);
	
	public int insertRole(Role role);
	
	public int delRole(Role role);
	
	public int updateRole(Role role);
	
	public int enableRole(Role role);
	
	public Role getRole(Role role);
	
	public int insertRolePrivilege(Map<String, Object> params);
	
	public int delRolePrivilege(Role role);
	
	public List<Privilege> listRolePrivilege(Role role);
	
	//public List<Privilege> listUserPrivilege(User user);
	
	public List<Privilege> listUserRolePrivilege(Role role);
	
	//public List<Privilege> listUserBtn(User user);
	
	public List<Privilege> listUserRoleBtn(Role role);
	
	public List<Role> listRole(Role role);
	
	public List<Map> listRolePrivilegeCount(Map<String, Object> params);
	
	public int getRoleTotal(Role role);
	
	
	
}
