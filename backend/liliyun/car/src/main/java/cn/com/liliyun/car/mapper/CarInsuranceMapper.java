package cn.com.liliyun.car.mapper;

import java.util.List;

import cn.com.liliyun.car.model.CarInsurance;

public interface CarInsuranceMapper {
	List<CarInsurance> queryCarInsuranceList(CarInsurance record);
	
    int deleteCarInsuranceList(CarInsurance record);
    
    int deleteByPrimaryKey(Integer id);

    int insert(CarInsurance record);

    int insertSelective(CarInsurance record);

    CarInsurance selectByPrimaryKey(Integer id);

    int updateCarInsuranceList(CarInsurance record);
    
    int updateByPrimaryKeySelective(CarInsurance record);

    int updateByPrimaryKey(CarInsurance record);
}