package cn.com.liliyun.car.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.car.model.CarOilwear;
import cn.com.liliyun.importexcel.model.CarOilwearImport;

public interface CarOilwearMapper {
	List<CarOilwear> queryCarOilwearList(CarOilwear record);
	
    int deleteCarOilwearList(CarOilwear record);
    
    int deleteByPrimaryKey(Integer id);

    int insert(CarOilwear record);

    int insertSelective(CarOilwear record);

    CarOilwear selectByPrimaryKey(Integer id);

    int updateCarOilwearList(CarOilwear record);
    
    int updateByPrimaryKeySelective(CarOilwear record);

    int updateByPrimaryKey(CarOilwear record);
    
    int addCarOilwearBatch(Map map);
}