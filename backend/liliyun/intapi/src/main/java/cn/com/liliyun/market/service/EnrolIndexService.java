package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;
import cn.com.liliyun.user.model.User;

public interface EnrolIndexService {

	/**
	 * Ƭ������ָ�����
	 * @param index
	 * @param log
	 * @param user
	 * @return
	 */
	public ResultBean addAreaEnrollIndex(AreaEnrolIndex index);
	
	public ResultBean updateAreaEnrollIndex(AreaEnrolIndex index);
	
	public ResultBean delAreaEnrollIndex(AreaEnrolIndex index);
	
	public AreaEnrolIndex getAreaEnrollIndex(AreaEnrolIndex index);
	
	public List<AreaEnrolIndex> listAreaEnrollIndex(AreaEnrolIndex index);
	
	public List<AreaEnrolIndex> listAllAreaEnrollIndex(AreaEnrolIndex index);
	
	/**
	 * Ա������ָ�����
	 * @param index
	 * @return
	 */
	public ResultBean editCSEnrolIndex(CSEnrolIndex index);
	
	public CSEnrolIndex getLastCSEnrolIndex();
	
	public ResultBean getCSEnrolIndex(CSEnrolIndex index);
	
	
	public ResultBean editCoachEnrolIndex(CoachEnrolIndex index);
	
	public CoachEnrolIndex getLastCoachEnrolIndex();
	
	public ResultBean getCoachEnrolIndex(CoachEnrolIndex index);
}
