package cn.com.liliyun.httpaccess.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachLoadStudentInfo;
import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.coach.model.CoachModApplyParam;
import cn.com.liliyun.coach.model.CoachStudentDTO;
import cn.com.liliyun.coach.model.HeadCoach;
import cn.com.liliyun.coach.model.StudentAssign;
import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.common.annotation.RequestAction;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.ExcelUtil;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;
import com.qiniu.api.auth.AuthException;

@Controller
public class CoachController extends ExportController {

	@Autowired
	private CoachService coachService;

	@RequestMapping(value = "/coach/list")
	@ResponseBody
	public ResultBean<PageInfo<Coach>> getList(Coach coach, HttpServletRequest request) {
		List<Coach> list = coachService.getCoachList(coach);
		PageInfo<Coach> pagedResult = new PageInfo<Coach>(list);
		ResultBean<PageInfo<Coach>> resultBean = new ResultBean<PageInfo<Coach>>(pagedResult);

		return resultBean;
	}

	@RequestMapping(value = "/coach/listnoassign")
	@ResponseBody
	public ResultBean<PageInfo<Coach>> getListNoassign(Coach coach, HttpServletRequest request) {
		List<Coach> list = coachService.getNoAssignCoachList(coach);
		PageInfo<Coach> pagedResult = new PageInfo<Coach>(list);
		ResultBean<PageInfo<Coach>> resultBean = new ResultBean<PageInfo<Coach>>(pagedResult);
		
		return resultBean;
	}
	
