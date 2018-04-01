package cn.com.liliyun.httpaccess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.StoreService;

@Controller
@ResponseBody
@RequestMapping(value="/store")
public class StoreController extends BaseController {

	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value="/add")
	public ResultBean add(Store store, HttpServletRequest request) {
		
		if (store.getId() == null) {
			storeService.insert(store);
		} else {
			storeService.updateByPrimaryKey(store);
		}
		return new ResultBean();
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Store store, HttpServletRequest request) {
		store.setPageNo(-1);
		List <Store> list = storeService.selectList(store);
		return this.<Store>buildListResult(list);
		
	}
	
	@RequestMapping(value="/listall")
	public ResultBean listall(Store store, HttpServletRequest request) {
		boolean isStorePage = true;
		List <Store> list = storeService.selectList(store,  isStorePage);
		return this.<Store>buildListResult(list);
	}
	
	@RequestMapping(value="/delete")
	public ResultBean deleteById(Store store, HttpServletRequest request) {
		return storeService.deleteById(store);
	}
	
	/*
	 * userType = 0, 联动Json格式
	 * userType = 1, Findgrid的筛选下拉框格式
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listInJson")
	public Object listinidname(Store store, @RequestParam(value = "userType", required = false, defaultValue = "0") Integer userType, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		
		List<Store> list = storeService.selectAllList(store);
		Map<String, String>[] result = new Map[list.size()];
		int i = 0;
		for (Store s : list) {
			Map<String, String> map = new HashMap<>();
			if (userType == 0){
				map.put("value", s.getId().toString());
				map.put("label", s.getName());
			} else if (userType == 1) {
				map.put(s.getId().toString(), s.getName());
			}
			result[i] = map;
			i++;
		}
		if (userType == 0 && store.getAreaid() == null){
			result = new Map[1];
			Map<String, String> map = new HashMap<>();
			map.put("value", "");
			map.put("label", "请选择");
			result[0] = map;
		}
		r.setResult(result);
		
		return r;
	}
}
