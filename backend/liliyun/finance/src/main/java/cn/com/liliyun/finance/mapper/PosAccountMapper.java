package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.PosAccount;

public interface PosAccountMapper {
    int deleteByPrimaryKey(PosAccount posAccount);

    int insert(PosAccount posAccount);

    int insertSelective(PosAccount posAccount);

    PosAccount selectByPrimaryKey(PosAccount posAccount);

    int updateByPrimaryKeySelective(PosAccount posAccount);

    int updateByPrimaryKey(PosAccount posAccount);
    
    List<PosAccount> selectList(PosAccount posAccount);
    
    int deleteByIds(PosAccount posAccount);
    
    PosAccount selectByAccount(PosAccount posAccount);
}