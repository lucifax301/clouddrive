package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Area;

public interface AreaService {

	public ResultBean insert(Area area);

	public ResultBean selectByPrimaryKey(Area area);

	public ResultBean updateByPrimaryKey(Area area);

	public ResultBean deleteById(Area area);
	
	public ResultBean selectList(Area area);
	
	public List<Area> selectAllList(Area area) ;
	
	public Area selectOne(Area area);
	
	public Map<Integer,MapDTO> getMap(Area area);
}
