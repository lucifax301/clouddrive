package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarCostRemind;
import cn.com.liliyun.car.model.CarRemind;

public interface CarCostRemindMapper {
    int insert(CarCostRemind record);

    int insertSelective(CarCostRemind record);
    
    CarCostRemind selectCost(CarCostRemind record);
    
    int updateSelective(CarCostRemind record);
    
    List <CarRemind> selectRemindList(CarCostRemind remind);
}