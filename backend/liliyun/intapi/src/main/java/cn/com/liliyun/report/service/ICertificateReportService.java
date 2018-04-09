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

	List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem);

	List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem);

	List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem);

	List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem);

	List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo);

}
