package cn.com.liliyun.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;

public interface PrivilegeService {
	
	List<Privilege> getAllPrivilege(Privilege privilege);
	
	List<Privilege> getAllDevPrivilege(Privilege privilege);
	
	List<Privilege> getAllBtn(User user);

	void insertRole(Role role) ;
	
	int delPrivilege(Privilege privilege) ;
	
	int insertPrivilege(Privilege privilege) ;
	
	int updatePrivilege(Privilege privilege) ;
	
	int delRole(Role role) ;
	
	int updateRole(Role role) ;
	
	int enable(Role role) ;
	
	Role getRole(Role role) ;
	
	List<Privilege> getUserPrivilege(User user);
	
	PageInfo <Role> listRole(Role role, Boolean isPage) ;
	
	int insertRoleUser(RoleUser roleUser) ;
	
	int delRoleUser(RoleUser roleUser) ;
	
	PageInfo <User> listRoleUser(RoleUser roleUser) ;
	
	PageInfo <User> listNotRoleUser(RoleUser roleUser) ;
	
	List<Privilege> getUserPrivilegeList(User user);
	
	List<Role> listAllRole(Role role);
}
