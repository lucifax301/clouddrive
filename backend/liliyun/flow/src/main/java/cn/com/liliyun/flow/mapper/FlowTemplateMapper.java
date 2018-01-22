package cn.com.liliyun.flow.mapper;

import java.util.List;

import cn.com.liliyun.flow.model.FlowTemplate;

public interface FlowTemplateMapper {
    
	public void add(FlowTemplate template);
	
	public FlowTemplate get(FlowTemplate template);
	
	public FlowTemplate getByBusiness(FlowTemplate template);
	
	public List<FlowTemplate> list(FlowTemplate template);
	
	public void delete(FlowTemplate template);
	
	public void update(FlowTemplate template);
}