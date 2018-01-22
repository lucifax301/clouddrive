package cn.com.liliyun.car.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.car.manager.ICarManager;
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
import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.BeanCopy;
import cn.com.liliyun.common.util.DateUtil;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.importexcel.model.CarOilwearImport;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Service
public class CarServiceImpl implements ICarService {

	private Logger logger = Logger.getLogger(CarServiceImpl.class);

	@Autowired
	ICarManager carManager;
	
	@Autowired AreaService areaService;
	

	@Override
	public ResultBean getCarList(Car car) {
		ResultBean r = new ResultBean();
		try {
			PageUtil.startPage(car);
			List<Car> list = carManager.queryCarList(car);
			for(Car c:list){
				//计算车龄
				int betWeenMonth=DateUtil.getBetWeenMonth(c.getRegDate(), new Date());
				c.setCarYear(getCarYear(betWeenMonth));	
			}
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			logger.error("*********************************** getCarAnnualList Error: " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean getCar(Car car) {
		ResultBean r = new ResultBean();
		try {
			Car carInfo = null;
			if (car.getCarId() != null)
				carInfo = carManager.queryCar(car);
			else if (car.getCarNo() != null && !"".equals(car.getCarNo()))
				carInfo = carManager.queryCarByCarno(car);
			if (carInfo != null) {
				r.setResult(carInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCar(User user,Car car) {
		ResultBean r = new ResultBean();
		
		Car carInfo = carManager.queryCarByCarno(car);
		if(carInfo!=null){
			r.setCode(ResultCode.ERRORCODE.DATAEXIST);
			r.setMsg("车牌号已经存在");
			return r;
		}
		carManager.addCar(user,car);
		
		return r;
	}

	@Override
	public ResultBean updateCar(User user,Car car) {
		ResultBean r = new ResultBean();
		
		carManager.updateCar(user,car);
		
		return r;
	}

	@Override
	public ResultBean deleteCar(Car car) {
		ResultBean r = new ResultBean();
		
		carManager.deleteCar(car);
		
		return r;
	}
	
	@Override
	public List<CarExport> getList(Car car) {
		PageUtil.startPage(car);
		List<Car> carList = carManager.queryCarList(car);
		List<CarExport> carExpList = new ArrayList<CarExport>();
		try {
			if (carList != null && carList.size() > 0 ) {
				carExpList = BeanCopy.copyList(carList, CarExport.class, BeanCopy.COPY2NULL);
				Area area=new Area();
				area.setDblink(car.getDblink());
				Map<Integer,MapDTO> areaMap= areaService.getMap(area);
				for(CarExport carExport:carExpList){
					carExport.setArea(areaMap.get(carExport.getAreaId()).getName());
					//计算车龄
					int betWeenMonth=DateUtil.getBetWeenMonth(carExport.getRegDate(), new Date());
					String carYear=getCarYear(betWeenMonth);
					carExport.setCarYear(carYear);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carExpList;
	}

	private String getCarYear(int betWeenMonth) {
		if(betWeenMonth>=0&&betWeenMonth<12){
			return "一类车龄";
		}else if(betWeenMonth>=12&&betWeenMonth<24){
			return  "二类车龄";
		}else if(betWeenMonth>=24&&betWeenMonth<36){
			return  "三类车龄";
		}else if(betWeenMonth>=36&&betWeenMonth<48){
			return  "四类车龄";
		}else if(betWeenMonth>=48&&betWeenMonth<60){
			return  "五类车龄";
		}else if(betWeenMonth>=60&&betWeenMonth<72){
			return  "六类车龄";
		}else if(betWeenMonth>=72&&betWeenMonth<82){
			return  "七类车龄";
		}else if(betWeenMonth>=82){
			return  "八类车龄";
		}
		return null;
	}

	@Override
	public ResultBean getCarAnnualList(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			PageUtil.startPage(carAnnual);
			List<CarAnnual> list = carManager.queryCarAnnualList(carAnnual);
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			logger.error("*********************************** getCarAnnualList Error: " + e.getMessage());
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean getCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		try {
			CarAnnual carAnnualInfo = carManager.queryCarAnnual(carAnnual);
			if (carAnnualInfo != null) {
				r.setResult(carAnnualInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarAnnual(CarAnnual ccarAnnual) {
		ResultBean r = new ResultBean();
		
		carManager.addCarAnnual(ccarAnnual);
		
		return r;
	}

	@Override
	public ResultBean updateCarAnnual(CarAnnual carAnnual) {
		ResultBean r = new ResultBean();
		
		carManager.updateCarAnnual(carAnnual);
		
		return r;
	}

	@Override
	public ResultBean deleteCarAnnual(CarAnnual carAnnual) {

		ResultBean r = new ResultBean();
		
		carAnnual.setIsDel(1);
		carManager.deleteCarAnnual(carAnnual);
		
		return r;
	}

	@Override
	public ResultBean getCarDetectList(CarDetect carDetect) {
		ResultBean r = new ResultBean();
		try {
			PageUtil.startPage(carDetect);
			List<CarDetect> list = carManager.queryCarDetectList(carDetect);
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean getCarDetect(CarDetect carDetect) {

		ResultBean r = new ResultBean();
		try {
			CarDetect carDetectInfo =carManager.queryCarDetect(carDetect);
			logger.info("*************************************** carDetectInfo " + carDetectInfo);
			if (carDetectInfo != null) {
				r.setResult(carDetectInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarDetect(CarDetect carDetect) {

		ResultBean r = new ResultBean();
		
		carManager.addCarDetect(carDetect);
		
		return r;
	}

	@Override
	public ResultBean updateCarDetect(CarDetect carDetect) {

		ResultBean r = new ResultBean();
		
		carManager.updateCarDetect(carDetect);
		
		return r;
	}

	@Override
	public ResultBean deleteCarDetect(CarDetect carDetect) {

		ResultBean r = new ResultBean();
		
		carManager.deleteCarDetect(carDetect);
		
		return r;
	}

	@Override
	public List<CarInsurance> getCarInsuranceList(CarInsurance carInsurance) {
		PageUtil.startPage(carInsurance);
		List<CarInsurance> list = carManager.queryCarInsuranceList(carInsurance);
		return list;
	}

	@Override
	public ResultBean getCarInsurance(CarInsurance carInsurance) {

		ResultBean r = new ResultBean();
		try {
			CarInsurance carInsuranceInfo = carManager.queryCarInsurance(carInsurance);
			if (carInsuranceInfo != null) { 
				r.setResult(carInsuranceInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarInsurance(CarInsurance carInsurance) {

		ResultBean r = new ResultBean();
		
		carManager.addCarInsurance(carInsurance);
		
		return r;
	}

	@Override
	public ResultBean updateCarInsurance(CarInsurance carInsurance) {

		ResultBean r = new ResultBean();
		
		carManager.updateCarInsurance(carInsurance);
		
		return r;
	}

	@Override
	public ResultBean deleteCarInsurance(CarInsurance carInsurance) {

		ResultBean r = new ResultBean();
		
		carManager.deleteCarInsurance(carInsurance);
		
		return r;
	}

	@Override
	public ResultBean getCarOilwearList(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			if (carOilwear.getEtime() != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(carOilwear.getEtime());
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				calendar.add(Calendar.SECOND, -1);
				carOilwear.setEtime(calendar.getTime());
			}
			PageUtil.startPage(carOilwear);
			List<CarOilwear> list = carManager.queryCarOilwearList(carOilwear);
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}
	

	@Override
	public ResultBean getCarOilwear(CarOilwear carOilwear) {
		ResultBean r = new ResultBean();
		try {
			CarOilwear carOilwearInfo = carManager.queryCarOilwear(carOilwear);
			if(carOilwearInfo != null) { 
				r.setResult(carOilwearInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}


	@Override
	public ResultBean addCarOilwear(CarOilwear carOilwear) {

		ResultBean r = new ResultBean();
		
		carManager.addCarOilwear(carOilwear);
		
		return r;
	}

	@Override
	public ResultBean updateCarOilwear(CarOilwear carOilwear) {

		ResultBean r = new ResultBean();
		
		carManager.updateCarOilwear(carOilwear);
		
		return r;
	}

	@Override
	public ResultBean deleteCarOilwear(CarOilwear carOilwear) {

		ResultBean r = new ResultBean();
		
		carManager.deleteCarOilwear(carOilwear);
		
		return r;
	}

	@Override
	public ResultBean getCarRepairList(CarRepair carRepair) {
		ResultBean r = new ResultBean();
		try {
			PageUtil.startPage(carRepair);
			List<CarRepair> list = carManager.queryCarRepairList(carRepair);
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean getCarRepair(CarRepair carRepair) {

		ResultBean r = new ResultBean();
		try {
			CarRepair carRepairInfo = carManager.queryCarRepair(carRepair);
			if (carRepairInfo != null) { 
				r.setResult(carRepairInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarRepair(CarRepair carRepair) {

		ResultBean r = new ResultBean();
		
		carManager.addCarRepair(carRepair);
		
		return r;
	}

	@Override
	public ResultBean updateCarRepair(CarRepair carRepair) {

		ResultBean r = new ResultBean();
		
		carManager.updateCarRepair(carRepair);
		
		return r;
	}

	@Override
	public ResultBean deleteCarRepair(CarRepair carRepair) {

		ResultBean r = new ResultBean();
		
		carManager.deleteCarRepair(carRepair);
		
		return r;
	}

	@Override
	public ResultBean getCarTaxList(CarTax carTax) {
		ResultBean r = new ResultBean();
		try {
			PageUtil.startPage(carTax);
			List<CarTax> list = carManager.queryCarTaxList(carTax);
			r.setResult(new PageInfo<>(list));
		} 
		catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean getCarTax(CarTax carTax) {

		ResultBean r = new ResultBean();
		try {
			CarTax carTaxInfo = carManager.queryCarTax(carTax);
			if (carTaxInfo != null) { 
				r.setResult(carTaxInfo);
			}
		} catch (Exception e) {
			r.setCode(ResultCode.ERRORCODE.EXCEPTION);
			r.setMsg(ResultCode.ERRORINFO.EXCEPTION);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarTax(CarTax carTax) {

		ResultBean r = new ResultBean();
		
		carManager.addCarTax(carTax);
		
		return r;
	}

	@Override
	public ResultBean updateCarTax(CarTax carTax) {

		ResultBean r = new ResultBean();
		
		carManager.updateCarTax(carTax);
		
		return r;
	}

	@Override
	public ResultBean deleteCarTax(CarTax carTax) {

		ResultBean r = new ResultBean();
		
			carManager.deleteCarTax(carTax);
		
		return r;
	}

	@Override
	public List<CarAccident> getCarAccidentList(CarAccident carAccident, User user) {
		carAccident.setDblink(user.getDblink());
		PageUtil.startPage(carAccident);
		List<CarAccident> list = carManager.queryCarAccidentList(carAccident);
		return list;
		
	}

	@Override
	public ResultBean getCarAccident(CarAccident carAccident, User user) {
		ResultBean r = new ResultBean();
		try {
			carAccident.setDblink(user.getDblink());
			CarAccident ca = carManager.queryCarAccident(carAccident);
			r.setResult(ca);
		} catch (Exception e) {
			r.setCode(HttpConstant.ERROR_CODE);
			r.setMsg(HttpConstant.ERROR_MSG);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean addCarAccident(CarAccident carAccident, User user) {
		ResultBean r = new ResultBean();
		
		carAccident.setDblink(user.getDblink());
		carAccident.setCuid(user.getId());
		carManager.addCarAccident(carAccident);
		
		return r;
	}

	@Override
	public ResultBean updateCarAccident(CarAccident carAccident, User user) {
		ResultBean r = new ResultBean();
		
		carAccident.setDblink(user.getDblink());
		carAccident.setCtime(null);
		carAccident.setCuid(null);
		carAccident.setMuid(user.getId());
		carManager.updateCarAccident(carAccident);
		
		return r;
	}

	@Override
	public List<CarAccident> getCarAccidentExport(CarAccident carAccident, User user) {
		List<CarAccident> r = null;
		try {
			carAccident.setDblink(user.getDblink());
			r = carManager.queryCarAccidentList(carAccident);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ResultBean importCarOilwear(List<CarOilwearImport> list,User user) {
		ResultBean r = new ResultBean();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("dblink", user.getDblink());
		params.put("list", list);
		List<Car> carList=carManager.queryCarListOilCards(params);
		Iterator<CarOilwearImport> it= list.iterator();
		while(it.hasNext()){
			CarOilwearImport carOilwear=it.next();
			String oilCard=carOilwear.getOilCard();
			if(oilCard!=null&&!"".equals(oilCard)){
				boolean exist=false;
				for(Car car:carList){
					if(oilCard.equals(car.getOilCard())){
						carOilwear.setOilType(carOilwear.getOilType_str()!=null?getOilType(carOilwear.getOilType_str()):null);
						carOilwear.setCarId(car.getCarId());
						carOilwear.setCarNo(car.getCarNo());
						exist=true;
						break;
					}
				}
				if(!exist){
					it.remove();
				}
			}			
		}	
		if(list.size()>0){
			params.put("list", list);
			params.put("dblink", user.getDblink());
			carManager.importCarOilwear(params);
		}
		return r;
	}

	private Integer getOilType(String oliTypeStr) {
		String[] oliType=oliTypeStr.split("号");//由于导入模板的限制  类似92号车用汽油(V)
		String prefix=oliType.length>0?oliType[0]:null;
		switch (prefix) {
		case "0":
			return 0;
		case "90":
			return 1;
		case "92":
			return 2;
		case "93":
			return 3;
		case "95":
			return 4;
		case "97":
			return 5;
		case "98":
			return 6;
		case "电":
			return 7;
		}
		return null;
	}

}
