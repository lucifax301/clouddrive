package cn.com.liliyun.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;

public interface PrivilegeService {
	
	public List<Privilege> getAllPrivilege(Privilege privilege)throws Exception;
	
	public List<Privilege> getAllDevPrivilege(Privilege privilege)throws Exception;
	
	public List<Privilege> getAllBtn(User user)throws Exception;

	public void insertRole(Role role) throws Exception;
	
	public int delPrivilege(Privilege privilege, User user) throws Exception;
	
	public int insertPrivilege(Privilege privilege) throws Exception;
	
	public int updatePrivilege(Privilege privilege) throws Exception;
	
	public int delRole(Role role) throws Exception;
	
	public int updateRole(Role role) throws Exception;
	
	public int enable(Role role) throws Exception;
	
	public Role getRole(Role role) throws Exception;
	
	public List<Privilege> getUserPrivilege(User user)throws Exception;
	
	public PageInfo <Role> listRole(Role role, Boolean isPage) throws Exception;
	
	public int insertRoleUser(RoleUser roleUser) throws Exception;
	
	public int delRoleUser(RoleUser roleUser) throws Exception;
	
	public PageInfo <User> listRoleUser(RoleUser roleUser) throws Exception;
	
	public PageInfo <User> listNotRoleUser(RoleUser roleUser) throws Exception;
	
	public List<Privilege> getUserPrivilegeList(User user)throws Exception;
	
	public List<Role> listAllRole(Role role);
}
