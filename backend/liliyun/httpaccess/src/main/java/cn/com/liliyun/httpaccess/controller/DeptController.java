package cn.com.liliyun.httpaccess.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.AllRoleMenu;
import cn.com.liliyun.user.model.Dept;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.DeptService;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/dept")
public class DeptController extends BaseController {

	protected final Logger access = Logger.getLogger(this.getClass());
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private StaffService staffService;
	/**
	 * 获取数据列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/list")
	public ResultBean list(Dept dept) {
		List<Dept> list= deptService.selectList(dept);
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<>(list));
		return rb; 
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ResultBean add(Dept dept) {
		
		if(dept.getId()==null){
			return deptService.addDept(dept);
		}
		else{
			return deptService.updateDept(dept);
		}
	} 
	
	
	@RequestMapping(value="/del")
	public ResultBean del(Dept dept) {
		return deptService.delDept(dept);
	} 
	
	@RequestMapping(value="/listDeptAreaStore")
	public ResultBean listDeptAreaStore(HttpServletRequest request) {
		ResultBean r = new ResultBean();
		User user = getUser(request);
		Staff s = new Staff();
		s.setDblink(user.getDblink());
		s.setEmploystatus("0");
		s.setIsdel(0);
		List<Staff> stafftemp = staffService.list(s);
		Map<String, List<AllRoleMenu>> staffMap = new HashMap<>();
		for (Staff staff : stafftemp) {
			try {
				if (StringUtils.isBlank(staff.getDetail()))
					continue;
				Map detailinf = JSON.parse(staff.getDetail(), Map.class);
				staff.setDetailinfo(detailinf);
			} catch (ParseException e) {
				e.printStackTrace();
				r.setCode(HttpConstant.ERROR_CODE);
				r.setMsg(HttpConstant.ERROR_MSG);
				return r;
			}
			String key = "";
			if (staff.getJob() == 1) {
				key = "0" + "_" + staff.getDetailinfo().get("deptid");
			} else if (staff.getJob() == 5 || staff.getJob() == 6) {
				key = staff.getDetailinfo().get("areaid") + "_" + staff.getDetailinfo().get("storeid");
			} else {
				continue;
			}
			if (staffMap.get(key) == null) {
				List<AllRoleMenu> list = new ArrayList<>();
				staffMap.put(key, list);
			}
			List<AllRoleMenu> list = staffMap.get(key);
			AllRoleMenu menu = new AllRoleMenu();
			menu.setVal(staff.getId().toString());
			menu.setTxt(staff.getName());
			list.add(menu);
		}
		
		Area area = new Area();
		area.setDblink(user.getDblink());
		Map<Integer, MapDTO> areaMap = areaService.getMap(area);
		for (Integer key : areaMap.keySet()) {
			System.out.println(key + " : " +areaMap.get(key).getName());
		}
		
		Store store = new Store();
		area.setDblink(user.getDblink());
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		for (Integer key : storeMap.keySet()) {
			System.out.println(key + " : " +storeMap.get(key).getName());
		}
		
		Dept dept = new Dept();
		dept.setDblink(user.getDblink());
		List<Dept> deptList = deptService.selectList(dept);
		Map<Integer, String> deptMap = new HashMap<>();
		for (Dept d : deptList) {
			deptMap.put(d.getId(), d.getName());
		}
		
		Map<String, List<AllRoleMenu>> menuMap = new HashMap<>();
		for (String key : staffMap.keySet()) {
			System.out.println(key);
			String[] keys = key.split("_");
			String deptype = keys[0];
			String deptid = keys[1];
			if (menuMap.get(deptype) == null) {
				List<AllRoleMenu> list = new ArrayList<>();
				menuMap.put(deptype, list);
			}
			List<AllRoleMenu> list = menuMap.get(deptype);
			AllRoleMenu menu = new AllRoleMenu();
			menu.setVal(deptid);
			if (deptype.equals("0")) 
				menu.setTxt(deptMap.get(Integer.parseInt(deptid)));
			else 
				menu.setTxt(storeMap.get(Integer.parseInt(deptid))!=null?storeMap.get(Integer.parseInt(deptid)).getName():deptid);
			menu.setMenu(staffMap.get(key));
			list.add(menu);
		}
		
		List<AllRoleMenu> menus = new ArrayList<>();
		for (String key : menuMap.keySet()) {
			System.out.println(key);
			AllRoleMenu menu = new AllRoleMenu();
			menu.setVal(key);
			if (key.equals("0"))
				menu.setTxt("总部");
			else 
				menu.setTxt(areaMap.get(Integer.parseInt(key))!=null?areaMap.get(Integer.parseInt(key)).getName():key);
			menu.setMenu(menuMap.get(key));
			menus.add(menu);
		}
		r.setResult(menus);
		return r;
	}
}
