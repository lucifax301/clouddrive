package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarLog;

public interface CarLogMapper {
    int deleteByPrimaryKey(CarLog record);

    int insert(CarLog record);

    int insertSelective(CarLog record);

    CarLog selectByPrimaryKey(CarLog record);

    int updateByPrimaryKeySelective(CarLog record);

    int updateByPrimaryKey(CarLog record);
    
    List <CarLog> selectList(CarLog carLog);
}