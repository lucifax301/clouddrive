package cn.com.liliyun.authcode.service;


import cn.com.liliyun.common.model.ResultBean;

public interface AuthcodeService {


	public ResultBean getRongToken(String userId, String schoolId);

	public ResultBean getAuthcode(String mobile, String reqType);

	public ResultBean isCodeExist(String codeInput, String mobile, String userId);

	public ResultBean getUptoken(String userId, String schoolId, String tokenId);

	public ResultBean getUpPublicToken(String userId, String schoolId, String tokenId);

	public ResultBean getDownUrl(String userId, String schoolId, String picType, String style, String carId, boolean b);

	public ResultBean getDownUrlByKey(String userId, String schoolId, String picName, String style);

	public ResultBean addDevice(String userId, String schoolId, String osType, String osv, String deviceType,
			String imei, String mac, String imsi, String mobile, String ca, String ac, String lge, String lae,
			String appPackName, String appVersion, String appCode, String jpush);

	public ResultBean getUpYunPublicToken(String userId, String schoolId, String tokenId);
	
	
	
	
	
}
