package cn.com.liliyun.car.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.car.model.CarParts;
import cn.com.liliyun.car.model.CarPartsNotice;

public interface CarPartsMapper {
    int deleteByPrimaryKey(CarParts carParts);

    int insert(CarParts carParts);

    int insertSelective(CarParts carParts);

    CarParts selectByPrimaryKey(CarParts carParts);

    int updateByPrimaryKeySelective(CarParts carParts);

    int updateByPrimaryKey(CarParts carParts);
    
    List<CarParts> selectList(CarParts carParts);
    
    /**
     * 根据carid（必填）、parttype更新数据
     * @param carParts
     * @return
     */
    int updateByCarOrTypeSelective(CarParts carParts);
    
    List<CarPartsNotice> selectPartsNotice(Map<String, Object> map);
}