package cn.com.liliyun.student.mapper;

import cn.com.liliyun.theory.model.TheoryStudent;
import cn.com.liliyun.theory.model.TheoryStudentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TheoryStudentMapper {
    int countByExample(TheoryStudentExample example);

    int deleteByExample(TheoryStudentExample example);

    int insert(TheoryStudent record);

    int insertSelective(TheoryStudent record);

    List<TheoryStudent> selectByExample(TheoryStudentExample example);

    int updateByExampleSelective(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("record") TheoryStudent record, @Param("example") TheoryStudentExample example);

    int updateByExample(@Param("record") TheoryStudent record, @Param("example") TheoryStudentExample example);
    
    int insertSelectiveBatch(Map<String, Object> theoryStudents);
    
    int deleteByTheoryId(@Param("dblink") String dblink, @Param("mgrdb") Boolean mgrdb, @Param("theoryId") Integer theoryId, @Param("storeId") Integer storeId);
    
}