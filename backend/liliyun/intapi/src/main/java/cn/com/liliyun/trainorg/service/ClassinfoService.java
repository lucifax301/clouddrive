package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Classinfo;

public interface ClassinfoService {

	ResultBean addClass(Classinfo classinfo);

	ResultBean updateClass(Classinfo classinfo);
	
	ResultBean updateStatus(Classinfo classinfo);

	ResultBean deleteClass(Classinfo classinfo);
	
	void deleteClassByType(Classinfo classinfo);
	
	ResultBean selectList(Classinfo classinfo);
	
	ResultBean selectListAll(Classinfo classinfo);
	
	ResultBean selectOne(Classinfo classinfo);
	
	Classinfo get(Classinfo classinfo);
	
	List<Classinfo> selectAllList(Classinfo classinfo) ;
	
	Map<Integer,MapDTO> getMap(Classinfo classinfo);
	
	void batchAddClass(Map classinfo);
	
	void batchDelClass(Map classinfo);
}
