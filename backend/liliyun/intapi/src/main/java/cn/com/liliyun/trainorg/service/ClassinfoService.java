package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Classinfo;

public interface ClassinfoService {

	ResultBean addClass(Classinfo classinfo);

	ResultBean updateClass(Classinfo classinfo);
	
	public ResultBean updateStatus(Classinfo classinfo);

	ResultBean deleteClass(Classinfo classinfo);
	
	public void deleteClassByType(Classinfo classinfo);
	
	ResultBean selectList(Classinfo classinfo);
	
	public ResultBean selectListAll(Classinfo classinfo);
	
	ResultBean selectOne(Classinfo classinfo);
	
	Classinfo get(Classinfo classinfo);
	
	public List<Classinfo> selectAllList(Classinfo classinfo) ;
	
	public Map<Integer,MapDTO> getMap(Classinfo classinfo);
	
	public void batchAddClass(Map classinfo);
	
	public void batchDelClass(Map classinfo);
}
