package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.*;
import cn.com.liliyun.common.model.ResultBean;

import java.util.List;

public interface CarBizService {

	CarCostRemind getCostRemind(CarCostRemind costRemind);
	
	ResultBean updateCostRemind(CarCostRemind costRemind);
	
	List <CarRemind> listCarRemind(CarCostRemind carCostRemind);
	
	List <CarMileage> listMileage(CarMileage mileage);
	
	List <CarScrap> listScrap(CarScrap carScrap);
	
	ResultBean editScrap(CarScrap carScrap);
	
	ResultBean addDelay(CarScrap carScrap);
	
	ResultBean editDelay(CarScrap carScrap);
	
	CarMileage getMileage(CarMileage mileage);
	
	ResultBean editMileage(CarMileage mileage);
	
	List<CarLog> getCarLogList(CarLog carLog);
	
	ResultBean getCarPartsList(CarParts carParts);
	
	ResultBean addCarParts(CarParts carParts);
	
	List<CarParts> getCarPartsExport(CarParts carParts);
	
	List<PartsSetting> getPartsSettings();

	ResultBean setPartsSetting(List<PartsSetting> partsSettings);
	
	ResultBean getPartsNotice(PartsSetting partsSetting);

	ResultBean editCarParts(CarParts carParts);

	Integer getCount(Car car);

	ResultBean addLog(CarLog carLog);

	CarLog getLog(CarLog carLog);

	void updateAuditLog(CarLog carLog,int i);
	
}
