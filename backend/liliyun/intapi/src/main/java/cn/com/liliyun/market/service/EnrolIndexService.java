package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;

public interface EnrolIndexService {

	/**
	 * Ƭ������ָ�����
	 * @param index
	 * @param log
	 * @param user
	 * @return
	 */
	ResultBean addAreaEnrollIndex(AreaEnrolIndex index);
	
	ResultBean updateAreaEnrollIndex(AreaEnrolIndex index);
	
	ResultBean delAreaEnrollIndex(AreaEnrolIndex index);
	
	AreaEnrolIndex getAreaEnrollIndex(AreaEnrolIndex index);
	
	List<AreaEnrolIndex> listAreaEnrollIndex(AreaEnrolIndex index);
	
	List<AreaEnrolIndex> listAllAreaEnrollIndex(AreaEnrolIndex index);
	
	/**
	 * Ա������ָ�����
	 * @param index
	 * @return
	 */
	ResultBean editCSEnrolIndex(CSEnrolIndex index);
	
	CSEnrolIndex getLastCSEnrolIndex();
	
	ResultBean getCSEnrolIndex(CSEnrolIndex index);
	
	
	ResultBean editCoachEnrolIndex(CoachEnrolIndex index);
	
	CoachEnrolIndex getLastCoachEnrolIndex();
	
	ResultBean getCoachEnrolIndex(CoachEnrolIndex index);
}
