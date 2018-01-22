package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Area;

public interface AreaService {

	ResultBean insert(Area area);

	ResultBean selectByPrimaryKey(Area area);

	ResultBean updateByPrimaryKey(Area area);

	ResultBean deleteById(Area area);
	
	ResultBean selectList(Area area);
	
	public List<Area> selectAllList(Area area) ;
	
	Area selectOne(Area area);
	
	public Map<Integer,MapDTO> getMap(Area area);
}
