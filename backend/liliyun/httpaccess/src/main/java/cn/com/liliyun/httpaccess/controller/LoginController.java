package cn.com.liliyun.httpaccess.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.EncryptUtil;
import cn.com.liliyun.common.util.WebUtil;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.Privilege;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.PrivilegeService;
import cn.com.liliyun.user.service.UserService;

@Controller
@ResponseBody
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private AreaService areaService;

	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @param authcode
	 * @return
	 */
	@RequestMapping(value = "/open/login")
	public ResultBean login(User user, String authcode, Integer isAutoLogin, 
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultBean r = new ResultBean();
		if (StringUtils.isBlank(user.getUsername())) {
			r.setMsg("用户账号不能为空!");
			r.setCode(100);
			return r;
		}
		if (StringUtils.isBlank(user.getPassword())) {
			r.setMsg("用户密码不能为空!");
			r.setCode(100);
			return r;
		}
		if (StringUtils.isBlank(authcode)) {
			r.setMsg("验证码不能为空!");
			r.setCode(100);
			return r;
		}
		HttpSession session = request.getSession();
		if (!authcode.equals(session.getAttribute("authcode"))) {
			r.setMsg("验证码错误!");
			r.setCode(100);
			return r;
		}
		user.setMgrdb(true);
		user.setUsername(user.getUsername().trim());
		user.setPassword(user.getPassword().trim());
		user = userService.checkUserLogin(user);
		if (user == null) {
			r.setMsg("账号或密码错误!");
			r.setCode(100);
			return r;
		} else {
			String dblink = user.getDblink();
			if (StringUtils.isBlank(dblink)) {
				r.setMsg("数据库连接信息错误!");
				r.setCode(100);
				return r;
			}
			if (user.getStatus() == 1) {
				r.setMsg("用户已停用!");
				r.setCode(100);
				return r;
			}
			
			session.setAttribute(ConstantUtil.USER_SESSION, user);//暂时使用session处理登录信息
		}
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("loginname",user.getUsername());
		resp.put("username",user.getRealname());
		resp.put("schoolname", user.getSchoolname());
		resp.put("rolename", user.getRolename());
		resp.put("areaname", user.getAreaname());
		resp.put("storename", user.getStorename());
		storeLoginStatus(request, response, user);
		if (ConstantUtil.IS_AUTO_LOGIN == isAutoLogin) {
			resp.put("token", EncryptUtil.encryptBASE64(user.getUsername()));
		}
		r.setResult(resp);
		return r;
	}

	/**
	 * 登录成功后,将登录信息存储在Session和Cookies中
	 * @param request
	 * @param response
	 * @param user
	 * @throws UnsupportedEncodingException
	 */
	private void storeLoginStatus(HttpServletRequest request,
			HttpServletResponse response, User user) throws UnsupportedEncodingException {
		
		Cookie loginCookie = new Cookie(WebUtil.COOKIE_ACCOUNT_STR, getPassword(request,request.getParameter("password"),user.getId()));
		loginCookie.setMaxAge(WebUtil.COOKIES_EXPIRY_SECONDS);
		loginCookie.setPath(WebUtil.getContextPath(request) + "/");
		response.addCookie(loginCookie);
		
	}
	
	private String getPassword(HttpServletRequest request,String pwd,long id) throws UnsupportedEncodingException{
		System.out.println("===========pwd:"+pwd);
		String ramdon = pwd.substring(pwd.length()-6, pwd.length());
		String orignPwd = request.getParameter("password");
		return EncryptUtil.SHA1(orignPwd + ramdon) + "*" + id;
	}
	
	/**
	 * 注销账号
	 */
	@RequestMapping(value = "/open/logout")
	public ResultBean logout(HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			rb.setMsg("用戶注销成功!");
		}
		return rb;
	}

	/**
	 * 获取登录用户的信息
	 */
	@RequestMapping(value = "/loginInfo")
	public ResultBean loginUserInfo(HttpServletRequest request) {
		ResultBean r = new ResultBean();
		Map<String, Object> userInfo = new HashMap<>();
		User user = getUser(request);
		if (user != null) { 
			userInfo.put("loginname", user.getUsername());
			userInfo.put("username", user.getRealname());
			userInfo.put("schoolname", user.getSchoolname());
			userInfo.put("storename", user.getStorename());
			userInfo.put("storenum", user.getStorenum());
			userInfo.put("areaname",user.getAreaname());
			userInfo.put("areanum", user.getAreanum());
			r.setResult(userInfo);
		} else {
			r.setMsg("用户未登录!");
		}
		return r;
	}
	
	/**
	 * 用户是否登录
	 */
	@RequestMapping(value = "/open/isLogin")
	public ResultBean isLogin(String username,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		if (getUser(request) == null) {
			rb.setCode(0);
			rb.setMsg("用户未登录!");
		} else {
			rb.setCode(1);
			rb.setMsg("用户已登录!");
		}
		return rb;
	}
	
	/**
	 * 用户是否自动登录
	 */
	@RequestMapping(value = "/open/isAutoLogin")
	public ResultBean isAutoLogin(String username,String token, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String dename = EncryptUtil.decryptBASE64(token);
		Map<String, Object> resp = new HashMap<>();
		if (StringUtils.isNotBlank(username) && dename.equals(username)) {
			HttpSession session = request.getSession();
			User u = new User();
			u.setMgrdb(true);
			u.setUsername(username);
			u = userService.getUser(u);
			try {
				List<Privilege>  privileges = privilegeService.getUserPrivilegeList(u);
				u.setPrivileges(privileges);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute(ConstantUtil.USER_SESSION, u);
			resp.put("isAutoLogin", 1);
		} else {
			resp.put("isAutoLogin", 0);
		}
		rb.setResult(resp);
		return rb;
	}

	/**
	 * 获取验证码
	 */
	@RequestMapping(value = "/open/authcode")
	public void authcode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			int width = 110, height = 50;
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			OutputStream os = response.getOutputStream();
			Graphics g = image.getGraphics();
			Random random = new Random();
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Arial", Font.PLAIN, 32));
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			String authcode = "";
			for (int j = 0; j < 4; j++) {
				String rand = String.valueOf(random.nextInt(10));
				authcode += rand;
				g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
				g.drawString(rand, 24 * j + 4, 35);
			}
			request.getSession().setAttribute("authcode", authcode);
			g.dispose();
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
