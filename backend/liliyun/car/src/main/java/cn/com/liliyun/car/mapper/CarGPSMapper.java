package cn.com.liliyun.car.mapper;

import cn.com.liliyun.car.model.CarGPS;

public interface CarGPSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarGPS record);

    int insertSelective(CarGPS record);

    CarGPS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarGPS record);

    int updateByPrimaryKey(CarGPS record);
    
}