package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.TrainExam;
import cn.com.liliyun.trainorg.model.TrainExamItem;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;

/**
 * 考试培训
 *
 */
public interface TrainExamService {
	
	List<TrainExam> list(TrainExam trainExam);
	
	List<TrainExamItem> listOfCoach(TrainExamItem trainExam);
	
	ResultBean add(List <TrainExamItem> list);
	
	Map <String,Object> importData(List <TrainExamItem> list);
	
	List<TrainExamItem> listItem(TrainExamItem trainExamItem);

	List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem);

	List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem);

	List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem);

	List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem);

	List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo);
	
}
