package cn.com.liliyun.finance.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.finance.model.FinancePosFlow;

public interface FinancePosFlowMapper {
    int deleteByPrimaryKey(FinancePosFlow financePosFlow);

    int insert(FinancePosFlow financePosFlow);

    int insertSelective(FinancePosFlow financePosFlow);

    FinancePosFlow selectByPrimaryKey(FinancePosFlow financePosFlow);

    int updateByPrimaryKeySelective(FinancePosFlow financePosFlow);

    int updateByPrimaryKey(FinancePosFlow financePosFlow);
    
    List<FinancePosFlow> selectList(FinancePosFlow financePosFlow);
    
    int insertBatch(Map<String, Object> map);
}