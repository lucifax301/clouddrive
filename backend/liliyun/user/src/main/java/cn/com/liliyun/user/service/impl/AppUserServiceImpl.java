package cn.com.liliyun.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.SecurityUtil;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;
import cn.com.liliyun.staff.vo.StaffVo;
import cn.com.liliyun.user.mapper.ConfigMapper;
import cn.com.liliyun.user.mapper.DeptMapper;
import cn.com.liliyun.user.mapper.TopContactsMapper;
import cn.com.liliyun.user.mapper.UserMapper;
import cn.com.liliyun.user.model.Config;
import cn.com.liliyun.user.model.Dept;
import cn.com.liliyun.user.model.TopContacts;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.AppUserService;
import cn.com.liliyun.user.vo.UserVo;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	private Logger logger = Logger.getLogger(AppUserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private ConfigMapper configMapper;
	
	@Autowired
	private TopContactsMapper topContactsMapper;
	
	@Autowired
	private StaffService staffService;
	
/*	@Autowired
	RedisUtil redisUtil;
	*/

	@Override
	public ResultBean login(String mobile, String password)  {
		ResultBean r = new ResultBean();
		
		User userQuery = new User();
		userQuery.setMobile(mobile);
		userQuery.setPassword(password);
		UserVo user = userMapper.selectAppUser(userQuery);
		if (user != null) {
			if (user.getStatus() == 1 || user.getIsdel() ==1) {
				r.setCode(ResultCode.ERRORCODE.USER_ACCOUNT_BAN);
				r.setMsg(ResultCode.ERRORINFO.USER_ACCOUNT_BAN);
				return r;
			}
			
			// 生成token
			String token = SecurityUtil.getUUID();
			long userId = user.getId();

			// 将token保存到redis中
			//redisUtil.setAll(REDISKEY.USER_TOKEN + userId, token, 0);
			user.setToken(token);
			
			r.setResult(user);
		}
		else {
			r.setCode(ResultCode.ERRORCODE.USER_LOGIN_FAIL);
			r.setMsg(ResultCode.ERRORINFO.USER_LOGIN_FAIL);
			return r;
		}
		return r;
	}
	


	@Override
	public ResultBean logout(String userId) {
		ResultBean r = new ResultBean();
		return r;
	}

	@Override
	public ResultBean getUserInfo(String userId) {
		ResultBean r = new ResultBean();
		User userQuery = new User();
		if (userId != null && !"".equals(userId)) {
			userQuery.setId(Integer.parseInt(userId));
		}
		UserVo user = userMapper.selectAppUser(userQuery);
		if (user != null) {
			r.setResult(user);
		}
		return r;
	}

	@Override
	public ResultBean updateUser(Integer userId, String schoolId, String password,String passwordOld,  String photo, String name, String sex, String mobile, String codeInput) {
		ResultBean r = new ResultBean();
		User userVo = new User();
		if (password != null && !"".equals(password)) { //修改密码
			//校验旧密码
			User userQuery = new User();
			userQuery.setId(userId);
			userQuery.setPassword(passwordOld);
			User user = userMapper.selectOne(userQuery);
			if (user != null) {
				userVo.setPassword(password);
				userVo.setId(userId);
				userMapper.updateByPrimaryKeySelective(userVo);
			}
			else {
				r.setCode(ResultCode.ERRORCODE.USER_PASSWORD_ERROR);
				r.setMsg(ResultCode.ERRORINFO.USER_PASSWORD_ERROR);
				return r;
			}
		}
		else {
			User userQuery = new User();
			userQuery.setId(userId);
			User user = userMapper.selectOne(userQuery);
			if (user != null && user.getStaffid() != null) {
				Staff staff = new Staff();
				if (sex != null && !"".equals(sex)) {
					staff.setSex(Integer.parseInt(sex));
				}
				staff.setPhoto(photo);
				staff.setId(user.getStaffid());
				staffService.updateByPrimaryKeySelective(staff);
			}
			else {
				r.setCode(ResultCode.ERRORCODE.USER_NOT_EXIT);
				r.setMsg(ResultCode.ERRORINFO.USER_NOT_EXIT);
				return r;
			}
		}
		
		
		return r;
	}

	@Override
	public ResultBean feedback(Long userId, Integer deptId, String content, String pic) {
		
		return null;
	}

	@Override
	public ResultBean getSysMsg(Long userId, Integer schoolId) {
		
		return null;
	}

	@Override
	public ResultBean getToDo(Long userId, Integer schoolId) {
		
		return null;
	}

	@Override
	public ResultBean getToDoDetail(Long userId, Integer schoolId, String flowId) {
		
		return null;
	}

	@Override
	public ResultBean approve(Long userId, Integer schoolId, String flowId, String content) {
		
		return null;
	}

	@Override
	public List<Config> getHomeConfig(Long userId, String schoolId, String companyId) {
		Config config = new Config();
		//根据用户ID判断用户角色
		return configMapper.getConfigList(config);
	}

	@Override
	public ResultBean getPotentialCustomerList(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getSalesChannel(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getFinanceReceipt(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getFinancePayOut(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getFinanceArrearage(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getExam(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getExamItem(Long userId, Integer deptId, String schoolId, String companyId) {
		
		return null;
	}

	@Override
	public ResultBean getDepartment(Long userId, String schoolId, String companyId) {
		ResultBean r = new ResultBean();
		Dept dept = new Dept();
		List<Dept> deptList = deptMapper.selectList(dept);
		if (deptList != null && deptList.size() > 0) {
			r.setResult(new PageInfo<>(deptList));
		}
		return r;
	}
	
	@Override
	public ResultBean getDepartmentStaff(Long userId, Integer deptId, String schoolId, String companyId) {
		ResultBean r = new ResultBean();
		Staff staff = new Staff();
		staff.setDeptid(deptId);
		List<Staff> staffList = staffService.getAppStafflist(staff);
		if (staffList != null && staffList.size() > 0) {
			r.setResult(new PageInfo<>(staffList));
		}
		return r;
	}

	@Override
	public ResultBean getTopContacts(Long userId, String schoolId, String companyId) {
		ResultBean r = new ResultBean();
		TopContacts record = new TopContacts();
		record.setUserId(userId);
		if (schoolId != null && !"".equals(schoolId)) {
			record.setSchoolId(Integer.parseInt(schoolId));
		}
		record.setPageNo(-1);
		List<StaffVo> topContactsList = topContactsMapper.selectTopContactsList(record);
		if (topContactsList != null && topContactsList.size() > 0) {
			r.setResult(new PageInfo<>(topContactsList));
		}
		return r;
	}

	@Override
	public ResultBean upTopContacts(Long userId, String deptId, String schoolId, String companyId, Integer contsStaffId, String contsSchoolId) {
		ResultBean r = new ResultBean();
		TopContacts record = new TopContacts();
		record.setUserId(userId);
		if (schoolId != null && !"".equals(schoolId)) {
			record.setSchoolId(Integer.parseInt(schoolId));
		}
		record.setContsStaffId(contsStaffId);
		if (contsSchoolId != null && !"".equals(contsSchoolId)) {
			record.setContsSchoolId(Integer.parseInt(contsSchoolId));
		}
		StaffVo staffVo = topContactsMapper.selectTopContacts(record);
		if (staffVo != null ) {
			return r;
		}
		
	    int re = topContactsMapper.insertSelective(record);
		r.setResult(re);
		return r;
	}

	@Override
	public ResultBean getStaffInfo(Integer userId, String deptId, String schoolId, String companyId, Integer staffId) {
		ResultBean r = new ResultBean();
		Staff staffQuery = new Staff();
		staffQuery.setId(staffId);
		try {
			StaffVo staff = staffService.getStaffVo(staffQuery);
			if (staff != null && !"".equals(staff)) {
				if (staff.getJob() != null && !"".equals(staff.getJob())) {
					Integer job = staff.getJob();
					Map<Integer, String> map = new HashMap<Integer, String>();
					map.put(1, "职能员工");
					map.put(2, "教练员");
					map.put(3, "片区主管");
					map.put(4, "考核员");
					map.put(5, "客服");
					map.put(6, "顶班客服");
					staff.setJobName(map.get(job));
				}
				r.setResult(staff);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}



	@Override
	public ResultBean updatePasswd(String mobile, String password) {
		ResultBean r = new ResultBean();
		User userVo = new User();
		userVo.setMobile(mobile);
		UserVo user = userMapper.selectAppUser(userVo);
		userVo.setPassword(password);
		userVo.setId(user.getId());
		userMapper.updateByPrimaryKeySelective(userVo);
		return r;
	}




}
