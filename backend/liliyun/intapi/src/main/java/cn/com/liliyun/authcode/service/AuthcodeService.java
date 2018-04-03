package cn.com.liliyun.authcode.service;


import cn.com.liliyun.common.model.ResultBean;

public interface AuthcodeService {


	ResultBean getRongToken(String userId, String schoolId);

	ResultBean getAuthcode(String mobile, String reqType);

	ResultBean isCodeExist(String codeInput, String mobile, String userId);

	ResultBean getUptoken(String userId, String schoolId, String tokenId);

	ResultBean getUpPublicToken(String userId, String schoolId, String tokenId);

	ResultBean getDownUrl(String userId, String schoolId, String picType, String style, String carId, boolean b);

	ResultBean getDownUrlByKey(String userId, String schoolId, String picName, String style);

	ResultBean addDevice(String userId, String schoolId, String osType, String osv, String deviceType,
			String imei, String mac, String imsi, String mobile, String ca, String ac, String lge, String lae,
			String appPackName, String appVersion, String appCode, String jpush);

	ResultBean getUpYunPublicToken(String userId, String schoolId, String tokenId);
	
	
	
	
	
}
