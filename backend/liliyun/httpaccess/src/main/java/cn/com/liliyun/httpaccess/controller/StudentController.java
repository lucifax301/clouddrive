package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
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
import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.importexcel.model.Flownum;
import cn.com.liliyun.student.dto.StudentCalcMoneyDTO;
import cn.com.liliyun.student.dto.StudentCoachDTO;
import cn.com.liliyun.student.model.CoachStudent;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentPauseApply;
import cn.com.liliyun.student.model.StudentPauseApplyParam;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.theory.dto.TheoryLessonStoreDto;
import cn.com.liliyun.theory.dto.TheoryStudentExport;
import cn.com.liliyun.theory.model.TheoryLesson;
import cn.com.liliyun.theory.model.TheoryStudent;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;

@Controller
@ResponseBody
@RequestMapping(value="/student")
public class StudentController extends ExportController {
	
	Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	
	//编辑
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ResultBean addStudent(HttpServletRequest request,Student student){
		if (student.getId() == null) {
			return studentService.addStudent(student);
		} else {
			return studentService.updateStudent(student);
		}
		
	}
	
	//删除
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResultBean delStudent(Student student){
		return studentService.deleteStudent(student);
	}
	
	//列表
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ResultBean getList(HttpServletRequest request,Student student) {
		return studentService.getStudentList(student);
	}
	
	//列表
    @RequestMapping(value="/enrollist", method=RequestMethod.GET)
    public ResultBean getEnrolList(HttpServletRequest request,Student student) {
        return studentService.getEnrolStudentList(student);
    }
	
	//学员状态变化列表
	@RequestMapping(value="/changeloglist", method=RequestMethod.GET)
	public ResultBean changeloglist(StudentStatusLog studentStatusLog) {
		return studentService.getChangeLogList(studentStatusLog);
	}
	
	//获取单个学员信息
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResultBean get(Student student) {
		ResultBean rb = new ResultBean();
		student = studentService.getStudent(student);
        rb.setResult(student);
		return rb;
	}
		
