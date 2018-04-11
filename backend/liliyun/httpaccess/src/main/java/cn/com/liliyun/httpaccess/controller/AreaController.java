package cn.com.liliyun.httpaccess.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@Controller
@ResponseBody
@RequestMapping(value = "/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/edit")
	public ResultBean edit(Area area) {
		if (area.getId() == null) {
			return areaService.insert(area);
		} else {
			return areaService.updateByPrimaryKey(area);
		}
	}
	
	//查询所有正常状态的片区,不分页
	@RequestMapping(value="/list")
	public ResultBean list(Area area) {
//		area.setStatus((byte)1);
//		area.setPageNo(-1);
		return areaService.selectList(area);
	}
	
	@RequestMapping(value="/listall")
	public ResultBean listall(Area area) {
		return areaService.selectList(area);
	}
	
	@RequestMapping(value="/delete")
	public ResultBean delete(Area area) {
		return areaService.deleteById(area);
	}
	
	@RequestMapping(value="/listInJson")
	public ResultBean listinidname(Area area) {
		ResultBean r = new ResultBean();
		
		List<Area> list = areaService.selectAllList(area);
		@SuppressWarnings("unchecked")
		Map<String, String>[] result = new Map[list.size()];
		int i = 0;
		for (Area a : list) {
			Map<String, String> map = new HashMap<>();
			map.put(a.getId().toString(), a.getName());
			result[i] = map;
			i++;
		}
		r.setResult(result);
		
		return r;
	}
	
	@RequestMapping(value="/region")
	public ResultBean regionlist(@RequestParam(value = "level", required = false, defaultValue = "0") Integer level,
			HttpServletRequest request) throws IOException {
		ResultBean r = new ResultBean();
		
			File file = ResourceUtils.getFile("classpath:region.json");
			FileInputStream fis = new FileInputStream(file);   
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
			BufferedReader br = new BufferedReader(isr);   
			String line = null, json = "";   
			while ((line = br.readLine()) != null) {   
				json += line;   
			}   
			br.close();
			Gson gson = new Gson();
			Map<Integer, List<Map<String, String>>> map = gson.fromJson(json, new TypeToken<Map<Integer, List<Map<String, String>>>>() {
				private static final long serialVersionUID = 1L;}.getType());
			if (level == 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("list", map.get(level));
				r.setResult(result);
			} else {
				r.setResult(map.get(level));
			}
			
		
		return r;
	}
}
