package cn.com.liliyun.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.EncryptUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.mapper.DbConfigMapper;
import cn.com.liliyun.user.mapper.PrivilegeMapper;
import cn.com.liliyun.user.mapper.RegisterMapper;
import cn.com.liliyun.user.mapper.UserMapper;
import cn.com.liliyun.user.model.DbConfig;
import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.Register;
import cn.com.liliyun.user.model.Role;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.PrivilegeService;
import cn.com.liliyun.user.service.UserService;

import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RegisterMapper registerMapper;
	
	@Autowired
	private DbConfigMapper dbConfigMapper;
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private PrivilegeService privilegeService;
	
//	@Autowired
//	private DefaultMQProducer dataProducer;
		
//	@Value("${data.synch}")
//	private boolean APP_SYNCH;
	
	/**
	 * 通过邀请码，分配系统根账号，初始化数据库 
	 * 
	 */
	public ResultBean saveAndInitSchoolInfo(User user) {
		ResultBean rb = new ResultBean();
		String code = user.getInvitecode();
		if (StringUtils.isBlank(code)) {
			rb.setCode(100);
			rb.setMsg("邀请码不能为空!");
			return rb;
		}
	
		Register r = new Register();
		r.setMgrdb(true);
		r.setInvitecode(code);
		r = registerMapper.selectOne(r);
		if (r == null) {
			rb.setCode(100);
			rb.setMsg("无效的邀请码!");
			return rb;
		}
	
		DbConfig dbConfig = new DbConfig();
		
		dbConfig = dbConfigMapper.selectDB(dbConfig);
		if (dbConfig == null) {
			rb.setCode(100);
			rb.setMsg("无可用数据源,请联系管理员!");
			return rb;
		}
		String dblink = dbConfig.getDblink();
		
		//更新注册信息表信息
		r.setMgrdb(true);
		r.setStatus(3);
		r.setDblink(dblink);
		registerMapper.updateByPrimaryKeySelective(r);
		
		//分配用户根账号 注册邮件为登录账号
		user.setMgrdb(true);
		user.setUsername(r.getContact());
		user.setRealname(r.getApplyname());
		user.setMobile(r.getContact());
		user.setPassword(r.getPassword());
		user.setSchoolid(r.getId());
		user.setSchoolname(r.getSchoolname());
		user.setIssuper((byte)1);
		user.setIsdel((byte)0);
		user.setRoleid(1);
		user.setStatus((byte)0);
		user.setDblink(dblink);
		userMapper.insertSelective(user);
		log.info("插入用户基本信息...");
	
		//分配超级用户角色权限
		RoleUser roleUser = new RoleUser();
		roleUser.setUserId(user.getId());
		roleUser.setRoleId(1);
		roleUser.setDblink(dblink);
		userMapper.insertRoleUser(roleUser);
		log.info("分配根账户角色...");
		
		//更新数据库配置信息
		dbConfig.setMgrdb(true);
		dbConfig.setSchoolid(r.getId());
		dbConfig.setSchoolname(r.getSchoolname());
		dbConfig.setIsused((byte)1);
		dbConfigMapper.updateByPrimaryKeySelective(dbConfig);
		log.info("初始化根账户成功...");

//		if (APP_SYNCH) {
//			//APP数据同步
//			log.info("app端驾校数据同步...");
//			Map <String,Object> params = new HashMap<String, Object>();
//			params.put("schoolId" , r.getId());
//			params.put("name" , r.getSchoolname());
//			params.put("phoneNum", r.getContact());
//			params.put("coachCount", 0);
//			params.put("carCount", 0);
//			params.put(ConstantUtil.SYNCH_DATA_TABLE_KEY, "t_s_school");
//			params.put(ConstantUtil.SYNCH_DATA_TYPE_KEY, ConstantUtil.SYNCH_DATA_TYPE_INSERT);
//			Message data = new Message();
//			data.setBody(SerializableUtil.serialize(params));
//			try {
//				dataProducer.send(data);
//			} catch (MQClientException e) {
//				e.printStackTrace();
//			} catch (RemotingException e) {
//				e.printStackTrace();
//			} catch (MQBrokerException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return rb;
	}

	@Override
	public User checkUserLogin(User user) throws Exception {
		String password = EncryptUtil.MD5(user.getPassword());
		user.setMgrdb(true);
		user.setDblink(null);
		user.setPassword(password);
		user = userMapper.selectOne(user);
		if (user != null) {
			String dblink = user.getDblink();
			Role role = new Role();
			role.setDblink(dblink);
			role.setId(user.getRoleid());
			role = privilegeMapper.getRole(role);
			user.setRolename(role.getRolename());
			List <Privilege> privileges = privilegeService.getUserPrivilegeList(user); //privilegeMapper.listUserPrivilege(user);
			user.setPrivileges(privileges);
			if (user.getAreaid() != null) {
				Area area = new Area();
				area.setDblink(dblink);
				area.setId(user.getAreaid());
				area = areaService.selectOne(area);
				if (area != null) {
					user.setAreanum(area.getAreanum());
					user.setAreaname(area.getName());
				}
			}
			if (user.getStoreid() != null) {
				Store store = new Store();
				store.setDblink(dblink);
				store.setId(user.getStoreid());
				store = storeService.selectOne(store);
				if (store != null) {
					user.setStorename(store.getName());
					user.setStorenum(store.getStorenum());
				}
			}
			user.getDomain().setAreaid(user.getAreaid());
			user.getDomain().setStoreid(user.getStoreid());
			if(user.getStaffid()!=null){
				Staff staff=new Staff();
				staff.setId(user.getStaffid());
				staff.setDblink(user.getDblink());
				Staff s= staffService.get(staff);
				user.setStaff(s);
				if(s.getJob()==ConstantUtil.STAFF_CS||s.getJob()==ConstantUtil.STAFF_VICE_CS){//客服
					user.getDomain().setAreaid(Integer.parseInt((String)s.getDetailinfo().get("areaid")));
					user.getDomain().setStoreid(Integer.parseInt((String)s.getDetailinfo().get("storeid")));
				}else if(s.getJob()==ConstantUtil.STAFF_DIRECTOR){
					user.getDomain().setAreaid(Integer.parseInt((String)s.getDetailinfo().get("areaid")));
				}
				
			}
		}
		return user;
	}

	@Override
	public ResultBean getList(User user) {
		ResultBean rb = new ResultBean();
		user.setMgrdb(true);
		PageUtil.startPage(user);
		List <User> list = userMapper.selectList(user); 
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@Override
	public ResultBean getListsome(User user) {
		ResultBean rb = new ResultBean();
		user.setMgrdb(true);
		List <User> list=new ArrayList();
		String[] ids= user.getIds().split(",");
		for(String id:ids){
			User up=new User();
			up.setId(Integer.parseInt(id));
			User eu= userMapper.selectByPrimaryKey(up);
			list.add(eu);
		}
		
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean updateUser(User user) {
		user.setMgrdb(true);
		String pwd = user.getPassword();
		if (StringUtils.isNotBlank(pwd)) {
			user.setPassword(EncryptUtil.MD5(pwd));
		} else {
			user.setPassword(null);
		}
		userMapper.updateByPrimaryKeySelective(user);
		return new ResultBean();
	}
	
	@Override
	public ResultBean changepwd(User newone, User user) {
		user.setMgrdb(true);
		String newpwd = newone.getPassword();
		String oldpwd = newone.getOldpassword();
		
		String epwd= EncryptUtil.MD5(oldpwd);
		
		User dbuser= userMapper.selectByPrimaryKey(user);
		
		if(!dbuser.getPassword().equals(epwd)){
			ResultBean rb=new ResultBean("原密码错误!");
			return rb;
		}
		String nepwd= EncryptUtil.MD5(newpwd);
		dbuser.setPassword(nepwd);
		dbuser.setMgrdb(true);
		userMapper.updatePwdByusername(dbuser);
		return new ResultBean();
	}

	@Override
	public ResultBean importUser(User user, List<User> list) {
		ResultBean rb = new ResultBean();
		String password = EncryptUtil.MD5("123456");
		Iterator <User> iterator = list.iterator();
		User userQuery = new User();
		userQuery.setMgrdb(true);
		while (iterator.hasNext()) {
			User u = iterator.next();
			userQuery.setUsername(u.getUsername());
			User qu = userMapper.selectOne(userQuery);
			if (qu != null) {
				iterator.remove();
			} else {
				u.setPassword(password);
				u.setDblink(user.getDblink());
				u.setSchoolid(u.getSchoolid());
				u.setSchoolname(u.getSchoolname());
			}
		}
		Map <String, Object> params = new HashMap<String, Object>();
		params.put("isMgrdb", true);
		params.put("list", list);
		userMapper.insertBatch(params);
		return rb;
	}

	@Override
	public ResultBean saveUser(User user) {
		ResultBean rb = new ResultBean();
		User userQuery = new User();
		userQuery.setMgrdb(true);
		userQuery.setUsername(user.getUsername());
		userQuery.setMobile(user.getUsername());
		userQuery = userMapper.selectOne(userQuery);
		if (userQuery != null) {
			rb.setCode(100);
			rb.setMsg("该手机号码已经注册!");
			return rb;
		}
		Register registerQuery = new Register();
		registerQuery.setMgrdb(true);
		registerQuery.setDblink(user.getDblink());
		registerQuery = registerMapper.selectOne(registerQuery);
		user.setMgrdb(true);
		user.setSchoolid(registerQuery.getId());
		user.setSchoolname(registerQuery.getSchoolname());
		if(user.getAreaid()==null&&user.getStoreid()==null){
			user.setLevel((byte)0);
		}else if(user.getAreaid()!=null&&user.getStoreid()==null){
			user.setLevel((byte)1);
		}else if(user.getAreaid()!=null&&user.getStoreid()!=null){
			user.setLevel((byte)2);
		}
		
		user.setPassword(EncryptUtil.MD5(user.getPassword()));
		insertUser(user);
		return rb;
	}
	
	private void insertUser(User user) {
		user.setMgrdb(true);
		user.setIssuper((byte)0);
		user.setIsdel((byte)0);
		user.setStatus((byte)0);
		userMapper.insertSelective(user);
	}

	@Override
	public ResultBean updatePassowrd(User user) {
		ResultBean rb = new ResultBean();
		user.setMgrdb(true);
		if (StringUtils.isBlank(user.getUsername())) {
			rb.setCode(100);
			rb.setMsg("手机号码出错!");
			return rb;
		}
		String password = EncryptUtil.MD5(user.getPassword());
		user.setPassword(password);
		userMapper.updatePwdByusername(user);
		return rb;
	}

	@Override
	public User getUser(User user) {
		user.setMgrdb(true);
		user = userMapper.selectOne(user);
		return user;
	}
	
	@Override
	public List<User> selectRoleUser(User user) {
		List<User> roleuser = userMapper.selectRoleUser(user);
		return roleuser;
	}
	
	@Override
	public List<User> selectSchoolUser(User user) {
		List<User> roleuser = userMapper.selectList(user);
		return roleuser;
	}

	@Override
	public ResultBean deleteUser(User user) {
		if(user.getIds()!=null&&user.getIds().length()>0){
			String ids[]=user.getIds().split(",");
			for(String id:ids){
				User up=new User();
				up.setId(Integer.parseInt(id));
				userMapper.deleteByPrimaryKey(up);
			}
		}else{
			userMapper.deleteByPrimaryKey(user);
		}
		return new ResultBean();
	}
	
//	@Override
//	public User getUserWithRole(User user) {
//		user.setMgrdb(true);
//		user = userMapper.selectOneWithRole(user);
//		return user;
//	}

	@Override
	public ResultBean getUserListWithRole(User user) {
		Role role=new Role();
		role.setDblink(user.getDblink());
		user.setMgrdb(true);
		ResultBean rb = new ResultBean();
		PageUtil.startPage(user);
		//List<User> list = userMapper.selectListWithRole(user);
		List<User> list=userMapper.selectList(user);
		
		List<Role> roles= privilegeService.listAllRole(role);
		for(User u:list){
			for(Role r:roles){
				if(u.getRoleid().intValue()==r.getId()){
					u.setRolename(r.getRolename());
					break;
				}
			}
		}
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean checkLogin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectMutiUser(List<Integer> ids) {
		Map params=new HashMap();
		params.put("list", ids);
		params.put("mgrdb", true);
		return userMapper.selectMutiUser(params);
	}
	
	
}
