package cn.com.liliyun.authcode.service;

import cn.com.liliyun.common.model.ResultBean;

public interface PictureService {

	ResultBean getUptoken(String userId,String userType) throws Exception;

	ResultBean getDownUrl(String userId,String userType, String picType, String style, String carId, boolean isCheckCar)throws Exception;
	
	ResultBean getDownUrlByKey(String userId,String userType, String picName, String style)throws Exception;
	
	ResultBean getUpPublicToken(String userId, String userType);

}
