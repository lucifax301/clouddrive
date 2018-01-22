package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;
import cn.com.liliyun.user.model.User;

public interface EnrolIndexService {

	/**
	 * 片区招生指标服务
	 * @param index
	 * @param log
	 * @param user
	 * @return
	 */
	public ResultBean addAreaEnrollIndex(AreaEnrolIndex index,LogCommon log,User user);
	
	public ResultBean updateAreaEnrollIndex(AreaEnrolIndex index,LogCommon log,User user);
	
	public ResultBean delAreaEnrollIndex(AreaEnrolIndex index,LogCommon log,User user);
	
	public AreaEnrolIndex getAreaEnrollIndex(AreaEnrolIndex index);
	
	public List<AreaEnrolIndex> listAreaEnrollIndex(AreaEnrolIndex index);
	
	public List<AreaEnrolIndex> listAllAreaEnrollIndex(AreaEnrolIndex index);
	
	/**
	 * 员工招生指标服务
	 * @param index
	 * @return
	 */
	public ResultBean editCSEnrolIndex(CSEnrolIndex index);
	
	public CSEnrolIndex getLastCSEnrolIndex(User user);
	
	public ResultBean getCSEnrolIndex(CSEnrolIndex index);
	
	
	public ResultBean editCoachEnrolIndex(CoachEnrolIndex index);
	
	public CoachEnrolIndex getLastCoachEnrolIndex(User user);
	
	public ResultBean getCoachEnrolIndex(CoachEnrolIndex index);
}
