package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarAnnual;


public interface CarAnnualMapper {
	
	List<CarAnnual> queryCarAnnualList(CarAnnual record);
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteCarAnnualList(CarAnnual record);

    int insert(CarAnnual record);

    int insertSelective(CarAnnual record);

    CarAnnual selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(CarAnnual record);
    
    int updateCarAnnualList(CarAnnual record);

    int updateByPrimaryKey(CarAnnual record);
}