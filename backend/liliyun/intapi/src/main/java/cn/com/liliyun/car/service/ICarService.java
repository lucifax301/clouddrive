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
import cn.com.liliyun.user.model.User;

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
	public ResultBean getCarList(Car car);
	
	public ResultBean getCar(Car car);
	
	public ResultBean addCar(User user,Car car);
	
	public ResultBean updateCar(User user,Car car);
	
	public ResultBean deleteCar(Car car);

	public List<CarExport> getList(Car car);
	
	/**
	 * 年审记录操作
	 * @param CarAnnual
	 * @return
	 */
	public ResultBean getCarAnnualList(CarAnnual carAnnual);
	
	public ResultBean getCarAnnual(CarAnnual carAnnual);
	
	public ResultBean addCarAnnual(CarAnnual ccarAnnual);
	
	public ResultBean updateCarAnnual(CarAnnual carAnnual);
	
	public ResultBean deleteCarAnnual(CarAnnual carAnnual);
	
	/**
	 * 黄绿标检测
	 * @param carDetect
	 * @return
	 */
	public ResultBean getCarDetectList(CarDetect carDetect);
	
	public ResultBean getCarDetect(CarDetect carDetect);
	
	public ResultBean addCarDetect(CarDetect carDetect);
	
	public ResultBean updateCarDetect(CarDetect carDetect);
	
	public ResultBean deleteCarDetect(CarDetect carDetect);
	
	/**
	 * 保险信息
	 * @param CarInsurance
	 * @return
	 */
	public List<CarInsurance> getCarInsuranceList(CarInsurance carInsurance);
	
	public ResultBean getCarInsurance(CarInsurance carInsurance);
	
	public ResultBean addCarInsurance(CarInsurance carInsurance);
	
	public ResultBean updateCarInsurance(CarInsurance carInsurance);
	
	public ResultBean deleteCarInsurance(CarInsurance carInsurance);
	
	
	/**
	 * 油耗记录
	 */
	public ResultBean getCarOilwearList(CarOilwear carOilwear);
	
	public ResultBean getCarOilwear(CarOilwear carOilwear);
	
	public ResultBean addCarOilwear(CarOilwear carOilwear);
	
	public ResultBean updateCarOilwear(CarOilwear carOilwear);
	
	public ResultBean deleteCarOilwear(CarOilwear carOilwear);
	
	public ResultBean importCarOilwear(List<CarOilwearImport> list, User user);
	
	
	/**
	 * 维修记录
	 * @param CarRepair
	 * @return
	 */
	public ResultBean getCarRepairList(CarRepair carRepair);
	
	public ResultBean getCarRepair(CarRepair carRepair);
	
	public ResultBean addCarRepair(CarRepair carRepair);
	
	public ResultBean updateCarRepair(CarRepair carRepair);
	
	public ResultBean deleteCarRepair(CarRepair carRepair);
	
	/**
	 * 车船税
	 * @param carTax
	 * @return
	 */
	public ResultBean getCarTaxList(CarTax carTax);
	
	public ResultBean getCarTax(CarTax carTax);
	
	public ResultBean addCarTax(CarTax carTax);
	
	public ResultBean updateCarTax(CarTax carTax);
	
	public ResultBean deleteCarTax(CarTax carTax);

	
	/**
	 * 车辆事故处理
	 * @param carAccident
	 * @return
	 */
	public List<CarAccident> getCarAccidentList(CarAccident carAccident, User user);
	
	public ResultBean getCarAccident(CarAccident carAccident, User user);
	
	public ResultBean addCarAccident(CarAccident carAccident, User user);
	
	public ResultBean updateCarAccident(CarAccident carAccident, User user);
	
	public List<CarAccident> getCarAccidentExport(CarAccident carAccident, User user);


}
