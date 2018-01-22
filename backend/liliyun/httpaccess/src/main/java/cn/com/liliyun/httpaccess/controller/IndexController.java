package cn.com.liliyun.httpaccess.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.service.CarBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.model.Trainarea;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.trainorg.service.TrainareaService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/index")
public class IndexController extends BaseController {


	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private TrainareaService trainareaService;
	
	@Autowired
	private CarBizService carBizService;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value="/statistics")
	public ResultBean statistics(HttpServletRequest request, Coach coach,Student student,
								 Trainarea trainarea,Store store,Car car) {
		User user = getUser(request);
		ResultBean rb = new ResultBean();
		Map <String,Object> data = new HashMap<>();
		data.put("coachCount", coachService.getCount(coach));
		data.put("studentCount", studentService.getCount(student));
		data.put("trainareaCount", trainareaService.getCount(trainarea));
		data.put("coachCarCount", carBizService.getCount(car));
		data.put("storeCount", storeService.getCount(store, user));
		data.put("daysCount", studentService.get7count(student));
		rb.setResult(data);
		return rb;
	}
}
