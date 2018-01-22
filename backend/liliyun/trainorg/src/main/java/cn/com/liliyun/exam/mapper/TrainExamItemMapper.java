package cn.com.liliyun.exam.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.trainorg.model.TrainExamItem;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;

public interface TrainExamItemMapper {
	int deleteByPrimaryKey(String tableid);

	int insert(TrainExamItem record);

	int insertSelective(TrainExamItem record);

	TrainExamItem selectByPrimaryKey(String tableid);

	int updateByPrimaryKeySelective(TrainExamItem record);

	int updateByPrimaryKey(TrainExamItem record);

	int insertBatch(Map<String, Object> params);

	List<TrainExamItem> selectList(TrainExamItem record);

	List<TrainExamItem> selectCoachStuList(TrainExamItem record);

	List<TrainExamItemVo> countArea(TrainExamItemVo record);

	List<TrainExamItemVo> countStore(TrainExamItemVo record);

	List<TrainExamItemVo> countCoach(TrainExamItemVo record);

	List<TrainExamItemVo> countExamplace(TrainExamItemVo record);

	List<TrainExamItemVo> countIndexcat(TrainExamItemVo record);

	List<TrainExamItemVo> countExamcount(TrainExamItemVo record);

	List<TrainExamItemVo> countTable(TrainExamItemVo trainExamItem);
	
	List<TrainExamItemVo> countTableCase(TrainExamItemVo trainExamItem);
	
	List<TrainExamItemVo> countWaitCase(TrainExamItemVo trainExamItem);

	List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem);

	List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo);
}