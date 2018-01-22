package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarAccident;

public interface CarAccidentMapper {
    int deleteByPrimaryKey(CarAccident record);

    int insert(CarAccident record);

    int insertSelective(CarAccident record);

    CarAccident selectByPrimaryKey(CarAccident record);

    int updateByPrimaryKeySelective(CarAccident record);

    int updateByPrimaryKey(CarAccident record);
    
    List<CarAccident> selectList(CarAccident carAccident);
}