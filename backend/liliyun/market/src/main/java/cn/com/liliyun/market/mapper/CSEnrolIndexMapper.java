package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;

public interface CSEnrolIndexMapper {

	public void addCSEnrolIndex(CSEnrolIndex index);
	
	public List<CSEnrolIndex> listCSEnrolIndex(CSEnrolIndex param);
	
	public CSEnrolIndex getCSEnrolIndex(CSEnrolIndex index);
	
	public void updateCSEnrolIndex(CSEnrolIndex index);
	
	
	
	
}
