package cn.com.liliyun.httpaccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Trainarea;
import cn.com.liliyun.trainorg.service.TrainareaService;


@Controller
@ResponseBody
@RequestMapping(value="/trainarea")
public class TrainareaController extends BaseController {

	@Autowired
	private TrainareaService trainareaService;
	
	@RequestMapping(value="/edit")
	public ResultBean edit(Trainarea trainarea) {
		ResultBean rb;
		if (trainarea.getId() == null) {
			rb = trainareaService.addTrainarea(trainarea);
		} else {
			rb = trainareaService.updateTrainarea(trainarea);
		}
		return rb;
	}
	
	@RequestMapping(value="/list")
	public ResultBean list(Trainarea trainarea) {
		return trainareaService.getList(trainarea);
	}
	
	@RequestMapping(value="/delete")
	public ResultBean delete(Trainarea trainarea) {
		return trainareaService.deleteTrainarea(trainarea);
	}
		
}
