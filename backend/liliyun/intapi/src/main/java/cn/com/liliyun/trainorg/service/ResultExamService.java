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

	public List<ResultExam> list(ResultExam resultExam);

	public List<ResultExamItem> listOfCoach(ResultExamItem resultExam);

	public ResultBean add(List<ResultExamItem> list);

	public Map <String,Object> importData(List<ResultExamItem> list);

	public List<ResultExamItem> listItem(ResultExamItem resultExamItem);

//	public List<TrainExamItemVo> getExamTime(TrainExamItemVo vo);
//
//	public List<TrainExamItemVo> getExamCase(TrainExamItemVo vo);
//
//	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo vo);
//
//	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo vo);
//
//	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo vo);
	
}
