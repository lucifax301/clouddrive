package cn.com.liliyun.car.service;


import java.util.List;

import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CarAccident;
import cn.com.liliyun.car.model.CarAnnual;
import cn.com.liliyun.car.model.CarDetect;
import cn.com.liliyun.car.model.CarExport;
import cn.com.liliyun.car.model.CarInsurance;
import cn.com.liliyun.car.model.CarOilwear;
import cn.com.liliyun.car.model.CarRepair;
import cn.com.liliyun.car.model.CarTax;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.importexcel.model.CarOilwearImport;


/**
 * 
 * @author lzb
 *
 */
public interface ICarService {
	
	/**
	 * 车辆基础信息
	 * @param car
	 * @return
	 */
	ResultBean getCarList(Car car);
	
	ResultBean getCar(Car car);
	
	ResultBean addCar(Car car);
	
	ResultBean updateCar(Car car);
	
	ResultBean deleteCar(Car car);

	List<CarExport> getList(Car car);
	
	/**
	 * 年审记录操作
	 * @param CarAnnual
	 * @return
	 */
	ResultBean getCarAnnualList(CarAnnual carAnnual);
	
	ResultBean getCarAnnual(CarAnnual carAnnual);
	
	ResultBean addCarAnnual(CarAnnual ccarAnnual);
	
	ResultBean updateCarAnnual(CarAnnual carAnnual);
	
	ResultBean deleteCarAnnual(CarAnnual carAnnual);
	
	/**
	 * 黄绿标检测
	 * @param carDetect
	 * @return
	 */
	ResultBean getCarDetectList(CarDetect carDetect);
	
	ResultBean getCarDetect(CarDetect carDetect);
	
	ResultBean addCarDetect(CarDetect carDetect);
	
	ResultBean updateCarDetect(CarDetect carDetect);
	
	ResultBean deleteCarDetect(CarDetect carDetect);
	
	/**
	 * 保险信息
	 * @param CarInsurance
	 * @return
	 */
	List<CarInsurance> getCarInsuranceList(CarInsurance carInsurance);
	
	ResultBean getCarInsurance(CarInsurance carInsurance);
	
	ResultBean addCarInsurance(CarInsurance carInsurance);
	
	ResultBean updateCarInsurance(CarInsurance carInsurance);
	
	ResultBean deleteCarInsurance(CarInsurance carInsurance);
	
	
	/**
	 * 油耗记录
	 */
	ResultBean getCarOilwearList(CarOilwear carOilwear);
	
	ResultBean getCarOilwear(CarOilwear carOilwear);
	
	ResultBean addCarOilwear(CarOilwear carOilwear);
	
	ResultBean updateCarOilwear(CarOilwear carOilwear);
	
	ResultBean deleteCarOilwear(CarOilwear carOilwear);
	
	ResultBean importCarOilwear(List<CarOilwearImport> list);
	
	
	/**
	 * 维修记录
	 * @param CarRepair
	 * @return
	 */
	ResultBean getCarRepairList(CarRepair carRepair);
	
	ResultBean getCarRepair(CarRepair carRepair);
	
	ResultBean addCarRepair(CarRepair carRepair);
	
	ResultBean updateCarRepair(CarRepair carRepair);
	
	ResultBean deleteCarRepair(CarRepair carRepair);
	
	/**
	 * 车船税
	 * @param carTax
	 * @return
	 */
	ResultBean getCarTaxList(CarTax carTax);
	
	ResultBean getCarTax(CarTax carTax);
	
	ResultBean addCarTax(CarTax carTax);
	
	ResultBean updateCarTax(CarTax carTax);
	
	ResultBean deleteCarTax(CarTax carTax);

	
	/**
	 * 车辆事故处理
	 * @param carAccident
	 * @return
	 */
	List<CarAccident> getCarAccidentList(CarAccident carAccident);
	
	ResultBean getCarAccident(CarAccident carAccident);
	
	ResultBean addCarAccident(CarAccident carAccident);
	
	ResultBean updateCarAccident(CarAccident carAccident);
	
	List<CarAccident> getCarAccidentExport(CarAccident carAccident);


}
