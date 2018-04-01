package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Dic;
import cn.com.liliyun.user.service.SystemService;

@Controller
@ResponseBody
@RequestMapping("/system")
public class SystemController extends BaseController{

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value="/listdic")
	public ResultBean getDicList(Dic dic) {
		List <Dic> list = systemService.selectValidList(dic);
		return this.<Dic>buildListResult(list);
	}
	
	@RequestMapping(value="/listalldic")
	public ResultBean getAllDicList(Dic dic) {
		List <Dic> list = systemService.selectList(dic);
		return this.<Dic>buildListResult(list);
	}
	
	@RequestMapping(value="/editdic")
	public ResultBean editDic(Dic dic) {
		if (dic.getDicid() == null) {
			systemService.addDic(dic);
		} else {
			systemService.updateDic(dic);
		}
		return new ResultBean();
	}
	
	@RequestMapping(value="/deldic")
	public ResultBean delDic(Dic dic) {
		systemService.delDic(dic);
		return new ResultBean();
	}
	
}
