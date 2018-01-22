package cn.com.liliyun.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.finance.model.FinanceSubject;

public interface FinanceSubjectMapper {
    int deleteByPrimaryKey(FinanceSubject financeSubject);

    int insert(FinanceSubject financeSubject);

    int insertSelective(FinanceSubject financeSubject);

    FinanceSubject selectByPrimaryKey(FinanceSubject financeSubject);

    int updateByPrimaryKeySelective(FinanceSubject financeSubject);

    int updateByPrimaryKey(FinanceSubject financeSubject);
    
    List<FinanceSubject> selectList(FinanceSubject financeSubject);
    
    int updateByModel(FinanceSubject financeSubject);
    
    @MapKey("id")
    Map<Integer, MapDTO> getMap(FinanceSubject financeSubject);
}