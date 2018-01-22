package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarScrap;

public interface CarScrapMapper {
  
	int deleteByPrimaryKey(CarScrap record);

    int insert(CarScrap record);

    int insertSelective(CarScrap record);

    CarScrap selectByPrimaryKey(CarScrap record);

    int updateByPrimaryKeySelective(CarScrap record);

    int updateByPrimaryKey(CarScrap record);
    
    List <CarScrap> selectList(CarScrap carScrap);
}