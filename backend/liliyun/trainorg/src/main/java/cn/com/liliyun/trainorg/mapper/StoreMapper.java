package cn.com.liliyun.trainorg.mapper;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.trainorg.model.Store;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface StoreMapper {
    public int deleteByPrimaryKey(Store store);

    public int insert(Store record);

    public int insertSelective(Store record);

    public Store selectByPrimaryKey(Store store);

    public int updateByPrimaryKeySelective(Store record);

    public int updateByPrimaryKey(Store record);
    
    public List<Store> selectList(Store store);
    
    @MapKey("id")
    Map<Integer, MapDTO> getMap(Store store);

    public int getCount(Store store);
}