package cn.com.liliyun.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;

public interface PrivilegeService {
	
	public List<Privilege> getAllPrivilege(Privilege privilege);
	
	public List<Privilege> getAllDevPrivilege(Privilege privilege);
	
	public List<Privilege> getAllBtn(User user);

	public void insertRole(Role role) ;
	
	public int delPrivilege(Privilege privilege) ;
	
	public int insertPrivilege(Privilege privilege) ;
	
	public int updatePrivilege(Privilege privilege) ;
	
	public int delRole(Role role) ;
	
	public int updateRole(Role role) ;
	
	public int enable(Role role) ;
	
	public Role getRole(Role role) ;
	
	public List<Privilege> getUserPrivilege(User user);
	
	public PageInfo <Role> listRole(Role role, Boolean isPage) ;
	
	public int insertRoleUser(RoleUser roleUser) ;
	
	public int delRoleUser(RoleUser roleUser) ;
	
	public PageInfo <User> listRoleUser(RoleUser roleUser) ;
	
	public PageInfo <User> listNotRoleUser(RoleUser roleUser) ;
	
	public List<Privilege> getUserPrivilegeList(User user);
	
	public List<Role> listAllRole(Role role);
}
