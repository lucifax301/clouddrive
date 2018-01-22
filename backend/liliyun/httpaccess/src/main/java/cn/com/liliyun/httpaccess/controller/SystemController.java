package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Dic;
import cn.com.liliyun.user.service.SystemService;

@Controller
@ResponseBody
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value="/listdic")
	public ResultBean getDicList(Dic dic) {
		ResultBean rb = new ResultBean();
		List <Dic> list = systemService.selectValidList(dic);
		rb.setResult(list);
		return rb;
	}
	
	@RequestMapping(value="/listalldic")
	public ResultBean getAllDicList(Dic dic) {
		ResultBean rb = new ResultBean();
		List <Dic> list = systemService.selectList(dic);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/editdic")
	public ResultBean editDic(Dic dic) {
		ResultBean rb = new ResultBean();
		if (dic.getDicid() == null) {
			systemService.addDic(dic);
		} else {
			systemService.updateDic(dic);
		}
		return rb;
	}
	
	@RequestMapping(value="/deldic")
	public ResultBean delDic(Dic dic) {
		ResultBean rb = new ResultBean();
		systemService.delDic(dic);
		return rb;
	}
	
}
