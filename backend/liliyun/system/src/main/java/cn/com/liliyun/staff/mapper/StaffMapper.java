package cn.com.liliyun.staff.mapper;

import java.util.List;

import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.vo.StaffVo;

public interface StaffMapper {
    
    int insert(Staff staff);
   
    Staff selectByPrimaryKey(Staff staff);

    int update(Staff staff);
    
    int updateByPrimaryKeySelective(Staff staff);
    
    int delete(Staff staff);
    
    List<Staff> selectByCondition(Staff staff);

	StaffVo selectStaffVo(Staff staff);
   
}