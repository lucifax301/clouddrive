package cn.com.liliyun.student.mapper;

import cn.com.liliyun.theory.dto.TheoryLessonStoreDto;
import cn.com.liliyun.theory.model.TheoryLesson;
import cn.com.liliyun.theory.model.TheoryLessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TheoryLessonMapper {
    int countByExample(TheoryLessonExample example);

    int deleteByExample(TheoryLessonExample example);

    int deleteByPrimaryKey(Integer theoryid);

    int insert(TheoryLesson record);

    int insertSelective(TheoryLesson record);

    List<TheoryLesson> selectByExample(TheoryLessonExample example);

    TheoryLesson selectByPrimaryKey(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("theoryid") Integer theoryid);

    int updateByExampleSelective(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("record") TheoryLesson record, @Param("example") TheoryLessonExample example);

    int updateByExample(@Param("record") TheoryLesson record, @Param("example") TheoryLessonExample example);

    int updateByPrimaryKeySelective(TheoryLesson record);

    int updateByPrimaryKey(TheoryLesson record);
    
    /*
     * 获取理论课join门店名额表数据
     */
    List<TheoryLessonStoreDto> selectLessonStore(TheoryLessonStoreDto dto);
    /*
     * 获取理论课数据
     */
    List<TheoryLessonStoreDto> selectLesson(TheoryLessonStoreDto dto);
    /*
     * 更新理论课已安排总人数
     */
    int updateLessonArrangedNum(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("theoryid") Integer theoryId);
}