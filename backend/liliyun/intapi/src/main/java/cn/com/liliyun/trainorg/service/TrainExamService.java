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
	
	public List<TrainExam> list(TrainExam trainExam);
	
	public List<TrainExamItem> listOfCoach(TrainExamItem trainExam);
	
	public ResultBean add(List <TrainExamItem> list);
	
	public Map <String,Object> importData(List <TrainExamItem> list);
	
	public List<TrainExamItem> listItem(TrainExamItem trainExamItem);

	public List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem);

	public List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem);

	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem);

	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem);

	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo);
	
}
