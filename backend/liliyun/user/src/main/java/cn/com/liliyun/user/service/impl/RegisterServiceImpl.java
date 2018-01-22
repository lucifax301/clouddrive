package cn.com.liliyun.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.DateUtil;
import cn.com.liliyun.common.util.EncryptUtil;
import cn.com.liliyun.common.util.MailUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.user.mapper.DbConfigMapper;
import cn.com.liliyun.user.mapper.PrivilegeMapper;
import cn.com.liliyun.user.mapper.RegisterMapper;
import cn.com.liliyun.user.mapper.UserMapper;
import cn.com.liliyun.user.model.DbConfig;
import cn.com.liliyun.user.model.Register;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService{

	private Logger logger = Logger.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	private RegisterMapper registerMapper;
	
	@Autowired
	private DbConfigMapper dbConfigMapper;
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Value("${data.synch}")
	private boolean APP_SYNCH;
	
	@Override
	public ResultBean saveRegister(Register register) {
		ResultBean rb = new ResultBean();
		String phone = register.getContact().trim();
		Register r = new Register();
		r.setMgrdb(true);
		r.setContact(phone);
		r = registerMapper.selectOne(register);
		if (r != null) {
			rb.setMsg("该手机号码已经申请注册过账号!");
			rb.setCode(100);
			return rb;
		}
		
		//查询数据库分配情况
		DbConfig dbConfig = new DbConfig();
		dbConfig.setMgrdb(true);
		dbConfig = dbConfigMapper.selectDB(dbConfig);
		if (dbConfig == null) {
			rb.setCode(100);
			rb.setMsg("无可用数据源,请联系管理员!");
			return rb;
		}
		String dblink = dbConfig.getDblink();
		
		//插入注册业务表 1 发送验证码 2审核不通过 3 审核通过
		register.setStatus(3);
		register.setMgrdb(true);
		register.setDblink(dblink);
		register.setPassword(EncryptUtil.MD5(register.getPassword()));
		registerMapper.insertSelective(register);
		
		//分配用户根账号手机号码登录账号
		User user = new User();
		user.setMgrdb(true);
		user.setUsername(phone);
		user.setRealname(register.getApplyname());
		user.setMobile(register.getContact());
		user.setPassword(register.getPassword());
		user.setSchoolid(register.getId());
		user.setSchoolname(register.getSchoolname());
		user.setIssuper((byte)1);
		user.setIsdel((byte)0);
		user.setRoleid(1);
		user.setStatus((byte)0);
		user.setDblink(dblink);
		userMapper.insertSelective(user);
		logger.info("插入用户基本信息...");
	
		//分配超级用户角色权限
		RoleUser roleUser = new RoleUser();
		roleUser.setUserId(user.getId());
		roleUser.setRoleId(1); //默认1为驾校根用户
		roleUser.setDblink(dblink);
		privilegeMapper.insertRoleUser(roleUser);
		logger.info("分配根账户角色...");
		
		//更新数据库配置信息
		dbConfig.setMgrdb(true);
		dbConfig.setSchoolid(register.getId());
		dbConfig.setSchoolname(register.getSchoolname());
		dbConfig.setIsused((byte)1);
		dbConfigMapper.updateByPrimaryKeySelective(dbConfig);
		logger.info("初始化根账户成功...");

//		APP数据同步
//		if (APP_SYNCH) {
//			logger.info("app端驾校数据同步...");
//			Map<String, Object> params = new HashMap<>();
//			params.put("schoolId" , register.getId());
//			params.put("name" , register.getSchoolname());
//			params.put("phoneNum", register.getContact());
//			params.put("coachCount", 0);
//			params.put("carCount", 0);
//			try {
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("---------------------> 同步驾校信息失败!");
//			}
//			logger.info("app端驾校数据同步成功...");
//		}
		return rb;
	}

	@Override
	public ResultBean getList(Register register) {
		ResultBean rb = new ResultBean();
		register.setMgrdb(true);
		PageUtil.startPage(register);
		List <Register> list = registerMapper.selectList(register);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean sendCodeMail(Register register) {
		ResultBean rb = new ResultBean();
		register.setMgrdb(true);
		Register r = registerMapper.selectOne(register);
		if (r == null) {
			rb.setCode(200);
			rb.setMsg("电子邮箱错误!");
			return rb;
		}
		try {
			String subject = "喱喱驾培云";
			String content = String.format("您的注册码为：[%s] <br>登录账号: %s 默认密码：123456 <br> %s" 
					,r.getInvitecode(),register.getEmail(),DateUtil.currentDatetime());
			MailUtil.sendMail(register.getEmail(), subject, content);
			register.setStatus(1);
			registerMapper.updateByPrimaryKeySelective(register);
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(200);
			rb.setMsg("发送邮件失败!");
		}
		return rb;
	}

	@Override
	public ResultBean checkRegister(Register register) {
		ResultBean rb = new ResultBean();
		if (register.getId() == null) {
			rb.setCode(200);
			rb.setMsg("获取主键错误!");
			return rb;
		}
		Register queryBean = new Register();
		queryBean.setMgrdb(true);
		queryBean.setId(register.getId());
		queryBean = registerMapper.selectByPrimaryKey(queryBean);
		if (queryBean == null) {
			rb.setCode(200);
			rb.setMsg("注册信息错误!");
			return rb;
		}
		register.setMgrdb(true);
		register.setDblink(queryBean.getDblink());
		String code = queryBean.getInvitecode();
		String email = queryBean.getEmail();
		String subject = "喱喱驾培云";
		//审核通过 发送邀请码邮件
		if ("1".equals(register.getCondition())) {
			try {
				String content = String.format("您的注册码为：(%s) <br>登录账号: %s 默认密码：123456 <br> %s" 
						,code,email,DateUtil.currentDatetime());
				MailUtil.sendMail(email, subject, content);
			} catch (Exception e) {
				e.printStackTrace();
				rb.setCode(200);
				rb.setMsg("发送邮件失败!");
				return rb;
			}
			register.setStatus(1);
			registerMapper.updateByPrimaryKeySelective(register); 
		//审核不通过
		} else {
			try {
				String content = "因注册信息不符合申请条件,申请拒绝!";
				MailUtil.sendMail(email, subject, content);
			} catch (Exception e) {
				e.printStackTrace();
				rb.setCode(200);
				rb.setMsg("发送邮件失败!");
				return rb;
			}
			register.setStatus(2);
			registerMapper.updateByPrimaryKeySelective(register); 
		}
		return rb;
	}

}