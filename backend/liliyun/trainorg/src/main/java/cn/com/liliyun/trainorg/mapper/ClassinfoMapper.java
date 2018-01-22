package cn.com.liliyun.trainorg.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.trainorg.model.Classinfo;

public interface ClassinfoMapper {
    int deleteByPrimaryKey(Classinfo record);
    
    int deleteByClassType(Classinfo record);

    int insert(Classinfo record);
    
    int batchinsert(Map map);
    
    int batchdel(Map map);

    int insertSelective(Classinfo record);

    Classinfo selectByPrimaryKey(Classinfo record);

    int updateByPrimaryKeySelective(Classinfo record);
    
    int updateStatus(Classinfo record);
    

    int updateByPrimaryKey(Classinfo record);
    
    List<Classinfo> selectList(Classinfo classinfo);
    
    @MapKey("id")
    Map<Integer, MapDTO> getMap(Classinfo classinfo);
}