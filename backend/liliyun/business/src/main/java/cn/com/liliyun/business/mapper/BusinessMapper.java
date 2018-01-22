package cn.com.liliyun.business.mapper;

import java.util.List;

import cn.com.liliyun.business.model.Business;


public interface BusinessMapper {
    
	public void add(Business business);
	
	public Business get(Business business);
	
	public Business getByBusinessid(Business business);
	
	
	public void delete(Business business);
	
	public void update(Business business);
	
	public List<Business> list(Business business);
}