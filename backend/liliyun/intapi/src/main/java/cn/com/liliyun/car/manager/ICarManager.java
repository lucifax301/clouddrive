package cn.com.liliyun.car.manager;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CarAccident;
import cn.com.liliyun.car.model.CarAnnual;
import cn.com.liliyun.car.model.CarDetect;
import cn.com.liliyun.car.model.CarInsurance;
import cn.com.liliyun.car.model.CarOilwear;
import cn.com.liliyun.car.model.CarRepair;
import cn.com.liliyun.car.model.CarTax;



public interface ICarManager {
	
	List<Car> queryCarList(Car car);

	Car queryCar(Car car);
	
	Car queryCarByCarno(Car car);

	void addCar(Car car);

	void updateCar(Car car);

	void deleteCar(Car car);

	 List<CarAnnual> queryCarAnnualList(CarAnnual carAnnual);
	 
	 CarAnnual queryCarAnnual(CarAnnual carAnnual);
	
	 void addCarAnnual(CarAnnual ccarAnnual);
	
	 void updateCarAnnual(CarAnnual carAnnual);
	
	 void deleteCarAnnual(CarAnnual carAnnual);
	
	 List<CarDetect> queryCarDetectList(CarDetect carDetect);
	 
	 CarDetect queryCarDetect(CarDetect carDetect);
	
	 void addCarDetect(CarDetect carDetect);
	
	 void updateCarDetect(CarDetect carDetect);
	
	 void deleteCarDetect(CarDetect carDetect);
	
	 List<CarInsurance> queryCarInsuranceList(CarInsurance carInsurance);
	 
	 CarInsurance queryCarInsurance(CarInsurance carInsurance);
	
	 void addCarInsurance(CarInsurance carInsurance);
	
	 void updateCarInsurance(CarInsurance carInsurance);
	
	 void deleteCarInsurance(CarInsurance carInsurance);
	
	 List<CarOilwear> queryCarOilwearList(CarOilwear carOilwear);
	 
	 CarOilwear queryCarOilwear(CarOilwear carOilwear);
	 
	 void addCarOilwear(CarOilwear carOilwear);
	
	 void updateCarOilwear(CarOilwear carOilwear);
	
	 void deleteCarOilwear(CarOilwear carOilwear);
	
	 List<CarRepair> queryCarRepairList(CarRepair carRepair);
	 
	 CarRepair queryCarRepair(CarRepair carRepair);
	
	 void addCarRepair(CarRepair carRepair);
	
	 void updateCarRepair(CarRepair carRepair);
	
	 void deleteCarRepair(CarRepair carRepair);
	
	 List<CarTax> queryCarTaxList(CarTax carTax);
	 
	 CarTax queryCarTax(CarTax carTax);
	
	 void addCarTax(CarTax carTax);
	
	 void updateCarTax(CarTax carTax);
	
	 void deleteCarTax(CarTax carTax);

	List<CarAccident> queryCarAccidentList(CarAccident carAccident);
	
	CarAccident queryCarAccident(CarAccident carAccident);
	
	void addCarAccident(CarAccident carAccident);
	
	void updateCarAccident(CarAccident carAccident);

	void importCarOilwear(Map params);

	List<Car> queryCarListOilCards(Map params);

}
