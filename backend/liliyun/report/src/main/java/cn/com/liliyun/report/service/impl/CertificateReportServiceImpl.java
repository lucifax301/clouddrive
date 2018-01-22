package cn.com.liliyun.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.report.service.ICertificateReportService;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;
import cn.com.liliyun.trainorg.service.TrainExamService;

@Service
public class CertificateReportServiceImpl implements ICertificateReportService {
	
	@Autowired
	TrainExamService trainExamService;

	@Override
	public List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem) {
		PageUtil.startPage(trainExamItem);
		List<TrainExamItemVo> list = trainExamService.getExamTime(trainExamItem);
		return list;
	}

	@Override
	public List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem) {
		PageUtil.startPage(trainExamItem);
		List<TrainExamItemVo> list = trainExamService.getExamCase(trainExamItem);
		return list;
	}

	@Override
	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem) {
		PageUtil.startPage(trainExamItem);
		List<TrainExamItemVo> trainExamItemList = trainExamService.getWaitCase(trainExamItem);
		return trainExamItemList;
	}

	@Override
	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem) {
		PageUtil.startPage(trainExamItem);
		List<TrainExamItemDetailVo> trainExamItemList = trainExamService.getWaitCaseDetail(trainExamItem);
		return trainExamItemList;
	}

	@Override
	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo) {
		List<TrainExamItemYearVo> trainExamItemList = trainExamService.getWaitCaseYear(trainExamItemYearVo);
		if (trainExamItemList != null && trainExamItemList.size() > 0) {
			for (TrainExamItemYearVo tearVo : trainExamItemList) {
				
			}
		}
		
		return trainExamItemList;
	}

}