	//审核
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public ResultBean check(HttpServletRequest request,Student student) {
		student.setCheckstatus(1);
		return studentService.updateStudent(student);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/importflownum",method=RequestMethod.POST)
	public ResultBean importflownum(@RequestParam("file") MultipartFile file,
			HttpServletRequest request,BaseModel model) throws Exception {
		ResultBean rb = new ResultBean();
		HashMap<String, Object> params = new HashMap<>();
		List <Flownum> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), Flownum.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		params.put("list", list);
		
		Map<String, Object> rtnData = studentService.importFlownum(params);
		list = (List<Flownum>) rtnData.get("errorlist");
		if (list != null && list.size() > 0) {
			ExportParams eparams = new ExportParams("流水号导入错误数据", "错误数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(eparams, Flownum.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	        String fileName = "流水号导入错误数据" + time + ".xlsx"; //生成文件名
	        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/";
	        FileUtils.writeByteArrayToFile(new File(path + fileName), os.toByteArray());
	    	rtnData.put("filename", fileName);
		}
		rb.setResult(rtnData);
		return rb;
	}
	
	//分配教练
	@RequestMapping(value="/assignCoach")
	public ResultBean assignCoach(HttpServletRequest request, CoachStudent coachStudent,
			@RequestParam(value = "isreview", required = false, defaultValue = "0") Integer isreview){
		return studentService.addCoachStudent(coachStudent, isreview==1);
		
	}
	
	//学员的教练信息
	@RequestMapping(value="/coach")
	public ResultBean coachinfo(HttpServletRequest request, CoachStudent coachStudent){
		coachStudent.setIsvalid(1);
		CoachStudent cs=studentService.getCoachStudent(coachStudent);
		ResultBean rb=new ResultBean();
		if(cs!=null){
			Coach p=new Coach();
			p.setCoachid(cs.getCoachid());
			Coach coach=coachService.getCoachById(p);
			rb.setResult(coach);
		}
		return rb;
	}
	
	//学员的教练信息
	@RequestMapping(value="/stuofcoach")
	public ResultBean stuofcoach(CoachStudent coachStudent){
		coachStudent.setIsvalid(1);
		List<Student> list=studentService.getCoachStudentList(coachStudent);
		return this.<Student>buildListResult(list);
	}
	
	//下载
	@RequestMapping(value = "/download", method=RequestMethod.GET)    
    public ResponseEntity<byte[]> download(HttpServletRequest request,String type) throws IOException {  
    	String templateName = "", name ="";
    	if ("student".equals(type)) {
    		templateName = "student";
    		name = "学员导入模板";
    	} else if ("flownum".equals(type)) {
    		templateName = "flownum";
    		name = "流水号导入模板";
    	}
        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/downloadExcel/" + templateName + ".xlsx";
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName = new String((name +".xlsx").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }
    
    
    @RequestMapping(value = "/export")
    public ResponseEntity<byte[]> export(Student student,HttpServletRequest request) throws IOException {
    	List<Student> list = studentService.getAllList(student); //获取数据
    	
		
		Map<Integer, MapDTO> areas = areaService.getMap(null);
		Map<Integer, MapDTO> stores = storeService.getMap(null);
		Map<Integer, MapDTO> classes = classinfoService.getMap(null);
    	
    	for (Student s : list) {
    		s.setAreaname(areas.get(s.getAreaid()) != null ? areas.get(s.getAreaid()).getName() : "");
    		s.setStorename(stores.get(s.getStoreid()) != null ? stores.get(s.getStoreid()).getName() : "");
    		s.setClassname(classes.get(s.getClassid()) != null ? classes.get(s.getClassid()).getName() : "");
    		s.setApplyexamname(s.getApplyexam() == null ? "" : ApplyExam.getName(s.getApplyexam(),s.getApplystatus()));
    	}
    	
    	return this.export("学员导出数据", "学员导出数据", "导出数据", list, Student.class);
    }
	
	//获取理论课列表
	@RequestMapping(value="/theoryLessonList", method = RequestMethod.GET)
	public ResultBean getTheoryList(HttpServletRequest request, TheoryLesson theoryLesson) {
		return studentService.getTheoryList(theoryLesson);
	}
	
	//获取单个理论课数据，包括学员
	@RequestMapping(value="/theoryLesson", method = RequestMethod.GET)
	public ResultBean getTheory(HttpServletRequest request, TheoryLessonStoreDto theoryLesson, 
			@RequestParam(required = false, defaultValue = "0") int isReview) {
		return studentService.getTheory(theoryLesson, isReview==1);
	}
	
	//新增理论课的时候，获取门店列表数据
	@RequestMapping(value="/theoryStores", method = RequestMethod.GET)
	public ResultBean getTheoryStores(HttpServletRequest request) {
		return studentService.getTheoryStores();
	}
	
	//获取可参加理论课的学员列表
	@RequestMapping(value="/theoryStudents", method = RequestMethod.GET)
	public ResultBean getTheoryStudents(HttpServletRequest request, Student student) {
		return studentService.getTheoryStudents(student);
	}
	
	//增加一个新的理论课，同时必须设置
	@RequestMapping(value="/add/theoryLesson", method = RequestMethod.POST)
	public ResultBean addTheory(HttpServletRequest request, TheoryLesson theoryLesson, String stores) {
			String start = request.getParameter("starttime");
			String end = request.getParameter("endtime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				if (start != null)
					theoryLesson.setStarttime(sdf.parse(start));
				if (end != null)
					theoryLesson.setEndtime(sdf.parse(end));
			} catch (ParseException e) {
				e.printStackTrace();
				ResultBean r = new ResultBean();
				r.setCode(HttpConstant.ERROR_CODE);
				r.setMsg(HttpConstant.ERROR_MSG);
				return r;
			}
			return studentService.addTheory(theoryLesson, stores);
	}
	
	//增加或删除学员
	@RequestMapping(value="/edit/theoryStudent", method = RequestMethod.POST)
	public ResultBean editTheoryStudent(HttpServletRequest request, Integer theoryId, String[] ids,
			@RequestParam(required = false, defaultValue = "0") int isDel) {
		return studentService.editTheoryStudent(theoryId, ids,  isDel==1);
	}
	
	//修改理论课状态
	@RequestMapping(value="/update/theoryLesson", method = RequestMethod.POST)
	public ResultBean updateTheory(HttpServletRequest request, TheoryLesson theoryLesson) {
		return studentService.updateTheory(theoryLesson);
	}
	
	@RequestMapping(value="/learnpause/addpause", method = RequestMethod.POST)
	public ResultBean learnpause(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.addStudentPauseApply(apply);
	}
	
	@RequestMapping(value="/learnpause/addresume", method = RequestMethod.POST)
	public ResultBean learnresume(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.addStudentPauseApply(apply);
	}
	
	@RequestMapping(value="/learnpause/update", method = RequestMethod.POST)
	public ResultBean updatelearnpause(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.updateStudentPauseApply(apply);
	}
	
	@RequestMapping(value="/learnpause/list")
	public ResultBean listlearnpause(HttpServletRequest request, StudentPauseApplyParam param) {
		return studentService.listStudentPauseApply(param);
	}
	
	@RequestMapping(value="/learnpause/get")
	public ResultBean getlearnpause(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.getStudentPauseApply(apply);
	}
	
	@RequestMapping(value="/learnpause/getByTran")
	public ResultBean getlearnpauseByTran(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.getStudentPauseApplyByTransaction(apply);
	}
	
	
	@RequestMapping(value="/learnpause/getByStuId")
	public ResultBean getlearnpauseBystuid(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.getStudentPauseApplyByStuId(apply);
	}
	
	//列表
	@RequestMapping(value="/pauselist", method=RequestMethod.GET)
	public ResultBean pauselist(Student student, HttpServletRequest request) {
		return studentService.getStudentList(student);
	}
	
	@RequestMapping(value="/learnpause/audit")
	public ResultBean auditlearnpause(HttpServletRequest request, StudentPauseApply apply) {
		return studentService.updateStudentPauseApplyStatus(apply);
	}
	
	@RequestMapping(value="/theoryLessonText", method = RequestMethod.GET)
	public ResultBean theoryLessonText(HttpServletRequest request, Integer type, Integer theoryid) {
		
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		r.setMsg(studentService.theoryLessonText(theoryid, type));
		return r;
	}
	
	@RequestMapping(value="/export/theoryStudent")
	public ResponseEntity<byte[]> theoryStudentExport(HttpServletRequest request, TheoryStudent theoryStudent) {
		ResponseEntity<byte[]> r = null;
		try {
			
			List<TheoryStudentExport> list = studentService.theoryStudentExport(theoryStudent);
			for (TheoryStudentExport tse : list) {
				tse.setApplyexamstr(tse.getApplyexam()==1?"已受理":tse.getApplyexam().toString());
			}
			ExportParams params = new ExportParams("学员信息导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, TheoryStudentExport.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
			workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String fileName = new String("参加理论课学员列表.xlsx".getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        r = new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);   
    	} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//获取学员列表，附带教练信息
	@RequestMapping(value="/studentCoach", method= RequestMethod.GET)
	public ResultBean getStudentCoach(HttpServletRequest request, StudentCoachDTO studetCoach){
		return studentService.getStudentCoach(studetCoach);
	}

	@RequestMapping(value="/calcMoney")
	public ResultBean getSales(HttpServletRequest request , StudentCalcMoneyDTO dto){
		if (dto.getClassinfoid() == null) { //班别必选
			ResultBean rb = new ResultBean();
			rb.setResult(0);
			return rb;
		}
		return studentService.calcMoney( dto);
	}

}
