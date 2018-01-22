package cn.com.liliyun.httpaccess.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.authcode.service.PictureService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;

/**
 * 7牛图片
 * @author lzb
 *
 */
@Controller
@ResponseBody
@RequestMapping("/picture")
public class PictureController {

	private Logger log = Logger.getLogger(PictureController.class);
	
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 获取上传照片的上传令牌
	 * @param userId
	 * @param userType
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upToken", method = RequestMethod.GET)
	@ResponseBody
	public Object getUptoken(@RequestParam String userId,
			@RequestParam String userType, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();

		try {
			r = pictureService.getUptoken(userId,userType);
		} catch (Exception e) {
			log.error("controller: file get upToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r.getResult();
	}
	
	/**
	 * 获取公共上传照片的上传令牌
	 * @param userId
	 * @param userType
	 * @param timestamp
	 * @param sign
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/upPublicToken", method = RequestMethod.GET)
	@ResponseBody
	public Object getUpPublicToken(@RequestParam String userId,
			@RequestParam String userType, @RequestParam String timestamp,
			@RequestParam String sign, @RequestHeader HttpHeaders headers) {
		ResultBean r = new ResultBean();
		
		try {
			r = pictureService.getUpPublicToken(userId,userType);
			
		} catch (Exception e) {
			log.error("controller: file get upToken failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}
		
		return r.getResult();
	}

	@RequestMapping(value = "/downUrl", method = RequestMethod.GET)
	@ResponseBody
	public Object getDowntoken(@RequestParam String userId,@RequestParam String userType,
			@RequestParam String picType, @RequestParam String timestamp,@RequestParam(required=false) String style,
			@RequestParam(required=false) String carId,
			@RequestParam String sign) {
		ResultBean r = new ResultBean();
		try {
			r = pictureService.getDownUrl(userId,userType, picType, style, carId, false);

		} catch (Exception e) {
			log.error("controller: file get downurl failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r.getResult();
	}

	/**
	 * 根据文件名获取图片下载地址
	 * @param userId
	 * @param userType
	 * @param picName
	 * @param style	图片样式，如：imageView2/1/w/200/h/200
	 * @param timestamp
	 * @param sign
	 * @param v
	 * @return
	 */
	@RequestMapping(value = "/downUrlByKey", method = RequestMethod.GET)
	@ResponseBody
	public Object downUrlByKey(@RequestParam(required=false) String userId,@RequestParam(required=false) String userType,
			@RequestParam String picName, @RequestParam(required=false) String style,@RequestParam String timestamp,
			@RequestParam String sign, @RequestParam(required=false) String v) {
		ResultBean r = new ResultBean();
		try {
			r = pictureService.getDownUrlByKey(userId, userType, picName, style);

		} catch (Exception e) {
			log.error("controller: file get downurl failed=" + e.getMessage(), e);
			e.printStackTrace();
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
		}

		return r.getResult();
	}

	

}
