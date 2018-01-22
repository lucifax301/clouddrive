
package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.car.model.CarCostRemind;
import cn.com.liliyun.car.model.CarLog;
import cn.com.liliyun.car.model.CarMileage;
import cn.com.liliyun.car.model.CarParts;
import cn.com.liliyun.car.model.CarPartsNotice;
import cn.com.liliyun.car.model.CarRemind;
import cn.com.liliyun.car.model.CarScrap;
import cn.com.liliyun.car.model.PartsSetting;
import cn.com.liliyun.car.model.PartsSettingDto;
import cn.com.liliyun.car.service.CarBizService;
import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.user.model.User;


@Controller
@ResponseBody
@RequestMapping(value = "/carbiz")
public class CarBizController extends BaseController {

	private Logger logger = Logger.getLogger(CarBizController.class);

	@Autowired
	private CarBizService carBizService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/loadremind")
	public ResultBean loadCostRemind(CarCostRemind costRemind) {
		ResultBean rb = new ResultBean();
		costRemind = carBizService.getCostRemind(costRemind);
		rb.setResult(costRemind);
		return rb;
	}
	
	@RequestMapping(value="/updateremind")
	public ResultBean updateCostRemind(CarCostRemind costRemind) {
		carBizService.updateCostRemind(costRemind);
		return new ResultBean();
	}
	
	@RequestMapping(value="/listmileage")
	public ResultBean listMileage(CarMileage mileage) {
		ResultBean rb = new ResultBean();
		List <CarMileage> list = carBizService.listMileage(mileage);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value = "/listlog")
	public ResultBean listLog(CarLog carLog) {
		ResultBean rb = new ResultBean();
		List <CarLog> list = carBizService.getCarLogList(carLog);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value = "/addLog")
	public ResultBean addLog(CarLog carLog,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		carBizService.addLog(carLog,user,bussinessid);
		return rb;
	}
	
