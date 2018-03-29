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
	
	public ResultBean getCarPartsList(CarParts carParts);
	
	public ResultBean addCarParts(CarParts carParts);
	
	public List<CarParts> getCarPartsExport(CarParts carParts);
	
	public List<PartsSetting> getPartsSettings();

	public ResultBean setPartsSetting(List<PartsSetting> partsSettings);
	
	public ResultBean getPartsNotice(PartsSetting partsSetting);

	public ResultBean editCarParts(CarParts carParts);

	public Integer getCount(Car car);

	ResultBean addLog(CarLog carLog);

	CarLog getLog(CarLog carLog);

	void updateAuditLog(CarLog carLog,int i);
	
}
