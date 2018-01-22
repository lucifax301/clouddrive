package cn.com.liliyun.exam.mapper;

import cn.com.liliyun.trainorg.model.ResultExamItem;

import java.util.List;
import java.util.Map;

public interface ResultExamItemMapper {
	int deleteByPrimaryKey(String tableid);

	int insert(ResultExamItem record);

	int insertSelective(ResultExamItem record);

	ResultExamItem selectByPrimaryKey(String tableid);

	int updateByPrimaryKeySelective(ResultExamItem record);

	int updateByPrimaryKey(ResultExamItem record);

	int insertBatch(Map<String, Object> params);

	List<ResultExamItem> selectList(ResultExamItem record);

	List<ResultExamItem> selectCoachStuList(ResultExamItem record);

}