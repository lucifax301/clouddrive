package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CarAccident;
import cn.com.liliyun.car.model.CarAnnual;
import cn.com.liliyun.car.model.CarDetect;
import cn.com.liliyun.car.model.CarExport;
import cn.com.liliyun.car.model.CarInsurance;
import cn.com.liliyun.car.model.CarOilwear;
import cn.com.liliyun.car.model.CarRepair;
import cn.com.liliyun.car.model.CarTax;
import cn.com.liliyun.car.service.ICarService;
import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.importexcel.model.CarOilwearImport;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author lzb
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = "/car")
public class CarController extends ExportController {

	private Logger logger = Logger.getLogger(CarController.class);

	@Autowired
	private ICarService carService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getCarList")
	public ResultBean getCarList(Car car) {
		return carService.getCarList(car);
	}
	
	@RequestMapping(value = "/getCar")
	public ResultBean getCar(Car car) {
		return carService.getCar(car);
		
	}
	
	@RequestMapping(value = "/addCar")
	public ResultBean addCar(HttpServletRequest request, Car car) {
		return carService.addCar(car);
	}
	
	@RequestMapping(value = "/updateCar")
	public ResultBean updateCar(HttpServletRequest request, Car car) {
		return carService.updateCar(car);
		
	}
	@RequestMapping(value = "/deleteCar")
	public ResultBean deleteCar(Car car) {
		return carService.deleteCar(car);
	}
	
	 @RequestMapping(value = "/exportCar")
	 public ResponseEntity<byte[]> exportCar(Car car) throws IOException {
    	List<CarExport> list = carService.getList(car); //获取数据
    	return this.export("车辆信息数据", "车辆信息数据", "导出数据", list, CarExport.class);
     }
	
	
	
