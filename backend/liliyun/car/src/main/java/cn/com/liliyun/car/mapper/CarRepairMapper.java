package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarRepair;

public interface CarRepairMapper {
	List<CarRepair> queryCarRepairList(CarRepair record);
	
    int deleteCarRepairList(CarRepair record);
    
    int deleteByPrimaryKey(Integer id);

    int insert(CarRepair record);

    int insertSelective(CarRepair record);

    CarRepair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarRepair record);
    
    int updateCarRepairList(CarRepair record);

    int updateByPrimaryKey(CarRepair record);
}