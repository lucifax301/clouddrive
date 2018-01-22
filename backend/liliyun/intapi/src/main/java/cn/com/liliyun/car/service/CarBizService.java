package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.*;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.User;

import java.util.List;

public interface CarBizService {

	CarCostRemind getCostRemind(CarCostRemind costRemind);
	
	ResultBean updateCostRemind(CarCostRemind costRemind);
	
	List <CarRemind> listCarRemind(User user,CarCostRemind carCostRemind);
	
	List <CarMileage> listMileage(CarMileage mileage);
	
	List <CarScrap> listScrap(CarScrap carScrap);
	
	ResultBean editScrap(User user,CarScrap carScrap);
	
	ResultBean addDelay(User user,CarScrap carScrap);
	
	ResultBean editDelay(User user,CarScrap carScrap);
	
	CarMileage getMileage(CarMileage mileage);
	
	ResultBean editMileage(User user,CarMileage mileage);
	
	List<CarLog> getCarLogList(CarLog carLog);
	
	public ResultBean getCarPartsList(CarParts carParts, User user);
	
	public ResultBean addCarParts(CarParts carParts, User user);
	
	public List<CarParts> getCarPartsExport(CarParts carParts, User user);
	
	public List<PartsSetting> getPartsSettings(User user);

	public ResultBean setPartsSetting(List<PartsSetting> partsSettings, User user);
	
	public ResultBean getPartsNotice(PartsSetting partsSetting, User user);

	public ResultBean editCarParts(CarParts carParts, User user);

	public Integer getCount(Car car);

	ResultBean addLog(CarLog carLog, User user, String bussinessid);

	CarLog getLog(CarLog carLog, User user);

	void updateAuditLog(CarLog carLog, User user, String bussinessid, int i);
	
}
