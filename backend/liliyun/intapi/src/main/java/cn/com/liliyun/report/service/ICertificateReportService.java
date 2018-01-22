package cn.com.liliyun.report.service;

import java.util.List;

import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;

/**
 * 牌证报表统计
 * @author lzb
 *
 */
public interface ICertificateReportService {

	public List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem);

	public List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem);

	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem);

	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem);

	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo);

}
