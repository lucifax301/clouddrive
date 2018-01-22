package cn.com.liliyun.car.mapper;


import java.util.List;

import cn.com.liliyun.car.model.CarTax;

public interface CarTaxMapper {
	List<CarTax> queryCarTaxList(CarTax record);
	
    int deleteCarTaxList(CarTax record);
    
    int deleteByPrimaryKey(Integer id);

    int insert(CarTax record);

    int insertSelective(CarTax record);

    CarTax selectByPrimaryKey(Integer id);

    int updateCarTaxList(CarTax record);
    
    int updateByPrimaryKeySelective(CarTax record);

    int updateByPrimaryKey(CarTax record);
}