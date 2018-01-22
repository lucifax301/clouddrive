package cn.com.liliyun.trainorg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.AreaMapper;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public ResultBean insert(Area area) {
		areaMapper.insertSelective(area);
		return new ResultBean();
	}

	@Override
	public ResultBean selectByPrimaryKey(Area area) {
		return null;
	}

	@Override
	public ResultBean updateByPrimaryKey(Area area) {
		areaMapper.updateByPrimaryKeySelective(area);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteById(Area area) {
		String [] ids = area.getIds().split(",");
		for (String id : ids) {
			area.setId(Integer.parseInt(id));
			areaMapper.deleteByPrimaryKey(area);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean selectList(Area area) {
		PageUtil.startPage(area);
		List<Area> list = areaMapper.selectList(area);
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@Override
	public List<Area> selectAllList(Area area) {
		List<Area> list = areaMapper.selectList(area);
		return list;
	}

	@Override
	public Area selectOne(Area area) {
		return areaMapper.selectByPrimaryKey(area);
	}


	@Override
	public Map<Integer, MapDTO> getMap(Area area) {
		return areaMapper.getMap(area);
	}

}
