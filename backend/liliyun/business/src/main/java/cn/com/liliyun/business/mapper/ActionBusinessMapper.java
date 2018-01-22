package cn.com.liliyun.business.mapper;

import java.util.List;

import cn.com.liliyun.business.model.ActionBusiness;


public interface ActionBusinessMapper {
    
	public void add(ActionBusiness actionbusiness);
	
	public void update(ActionBusiness actionbusiness);
	
	public void delete(ActionBusiness actionbusiness);
	
	public ActionBusiness get(ActionBusiness actionbusiness);
	
	public ActionBusiness getByAction(ActionBusiness actionbusiness);
	
	public List<ActionBusiness> list(ActionBusiness actionbusiness);
}