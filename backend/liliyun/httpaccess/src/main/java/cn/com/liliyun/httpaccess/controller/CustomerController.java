package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.finance.model.FinanceSubject;
import cn.com.liliyun.market.model.CustomerRecord;
import cn.com.liliyun.market.model.PotentialCustomer;
import cn.com.liliyun.market.service.CustomerService;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/customer")
public class CustomerController extends ExportController{
	Logger logger = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ClassinfoService classService;
	
	@RequestMapping(value = "addCustomerRecord", method = RequestMethod.POST)
	public ResultBean addCustomerRecord(CustomerRecord customerRecord, HttpServletRequest request) {
		return customerService.addCustomerRecord(customerRecord);
	}
	
	@RequestMapping(value = "getCustomerRecord", method = RequestMethod.GET)
	public ResultBean getCustomerRecord(CustomerRecord customerRecord, HttpServletRequest request) {
		return customerService.getCustomerRecord(customerRecord);
	}
	
	@RequestMapping(value = "customerRecordList", method = RequestMethod.GET)
	public ResultBean getCustomerRecordList(CustomerRecord customerRecord, HttpServletRequest request) {
		return customerService.getCustomerRecordList(customerRecord);
	}
	
	@RequestMapping(value = "editCustomerRecord", method = RequestMethod.POST)
	public ResultBean editCustomerRecord(CustomerRecord customerRecord, HttpServletRequest request) {
		
		return customerService.editCustomerRecord(customerRecord);
	}
	
	@RequestMapping(value = "handleCustomerRecord", method = RequestMethod.POST)
	public ResultBean handleCustomerRecord(CustomerRecord customerRecord, HttpServletRequest request) {
		
		return customerService.handleCustomerRecord(customerRecord);
	}
	
	@RequestMapping(value = "customerHistory", method = RequestMethod.GET)
	public ResultBean getCustomerHistory(CustomerRecord customerRecord, HttpServletRequest request) {
		
		ResultBean r = new ResultBean();
		
		r.setResult(customerService.getCustomerRecordByStuID(customerRecord.getStudentid()));
		
		return r;
	}
	
	@RequestMapping(value = "customerRecord/export")
    public ResponseEntity<byte[]> export(CustomerRecord customerRecord, HttpServletRequest request) throws IOException {
    	
		List<CustomerRecord> list = customerService.getCustomerRecordExport(customerRecord);
		
		
		Map<Integer, MapDTO> classMap = classService.getMap(null);
		Map<Byte, String> stateMap = new HashMap<>();
		stateMap.put((byte) 0, "未处理");
		stateMap.put((byte) 1, "已处理");
		stateMap.put((byte) 2, "无需处理");
		Map<Byte, String> servicesourceMap = new HashMap<>();
		servicesourceMap.put((byte) 1, "服务门店");
		servicesourceMap.put((byte) 2, "呼叫中心");
		servicesourceMap.put((byte) 3, "客户来电");
		servicesourceMap.put((byte) 4, "客户上门");
		servicesourceMap.put((byte) 5, "主管部门");
		servicesourceMap.put((byte) 6, "市场活动");
		servicesourceMap.put((byte) 7, "网上投诉");
		servicesourceMap.put((byte) 8, "回访投诉");
		servicesourceMap.put((byte) 0, "其他");
		Map<Integer, String> servicetypeMap = new HashMap<>();
		servicetypeMap.put(1, "催交资料");
		servicetypeMap.put(2, "理论课培训及采集指纹");
		servicetypeMap.put(4, "科目一测试");
		servicetypeMap.put(3, "报考试");
		servicetypeMap.put(5, "考试批复");
		servicetypeMap.put(6, "学员回访");
		servicetypeMap.put(7, "学车安排");
		servicetypeMap.put(8, "其它");
		servicetypeMap.put(101, "报名资料");
		servicetypeMap.put(102, "重交资料");
		servicetypeMap.put(103, "转校资料");
		servicetypeMap.put(104, "转车型资料");
		servicetypeMap.put(200, "无");
		servicetypeMap.put(300, "无");
		servicetypeMap.put(401, "科目一考试");
		servicetypeMap.put(402, "科目二考试");
		servicetypeMap.put(403, "科目三考试");
		servicetypeMap.put(404, "科目四考试");
		servicetypeMap.put(405, "改期");
		servicetypeMap.put(406, "转场");
		servicetypeMap.put(400, "其它");
		servicetypeMap.put(501, "科目一考试");
		servicetypeMap.put(502, "科目二考试");
		servicetypeMap.put(503, "科目三考试");
		servicetypeMap.put(504, "科目四考试");
		servicetypeMap.put(505, "改期");
		servicetypeMap.put(500, "其它");
		servicetypeMap.put(601, "交资料");
		servicetypeMap.put(602, "科目一培训");
		servicetypeMap.put(603, "科目一练题");
		servicetypeMap.put(604, "转学");
		servicetypeMap.put(605, "缺考");
		servicetypeMap.put(606, "补考");
		servicetypeMap.put(607, "领证");
		servicetypeMap.put(608, "更换教练");
		servicetypeMap.put(609, "服务质量");
		servicetypeMap.put(610, "暂停学车");
		servicetypeMap.put(611, "恢复学车");
		servicetypeMap.put(612, "学习证剩5月到期");
		servicetypeMap.put(613, "报名日期到期");
		servicetypeMap.put(600, "其它");
		servicetypeMap.put(701, "教练分配");
		servicetypeMap.put(702, "学车体验");
		servicetypeMap.put(703, "模拟体验");
		servicetypeMap.put(704, "科目二训练");
		servicetypeMap.put(705, "科目三训练");
		servicetypeMap.put(706, "考前强化");
		servicetypeMap.put(707, "考前测试");
		servicetypeMap.put(708, "其它");
		servicetypeMap.put(800, "无");
		Map<Byte, String> servicemodeMap = new HashMap<>();
		servicemodeMap.put((byte) 1, "去电");
		servicemodeMap.put((byte) 2, "上门服务");
		servicemodeMap.put((byte) 3, "客户来电");
		servicemodeMap.put((byte) 4, "客户上门");
		if (list != null && list.size() > 0) {
			for (CustomerRecord cr : list) {
				cr.setHandlestatestr(stateMap.get(cr.getHandlestate()));
				cr.setClassname(classMap.get(cr.getClassid()) != null? classMap.get(cr.getClassid()).getName() : "");
				cr.setServicesourcestr(servicesourceMap.get(cr.getServicesource()));
				cr.setServicetypestr(servicetypeMap.get(cr.getServicetype()) + "-" + servicetypeMap.get(cr.getServicesubtype()));
				cr.setServicemodestr(servicemodeMap.get(cr.getServicemode()));
			}
		}
		
		return this.export("客户服务记录", "客户服务记录", "导出数据", list, CustomerRecord.class);
		    
    }
    
