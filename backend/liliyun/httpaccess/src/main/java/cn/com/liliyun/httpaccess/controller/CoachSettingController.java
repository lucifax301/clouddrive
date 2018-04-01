package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/coachSetting")
public class CoachSettingController extends BaseController{

	//private static Logger logger=Logger.getLogger(CoachSettingController.class);
	
	@Autowired
	private CoachSettingService coachSettingService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@RequestMapping(value="/listTeachType")
	public ResultBean listTeachType(CoachTeachType type) {
		ResultBean rb = new ResultBean();
		
		List<CoachTeachType> list = coachSettingService.listTeachType(type);
		rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	@RequestMapping(value="/listAvailTeachType")
	public ResultBean listAvailTeachType(CoachTeachType type) {
		ResultBean rb = new ResultBean();
		
		type.setStatus(0);
		List<CoachTeachType> list = coachSettingService.listTeachType(type);
		rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	@RequestMapping(value="/addTeachType")
	public ResultBean addTeachType(CoachTeachType type,HttpServletRequest request){
		ResultBean rb = new ResultBean();
		
		User user=AccessWebUtil.getSessionUser(request);
		type.setUserId(user.getId());
		String[] subject=request.getParameterValues("subject");
			
		coachSettingService.addTeachType(type,subject);
		
		return rb;
	}
	
	@RequestMapping(value="/deleteTeachType")
	public ResultBean deleteTeachType(CoachTeachType type) {
		ResultBean rb = new ResultBean();
		
		coachSettingService.deleteTeachType(type);
		
		return rb;
	}
	
	@RequestMapping(value="/updateTeachType")
	public ResultBean updateTeachType(CoachTeachType type,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
			//LogCommon log = initLogParams(request, 0, LogConstant.ACTION_UPDATE);
		
			String[] subject=request.getParameterValues("subject");
			coachSettingService.updateTeachType(type,subject);
		
		return rb;
	}
	
	@RequestMapping(value="/updateTeachTypeStatus")
	public ResultBean updateTeachTypeStatus(CoachTeachType type,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
			//LogCommon log = initLogParams(request, 0, LogConstant.ACTION_UPDATE);
		
			coachSettingService.updateTeachTypeStatus(type);
		
		return rb;
	}
	
	///////////////////////////////////////////////////////////////
	@RequestMapping(value="/listClassType")
	public ResultBean listClassType(CoachClassType type) {
		ResultBean rb = new ResultBean();
		
			List<CoachClassType> list = coachSettingService.listClassType(type);
			rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	@RequestMapping(value="/listAllClassType")
	public ResultBean listAllClassType(CoachClassType type) {
		ResultBean rb = new ResultBean();
		
			
			List<CoachClassType> list = coachSettingService.listAllClassType(type);
			rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	
	
//	@RequestMapping(value="/addClassType")
//	public ResultBean addClassType(CoachClassType type,HttpServletRequest request){
//		ResultBean rb = new ResultBean();
//		try{
//			LogCommon log = initLogParams(request, 0, LogConstant.ACTION_ADD);
//			User user=AccessWebUtil.getSessionUser(request);
//			type.setUserId(user.getId());
//			System.out.println("==========="+type.getClassinfo());
//			//coachSettingService.addClassType(type, log);
//		}catch(Exception ex){
//			logger.error(ex);
//			ex.printStackTrace();
//			rb.setCode(1);
//		}
//		return rb;
//	}
	
	@RequestMapping(value="/addClassType")
	public ResultBean addClassType(CoachClassType type,HttpServletRequest request){
		ResultBean rb = new ResultBean();
		
			
			User user=AccessWebUtil.getSessionUser(request);

			type.setUserId(user.getId());
			coachSettingService.addClassType(type);
		
		return rb;
	}
	
	@RequestMapping(value="/deleteClassType")
	public ResultBean deleteClassType(CoachClassType type) {
		
		
	return coachSettingService.deleteClassType(type);
		
	}
	
//	@RequestMapping(value="/updateClassType")
//	public ResultBean updateClassType(CoachClassType type,HttpServletRequest request) {
//		ResultBean rb = new ResultBean();
//		try{
//			LogCommon log = initLogParams(request, 0, LogConstant.ACTION_UPDATE);
//			User user=AccessWebUtil.getSessionUser(request);
//			coachSettingService.updateClassType(type);
//		}catch(Exception ex){
//			rb.setCode(1);
//		}
//		return rb;
//	}
	
	@RequestMapping(value="/updateClassType")
	public ResultBean updateClassType(CoachClassType type,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		coachSettingService.updateClassType(type);
		return rb;
	}
	
	@RequestMapping(value="/updateClassTypeStatus")
	public ResultBean updateClassTypeStatus(CoachClassType type,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
		coachSettingService.updateClassTypeStatus(type);
		
		return rb;
	}
	
	///////////////////////////////////////////////////////
	@RequestMapping(value="/listCarType")
	public ResultBean listCarType(CoachCarType type) {
		ResultBean rb = new ResultBean();
		
			List<CoachCarType> list = coachSettingService.listCarType(type);
			rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	@RequestMapping(value="/saveCarType")
	public ResultBean saveCarType(CoachCarType type,HttpServletRequest request){
		ResultBean rb = new ResultBean();
		
			
			User user=AccessWebUtil.getSessionUser(request);
			type.setUserId(user.getId());
			
			String types[]= request.getParameterValues("type");
			
			coachSettingService.saveCarType(types,user);
		
		return rb;
	}
	
	/////////////////////////////////////////
	@RequestMapping(value="/listJob")
	public ResultBean listJob(CoachJob job) {
		ResultBean rb = new ResultBean();
		
			List<CoachJob> list = coachSettingService.listJob(job);
			rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	@RequestMapping(value="/listAllJob")
	public ResultBean listAllJob(CoachJob job) {
		ResultBean rb = new ResultBean();
		
			List<CoachJob> list = coachSettingService.listAllJob(job);
			rb.setResult(new PageInfo<>(list));
		
		return rb;
	}
	
	
	@RequestMapping(value="/addJob")
	public ResultBean addJob(CoachJob job,HttpServletRequest request){
		ResultBean rb = new ResultBean();
			User user=AccessWebUtil.getSessionUser(request);
			job.setUserId(user.getId());
			coachSettingService.addJob(job);
		
		return rb;
	}
	
	@RequestMapping(value="/deleteJob")
	public ResultBean deleteJob(CoachJob job,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		coachSettingService.deleteJob(job);
		return rb;
	}
	
	@RequestMapping(value="/updateJob")
	public ResultBean updateJob(CoachJob job,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
		coachSettingService.updateJob(job);
		
		return rb;
	}
	
	@RequestMapping(value="/updateJobTypeStatus")
	public ResultBean updateJobTypeStatus(CoachJob job,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
			
		
			coachSettingService.updateJobStatus(job);
		
		return rb;
	}
}
