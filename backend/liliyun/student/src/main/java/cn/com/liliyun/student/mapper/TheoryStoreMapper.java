package cn.com.liliyun.student.mapper;

import cn.com.liliyun.theory.dto.TheoryStoreDto;
import cn.com.liliyun.theory.model.TheoryStore;
import cn.com.liliyun.theory.model.TheoryStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TheoryStoreMapper {
    int countByExample(TheoryStoreExample example);

    int deleteByExample(TheoryStoreExample example);

    int deleteByPrimaryKey(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("theoryid") Integer id);

    int insert(TheoryStore record);

    int insertSelective(TheoryStore record);

    List<TheoryStore> selectByExample(TheoryStoreExample example);

    TheoryStore selectByPrimaryKey(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("theoryid") Integer id);

    int updateByExampleSelective(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("record") TheoryStore record, @Param("example") TheoryStoreExample example);

    int updateByExample(@Param("record") TheoryStore record, @Param("example") TheoryStoreExample example);

    int updateByPrimaryKeySelective(TheoryStore record);

    int updateByPrimaryKey(TheoryStore record);
    
    /*
     * 获取某个理论课的门店数据
     */
    List<TheoryStoreDto> selectTheoryStore(TheoryStore theoryStore);
    
    /*
     * 根据片区ID获取门店列表
     */
    List<TheoryStoreDto> selectStoreByAreaId(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("areaid") Integer areaId);
    
    /*
     * 更新门店列表的已安排学员数,theoryId、storeId不能为空！
     */
    int updateStoreArrangedNum(TheoryStore theoryStore);
}