    @RequestMapping(value = "addPotentialCustomer", method = RequestMethod.POST)
	public ResultBean addPotentialCustomer(PotentialCustomer potentialCustomer, HttpServletRequest request) {
		
		return customerService.addPotentialCustomer(potentialCustomer);
	}
	
	@RequestMapping(value = "potentialCustomerList", method = RequestMethod.GET)
	public ResultBean getPotentialCustomerList(PotentialCustomer potentialCustomer, HttpServletRequest request) {
		
		return customerService.getPotentialCustomerList(potentialCustomer);
	}
	
	@RequestMapping(value = "editPotentialCustomer", method = RequestMethod.POST)
	public ResultBean editPotentialCustomer(PotentialCustomer potentialCustomer, HttpServletRequest request) {
		
		return customerService.editPotentialCustomer(potentialCustomer);
	}
	
	@RequestMapping(value = "potentialCustomer/export")
    public ResponseEntity<byte[]> potentialCustomerExport(PotentialCustomer potentialCustomer, HttpServletRequest request) throws IOException {
    	
		List<PotentialCustomer> list = customerService.getPotentialCustomerExport(potentialCustomer);
		
		
		
		
		
		Map<Integer, MapDTO> areaMap = areaService.getMap(null);
		
		Map<Integer, MapDTO> storeMap = storeService.getMap(null);
		
		Classinfo ci=new Classinfo();
		
		Map<Integer, MapDTO> classMap = classService.getMap(ci);
		
		Map<Byte, String> infosourceMap = new HashMap<>(); // 等确定后添加信息来源的映射
		infosourceMap.put((byte) 1, "上门咨询");
		infosourceMap.put((byte) 2, "来电咨询");
		infosourceMap.put((byte) 3, "呼叫中心");
		infosourceMap.put((byte) 4, "市场活动");
		infosourceMap.put((byte) 5, "官方网站");
		infosourceMap.put((byte) 6, "在线咨询");
		infosourceMap.put((byte) 7, "员工介绍");
		infosourceMap.put((byte) 0, "其他");
		if (list != null && list.size() > 0) {
			for (PotentialCustomer pc : list) {
				String region = areaMap.get(pc.getTargetarea()) != null? areaMap.get(pc.getTargetarea()).getName() : "";
				region = region + "-" + storeMap.get(pc.getTargetstore()) != null? storeMap.get(pc.getTargetstore()).getName() : "";
				pc.setTargetregion(region);
				pc.setApplystatestr(pc.getApplystate() == 0? "未报名" : "已报名");
				pc.setTargetclassname(classMap.get(pc.getTargetclassid()) != null? classMap.get(pc.getTargetclassid()).getName() : "");
				pc.setInfosourcestr(infosourceMap.get(pc.getInfosource()) != null? infosourceMap.get(pc.getInfosource()) : "");
			}
		}
		
		return this.export("潜在客户管理记录", "潜在客户管理记录", "导出数据", list, PotentialCustomer.class);
    }
}
