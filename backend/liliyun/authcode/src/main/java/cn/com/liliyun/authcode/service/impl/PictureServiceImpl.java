package cn.com.liliyun.authcode.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;

import cn.com.liliyun.authcode.service.PictureService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;


@Service
public class PictureServiceImpl implements PictureService {

	// 公司七牛账号
	private final String BUCKET = "lili";
	private final String AK = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
	private final String SK = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";
	private final String DOMAIN = "http://7xnvu2.com1.z0.glb.clouddn.com/";

	private final static String BUCKET_PUBLIC = "liliyun";

	Auth auth = Auth.create(AK, SK);

	@Override
	public ResultBean getUptoken(String userId, String userType) throws Exception {
		ResultBean r = new ResultBean();

		Map<String, String> data = new HashMap<String, String>();
		data.put("upToken", auth.uploadToken(BUCKET));

		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean getDownUrl(String userId, String userType, String picType, String style, String carId,
			boolean isCheckCar) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultBean getDownUrlByKey(String userId, String userType, String picName, String style) throws Exception {
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
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

	@Override
	public ResultBean getUpPublicToken(String userId, String userType) {
		ResultBean r = new ResultBean();

		Map<String, String> data = new HashMap<String, String>();
		data.put("upToken", auth.uploadToken(BUCKET_PUBLIC));

		r.setCode(ResultCode.ERRORCODE.SUCCESS);
		r.setMsg(ResultCode.ERRORINFO.SUCCESS);
		r.setResult(data);
		return r;
	}

}
