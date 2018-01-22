package cn.com.liliyun.httpaccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.LiliObjectFactory;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;

@Controller
@ResponseBody
@RequestMapping(value = "/dic")
public class DicController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@RequestMapping(value="/info")
	public ResultBean info() {
		ResultBean arearesult= areaService.selectList((Area)LiliObjectFactory.getObject(Area.class));
		//ResultBean storeresult=storeService.selectList((Store)LiliObjectFactory.getObject(Store.class));
		ResultBean classinforesult=classinfoService.selectList((Classinfo)LiliObjectFactory.getObject(Classinfo.class));
		return null;
	}
	
	
}
