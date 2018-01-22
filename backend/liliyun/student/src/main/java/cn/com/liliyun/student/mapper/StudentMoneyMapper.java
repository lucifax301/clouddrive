package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.dto.StudentMoneyDTO;
import cn.com.liliyun.student.model.StudentMoney;

public interface StudentMoneyMapper {
    int deleteByPrimaryKey(StudentMoney record);

    int insert(StudentMoney record);

    int insertSelective(StudentMoney record);

    StudentMoney selectByPrimaryKey(StudentMoney record);

    int updateByPrimaryKeySelective(StudentMoney record);

    int updateByPrimaryKey(StudentMoney record);
    
    List <StudentMoneyDTO> selectOweList(StudentMoneyDTO studentMoneyDTO);
    
    int updateReceiptOweMoney(Map<String, Object> map);
    
    int updateReceiptOweStatus(StudentMoney studentMoney);
}