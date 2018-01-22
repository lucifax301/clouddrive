package cn.com.liliyun.user.mapper;


import java.util.List;

import cn.com.liliyun.staff.vo.StaffVo;
import cn.com.liliyun.user.model.TopContacts;

public interface TopContactsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopContacts record);

    int insertSelective(TopContacts record);

    TopContacts selectByPrimaryKey(Integer id);
    
    List<StaffVo> selectTopContactsList(TopContacts record);
    
    StaffVo selectTopContacts(TopContacts record);

    int updateByPrimaryKeySelective(TopContacts record);

    int updateByPrimaryKey(TopContacts record);
}