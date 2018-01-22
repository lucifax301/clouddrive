package cn.com.liliyun.authcode.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;

import cn.com.liliyun.authcode.service.AuthcodeService;
import cn.com.liliyun.common.dto.ReqConstants;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.MobileUtil;
import cn.com.liliyun.io.rong.RongCloud;
import cn.com.liliyun.io.rong.models.TokenReslut;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;


@Service
public class AuthcodeServiceImpl implements AuthcodeService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthcodeServiceImpl.class);
	
/*	@Resource(name="authcodeProp")
	private Properties authcodeProp;
	*/
	@Autowired
	private UserService userService;
	
	@Autowired
	SMSMessageerviceImpl sms;
	
	//公司七牛账号
	private final String BUCKET = "lili";
	private final String AK = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
	private final String SK = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";	
	private final String DOMAIN = "http://7xnvu2.com1.z0.glb.clouddn.com/";
	
	
	private final static String LILIYUN_PUBLIC = "liliyun";
	private final static String BUCKET_PUBLIC = "lili-public";
	
	Auth auth = Auth.create(AK, SK);

	
	@Override
	public ResultBean getRongToken(String userId, String schoolId) {
			ResultBean r = new ResultBean();
			
			try {
				String userName = "lili";
				String userImg = "";
				String rongUserId = "";
				User userQuery = new User();
				userQuery.setId(Integer.parseInt(userId));
				User userVo = userService.getUser(userQuery);
				String token =userVo.getRongToken();
				if(null != token && !"".equals(token)){
					r.setResult(token);
					return r;
				}
				
				userName = userVo.getUsername();
				userImg = "http://o7d94lzvx.bkt.clouddn.com/default_portrait_msg@3x.png";
				rongUserId = userId;
				
				// 2.不存在则获取并保存
				String appKey = "0vnjpoad0citz";//替换成您的appkey
				String appSecret = "i9DV5e5YLl";//替换成匹配上面key的secret
				
				String authcodeEnv = "test";
				if(authcodeEnv.equals(ReqConstants.ENV_PRODUCT)){
					appKey ="0vnjpoad0citz";
					appSecret = "i9DV5e5YLl";
				}else {
					appKey ="0vnjpoad0citz";
					appSecret = "i9DV5e5YLl";
				}
				
				RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
				// 获取 Token 方法 
				TokenReslut userGetTokenResult = rongCloud.user.getToken(rongUserId, userName, userImg);
				if(userGetTokenResult.getCode() == 200){
					User user = new User();
					user.setId(Integer.parseInt(userId));
					user.setRongToken(userGetTokenResult.getToken());
					userService.updateUser(user);
					r.setResult(userGetTokenResult.getToken());
					return r;
				}else {
					r.setCode(ResultCode.ERRORCODE.AUTHCODE_ERROR);
					r.setMsg(userGetTokenResult.getErrorMessage());
					return r;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return r;
	}

	@Override
	public ResultBean getAuthcode(String mobile, String reqType) {
		ResultBean r =new ResultBean();
		String mo = mobile.trim();
		// 手机号码格式错误
		if (!MobileUtil.isMobile(mo)) {
			r.setCode(ResultCode.ERRORCODE.MOBILE_NUMBER_ERROR);
			r.setMsg(ResultCode.ERRORINFO.MOBILE_NUMBER_ERROR);
			return r;
		}
		User userQuery = new User();
		userQuery.setMobile(mobile);
		User userVo = userService.getUser(userQuery);
		if (userVo == null) {
			r.setCode(ResultCode.ERRORCODE.MOBILE_NOT_EXIT_ERROR);
			r.setMsg(ResultCode.ERRORINFO.MOBILE_NOT_EXIT_ERROR);
			return r;
		}
		
		int rt = 0;
		if (reqType != null && !"".equals(reqType)) {
			rt = Integer.parseInt(reqType);
			
			String code = MobileUtil.generateAuthCode().toString();
			String[] codes = { code };
			//redisUtil.setAll(REDISKEY.COACH_AUTHCODE + mo, code,300);
			if (rt == ReqConstants.REQ_TYPE_REGISTER) {
				// sms.SendMessage("尊敬的学员，感谢您的注册，您的验证码为："+code, mo+";");
				sms.SMSSendMessage(mo, "90128", codes);
			} else if (rt == ReqConstants.REQ_TYPE_FIND_PASSWORD) {
				// sms.SendMessage("尊敬的学员，您正在找回密码，您的验证码为："+code, mo+";");
				sms.SMSSendMessage(mo, "90128", codes);
			} else if (rt == ReqConstants.REQ_TYPE_LOGIN) {
				// sms.SendMessage("尊敬的学员，您正在通过手机验证码进行登录，您的验证码为："+code, mo+";");
				sms.SMSSendMessage(mo, "90128", codes);
			} else if (rt == ReqConstants.REQ_TYPE_SUPER_LOGIN){
				sms.SMSSendMessage(mo, "90128", codes);
			} else if (rt == ReqConstants.REQ_TYPE_JPY_PWD){
				sms.SMSSendMessage(mo, "90128", codes);
			}
			
			// 临时返回给客户端，正常流程不需要
			Map<String, String> data = new HashMap<String, String>();
			String authcodeEnv = "test";
			logger.debug("getAuthcode authcodeEnv: " + authcodeEnv);
			if(authcodeEnv.equals(ReqConstants.ENV_TEST)){
				data.put("authcode", code);
			}else {
				data.put("authcode", "123456");
			}
			r.setResult(data);
		}
		return r;
	}

	@Override
	public ResultBean isCodeExist(String codeInput, String mobile, String userId) {
		ResultBean r = new ResultBean();
		String authcodes = "";
		//authcodes = redisUtil.get(REDISKEY.USER_AUTHCODE + mobile);

		if (!codeInput.equals(authcodes)) {
			r.setCode(ResultCode.ERRORCODE.AUTHCODE_ERROR);
			r.setMsg(ResultCode.ERRORINFO.AUTHCODE_ERROR);
		}
		
		return r;
	}

	@Override
	public ResultBean getUptoken(String userId, String schoolId, String tokenId) {
		ResultBean r = new ResultBean();
		
		Map<String,String>data = new HashMap<String, String>();
		data.put("upToken", auth.uploadToken(BUCKET));		
		
		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean getUpPublicToken(String userId, String schoolId, String tokenId) {
		ResultBean r = new ResultBean();
		
		Map<String,String>data = new HashMap<String, String>();
		data.put("upToken", auth.uploadToken(BUCKET_PUBLIC));		
		
		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}
	
	@Override
	public ResultBean getUpYunPublicToken(String userId, String schoolId, String tokenId) {
		ResultBean r = new ResultBean();
		
		Map<String,String>data = new HashMap<String, String>();
		data.put("upToken", auth.uploadToken(LILIYUN_PUBLIC));		
		
		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean getDownUrl(String userId, String schoolId, String picType, String style, String carId,
			boolean b) {
		ResultBean r = new ResultBean();
		
		String resoureName ="FnjqWFunVDKhLb3fDO0OeDCFzuWB";//默认图片
		//根据图片类型在数据库中找到存储名称
		
		//构造下载地址
		String baseUrl = DOMAIN + resoureName;
		if(null!= style && !"".equals(style)){
			resoureName = resoureName + "?"+ style;
		}
		long expires = System.currentTimeMillis()/1000L + 600; //十分钟

		String downUrl = auth.privateDownloadUrl(baseUrl, expires);
		
		Map<String,String>data = new HashMap<String, String>();
		data.put("downUrl", downUrl);
		
		
		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean getDownUrlByKey(String userId, String schoolId, String picName, String style) {
		ResultBean r = new ResultBean();
		
		if(picName.indexOf("http://")>=0 || picName.indexOf("https://")>=0){
			Map<String,String>data = new HashMap<String, String>();
			data.put("downUrl", picName);
			r.setCode(ResultCode.ERRORCODE.SUCCESS);
			r.setMsg(ResultCode.ERRORINFO.SUCCESS);
			r.setResult(data);
			return r;
		}
		
		String resoureName ="FnjqWFunVDKhLb3fDO0OeDCFzuWB";//默认图片
		
		//判断传递的picName是否为空，不为空则覆盖resoureName的值
		if(picName != null && !"".equals(picName)){
			resoureName = picName;
		}
		
		if(null!= style && !"".equals(style)){
			resoureName = resoureName + "?"+ style;
		}
		
		//构造下载地址
		String baseUrl = DOMAIN + resoureName;
		long expires = System.currentTimeMillis()/1000L + 600; //十分钟

		String downUrl = auth.privateDownloadUrl(baseUrl, expires);
		
		
		Map<String,String>data = new HashMap<String, String>();
		data.put("downUrl", downUrl);
		
		
		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setResult(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean addDevice(String userId, String schoolId, String osType, String osv, String deviceType,
			String imei, String mac, String imsi, String mobile, String ca, String ac, String lge, String lae,
			String appPackName, String appVersion, String appCode, String jpush) {
		// TODO Auto-generated method stub
		return null;
	}
	

}


