	@RequestMapping(value = "/getLog")
	public ResultBean getLog(CarLog carLog,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		try{		
			User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
			CarLog c=carBizService.getLog(carLog,user);
			rb.setResult(c);
			return rb;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rb;
	}
	
	@RequestMapping(value = "/passAuditLog")
	public ResultBean passAuditLog(CarLog carLog,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		carBizService.updateAuditLog(carLog,user,bussinessid,1);//1代表通过审批
		return rb;
	}
	
	@RequestMapping(value = "/refuseAuditLog")
	public ResultBean refuseAuditLog(CarLog carLog,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		carBizService.updateAuditLog(carLog,user,bussinessid,2);//2表示拒绝审批
		return rb;
	}
	
	
	@RequestMapping(value = "/listremind")
	public ResultBean listRemind(HttpServletRequest request,CarCostRemind carCostRemind) {
		ResultBean rb = new ResultBean();
		List <CarRemind> list = carBizService.listCarRemind(getUser(request), carCostRemind);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/editmileage")
	public ResultBean editMileage(HttpServletRequest request, CarMileage mileage) {
		return carBizService.editMileage(getUser(request),mileage);
	}
	
	@RequestMapping(value = "/getCarPartsList", method = RequestMethod.GET)
	public ResultBean getCarPartsList(CarParts carParts, HttpServletRequest request) {
		return carBizService.getCarPartsList(carParts, getUser(request));
	}
	
	@RequestMapping(value = "/addCarPartsList", method = RequestMethod.POST)
	public ResultBean addCarPartsList(CarParts carParts, HttpServletRequest request) {
		return carBizService.addCarParts(carParts, getUser(request));
	}
	
	@RequestMapping(value = "/editCarParts", method = RequestMethod.POST)
	public ResultBean editCarPartsList(CarParts carParts, HttpServletRequest request) {
		return carBizService.editCarParts(carParts, getUser(request));
	}
	
	@RequestMapping(value = "/listscrap", method = RequestMethod.GET)
	public ResultBean listcarscap(CarScrap carScrap) {
		ResultBean rb = new ResultBean();
		List <CarScrap> list = carBizService.listScrap(carScrap);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value = "/editscrap", method = RequestMethod.POST)
	public ResultBean addScrap(CarScrap carScrap, HttpServletRequest request) {
		return carBizService.editScrap(getUser(request), carScrap);
	}
	
	@RequestMapping(value = "/adddelay", method = RequestMethod.POST)
	public ResultBean adddelay(CarScrap carScrap, HttpServletRequest request) {
		return carBizService.addDelay(getUser(request), carScrap);
	}
	
	@RequestMapping(value = "/editdelay", method = RequestMethod.POST)
	public ResultBean editdelay(CarScrap carScrap, HttpServletRequest request) {
		return carBizService.editDelay(getUser(request), carScrap);
	}
	
	@RequestMapping(value = "/getCarPartsExport")
	public ResponseEntity<byte[]> getCarPartsExport(CarParts carParts, HttpServletRequest request) {
		try {
			List<CarParts> list = carBizService.getCarPartsExport(carParts, getUser(request));
			Map<Integer, String> parttypeMap = new HashMap<>();
			parttypeMap.put(1, "轮胎");
			parttypeMap.put(2, "机油");
			parttypeMap.put(3, "时规带");
			parttypeMap.put(4, "二保");
			parttypeMap.put(5, "电池");
			for(CarParts cp : list) {
				cp.setPartstypestr(parttypeMap.get(cp.getPartstype()));
			}
			ExportParams params = new ExportParams("更换配件记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, CarParts.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("更换配件记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			logger.error("*********************************** CarController getCarAccidentExport Error : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/setPartsSetting", method = RequestMethod.POST)
	public ResultBean setPartsSetting(PartsSettingDto partsSettingDto, HttpServletRequest request) {
		
		List<PartsSetting> list = new ArrayList<>();
		PartsSetting ps1 = new PartsSetting();
		ps1.setPartstype(1); 
		ps1.setMonth(partsSettingDto.getTyremonth()); 
		ps1.setMileage(partsSettingDto.getTyremileage());
		list.add(ps1);
		PartsSetting ps2 = new PartsSetting();
		ps2.setPartstype(2); 
		ps2.setMonth(partsSettingDto.getOilmonth()); 
		ps2.setMileage(partsSettingDto.getOilmileage());
		list.add(ps2);
		PartsSetting ps3 = new PartsSetting();
		ps3.setPartstype(3); 
		ps3.setMonth(partsSettingDto.getBeltmonth()); 
		ps3.setMileage(partsSettingDto.getBeltmileage());
		list.add(ps3);
		PartsSetting ps4 = new PartsSetting();
		ps4.setPartstype(4); 
		ps4.setMonth(partsSettingDto.getMaintainmonth()); 
		ps4.setMileage(partsSettingDto.getMaintainmileage());
		list.add(ps4);
		PartsSetting ps5 = new PartsSetting();
		ps5.setPartstype(5); 
		ps5.setMonth(partsSettingDto.getBatterymonth()); 
		list.add(ps5);
		return carBizService.setPartsSetting(list, getUser(request));
		
	}
	
	@RequestMapping(value = "/getPartsSettings", method = RequestMethod.GET)
	public ResultBean getPartsSettings(HttpServletRequest request) {
		ResultBean r = new ResultBean();
		
		r.setResult(carBizService.getPartsSettings(getUser(request)));
		
		return r;
	}
	
	@RequestMapping(value = "/getPartsNoticeList", method = RequestMethod.GET)
	public ResultBean getPartsNoticeList(PartsSetting partsSetting, HttpServletRequest request) {
		return carBizService.getPartsNotice(partsSetting, getUser(request));
	}
	
	@RequestMapping(value = "/getPartsNoticeExport")
	public ResponseEntity<byte[]> getPartsNoticeExport(PartsSetting partsSetting, HttpServletRequest request) {
		try {
			partsSetting.setPageNo(-1);
			@SuppressWarnings("unchecked")
			List<CarPartsNotice> list = ((PageInfo<CarPartsNotice>) carBizService.getPartsNotice(partsSetting, getUser(request)).getResult()).getList();
			User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
			Area area=new Area();
			area.setDblink(user.getDblink());
			Map<Integer, MapDTO> areaMap = areaService.getMap(area);
			
			Map<Integer, String> parttypeMap = new HashMap<>();
			parttypeMap.put(1, "轮胎");
			parttypeMap.put(2, "机油");
			parttypeMap.put(3, "时规带");
			parttypeMap.put(4, "二保");
			parttypeMap.put(5, "电池");
			for(CarPartsNotice cpn : list) {
				cpn.setPartstypestr(parttypeMap.get(cpn.getPartstype()));
				cpn.setAreastr(areaMap.get(cpn.getAreaid()) != null? areaMap.get(cpn.getAreaid()).getName() : "");
			}
			ExportParams params = new ExportParams("配件更换提醒", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, CarPartsNotice.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("配件更换提醒" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			logger.error("*********************************** CarController getCarAccidentExport Error : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