	@RequestMapping(value = "/coach/export")
	public ResponseEntity<byte[]> export(Coach coach, HttpServletRequest request) throws IOException {
		List<Coach> list = coachService.getExportCoachList(coach); // 获取数据
		return this.export("教练导出数据", "教练导出数据", "导出数据", list, Coach.class);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/coach/deleteCoach")
	@ResponseBody
	public ResultBean deleteById(Coach coach) {
		ResultBean resultBean = new ResultBean();
		coachService.deleteById(coach);
		return resultBean;
	}

	@RequestMapping(value = "/coach/getCoach")
	@ResponseBody
	public ResultBean<Coach> getCoachById(Coach coach) {
		Coach coachResult = coachService.getCoachById(coach);
		ResultBean<Coach> resultBean = new ResultBean<Coach>(coachResult);
		return resultBean;
	}

	@RequestMapping(value = "/coach/extendinfo")
	@ResponseBody
	public ResultBean<Map<String,String>> getCoachExtendById(Coach coach,HttpServletRequest request) {
		Map<String,String> map = coachService.getCoachExtendById(coach);
		ResultBean<Map<String,String>> resultBean = new ResultBean<Map<String,String>>(map);
		return resultBean;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/coach/addCoach")
	@ResponseBody
	public ResultBean addCoach(Coach coach, HttpServletRequest request) {
		Map<String,String[]> extendsinfo = new HashMap<String,String[]>();
		processExt(extendsinfo, request);
		return coachService.addCoach(coach, extendsinfo);
	}

	@RequestMapping(value = "/coach/listhead")
	@ResponseBody
	public ResultBean<PageInfo<HeadCoach>> getHeadList(Coach coach,HttpServletRequest request) {
		List<HeadCoach> list = coachService.getHeadCoachList(coach);
		PageInfo<HeadCoach> pagedResult = new PageInfo<HeadCoach>(list);
		ResultBean<PageInfo<HeadCoach>> resultBean = new ResultBean<PageInfo<HeadCoach>>(pagedResult);
		return resultBean;
	}

	

	// update
	@RequestMapping(value = "/coach/updateCoachById")
	@ResponseBody
	public ResultBean updateCoachById(Coach coach, HttpServletRequest request)
			throws AuthException, JSONException {
		
		User user = AccessWebUtil.getSessionUser(request);

		Map extendsinfo = new HashMap();
		processExt(extendsinfo, request);
		coach.setMuid(user.getId());
		ResultBean resultBean = new ResultBean();
		coachService.updateCoach(coach,  extendsinfo);
		return resultBean;
	}

	

	private void processExt(Map<String,String[]> extendsinfo, HttpServletRequest request) {
		String classinfoid[] = request.getParameterValues("classinfoid");
		String storeid[] = request.getParameterValues("storeid");
		String step2areaid[] = request.getParameterValues("step2areaid");
		String step3areaid[] = request.getParameterValues("step3areaid");
		if (storeid != null) {
			extendsinfo.put("storeid", storeid);
		}
		if (classinfoid != null) {
			extendsinfo.put("classinfoid", classinfoid);
		}
		if (step2areaid != null) {
			extendsinfo.put("step2areaid", step2areaid);
		}
		if (step3areaid != null) {
			extendsinfo.put("step3areaid", step3areaid);
		}
	}

	@RequestMapping(value = "/coach/updateCoach")
	@ResponseBody
	public ResultBean updateCoach(Coach coach, HttpServletRequest request)
			throws AuthException, JSONException {
		
		User user = AccessWebUtil.getSessionUser(request);
		Map extendsinfo = new HashMap();
		processExt(extendsinfo, request);
		coach.setMuid(user.getId());
		return coachService.updateCoach(coach,  extendsinfo);
	}

	@RequestMapping(value = "/coach/updateCoachTeachState")
	@ResponseBody
	public ResultBean updateCoachTeachState(Coach coach,
			HttpServletRequest request) throws AuthException, JSONException {
		User user = AccessWebUtil.getSessionUser(request);

		coach.setMuid(user.getId());
		return coachService.updateCoachTeachState(coach);
	}

	@RequestMapping(value = "/coach/updateCoachEmploystatus")
	@ResponseBody
	public ResultBean updateCoachEmploystatus(Coach coach,
			HttpServletRequest request) throws AuthException, JSONException {
		User user = AccessWebUtil.getSessionUser(request);

		coach.setMuid(user.getId());
		return coachService.updateCoachEmploystatus(coach);
	}

	/**
	 * 导入用户
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/coach/uploadExcel", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean importUser(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {

		String[] header = { "name:str", "sex:str", "mobile:str", "idcard:str",
				"address:str", "dripermitted:str", "drilicence:str",
				"teachpermitted:str", "employstatus:str" };
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("WEB-INF")
				+ "/uploadExcel/";
		ResultBean rb = ExcelUtil.excelToList(header, file, uploadPath,
				Coach.class, 2, null);
		return rb;
	}

	/**
	 * 
	 * @param filename
	 * @param type
	 *            1确定导入 2放弃导入
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/coach/importExcel", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean importExcel(String filename, String type,
			HttpServletRequest request, Coach coach) throws IOException,
			ClassNotFoundException {
		ResultBean rb = new ResultBean();
		String targetDir = request.getSession().getServletContext()
				.getRealPath("WEB-INF")
				+ "/uploadExcel/";
		List<Coach> list = null;
		File file = new File(targetDir + filename);

		
			if (file.exists()) {
				if ("1".equals(type)) {
					ObjectInputStream ois = new ObjectInputStream(
							new FileInputStream(file));
					list = (List<Coach>) ois.readObject();
					coachService.importCoach(coach, list);
					ois.close();
				} else if ("2".equals(type)) {
					file.delete();
				}
				rb.setResult(list);
				rb.setCode(0);
				rb.setMsg("操作成功");
				return rb;

			}
		
		rb.setCode(2);
		rb.setMsg("操作失败");
		return rb;

	}

	// 下载
	@RequestMapping(value = "/coach/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request)
			throws IOException {
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF")
				+ "/downloadExcel/coach.xlsx";
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("教练导入模板.xlsx".getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/coach/notStuListOfCoach")
	@ResponseBody
	public ResultBean getNotStuList(CoachStudentDTO coachStudentDTO) {
		return coachService.getNotStuListOfCoach(coachStudentDTO);
	}

	@RequestMapping(value = "/coach/stuListOfCoach")
	@ResponseBody
	public ResultBean getStuList(CoachStudentDTO coachStudentDTO) {
		return coachService.getStuListOfCoach(coachStudentDTO);
	}

	@RequestMapping(value = "/coach/stuAssignRecord")
	@ResponseBody
	public ResultBean stuAssignRecord(StudentAssign studentAssign, HttpServletRequest request) {
		User user = AccessWebUtil.getSessionUser(request);
		
		return coachService.getStuAssignRecord(studentAssign);
		
	}

	@RequestMapping(value = "/coach/stuAssignRecord/export")
	@ResponseBody
	public ResponseEntity<byte[]> stuAssignRecordExport(StudentAssign studentAssign, HttpServletRequest request)throws Exception {
		List<StudentAssign> list=coachService.getAllStuAssignRecord(studentAssign);
		return this.export("分配记录", "分配记录", "导出数据", list, StudentAssign.class);
	}
	
	// update
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value = "/coach/modApply")
	@ResponseBody
	public ResultBean modApply(Coach coach, HttpServletRequest request)
			throws AuthException, JSONException {
		Map extendsinfo = new HashMap();
		processExt(extendsinfo, request);
		
		return coachService.modCoachApply(coach, 
				extendsinfo);
		
	}

	@RequestMapping(value = "/coach/modApplyList")
	@ResponseBody
	public ResultBean modApplyList(CoachModApplyParam param, HttpServletRequest request) {
		return coachService.listModCoachApply(param);
	}
	
	@RequestMapping(value = "/coach/getModApply")
	@ResponseBody
	public ResultBean getModApply(CoachModApply apply, HttpServletRequest request) {
		
		ResultBean resultBean = new ResultBean();
		CoachModApply result = coachService.getModApply(apply);
		resultBean.setResult(result);
		return resultBean;
	}

	@RequestMapping(value = "/coach/getCoachModinfo")
	@ResponseBody
	public ResultBean getCoachModinfo(Coach coach, HttpServletRequest request) {
		String applyid = request.getParameter("applyid");
		return coachService.getCoachModinfo(coach,
				Integer.parseInt(applyid));
		
	}

	@RequestMapping(value = "/coach/getCoachModinfoextendinfo")
	@ResponseBody
	public ResultBean getCoachModinfoextendinfo(Coach coach, HttpServletRequest request) {
		
		String applyid = request.getParameter("applyid");
		ResultBean resultBean = new ResultBean();
		Map map = coachService.getCoachModExtendinfo(coach,
				Integer.parseInt(applyid));
		resultBean.setResult(map);
		return resultBean;
	}
	
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value = "/coach/modApplyUpdate")
	@ResponseBody
	public ResultBean modApplyUpdate(Coach coach, HttpServletRequest request) {
		String applyid = request.getParameter("applyid");
		Map extendsinfo = new HashMap();
		processExt(extendsinfo, request);
		return coachService.updateModCoachApply(coach, 
				extendsinfo,  Integer.parseInt(applyid));

	}

	@RequestMapping(value = "/coach/auditModApply")
	@ResponseBody
	public ResultBean auditModApply(HttpServletRequest request) {
		String applyid = request.getParameter("applyid");
		String state = request.getParameter("state");

		return coachService.auditModCoachApply(
				Integer.parseInt(applyid), Integer.parseInt(state));
	}
	
	@RequestMapping(value = "/coach/batchAuditModApply")
	@ResponseBody
	public ResultBean batchAuditModApply(HttpServletRequest request) {

		User user = AccessWebUtil.getSessionUser(request);
		String applyids = request.getParameter("applyids");
		String state = request.getParameter("state");
		
		String ids[]= applyids.split(",");
		
		return coachService.batchAuditModCoachApply(
				ids, Integer.parseInt(state));

	}
	
	/**
	 * 给教学组长分配教练
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/coach/assign")
	@ResponseBody
	public ResultBean assign(HttpServletRequest request) {

		String headcoachid = request.getParameter("headcoachid");
		String coachidstr = request.getParameter("coachid");
		String coachid[] = coachidstr.split(",");
		ResultBean resultBean = coachService.assignCoach(
				Integer.parseInt(headcoachid), coachid, null);

		return resultBean;
	}

	/**
	 * 教学组长解绑教练
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/coach/unassign")
	@ResponseBody
	public ResultBean unassign(HttpServletRequest request) {

		String headcoachid = request.getParameter("headcoachid");
		String coachidstr = request.getParameter("coachid");
		String coachid[] = coachidstr.split(",");
		ResultBean resultBean = coachService.assignCoach(
				Integer.parseInt(headcoachid), null, coachid);

		return resultBean;
	}

	@RequestMapping(value = "/coach/batchupdate")
	@ResponseBody
	public ResultBean batchupdate(HttpServletRequest request, Coach coach) {

		String ids = request.getParameter("ids");
		String coachid[] = ids.split(",");
		String classinfoid[] = request.getParameterValues("classinfoid");
		return coachService.batchUpdateCoach(coachid, coach,
				classinfoid);
	}

	@RequestMapping(value = "/coach/getCoachLoadStudentInfo")
	@ResponseBody
	public ResultBean getCoachLoadStudentInfo(CoachLoadStudentInfo param){
			ResultBean rb=new ResultBean();
			List<CoachLoadStudentInfo> list= coachService.getCoachLoadStudentInfo(param);
			rb.setResult(new PageInfo<>(list));
			return rb;
		
	}
	
	@RequestMapping(value = "/coach/assignList")
	@ResponseBody
	public ResultBean assignList(Coach coach, @RequestParam("studentid") Integer studentid,
			HttpServletRequest request){
		return coachService.assignList(coach, studentid);
	}
}