	/**
	 * 获取年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarAnnualList")
	public ResultBean getCarAnnualList(CarAnnual carAnnual) {
		return carService.getCarAnnualList(carAnnual);
	}
	
	/**
	 * 获取年审详情
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarAnnual")
	public ResultBean getCarAnnual(CarAnnual carAnnual) {
		return carService.getCarAnnual(carAnnual);
	}

	/**
	 * 添加年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarAnnual")
	public ResultBean addCarAnnual(CarAnnual carAnnual) {
		return carService.addCarAnnual(carAnnual);
	}

	/**
	 * 更新年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarAnnual")
	public ResultBean updateCarAnnual(CarAnnual carAnnual) {
		return carService.updateCarAnnual(carAnnual);
	}
	
	/**
	 * 删除年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarAnnual")
	public ResultBean deleteCarAnnual(CarAnnual carAnnual) {
		return carService.deleteCarAnnual(carAnnual);
	}
	
	/**
	 * 获取黄绿标检测记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarDetectList")
	public ResultBean getCarDetectList(CarDetect carDetect) {
		return carService.getCarDetectList(carDetect);
	}
	
	/**
	 * 获取黄绿标检测详情
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarDetect")
	public ResultBean getCarDetect(CarDetect carDetect) {
		return carService.getCarDetect(carDetect);
	}
	
	/**
	 * 添加黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarDetect")
	public ResultBean addCarDetect(CarDetect carDetect) {
		return carService.addCarDetect(carDetect);
	}
	
	/**
	 * 更新黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarDetect")
	public ResultBean updateCarDetect(CarDetect carDetect) {
		return carService.updateCarDetect(carDetect);
	}
	
	/**
	 * 删除黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarDetect")
	public ResultBean deleteCarDetect(CarDetect carDetect) {
		return carService.deleteCarDetect(carDetect);
	}
	
	
	/**
	 * 获取 保险信息记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarInsuranceList")
	public ResultBean getCarInsuranceList(CarInsurance carInsurance) {
		ResultBean r = new ResultBean();
		List<CarInsurance> list = carService.getCarInsuranceList(carInsurance);
		r.setResult(new PageInfo<>(list));
		return r;
	}
	
	@RequestMapping(value = "/getCarInsuranceList/export")
	public ResponseEntity<byte[]> getCarInsuranceListExport(CarInsurance carInsurance) throws IOException {
		List<CarInsurance> list = carService.getCarInsuranceList(carInsurance);
		
		for(CarInsurance ci:list){
			String[] paidval = ci.getPaid().split(",");
			StringBuilder b=new StringBuilder();
			for(int i=0;i<paidval.length;i++){
				b.append(paidvalue(paidval[i]));
				if(i<paidval.length-1){
					b.append(",");
				}
			}
			ci.setPaidName(b.toString());
		}
		
		return this.export("保险数据", "保险数据", "导出数据", list, CarInsurance.class);
		
		
	}
	
	private String paidvalue(String str){
		
				 switch (str) {
			      case "1": return  "玻璃险";
						case "2": return  "车损";
						case "5": return  "盗抢险";
						case "6": return  "第三者10万";
						case "7": return  "第三者30万";
						case "8": return  "第三者50万";
						case "9": return  "第三者100万";
						case "3": return  "乘客4座*10万";
						case "4": return  "乘客4座*20万";
						case "10": return  "司机2万";
						case "11": return  "司机5万";
						case "12": return  "司机10万";
						case "13": return  "司机20万";
						default:return "";
			  	}
			}
	
	/**
	 * 获取 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarInsurance")
	public ResultBean getCarInsurance(CarInsurance carInsurance) {
		return carService.getCarInsurance(carInsurance);
	}
	
	/**
	 * 添加 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarInsurance")
	public ResultBean addCarInsurance(CarInsurance carInsurance) {
		return carService.addCarInsurance(carInsurance);
	}
	
	/**
	 * 更新 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarInsurance")
	public ResultBean updateCarInsurance(CarInsurance carInsurance) {
		return carService.updateCarInsurance(carInsurance);
	}
	
	/**
	 * 删除 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarInsurance")
	public ResultBean deleteCarInsurance(CarInsurance carInsurance) {
		return carService.deleteCarInsurance(carInsurance);
	}
	
	
	/**
	 * 获取 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarRepairList")
	public ResultBean getCarRepairList(CarRepair carRepair) {
		return carService.getCarRepairList(carRepair);
	}
	
	/**
	 * 获取 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarRepair")
	public ResultBean getCarRepair(CarRepair carRepair) {
		return carService.getCarRepair(carRepair);
	}
	
	/**
	 * 添加 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarRepair")
	public ResultBean addCarRepair(HttpServletRequest request,CarRepair carRepair) {
		String parts[]= request.getParameterValues("parts");
		String pmoney[]=request.getParameterValues("pmoney");
		
		JSONArray array=new JSONArray();
		int money=0;
		if(parts!=null){
			for(int i=0;i<parts.length;i++){
				if(!"".equals(parts[i])){
					JSONObject json=new JSONObject();
					json.put("parts", parts[i]);
					json.put("pmoney", pmoney[i]);
					money+=Integer.parseInt(pmoney[i]);
					array.add(json);
				}
			}
		}
		carRepair.setMoney(money);
		carRepair.setParts(array.toJSONString());
		return carService.addCarRepair(carRepair);
	}
	
	/**
	 * 更新 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarRepair")
	public ResultBean updateCarRepair(HttpServletRequest request,CarRepair carRepair) {
		
		String parts[]= request.getParameterValues("parts");
		String pmoney[]=request.getParameterValues("pmoney");
		
		JSONArray array=new JSONArray();
		int money=0;
		if(parts!=null){
			for(int i=0;i<parts.length;i++){
				if(!"".equals(parts[i])){
					JSONObject json=new JSONObject();
					json.put("parts", parts[i]);
					json.put("pmoney", pmoney[i]);
					money+=Integer.parseInt(pmoney[i]);
					array.add(json);
				}
			}
		}
		carRepair.setMoney(money);
		carRepair.setParts(array.toJSONString());
		return carService.updateCarRepair(carRepair);
	}
	
	/**
	 * 删除 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarRepair")
	public ResultBean deleteCarRepair(CarRepair carRepair) {
		return carService.deleteCarRepair(carRepair);
	}
	
	/**
	 * 获取 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarTaxList")
	public ResultBean getCarTaxList(CarTax carTax) {
		return carService.getCarTaxList(carTax);
	}
	
	/**
	 * 获取 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarTax")
	public ResultBean getCarTax(CarTax carTax) {
		return carService.getCarTax(carTax);
	}
	
	/**
	 * 添加 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarTax")
	public ResultBean addCarTax(CarTax carTax) {
		return carService.addCarTax(carTax);
	}
	
	/**
	 * 更新 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarTax")
	public ResultBean updateCarTax(CarTax carTax) {
		return carService.updateCarTax(carTax);
	}
	
	/**
	 * 删除 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarTax")
	public ResultBean deleteCarTax(CarTax carTax) {
		return carService.deleteCarTax(carTax);
	}
	
	/**
	 * 获取油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarOilwearList")
	public ResultBean getCarOilwearList(CarOilwear carOilwear) {
		return carService.getCarOilwearList(carOilwear);
	}
	
	/**
	 * 获取油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarOilwear")
	public ResultBean getCarOilwear(CarOilwear carOilwear) {
		return carService.getCarOilwear(carOilwear);
	}
	
	/**
	 * 添加油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarOilwear")
	public ResultBean addCarOilwear(CarOilwear carOilwear) {
		return carService.addCarOilwear(carOilwear);
	}
	
	/**
	 * 更新油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarOilwear")
	public ResultBean updateCarOilwear(CarOilwear carOilwear) {
		return carService.updateCarOilwear(carOilwear);
	}
	
	/**
	 * 删除油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarOilwear")
	public ResultBean deleteCarOilwear(CarOilwear carOilwear) {
		return carService.deleteCarOilwear(carOilwear);
	}

	/**
	 * 导入加油数据
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/importCarOilwear",method=RequestMethod.POST)
	public ResultBean importCarOilwear(@RequestParam("file") MultipartFile file,
			HttpServletRequest request,BaseModel model) throws Exception {
		List <CarOilwearImport> list = ExcelImportUtil.importExcel(file.getInputStream(), CarOilwearImport.class, new ImportParams());
		return carService.importCarOilwear(list);
		
	}
	/**
	 * 获取车辆事故理赔列表
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccidentList", method = RequestMethod.GET)
	public ResultBean getCarAccidentList(CarAccident carAccident, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		List<CarAccident> list = carService.getCarAccidentList(carAccident);
		r.setResult(new PageInfo<>(list));
		return r;
	}
	
	@RequestMapping(value = "/getCarAccidentList/export")
	public ResponseEntity<byte[]> getCarAccidentListExport(CarAccident carAccident, HttpServletRequest request) throws IOException {
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM-dd");
		List<Area> areas= areaService.selectAllList(new Area());
		List<CarAccident> list = carService.getCarAccidentList(carAccident);
		
		for(CarAccident acc:list){
			if(acc.getAccidenttype().intValue()==1){
				acc.setAccidenttypestr("违章");
			}else if(acc.getAccidenttype().intValue()==2){
				acc.setAccidenttypestr("机械事故");
			}else if(acc.getAccidenttype().intValue()==3){
				acc.setAccidenttypestr("交通事故");
			}else if(acc.getAccidenttype().intValue()==4){
				acc.setAccidenttypestr("人伤事故");
			}else if(acc.getAccidenttype().intValue()==5){
				acc.setAccidenttypestr("财产损失事故");
			}
			
			if(acc.getLiabletype().intValue()==1){
				acc.setLiabletypestr("主责");
			}else if(acc.getLiabletype().intValue()==2){
				acc.setLiabletypestr("同责");
			}else if(acc.getLiabletype().intValue()==3){
				acc.setLiabletypestr("次责");
			}else if(acc.getLiabletype().intValue()==4){
				acc.setLiabletypestr("无责");
			}
			
			for(Area area:areas){
				if(acc.getAreaid()!=null&& acc.getAreaid().intValue()==area.getId()){
					acc.setArea(area.getName());
				}
			}
			
			acc.setAccidentdatestr(format.format(acc.getAccidentdate()));
		}
		
		return this.export("车辆事故数据", "车辆事故数据", "导出数据", list, CarAccident.class);
		
	}
	
	/**
	 * 获取车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccident", method = RequestMethod.GET)
	public ResultBean getCarAccident(CarAccident carAccident, HttpServletRequest request) {
		return carService.getCarAccident(carAccident);
	}
	
	/**
	 * 新增车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/addCarAccident", method = RequestMethod.POST)
	public ResultBean addCarAccident(CarAccident carAccident, HttpServletRequest request) {
		return carService.addCarAccident(carAccident);
	}
	
	/**
	 * 修改车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/updateCarAccident", method = RequestMethod.POST)
	public ResultBean updateCarAccident(CarAccident carAccident, HttpServletRequest request) {
		return carService.updateCarAccident(carAccident);
	}
	
	/**
	 * 获取车辆事故理赔导出数据
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccidentExport")
	public ResponseEntity<byte[]> getCarAccidentExport(CarAccident carAccident, HttpServletRequest request) throws IOException {
		List<CarAccident> list = carService.getCarAccidentExport(carAccident);
		return this.export("车辆事故理赔导记录", "车辆事故理赔导记录", "导出数据", list, CarAccident.class);
}
}
