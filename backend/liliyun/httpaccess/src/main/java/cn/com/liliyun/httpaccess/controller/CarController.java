package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.importexcel.model.CarOilwearImport;
import cn.com.liliyun.importexcel.model.Flownum;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.user.model.User;

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
public class CarController extends BaseController {

	private Logger logger = Logger.getLogger(CarController.class);

	@Autowired
	private ICarService carService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getCarList")
	public ResultBean getCarList(Car car) {
		logger.info("********************************* coachName : " + car.getCoachName());
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarList(car);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value = "/getCar")
	public ResultBean getCar(Car car) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCar(car);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCar Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value = "/addCar")
	public ResultBean addCar(HttpServletRequest request, Car car) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCar(getUser(request), car);
		} catch (Exception e) {
			logger.error( "*********************************** CarController addCar Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value = "/updateCar")
	public ResultBean updateCar(HttpServletRequest request, Car car) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCar(getUser(request), car);
		} catch (Exception e) {
			logger.error( "*********************************** CarController updateCar Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	@RequestMapping(value = "/deleteCar")
	public ResultBean deleteCar(Car car) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCar(car);
		} catch (Exception e) {
			logger.error( "*********************************** CarController deleteCar Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	 @RequestMapping(value = "/exportCar")
	 public ResponseEntity<byte[]> exportCar(Car car) throws IOException {
    	List<CarExport> list = carService.getList(car); //获取数据
    	ExportParams params = new ExportParams("车辆信息导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
    	Workbook workbook = ExcelExportUtil.exportExcel(params, CarExport.class, list);
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	workbook.write(os);
        HttpHeaders headers = new HttpHeaders();    
        String fileName = new String("车辆基础信息.xlsx".getBytes("UTF-8"),"iso-8859-1"); //生成文件名
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
     }
	
	
	
	/**
	 * 获取年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarAnnualList")
	public ResultBean getCarAnnualList(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarAnnualList(carAnnual);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarAnnualList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}

		return r;
	}
	
	/**
	 * 获取年审详情
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarAnnual")
	public ResultBean getCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarAnnual(carAnnual);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarAnnual Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}

	/**
	 * 添加年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarAnnual")
	public ResultBean addCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarAnnual(carAnnual);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarAnnual Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 更新年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarAnnual")
	public ResultBean updateCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarAnnual(carAnnual);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarAnnual Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除年审记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarAnnual")
	public ResultBean deleteCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarAnnual(carAnnual);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarAnnual Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 获取黄绿标检测记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarDetectList")
	public ResultBean getCarDetectList(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarDetectList(carDetect);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarDetectList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 获取黄绿标检测详情
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarDetect")
	public ResultBean getCarDetect(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarDetect(carDetect);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarDetect Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 添加黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarDetect")
	public ResultBean addCarDetect(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarDetect(carDetect);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarDetect Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 更新黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarDetect")
	public ResultBean updateCarDetect(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarDetect(carDetect);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarDetect Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除黄绿标检测
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarDetect")
	public ResultBean deleteCarDetect(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarDetect(carDetect);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarDetect Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	
	/**
	 * 获取 保险信息记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarInsuranceList")
	public ResultBean getCarInsuranceList(CarInsurance carInsurance) {
		ResultBean r = new ResultBean();
		try {
			List<CarInsurance> list = carService.getCarInsuranceList(carInsurance);
			r.setResult(new PageInfo<>(list));
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarInsuranceList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
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
		
		ExportParams params = new ExportParams("保险数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, CarInsurance.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("保险记录" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
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
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarInsurance(carInsurance);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarInsurance Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 添加 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarInsurance")
	public ResultBean addCarInsurance(CarInsurance carInsurance) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarInsurance(carInsurance);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarInsurance Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 更新 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarInsurance")
	public ResultBean updateCarInsurance(CarInsurance carInsurance) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarInsurance(carInsurance);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarInsurance Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除 保险信息
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarInsurance")
	public ResultBean deleteCarInsurance(CarInsurance carInsurance) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarInsurance(carInsurance);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarInsurance Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	
	/**
	 * 获取 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarRepairList")
	public ResultBean getCarRepairList(CarRepair carRepair) {
		ResultBean r = new ResultBean();
		try {
			 r = carService.getCarRepairList(carRepair);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarRepairList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 获取 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarRepair")
	public ResultBean getCarRepair(CarRepair carRepair) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarRepair(carRepair);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarRepair Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
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
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarRepair(carRepair);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarRepair Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
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
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarRepair(carRepair);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarRepair Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除 维修记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarRepair")
	public ResultBean deleteCarRepair(CarRepair carRepair) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarRepair(carRepair);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarRepair Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 获取 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarTaxList")
	public ResultBean getCarTaxList(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarTaxList(carTax);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarTaxList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 获取 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarTax")
	public ResultBean getCarTax(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarTax(carTax);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarTax Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 添加 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarTax")
	public ResultBean addCarTax(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarTax(carTax);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarTax Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 更新 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarTax")
	public ResultBean updateCarTax(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarTax(carTax);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarTax Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除 车船税
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarTax")
	public ResultBean deleteCarTax(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarTax(carTax);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarTax Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 获取油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarOilwearList")
	public ResultBean getCarOilwearList(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarOilwearList(carOilwear);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarOilwearList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 获取油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/getCarOilwear")
	public ResultBean getCarOilwear(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarOilwear(carOilwear);
		} catch (Exception e) {
			logger.error( "*********************************** CarController getCarTax Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 添加油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/addCarOilwear")
	public ResultBean addCarOilwear(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarOilwear(carOilwear);
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarOilwear Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 更新油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/updateCarOilwear")
	public ResultBean updateCarOilwear(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarOilwear(carOilwear);
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarOilwear Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除油耗记录
	 * @param carAnnual
	 * @return
	 */
	@RequestMapping(value = "/deleteCarOilwear")
	public ResultBean deleteCarOilwear(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			r = carService.deleteCarOilwear(carOilwear);
		} catch (Exception e) {
			logger.error("*********************************** CarController deleteCarOilwear Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 导入加油数据
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/importCarOilwear",method=RequestMethod.POST)
	public ResultBean importCarOilwear(@RequestParam("file") MultipartFile file,
			HttpServletRequest request,BaseModel model) throws Exception {
		ResultBean r = new ResultBean();
		List <CarOilwearImport> list = null;
		try {
			User user = AccessWebUtil.getSessionUser(request);
			list = ExcelImportUtil.importExcel(file.getInputStream(), CarOilwearImport.class, new ImportParams());
			r=carService.importCarOilwear(list,user);
		} catch (ExcelImportException e) {
			r.setCode(100);
			r.setMsg("数据解析错误,请检查导入数据模板!");
			return r;
		}
		return r;
	}
	/**
	 * 获取车辆事故理赔列表
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccidentList", method = RequestMethod.GET)
	public ResultBean getCarAccidentList(CarAccident carAccident, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		try {
			List<CarAccident> list = carService.getCarAccidentList(carAccident, getUser(request));
			r.setResult(new PageInfo<>(list));
		} catch (Exception e) {
			logger.error("*********************************** CarController getCarAccidentList Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value = "/getCarAccidentList/export")
	public ResponseEntity<byte[]> getCarAccidentListExport(CarAccident carAccident, HttpServletRequest request) throws IOException {
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM-dd");
		List<Area> areas= areaService.selectAllList(new Area());
		List<CarAccident> list = carService.getCarAccidentList(carAccident, getUser(request));
		
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
		
		ExportParams params = new ExportParams("车辆事故数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, CarAccident.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("车辆事故记录" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
		
	}
	
	/**
	 * 获取车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccident", method = RequestMethod.GET)
	public ResultBean getCarAccident(CarAccident carAccident, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		try {
			r = carService.getCarAccident(carAccident, getUser(request));
		} catch (Exception e) {
			logger.error("*********************************** CarController getCarAccident Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 新增车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/addCarAccident", method = RequestMethod.POST)
	public ResultBean addCarAccident(CarAccident carAccident, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		try {
			r = carService.addCarAccident(carAccident, getUser(request));
		} catch (Exception e) {
			logger.error("*********************************** CarController addCarAccident Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 修改车辆事故理赔
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/updateCarAccident", method = RequestMethod.POST)
	public ResultBean updateCarAccident(CarAccident carAccident, HttpServletRequest request) {
		ResultBean r = new ResultBean();
		try {
			r = carService.updateCarAccident(carAccident, getUser(request));
		} catch (Exception e) {
			logger.error("*********************************** CarController updateCarAccident Error : " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 获取车辆事故理赔导出数据
	 * @param carAccident
	 * @return
	 */
	@RequestMapping(value = "/getCarAccidentExport")
	public ResponseEntity<byte[]> getCarAccidentExport(CarAccident carAccident, HttpServletRequest request) {
		try {
			List<CarAccident> list = carService.getCarAccidentExport(carAccident, getUser(request));
			ExportParams params = new ExportParams("车辆事故理赔导记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, CarAccident.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("车辆事故理赔导记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (Exception e) {
			logger.error("*********************************** CarController getCarAccidentExport Error : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
