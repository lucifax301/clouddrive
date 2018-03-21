package cn.com.liliyun.httpaccess.controller;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.authcode.service.AuthcodeService;
import cn.com.liliyun.common.dto.ReqConstants;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.BeanCopy;
import cn.com.liliyun.finance.model.FinanceAppStat;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.market.model.CustomerStat;
import cn.com.liliyun.market.service.CustomerService;
import cn.com.liliyun.report.model.AppIndexInfo;
import cn.com.liliyun.report.service.AppReportService;
import cn.com.liliyun.user.model.Config;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.AppUserService;
import cn.com.liliyun.user.vo.ConfigVo;

/**
 * APP用户控制中心
 * @author lzb
 *
 */
@Controller
@ResponseBody
@RequestMapping("/v1/app/user/open/")
public class AppUserController {

	private Logger log = Logger.getLogger(AppUserController.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AuthcodeService authcodeService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private AppReportService appReportService;

	/**
	 * 用户登录
	 * @param mobile
	 * @param password
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestParam String mobile, 
			@RequestParam String password, 
			@RequestParam String timestamp, @RequestParam String sign) {
		ResultBean r = new ResultBean();
		
		try {
			r = appUserService.login(mobile, password);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		
		return r;
	}
	
	/**
	 * 融云通讯IM
	 * @param userId
	 * @param schoolId
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/rong/token", method = RequestMethod.GET)
	@ResponseBody
	public Object getRongToken(@RequestParam String userId, @RequestParam String schoolId ,
			@RequestParam String timestamp,	@RequestParam String sign){
		ResultBean r = new ResultBean();
		try {
			r = authcodeService.getRongToken(userId,schoolId);
		} catch (Exception e) {
			log.error("controller: getRongToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取验证码 
	 * 未完成：验证码未放缓存，缓存OK后须放缓存
	 * @param mobile
	 * @param schoolId
	 * @param reqType
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/authcode", method = RequestMethod.GET)
	@ResponseBody
	public Object getAuthcode(@RequestParam String mobile,
			@RequestParam String reqType,
			@RequestParam String timestamp, @RequestParam String sign) {
		ResultBean r = new ResultBean();

		try {
			r = authcodeService.getAuthcode(mobile, reqType);
		} catch (Exception e) {
			log.error("controller: authcode get authcode failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	
	
	/**
	 * 判断验证码的正确性
	 * 未完成：验证码未从缓存中拿出进行比较
	 * @param codeInput
	 * @param mobile
	 * @param userId
	 * @param schoolId
	 * @return
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	@ResponseBody
	public Object isCodeExist(@RequestParam String codeInput,
			@RequestParam String mobile, 
			@RequestParam(required = false)  String userId,
			@RequestParam String timestamp, @RequestParam String sign){
		ResultBean r = new ResultBean();
		
		try {
			r = authcodeService.isCodeExist(codeInput, mobile, userId);
		} catch (Exception e) {
			log.error("controller: isCodeExist get authcode failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	

	/**
	 * 获取上传照片的上传令牌
	 * @param userId
	 * @param schoolId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upToken", method = RequestMethod.GET)
	@ResponseBody
	public Object getUptoken(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();

		try {
			String tokenId = headers.getFirst(ReqConstants.TOKEN);
			r = authcodeService.getUptoken(userId,schoolId,tokenId);

		} catch (Exception e) {
			log.error("controller: file get upToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	
	/**
	 * 获取公共上传照片的上传令牌
	 * @param userId
	 * @param schoolId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upPublicToken", method = RequestMethod.GET)
	@ResponseBody
	public Object getUpPublicToken(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		
		try {
			String tokenId = headers.getFirst(ReqConstants.TOKEN);
			r = authcodeService.getUpPublicToken(userId,schoolId,tokenId);
			
		} catch (Exception e) {
			log.error("controller: file get upToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取公共上传照片的上传令牌
	 * @param userId
	 * @param schoolId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upYunPublicToken ", method = RequestMethod.GET)
	@ResponseBody
	public Object getUpYunPublicToken(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		
		try {
			String tokenId = headers.getFirst(ReqConstants.TOKEN);
			r = authcodeService.getUpYunPublicToken(userId,schoolId,tokenId);
			
		} catch (Exception e) {
			log.error("controller: file get upToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}

	@RequestMapping(value = "/downUrl", method = RequestMethod.GET)
	@ResponseBody
	public Object getDowntoken(@RequestParam String userId,@RequestParam String schoolId,
			@RequestParam String picType, @RequestParam String timestamp,@RequestParam(required=false) String style,
			@RequestParam(required=false) String carId,
			@RequestParam String sign) {
		ResultBean r = new ResultBean();
		try {
			r = authcodeService.getDownUrl(userId,schoolId, picType, style, carId, false);

		} catch (Exception e) {
			log.error("controller: file get downurl failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}

	/**
	 * 根据文件名获取图片下载地址
	 * @param userId
	 * @param schoolId
	 * @param picName
	 * @param style	图片样式，如：imageView2/1/w/200/h/200
	 * @param timestamp
	 * @param sign
	 * @param v
	 * @return
	 */
	@RequestMapping(value = "/downUrlByKey", method = RequestMethod.GET)
	@ResponseBody
	public Object downUrlByKey(@RequestParam(required=false) String userId,@RequestParam(required=false) String schoolId,
			@RequestParam String picName, @RequestParam(required=false) String style,@RequestParam String timestamp,
			@RequestParam String sign, @RequestParam(required=false) String v) {
		ResultBean r = new ResultBean();
		try {
			r = authcodeService.getDownUrlByKey(userId, schoolId, picName, style);

		} catch (Exception e) {
			log.error("controller: file get downurl failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}


	/**
	 * 添加客户端设备信息
	 * @return
	 */
	@RequestMapping(value = "/devices", method = RequestMethod.POST)
	@ResponseBody
	public Object addDevice(@RequestParam String userId,@RequestParam String schoolId,
			@RequestParam String osType,@RequestParam(required=false) String osv,
			@RequestParam(required=false) String deviceType,@RequestParam(required=false) String imei,
			@RequestParam(required=false) String mac,@RequestParam(required=false) String imsi,
			@RequestParam(required=false) String mobile,@RequestParam(required=false) String ca,
			@RequestParam(required=false) String ac,@RequestParam(required=false) String lge,
			@RequestParam(required=false) String lae,@RequestParam(required=false) String appPackName,
			@RequestParam(required=false) String appVersion,@RequestParam(required=false) String appCode,
			@RequestParam String jpush,@RequestParam String timestamp,@RequestParam String sign){
		ResultBean r = new ResultBean();
		try {
			r = authcodeService.addDevice(userId,schoolId,osType,osv,deviceType,imei,
					mac,imsi,mobile,ca,ac,lge,lae,appPackName,appVersion,appCode,jpush);

		} catch (Exception e) {
			log.error("controller: file post devices failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}

	
	/**
	 * 消息中心
	 * @param userId
	 * @param schoolId
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/notice/getNotice", method = RequestMethod.GET)
	@ResponseBody
	public Object getNotice(@RequestParam String userId, @RequestParam String schoolId ,
			@RequestParam String pageNo,	@RequestParam String pageSize,
		@RequestParam String timestamp,	@RequestParam String sign){
		ResultBean r = new ResultBean();
		try {
			//r = noticeManager.getNoticesByUserId(userId, schoolId, pageNo, pageSize);
		} catch (Exception e) {
			log.error("controller: getRongToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	

	/**
	 * 用户自动登录
	 * 
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object autoLogin(@RequestParam String userId,
			@RequestParam String schoolId, 
			@RequestParam String timestamp, @RequestParam String sign) {
		ResultBean r = null;
		// 设置登录时间
    	//Date now = new Date();
    	/*User user = userManager.getUserInfo(Long.parseLong(userId));
    	if (user.getFirstLoginTime() == null) {
    		user.setFirstLoginTime(now);
    	}
    	user.setLastLoginTime(now);
    	userManager.updateUser(user);
    	userManager.addLoginCount(user);*/
    	// 已在过滤器中判断是否已登录
		return r;
	}

	/**
	 * 用户注销
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
     * @return
     */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign) {
		ResultBean r = null;
		
		r = appUserService.logout(userId);

		return r;
	}

	/**
	 * 获取用户信息
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
     * @return
     */
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object userInfo(@RequestParam String userId, @RequestParam String schoolId, @RequestParam String id, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.getUserInfo(id);
		} catch (Exception e) {
			log.error("***************************************** controller: user post logout failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	

	/**
	 * 修改用户信息
	 * @param mobile
	 * @param password
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUser(@RequestParam String userId,
			@RequestParam String schoolId,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String passwordOld,
			@RequestParam(required = false) String photo,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String sex,
			@RequestParam(required = false) String mobile,
			@RequestParam(required = false) String codeInput,
			@RequestParam String timestamp, @RequestParam String sign,@RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.updateUser(Integer.parseInt(userId), schoolId, password,passwordOld, photo, name, sex, mobile, codeInput);

		} catch (Exception e) {
			log.error("***************************************** controller: user put updateUser failed=" + e.getMessage(),e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param schoolId
	 * @param password
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/updatePasswd", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePasswd(@RequestParam String mobile,
			@RequestParam String password,
			@RequestParam String timestamp, @RequestParam String sign,@RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.updatePasswd(mobile,  password);
			
		} catch (Exception e) {
			log.error("***************************************** controller: user put updatePasswd failed=" + e.getMessage(),e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		
		return r;
	}


	/**
	 * 意见反馈
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	@ResponseBody
	public Object feedback(@RequestParam String userId,
			@RequestParam String schoolId, 
			@RequestParam String content, 
			@RequestParam(required = false) String pic, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.feedback(Long.parseLong(userId), Integer.parseInt(schoolId), content, pic);
		} catch (Exception e) {
			log.error( "***************************************** controller: user get feedback failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	
	/**
	 * 系统消息列表
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/sysMsg", method = RequestMethod.GET)
	@ResponseBody
	public Object sysMsg(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getSysMsg(Long.parseLong(userId), Integer.parseInt(schoolId));
		} catch (Exception e) {
			log.error( "***************************************** controller: user get getSysMsg failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 代办事项列表
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/toDo", method = RequestMethod.GET)
	@ResponseBody
	public Object toDo(@RequestParam String userId,
			@RequestParam String schoolId, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getToDo(Long.parseLong(userId), Integer.parseInt(schoolId));
		} catch (Exception e) {
			log.error("***************************************** controller: user get getToDo failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 代办事项详情
	 * @param userId
	 * @param deptId
	 * @param flowId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/toDoDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object toDoDetail(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam String flowId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getToDoDetail(Long.parseLong(userId), Integer.parseInt(deptId), flowId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user get getToDoDetail failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 审批
	 * @param userId
	 * @param deptId
	 * @param flowId
	 * @param content
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	@ResponseBody
	public Object approve(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam String flowId, 
			@RequestParam String content, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.approve(Long.parseLong(userId), Integer.parseInt(deptId), flowId, content);
		} catch (Exception e) {
			log.error( "***************************************** controller: user approve failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	
	/**
	 * 获取首页统计数据
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/homeData", method = RequestMethod.GET)
	@ResponseBody
	public Object homeData(@RequestParam String userId,
			@RequestParam String today, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		try {
			//首页统计信息
			AppIndexInfo appIndexInfo = new AppIndexInfo();
			User user = new User();
			user.setId(Integer.parseInt(userId));
			AppIndexInfo appIndex= appReportService.getAppIndexInfo(appIndexInfo, user);
			
			//配置文件信息
			List<Config>  configList= appUserService.getHomeConfig(Long.parseLong(userId), schoolId, companyId);
			if (configList != null && configList.size() > 0) {
				List<ConfigVo> configVo = BeanCopy.copyList(configList, ConfigVo.class, BeanCopy.COPY2NULL);
				appIndex.setConfigList(configVo);
			}
			r.setResult(appIndex);
		} catch (Exception e) {
			log.error( "***************************************** controller: user get getHome failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	
	/**
	 * 获取客户列表
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getCustomerList", method = RequestMethod.GET)
	@ResponseBody
	public Object getCustomerList(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		try {
			CustomerStat customerStat = new CustomerStat();
			User user = new User();
			List<CustomerStat> customerStatlist =  customerService.getChannelNewStuStat(customerStat, user);
			if (customerStatlist != null && customerStatlist.size() > 0) {
				r.setResult(customerStatlist);
			}
		} catch (Exception e) {
			log.error( "***************************************** controller: user get getCustomerList failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取潜在客户列表
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getPotentialCustomerList", method = RequestMethod.GET)
	@ResponseBody
	public Object getPotentialCustomerList(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		try {
			CustomerStat customerStat = new CustomerStat();
			User user = new User();
			List<CustomerStat> customerStatlist =  customerService.getPotentialNewStuStat(customerStat, user);
			if (customerStatlist != null && customerStatlist.size() > 0) {
				r.setResult(customerStatlist);
			}
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getPotentialCustomerList failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取营销渠道
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getSalesChannel", method = RequestMethod.GET)
	@ResponseBody
	public Object getSalesChannel(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		try {
			CustomerStat customerStat = new CustomerStat();
			User user = new User();
			List<CustomerStat> customerStatlist =  customerService.getNewStuStat(customerStat, user);
			if (customerStatlist != null && customerStatlist.size() > 0) {
				r.setResult(customerStatlist);
			}
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getSalesChannel failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 收入
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getFinanceReceipt", method = RequestMethod.GET)
	@ResponseBody
	public Object getFinanceReceipt(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
	    try
	    {
	      FinanceAppStat financeAppStat = new FinanceAppStat();
	      User user = new User();
	      Map<String, Object> financeAppStatList = this.financeService.getIncomeStat(financeAppStat, user);
	      if ((financeAppStatList != null) && (financeAppStatList.size() > 0)) {
	        r.setResult(financeAppStatList);
	      }
	    }
	    catch (Exception e)
	    {
	      this.log.error("***************************************** controller: user  getFinanceReceipt failed=" + e.getMessage(), e);
	      e.printStackTrace();
	      r.setCode(5);
	      r.setMsg("网络连接失败");
	    }
	    return r;
	}
	
	/**
	 * 支出
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getFinancePayOut", method = RequestMethod.GET)
	@ResponseBody
	public Object getFinancePayOut(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
	    try
	    {
	      FinanceAppStat financeAppStat = new FinanceAppStat();
	      User user = new User();
	      Map<String, Object> financeAppStatList = this.financeService.getOutcomeStat(financeAppStat, user);
	      if ((financeAppStatList != null) && (financeAppStatList.size() > 0)) {
	        r.setResult(financeAppStatList);
	      }
	    }
	    catch (Exception e)
	    {
	      this.log.error("***************************************** controller: user  getFinancePayOut failed=" + e.getMessage(), e);
	      e.printStackTrace();
	      r.setCode(5);
	      r.setMsg("网络连接失败");
	    }
	    return r;
	}
	
	/**
	 * 欠费
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getFinanceArrearage", method = RequestMethod.GET)
	@ResponseBody
	public Object getFinanceArrearage(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
	    try
	    {
	      FinanceAppStat financeAppStat = new FinanceAppStat();
	      User user = new User();
	      Map<String, Object> financeAppStatList = this.financeService.getOwemoneyStat(financeAppStat, user);
	      if ((financeAppStatList != null) && (financeAppStatList.size() > 0)) {
	        r.setResult(financeAppStatList);
	      }
	    }
	    catch (Exception e)
	    {
	      this.log.error("***************************************** controller: user  getFinanceArrearage failed=" + e.getMessage(), e);
	      e.printStackTrace();
	      r.setCode(5);
	      r.setMsg("网络连接失败");
	    }
	    return r;
	}
	
	/**
	 * 考试
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getExam", method = RequestMethod.GET)
	@ResponseBody
	public Object getExam(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getExam(Long.parseLong(userId), Integer.parseInt(deptId), schoolId, companyId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getExam failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	
	/**
	 * 进度
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getExamItem", method = RequestMethod.GET)
	@ResponseBody
	public Object getExamItem(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getExamItem(Long.parseLong(userId), Integer.parseInt(deptId), schoolId, companyId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getExamItem failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	
	/**
	 * 获取部门
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getDepartment", method = RequestMethod.GET)
	@ResponseBody
	public Object getDepartment(@RequestParam String userId,
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getDepartment(Long.parseLong(userId), schoolId, companyId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getDepartment failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取该部门下的所有员工
	 * @param userId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getDepartmentStaff", method = RequestMethod.GET)
	@ResponseBody
	public Object getDepartmentStaff(@RequestParam String userId,
			@RequestParam String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getDepartmentStaff(Long.parseLong(userId), Integer.parseInt(deptId), schoolId, companyId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getDepartment failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 获取员工信息
	 * @param userId
	 * @param deptId
	 * @param timestamp
	 * @param sign
	 * @param headers
     * @return
     */
	@RequestMapping(value = "/getStaffInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object getStaffInfo(@RequestParam String userId,
			@RequestParam(required = false) String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String staffId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.getStaffInfo(Integer.parseInt(userId), deptId,schoolId, companyId, Integer.parseInt(staffId));
		} catch (Exception e) {
			log.error("***************************************** controller: user post logout failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r;
	}
	
	
	/**
	 * 获取常用联系人
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/getTopContacts", method = RequestMethod.GET)
	@ResponseBody
	public Object getTopContacts(@RequestParam String userId,
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			
			r = appUserService.getTopContacts(Long.parseLong(userId),  schoolId, companyId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  getTopContacts failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	/**
	 * 上传联系人
	 * @param userId
	 * @param deptId
	 * @param schoolId
	 * @param companyId
	 * @param contsUserId
	 * @param contsschoolId
	 * @param contsPhoneNum
	 * @param contsHeadIcon
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upTopContacts", method = RequestMethod.POST)
	@ResponseBody
	public Object upTopContacts(@RequestParam String userId,
			@RequestParam(required = false)  String deptId, 
			@RequestParam(required = false) String schoolId, 
			@RequestParam(required = false) String companyId, 
			@RequestParam String contsStaffId, 
			@RequestParam(required = false) String contsSchoolId, 
			@RequestParam String timestamp, @RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = null;
		try {
			r = appUserService.upTopContacts(Long.parseLong(userId), deptId, schoolId, companyId, Integer.parseInt(contsStaffId), contsSchoolId);
		} catch (Exception e) {
			log.error( "***************************************** controller: user  upTopContacts failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		return r;
	}
	
	

}
