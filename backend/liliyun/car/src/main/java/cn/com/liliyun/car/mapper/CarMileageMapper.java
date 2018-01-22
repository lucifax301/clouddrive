package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarMileage;

public interface CarMileageMapper {
    int deleteByPrimaryKey(CarMileage record);

    int insert(CarMileage record);

    int insertSelective(CarMileage record);

    CarMileage selectByPrimaryKey(CarMileage record);

    int updateByPrimaryKeySelective(CarMileage record);

    int updateByPrimaryKey(CarMileage record);
    
    List <CarMileage> selectList(CarMileage mileage);
}