package cn.com.liliyun.car.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CoachCar;
import cn.com.liliyun.importexcel.model.CarOilwearImport;

public interface CarMapper {

	int deleteByPrimaryKey(Car record);
	
	int deleteCarList(Car car);

	int insert(Car record);

	int insertSelective(Car record);

	Car selectByPrimaryKey(Car record);

	int updateCarList(Car car);
	
	int updateByPrimaryKeySelective(Car record);

	int updateByPrimaryKey(Car record);

	List<Car> queryCarList(Car car);
	
	Car selectByCarNo(Car car);
	
	int updateCoachCar(CoachCar coachCar);
	
	List<Car> queryByOilCards(Map map);

	int getCount(Car car);
}