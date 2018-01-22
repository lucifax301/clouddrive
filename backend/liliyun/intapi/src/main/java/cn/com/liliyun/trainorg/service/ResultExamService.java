package cn.com.liliyun.trainorg.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.*;
import cn.com.liliyun.user.model.User;

import java.util.List;
import java.util.Map;

/**
 * 考试成绩
 *
 */
public interface ResultExamService {

	public List<ResultExam> list(ResultExam resultExam);

	public List<ResultExamItem> listOfCoach(ResultExamItem resultExam);

	public ResultBean add(User user, List<ResultExamItem> list);

	public Map <String,Object> importData(User user, List<ResultExamItem> list);

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
