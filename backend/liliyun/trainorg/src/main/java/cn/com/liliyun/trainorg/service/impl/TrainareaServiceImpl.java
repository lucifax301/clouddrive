package cn.com.liliyun.trainorg.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.TrainareaMapper;
import cn.com.liliyun.trainorg.model.Trainarea;
import cn.com.liliyun.trainorg.service.TrainareaService;

@Service
public class TrainareaServiceImpl implements TrainareaService {
	
	Logger logger = Logger.getLogger(TrainareaServiceImpl.class);
	
	@Autowired
	private TrainareaMapper trainareaMapper;

	@Override
	public ResultBean addTrainarea(Trainarea trainarea) {
		trainareaMapper.insertSelective(trainarea);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteTrainarea(Trainarea trainarea) {
		String [] ids = trainarea.getIds().split(",");
		for (String id : ids) {
			trainarea.setId(Integer.parseInt(id));
			trainareaMapper.deleteByPrimaryKey(trainarea);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean getList(Trainarea trainarea) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(trainarea);
		System.out.println("trainarea:"+trainarea);
		if(trainarea==null)trainarea=new Trainarea();
		List<Trainarea> list = trainareaMapper.selectList(trainarea);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean getTrainareaById(Trainarea trainarea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(Trainarea trainarea) {
		return trainareaMapper.getCount(trainarea);
	}

	@Override
	public ResultBean updateTrainarea(Trainarea trainarea) {
		trainareaMapper.updateByPrimaryKeySelective(trainarea);
		return new ResultBean();
	}

}
