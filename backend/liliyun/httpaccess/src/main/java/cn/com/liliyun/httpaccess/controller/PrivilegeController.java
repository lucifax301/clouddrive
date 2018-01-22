package cn.com.liliyun.httpaccess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.LogUtil;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.PrivilegeService;

/*
 * 用户权限
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeController extends BaseController {

	Logger logger = Logger.getLogger(PrivilegeController.class);

	@Autowired
	private PrivilegeService privilegeService;

	/**
	 * 获取所有的系统权限配置
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/allPrivilege", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean allprivilege(Privilege privilege) {
		ResultBean rb = new ResultBean();
		try {
			List<Privilege> privileges = privilegeService.getAllPrivilege(privilege);
			Map <String,Object> data = new HashMap<String, Object>();
			data.put("list", privileges);
			rb.setResult(data);
		} catch (Exception ex) {
			logger.warn(ex.getStackTrace());
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 添加/修改权限菜单
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/editPrivilege", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean editprivilege(Privilege privilege) {
		ResultBean rb = new ResultBean();
		try {
			if (privilege.getId() == null) {
				privilegeService.insertPrivilege(privilege);
			} else {
				privilegeService.updatePrivilege(privilege);
			}
			
		} catch (Exception ex) {
			logger.warn(ex.getStackTrace());
			ex.printStackTrace();
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	/**
	 * 删除权限菜单
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/delPrivilege", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean delPrivilege(Privilege privilege, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		try {
			User user = AccessWebUtil.getSessionUser(request);
			privilegeService.delPrivilege(privilege, user);
		} catch (Exception ex) {
			logger.warn(ex.getStackTrace()); 
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean addRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			User user = AccessWebUtil.getSessionUser(request);
			role.setCreator(user.getRealname());
			role.setIsAdmin(0);
			privilegeService.insertRole(role);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 更新角色
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean updateRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			User user = AccessWebUtil.getSessionUser(request);
			role.setUpdator(user.getRealname());
			role.setUpdateTime(new java.util.Date());
			privilegeService.updateRole(role);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 删除角色
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delRole", method=RequestMethod.POST)
    @ResponseBody
    public ResultBean delRole(Role role,HttpServletRequest request,HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try{
			privilegeService.delRole(role);
		}catch(Exception ex){
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 启用角色
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activeRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean activeRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			role.setEnable(1);
			User user = AccessWebUtil.getSessionUser(request);
			role.setUpdator(user.getRealname());
			role.setUpdateTime(new java.util.Date());
			privilegeService.enable(role);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 停用角色
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/inactiveRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean inactiveRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			role.setEnable(0);
			User user = AccessWebUtil.getSessionUser(request);
			role.setUpdator(user.getRealname());
			role.setUpdateTime(new java.util.Date());
			privilegeService.enable(role);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 获取角色信息
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			Role roledata = privilegeService.getRole(role);
			rb.setResult(roledata);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 获取某个用户的权限
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getUserPrivilege", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserPrivilege(User user, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		Integer id = AccessWebUtil.getSessionUser(request).getId();
		try {
			user.setId(id);
			List<Privilege> privileges = privilegeService.getAllDevPrivilege(new Privilege());
			rb.setResult(privileges);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 角色列表
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listRole", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean listRole(Role role, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "isPage", defaultValue = "1") Integer isPage) {
		ResultBean rb = new ResultBean();
		try {
			PageInfo <Role> data = privilegeService.listRole(role, isPage==0);
			rb.setResult(data);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 给某个角色添加用户
	 * @param roleUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRoleUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean addRoleUser(RoleUser roleUser, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			int code = privilegeService.insertRoleUser(roleUser);
			if (code == 0) {
				rb.setMsg("更新角色失败！");
				rb.setCode(400);
			}
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 删除某个角色中的用户
	 * @param roleUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delRoleUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean delRoleUser(RoleUser roleUser, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			privilegeService.delRoleUser(roleUser);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 获取某个角色的所有用户
	 * @param roleUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listRoleUser", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean listRoleUser(RoleUser roleUser, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			PageInfo <User> users = privilegeService.listRoleUser(roleUser);
			rb.setResult(users);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	/**
	 * 列出不属于该角色的用户
	 * @param roleUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listNotRoleUser", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean listNotRoleUser(RoleUser roleUser, HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			PageInfo <User> users = privilegeService.listNotRoleUser(roleUser);
			rb.setResult(users);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}

	/**
	 * 获取用户权限列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getUserMenu", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserMenu(User user,Privilege privilege,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User loginuser = AccessWebUtil.getSessionUser(request);
		try {
//			user.setId(loginuser.getId());
			List<Privilege> privileges = privilegeService.getUserPrivilege(loginuser);
//			List<Privilege> privileges = privilegeService.getAllDevPrivilege(privilege);
			rb.setResult(privileges);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	@RequestMapping(value = "/getUserBtn", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserBtn(User user,Privilege privilege,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User loginuser = AccessWebUtil.getSessionUser(request);
		try {
//			user.setId(loginuser.getId());
			//List<Privilege> privileges = privilegeService.getUserPrivilege(loginuser);
			List<Privilege> privileges = privilegeService.getAllBtn(loginuser);
			rb.setResult(privileges);
		} catch (Exception ex) {
			logger.warn(LogUtil.getStackMsg(ex));
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
//	private JSONObject buildMenuJson(List<Privilege> privileges) {
//		JSONObject menujson = new JSONObject();
//		JSONArray btnjson = new JSONArray();
//		JSONArray menulist = new JSONArray();
//		for (Privilege p1 : privileges) {
//			JSONObject level1json = new JSONObject();
//			level1json.put("id", p1.getId());
//			level1json.put("name", p1.getName());
//			level1json.put("url", p1.getUrl());
//			List<Privilege> level2 = p1.getPrivileges();
//			if (level2 != null && level2.size() > 0) {
//				JSONArray listjson = new JSONArray();
//				for (Privilege p2 : level2) {
//					JSONObject level2json = new JSONObject();
//					level2json.put("id", p2.getId());
//					level2json.put("name", p2.getName());
//					level2json.put("url", p2.getUrl());
//					if (StringUtils.isNotBlank(p2.getUrl())) {
//						listjson.add(level2json);
//					}
//					List<Privilege> level3 = p2.getPrivileges();
//					if (level3 != null && level3.size() > 0) {
//						for (Privilege p3 : level3) {
//							JSONObject level3json = new JSONObject();
//							level3json.put("id", p3.getId());
//							level3json.put("name", p3.getName());
//							btnjson.add(level3json);
//						}
//					}
//				}
//				if (listjson.size() > 0) {
//					level1json.put("child", listjson);
//				}
//			}
//			menulist.add(level1json);
//		}
//		menujson.put("menu", menulist);
//		menujson.put("btn", btnjson);
//		return menujson;
//	}
	
	private JSONArray buildMenuJson(List<Privilege> privileges) {
	JSONArray menulist = new JSONArray();
	for (Privilege p1 : privileges) {
		JSONObject level1json = new JSONObject();
		level1json.put("id", p1.getId());
		level1json.put("name", p1.getName());
		level1json.put("url", p1.getUrl());
		List<Privilege> level2 = p1.getChildren();
		if (level2 != null && level2.size() > 0) {
			for (Privilege p2 : level2) {
				JSONObject level2json = new JSONObject();
				level2json.put("id", p2.getId());
				level2json.put("name", p2.getName());
				level2json.put("url", p2.getUrl());
				List<Privilege> level3 = p2.getChildren();
				if (level3 != null && level3.size() > 0) {
					for (Privilege p3 : level3) {
						JSONObject level3json = new JSONObject();
						level3json.put("id", p3.getId());
						level3json.put("name", p3.getName());
						level3json.put("url", p3.getUrl());
						level3json.put("target", "navtab");
					}
				}
			}
		}
		menulist.add(level1json);
	}
	return menulist;
}
}
