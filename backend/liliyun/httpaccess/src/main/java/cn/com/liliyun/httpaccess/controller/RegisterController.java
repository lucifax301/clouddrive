package cn.com.liliyun.httpaccess.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.MobileUtil;
import cn.com.liliyun.common.util.RandomUtil;
import cn.com.liliyun.common.util.SMSUtil;
import cn.com.liliyun.user.model.Register;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.RegisterService;
import cn.com.liliyun.user.service.UserService;

/**
 * 注册申请接口
 * @author lilixc
 *
 */
@Controller
@ResponseBody
public class RegisterController extends BaseController {
	
	private Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 保存注册信息,生成邀请码
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/open/register")
	public ResultBean invitecode(Register register,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("regcode");
		String mobile = (String) session.getAttribute("mobile");
		if (StringUtils.isBlank(code)) {
			rb.setCode(100);
			rb.setMsg("会话失效,请重新发送验证码!");
			return rb;
		}
		if (!code.equals(register.getInvitecode())) {
			rb.setCode(100);
			rb.setMsg("无效验证码,请重新发送验证码!");
			return rb;
		}
		if (!mobile.equals(register.getContact())) {
			rb.setCode(100);
			rb.setMsg("无效手机号码,请重新发送验证码!");
			return rb;
		}
		rb = registerService.saveRegister(register);
		return rb;
	}
	
	/**
	 * 通过邀请码生成根账号
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/open/invitecode")
	public ResultBean register(User user) {
		return userService.saveAndInitSchoolInfo(user);
	}
	
	/**
	 * 注册用户列表
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/register/list")
	public ResultBean list(Register register) {
		return registerService.getList(register);
	}
	
	/**
	 * 发送验证码短信获取邀请码
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/sendCodeMsg")
	public ResultBean sendCodeMail(Register register) {
		return registerService.sendCodeMail(register);
	}
	
	/**
	 * 发送注册验证码短信
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/open/sendRegMsg")
	public ResultBean sendRegMsg(String mobile,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		mobile = mobile.trim();
		if (!MobileUtil.isMobile(mobile)) {
			rb.setCode(100);
			rb.setMsg("请填写正确的手机号码!");
			return rb;
		}
		User u = new User();
		u.setMgrdb(true);
		u.setUsername(mobile);
		u = userService.getUser(u);
		if (u != null) {
			rb.setCode(100);
			rb.setMsg("该手机号码已注册!");
			return rb;
		}
		HttpSession session = request.getSession();
		String code = String.valueOf(RandomUtil.next6Number());
		session.setAttribute("regcode", code);
		session.setAttribute("mobile", mobile);
		String datas [] = {code};
		SMSUtil.send(mobile, ConstantUtil.SMS_CODE_REG_CODE, datas);
		logger.info("发送找回密码短信验证码 ------------->" + mobile + ":" + code);
		return rb;
	}
	
	/**
	 * 发送验证码短信重置密码
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/open/sendPwdMsg")
	public ResultBean sendPwdMail(String mobile,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		mobile = mobile.trim();
		if (!MobileUtil.isMobile(mobile)) {
			rb.setCode(100);
			rb.setMsg("请填写正确的手机号码!");
			return rb;
		}
		User u = new User();
		u.setMgrdb(true);
		u.setUsername(mobile);
		u = userService.getUser(u);
		if (u == null) {
			rb.setCode(100);
			rb.setMsg("该手机号码不是注册用户!");
			return rb;
		}
		HttpSession session = request.getSession();
		String code = String.valueOf(RandomUtil.next6Number());
		session.setAttribute("pwdcode", code);
		session.setAttribute("mobile", mobile);
		String datas [] = {code};
		SMSUtil.send(mobile, ConstantUtil.SMS_CODE_REG_CODE, datas);
		logger.info("发送找回密码短信验证码 ------------->" + mobile + ":" + code);
		return rb;
	}
	
	/**
	 * 验证短信验证码
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/open/checkPwdCode")
	public ResultBean checkPwdCode(String mobile,String code,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User queryUser = new User();
		queryUser.setUsername(mobile);
		queryUser = userService.getUser(queryUser);
		if (queryUser == null) {
			rb.setCode(100);
			rb.setMsg("该手机号码不是注册用户!");
			return rb;
		}
		HttpSession session = request.getSession();
		String pwdcode = (String) session.getAttribute("pwdcode");
		if (StringUtils.isBlank(pwdcode) || !pwdcode.equals(code)) {
			rb.setCode(100);
			rb.setMsg("验证码错误!");
			return rb;
		}
		return rb;
	}
	
	
	/**
	 * 重置密码
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/open/resetPwd")
	public ResultBean resetPwd(User user,String password1, String code, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		HttpSession session = request.getSession();
		String mobile = (String) session.getAttribute("mobile");
		if (StringUtils.isBlank(mobile)) {
			rb.setCode(100);
			rb.setMsg("会话失效,请重新验证!");
			return rb;
		}
		if (!password1.equals(user.getPassword())) {
			rb.setCode(100);
			rb.setMsg("两次密码不一致!");
			return rb;
		}
		user.setUsername(mobile);
		userService.updatePassowrd(user);
		session.invalidate();
		return rb;
	}
	
	/**
	 * 注册用户审核
	 * @param register
	 * @return
	 */
	@RequestMapping(value="/register/check")
	public ResultBean registerCheck(Register register,HttpServletRequest request) {
		return registerService.checkRegister(register);
	}
}
