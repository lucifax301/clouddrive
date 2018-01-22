package cn.com.liliyun.trainorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.ExamareaMapper;
import cn.com.liliyun.trainorg.model.Examarea;
import cn.com.liliyun.trainorg.model.Trainarea;
import cn.com.liliyun.trainorg.service.ExamareaService;

import com.github.pagehelper.PageInfo;

@Service
public class ExamareaServiceImpl implements ExamareaService {

	@Autowired
	private ExamareaMapper examareaMapper;
	
	@Override
	public ResultBean addExamarea(Examarea trainarea) {
		examareaMapper.insert(trainarea);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteExamarea(Examarea trainarea) {
		String [] ids = trainarea.getIds().split(",");
		for (String id : ids) {
			trainarea.setId(Integer.parseInt(id));
			examareaMapper.deleteByPrimaryKey(trainarea);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean updateExamarea(Examarea trainarea) {
		examareaMapper.updateByPrimaryKey(trainarea);
		return new ResultBean();
	}

	@Override
	public ResultBean getList(Examarea trainarea) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(trainarea);
		
		List<Examarea> list = examareaMapper.selectList(trainarea);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@Override
	public List<Examarea> getListAll(Examarea trainarea) {
		
		List<Examarea> list = examareaMapper.selectList(trainarea);
		return list;
	}

	@Override
	public ResultBean getExamareaById(Examarea trainarea) {
		// TODO Auto-generated method stub
		return null;
	}

}
