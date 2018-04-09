package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.ResultExam;
import cn.com.liliyun.trainorg.model.ResultExamItem;

/**
 * 考试成绩
 *
 */
public interface ResultExamService {

	List<ResultExam> list(ResultExam resultExam);

	List<ResultExamItem> listOfCoach(ResultExamItem resultExam);

	ResultBean add(List<ResultExamItem> list);

	Map <String,Object> importData(List<ResultExamItem> list);

	List<ResultExamItem> listItem(ResultExamItem resultExamItem);

//	List<TrainExamItemVo> getExamTime(TrainExamItemVo vo);
//
//	List<TrainExamItemVo> getExamCase(TrainExamItemVo vo);
//
//	List<TrainExamItemVo> getWaitCase(TrainExamItemVo vo);
//
//	List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo vo);
//
//	List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo vo);
	
}
