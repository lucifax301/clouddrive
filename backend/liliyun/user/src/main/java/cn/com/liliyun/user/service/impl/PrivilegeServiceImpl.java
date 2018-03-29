package cn.com.liliyun.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.user.mapper.PrivilegeMapper;
import cn.com.liliyun.user.mapper.UserMapper;
import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RolePrivilege;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.PrivilegeService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private UserMapper userMapper;

	PrivilegeCompare pcompare=new PrivilegeCompare();
	
	private List<Privilege> merge(List<Privilege> privileges,boolean flag) {
		List<Privilege> level0 = new ArrayList();
		List<Privilege> level1 = new ArrayList();
		List<Privilege> level2 = new ArrayList();
		List<Privilege> level3 = new ArrayList();
		
		for (Privilege privilege : privileges) {
			if (privilege.getMenulevel() == 0) {//一级菜单
				level0.add(privilege);	
			} else if (privilege.getMenulevel() == 1) {//二级菜单
				level1.add(privilege);
			} else if (privilege.getMenulevel() == 2) {//三级菜单
				level2.add(privilege);
			} else if (privilege.getMenulevel() == 3) {//四级按钮
				level3.add(privilege);
			}
		}
		
		for (Privilege p0 : level0) {
			for (Privilege p1 : level1) {
				if (p0.getId().intValue() == p1.getPid().intValue()) {
					if (p0.getChildren() == null) {
						p0.setChildren(new ArrayList<Privilege>());
					}
					p0.getChildren().add(p1); 
				}
			}
		}
		
		for (Privilege p1 : level1) {
			for (Privilege p2 : level2) {
				if (p1.getId().intValue() == p2.getPid().intValue()) {
					if (p1.getChildren() == null) {
						p1.setChildren(new ArrayList<Privilege>());
					}
					p1.getChildren().add(p2); 
				}
			}
		}
		if(flag)
		for (Privilege p2 : level2) {
			for (Privilege p3 : level3) {
				if (p2.getId().intValue() == p3.getPid().intValue()) {
					if (p2.getChildren() == null) {
						p2.setChildren(new ArrayList<Privilege>());
					}
					p2.getChildren().add(p3); 
				}
			}
		}
		Collections.sort(level0,pcompare);
		return level0;
	}
	
	class PrivilegeCompare implements Comparator<Privilege>{

		@Override
		public int compare(Privilege o1, Privilege o2) {
			if(o1.getMenuorder()!=null&&o2.getMenuorder()==null){
				return 1;
			}else if(o1.getMenuorder()==null&&o2.getMenuorder()!=null){
				return -1;
			}else if(o1.getMenuorder()==null&&o2.getMenuorder()==null){
				return 0;
			}else if(o1.getMenuorder()!=null&&o2.getMenuorder()!=null){
				if(o1.getMenuorder().intValue()<o2.getMenuorder().intValue()){
					return -1;
				}else if(o1.getMenuorder().intValue()==o2.getMenuorder().intValue()){
					return o1.getId().compareTo(o2.getId());
				}else if(o1.getMenuorder().intValue()>o2.getMenuorder().intValue()){
					return 1;
				}
			}
			return 0;
		}
		
		
	}

	public List<Privilege> getAllPrivilege(Privilege privilege) {
		List<Privilege> privileges = privilegeMapper.getAllPrivilege(privilege);
		return privileges;
	}

	public List<Privilege> getAllDevPrivilege(Privilege privilege) {
		List<Privilege> privileges = privilegeMapper.getAllDevPrivilege(privilege);
		return merge(privileges,false);
	}
	
	
	
	@Override
	public List<Privilege> getAllBtn(User user) {
		//List<Privilege> privileges = privilegeMapper.listUserBtn(user);
		int roleid= user.getRoleid();
		Role role=new Role();
		role.setId(roleid);
		role.setDblink(user.getDblink());
		List<Privilege> privileges = privilegeMapper.listUserRoleBtn(role);
		
		return privileges;
	}


	public List<Privilege> getUserPrivilege(User user) {
		//List<Privilege> privileges = privilegeMapper.listUserPrivilege(user);
		int roleid= user.getRoleid();
		Role role=new Role();
		role.setId(roleid);
		role.setDblink(user.getDblink());
		List<Privilege> privileges = privilegeMapper.listUserRolePrivilege(role);
		return merge(privileges,false);
	}

	public List<Privilege> getUserPrivilegeList(User user) {
		int roleid= user.getRoleid();
		Role role=new Role();
		role.setId(roleid);
		role.setDblink(user.getDblink());
		//List<Privilege> privileges = privilegeMapper.listUserPrivilege(user);
		List<Privilege> privileges = privilegeMapper.listUserRolePrivilege(role);
		return privileges;
	}

	@Override
	public void insertRole(Role role) {
		String privilegestr = role.getPrivilegestr();
		if (privilegestr != null) {
			String item[] = privilegestr.split(",");
			List<RolePrivilege> privileges = new ArrayList();
			for (int i = 0; i < item.length; i++) {
				if (item[i] == null || item[i].equals(""))
					continue;
				RolePrivilege p = new RolePrivilege();
				p.setDblink(role.getDblink());
				p.setPrivilegeId(Integer.parseInt(item[i]));
				privileges.add(p);
			}
			role.setRolePrivileges(privileges);
		}
		privilegeMapper.insertRole(role);
		int roleId = role.getId();
		List<RolePrivilege> rolePrivileges = role.getRolePrivileges();
		if (rolePrivileges != null && !rolePrivileges.isEmpty()) {
			for (RolePrivilege item : rolePrivileges) {
				item.setRoleId(roleId);
			}
		}
		if(rolePrivileges!=null&&rolePrivileges.size()>0){
			Map<String, Object> params = new HashMap<>();
			params.put("list", rolePrivileges);
			params.put("dblink", role.getDblink());
			
			privilegeMapper.insertRolePrivilege(params);
		}
		
	}

	@Override
	public int delRole(Role role) {
		if (role != null) {
			if (role.getIds() == null) {
				privilegeMapper.delRolePrivilege(role);
				privilegeMapper.delRole(role);
				return 1;
			} else {
				String ids[] = role.getIds().split(",");
				for (int i = 0; i < ids.length; i++) {
					privilegeMapper.delRolePrivilege(role);
					privilegeMapper.delRole(role);
				}
			}
		}
		return 0;
	}

	@Override
	public int updateRole(Role role) {
		String privilegestr = role.getPrivilegestr();
		if (privilegestr != null) {
			String item[] = privilegestr.split(",");
			List<RolePrivilege> privileges = new ArrayList();
			for (int i = 0; i < item.length; i++) {
				if (item[i] == null || item[i].equals(""))
					continue;
				RolePrivilege p = new RolePrivilege();
				p.setPrivilegeId(Integer.parseInt(item[i]));
				privileges.add(p);
			}
			role.setRolePrivileges(privileges);
		}
		privilegeMapper.delRolePrivilege(role);
		List<RolePrivilege> rolePrivileges = role.getRolePrivileges();
		if (rolePrivileges != null && !rolePrivileges.isEmpty()) {
			for (RolePrivilege item : rolePrivileges) {
				item.setRoleId(role.getId());
			}
		}
		Map<String, Object> params = new HashMap<>();
		params.put("list", rolePrivileges);
		params.put("dblink", role.getDblink());
		
		privilegeMapper.insertRolePrivilege(params);
		privilegeMapper.updateRole(role);
		return 1;
	}

	public int enable(Role role) {
		return privilegeMapper.enableRole(role);
	}

	@Override
	public Role getRole(Role role) {
		Role role1 = privilegeMapper.getRole(role);
		if (role1 != null) {
			List<Privilege> privileges = privilegeMapper.listRolePrivilege(role);
			List<Privilege> allprivileges = privilegeMapper.getAllPrivilege(role);
			for (Privilege oitem : allprivileges) {
				for (Privilege uitem : privileges) {
					if (oitem.getId().intValue() == uitem.getId().intValue()) {
						oitem.setLayoutChecked(true);
						break;
					}
				}
			}
			role1.setPrivileges(merge(allprivileges,true));
		}
		return role1;
	}

	@Override
	public PageInfo <Role> listRole(Role role, Boolean isPage) {
		if (isPage) {
			role.setPageSize(9999);
			role.setPageNo(1);
		}
		PageUtil.startPage(role);
		List<Role> roles = privilegeMapper.listRole(role);
		PageInfo<Role> list = new PageInfo<>(roles);
		if (roles.size() > 0) {
			int ids[] = new int[roles.size()];
			for (int i = 0; i < roles.size(); i++) {
				ids[i] = roles.get(i).getId();
			}
			Map<String, Object> params = new HashMap<>();
			params.put("ids", ids);
			params.put("dblink", role.getDblink());
			
			List<Map> c1 = privilegeMapper.listRolePrivilegeCount(params);
			List<Map> c2 = userMapper.listRoleUserCount(params);
			for (Role item : roles) {
				for (Map i1 : c1) {
					if (item.getId() == (int) i1.get("role_id")) {
						item.setPrivilegeCount(((Long) i1.get("count")).intValue());
						break;
					}
				}
				for (Map i2 : c2) {
					if (item.getId() == (int) i2.get("roleid")) {
						item.setUserCount(((Long) i2.get("count")).intValue());
						break;
					}
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Role> listAllRole(Role role) {
		
		List<Role> roles = privilegeMapper.listRole(role);
		
		return roles;
	}

	@Override
	public int insertRoleUser(RoleUser roleUser) {
		List<RoleUser> roleUsers = new ArrayList();
		if (roleUser.getIds() != null && roleUser.getIds().length() > 0) {
			String ids[] = roleUser.getIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].length() == 0)
					continue;
				RoleUser item = new RoleUser(roleUser.getRoleId(), Integer.parseInt(ids[i]));
				roleUsers.add(item);
			}
		} else {
			roleUsers.add(roleUser);
		}
		if (roleUsers.size() == 0) {
			RoleUser params = roleUsers.get(0);
			params.setDblink(roleUser.getDblink());
			params.setMgrdb(roleUser.getMgrdb());
			return userMapper.insertRoleUser(params);
		} else
			return 0;
	}

	@Override
	public int delRoleUser(RoleUser roleUser) {
		return userMapper.delRoleUser(roleUser);
	}

	@Override
	public PageInfo <User> listRoleUser(RoleUser roleUser) {
		List <Integer> ids = userMapper.listRoleUserIds(roleUser);
		Map<String, Object> params = new HashMap<>();
		params.put("list", ids);
		params.put("dblink", roleUser.getDblink());
		
		PageHelper.startPage(roleUser.getPageNo(), roleUser.getPageSize());
		List <User> users = userMapper.listRoleUser(params);
		return new PageInfo<>(users);
	}
	
	@Override
	public PageInfo <User> listNotRoleUser(RoleUser roleUser) {
		List <Integer> ids = userMapper.listRoleUserIds(roleUser);
		Map<String, Object> params = new HashMap<>();
		params.put("list", ids);
		params.put("dblink", roleUser.getDblink());
		
		PageHelper.startPage(roleUser.getPageNo(), roleUser.getPageSize());
		List <User>	users = userMapper.listNotRoleUser(params);
		return new PageInfo<>(users);
	}

	@Override
	public int insertPrivilege(Privilege privilege) {
		return privilegeMapper.insertPrivilege(privilege);
	}

	@Override
	public int delPrivilege(Privilege privilege, User user) {
		String [] ids = privilege.getIds().split(",");
		for (String id : ids) {
			privilege.setId(Integer.parseInt(id));
			privilege.setDblink(user.getDblink());
			privilegeMapper.delPrivilege(privilege);
		}
		return ids.length;
	}

	@Override
	public int updatePrivilege(Privilege privilege) {
		return privilegeMapper.updatePrivilege(privilege);
	}

}
