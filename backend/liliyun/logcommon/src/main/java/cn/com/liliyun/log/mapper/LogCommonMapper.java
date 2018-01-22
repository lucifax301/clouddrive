package cn.com.liliyun.log.mapper;

import java.util.List;

import cn.com.liliyun.log.model.LogCommon;



/**
 * 日志DAO
 * @author lzb
 *
 */
public interface LogCommonMapper {
	
	/**
	 * 查询日志记录
	 * @param params
	 * @return
	 */
	List<LogCommon> findBatch(LogCommon dto);
	

	Long addOne(LogCommon logCommon);
	
	/**
	 * 批量插入日志
	 * @param logCommonList
	 */
	void inertLogList(List<LogCommon> logCommonList);
	
    int deleteByPrimaryKey(Integer id);

    int insert(LogCommon record);

    int insertSelective(LogCommon record);

    LogCommon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogCommon record);

    int updateByPrimaryKey(LogCommon record);


}