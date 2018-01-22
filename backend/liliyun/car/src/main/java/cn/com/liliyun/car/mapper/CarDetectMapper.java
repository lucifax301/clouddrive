package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarDetect;

public interface CarDetectMapper {
	List<CarDetect> queryCarDetectList(CarDetect record);
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteCarDetectList(CarDetect record);

    int insert(CarDetect record);

    int insertSelective(CarDetect record);

    CarDetect selectByPrimaryKey(Integer id);

    int updateCarDetectList(CarDetect record);
    
    int updateByPrimaryKeySelective(CarDetect record);

    int updateByPrimaryKey(CarDetect record);
}