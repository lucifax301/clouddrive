package cn.com.liliyun.user.mapper;

import java.util.List;

import cn.com.liliyun.common.annotation.DBRoute;
import cn.com.liliyun.user.model.Register;

@DBRoute("MRG")
public interface RegisterMapper {
    int deleteByPrimaryKey(Register register);

    int insert(Register register);

    int insertSelective(Register register);

    Register selectByPrimaryKey(Register register);

    int updateByPrimaryKeySelective(Register register);

    int updateByPrimaryKey(Register register);
    
    Register selectOne(Register register);
    
    List <Register> selectList(Register register);
}