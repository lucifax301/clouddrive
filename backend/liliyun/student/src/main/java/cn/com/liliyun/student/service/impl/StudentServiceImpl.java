package cn.com.liliyun.student.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachClassinfo;
import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.common.CommonService;
import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.FeeSubject;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.OweFee;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.finance.model.FinanceFeeItem;
import cn.com.liliyun.finance.model.FinancePos;
import cn.com.liliyun.finance.model.FinanceReceipt;
import cn.com.liliyun.finance.service.FinanceFeeService;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.importexcel.model.Flownum;
import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.service.SalesService;
import cn.com.liliyun.student.dto.CountDTO;
import cn.com.liliyun.student.dto.StudentApplyStat;
import cn.com.liliyun.student.dto.StudentCalcMoneyDTO;
import cn.com.liliyun.student.dto.StudentCoachDTO;
import cn.com.liliyun.student.dto.StudentMoneyDTO;
import cn.com.liliyun.student.dto.StudentUpdateDTO;
import cn.com.liliyun.student.mapper.CoachStudentMapper;
import cn.com.liliyun.student.mapper.FileMapper;
import cn.com.liliyun.student.mapper.MaterialMapper;
import cn.com.liliyun.student.mapper.StudentMapper;
import cn.com.liliyun.student.mapper.StudentMoneyMapper;
import cn.com.liliyun.student.mapper.StudentPauseApplyMapper;
import cn.com.liliyun.student.mapper.StudentStatusLogMapper;
import cn.com.liliyun.student.mapper.TheoryLessonMapper;
import cn.com.liliyun.student.mapper.TheoryStoreMapper;
import cn.com.liliyun.student.mapper.TheoryStudentMapper;
import cn.com.liliyun.student.mapper.TransferStudentMapper;
import cn.com.liliyun.student.model.CoachStudent;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentMoney;
import cn.com.liliyun.student.model.StudentPauseApply;
import cn.com.liliyun.student.model.StudentPauseApplyParam;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.model.TransferStudent;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.theory.dto.TheoryLessonStoreDto;
import cn.com.liliyun.theory.dto.TheoryStoreDto;
import cn.com.liliyun.theory.dto.TheoryStudentExport;
import cn.com.liliyun.theory.model.TheoryLesson;
import cn.com.liliyun.theory.model.TheoryLessonExample;
import cn.com.liliyun.theory.model.TheoryStore;
import cn.com.liliyun.theory.model.TheoryStoreExample;
import cn.com.liliyun.theory.model.TheoryStudent;
import cn.com.liliyun.theory.model.TheoryStudentExample;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class StudentServiceImpl extends CommonService implements StudentService {

	Logger logger = Logger.getLogger(StudentServiceImpl.class);
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private MaterialMapper materialMapper;

	@Autowired
	private CoachService coachService;

//	@Autowired
//	private DefaultMQProducer dataProducer;

	@Autowired
	private TheoryLessonMapper theoryLessonMapper;

	@Autowired
	private TheoryStudentMapper theoryStudentMapper;

	@Autowired
	private TheoryStoreMapper theoryStoreMapper;

	@Autowired
	private CoachStudentMapper coachStudentMapper;

	@Autowired
	private StudentPauseApplyMapper studentPauseApplyMapper;

	@Autowired
	private TransferStudentMapper transferStudentMapper;

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	@Autowired
	private StudentStatusLogMapper studentStatusLogMapper;

	@Autowired
	private AreaService areaService;

	@Autowired
	private SalesService salesService;

	@Autowired
	private ClassinfoService classinfoService;

	@Autowired
	private  StudentMoneyMapper studentMoneyMapper;

	@Autowired
	private FinanceFeeService financeFeeService;

    @Autowired
    private FinanceService financeService;

	@Autowired
	private FlowService flowService;

//	@Value("${data.synch}")
//	private boolean DATA_SYNCH;

	@Override
	public ResultBean addStudent(Student student) {
		ResultBean r = new ResultBean();
		
        Student stu = studentMapper.selectOne(student);
        if (stu != null) {
            r.setCode(HttpConstant.DATA_ERROR_COCE);
            r.setMsg("身份证号码重复");
            return r;
        }
        User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
        student.setApplyexam(ApplyExam.SIGNUP_STUDENT_INFO.getApplyexam());
        student.setApplystatus(ApplyExam.SIGNUP_STUDENT_INFO.getApplystatus());
        student.setAreaid(user.getAreaid());
        student.setStoreid(user.getStoreid());
        student.setCuid(user.getId());
        student.setCname(user.getRealname());
        student.setStatus(1);

        //计算费用
        Classinfo classinfo = new Classinfo();
        
        Integer classinfoid = student.getClassid();
        classinfo.setId(classinfoid);
        //获取班别信息
        classinfo = classinfoService.get(classinfo);
        String traintype = student.getTraintype();
        if (StringUtils.isBlank(traintype)) {
            r.setCode(HttpConstant.DATA_ERROR_COCE);
            r.setMsg("获取学车车型为空!");
            return r;
		}
		Integer fp = "C1".equals(traintype) ? classinfo.getC1amount() : classinfo.getC2amount();
		BigDecimal fullPrice = new BigDecimal(fp);
		if (fullPrice.compareTo(BigDecimal.ZERO) == -1) {
			r.setCode(HttpConstant.BIZ_ERROR_COCE);
			r.setMsg("班别金额不能为小于0");
			return r;
		}
		BigDecimal favorPrice;
		Integer activityid = student.getActivityid();
		if (activityid == null) { //不参与营销活动
			favorPrice = BigDecimal.ZERO;
		} else {
			SalesActivityClassinfo activityClassinfo = new SalesActivityClassinfo();
			
			activityClassinfo.setClassinfoid(classinfoid);
			activityClassinfo.setActivityid(activityid);
			activityClassinfo = salesService.getMatchActivityClass(activityClassinfo);
			Integer ifp = "C1".equals(traintype) ? activityClassinfo.getC1price() : activityClassinfo.getC2price();
			favorPrice = new BigDecimal(ifp);
		}
		BigDecimal submoney = ObjectUtils.defaultIfNull(student.getSubmoney(),BigDecimal.ZERO); //减免金额
		BigDecimal couponmoney = ObjectUtils.defaultIfNull(student.getCouponmoney(),BigDecimal.ZERO);//代金券金额
		BigDecimal contractmoney =  fullPrice.subtract(submoney).subtract(couponmoney); //合同金额
		if (contractmoney.compareTo(BigDecimal.ZERO) == -1) {
			r.setCode(HttpConstant.BIZ_ERROR_COCE);
			r.setMsg("合同金额不能为小于0");
			return r;
		}
		student.setSubmoney(submoney);
		student.setCouponmoney(couponmoney);
		student.setContractmoney(contractmoney);
		student.setSignmoney(fullPrice);
		studentMapper.insertSelective(student);

		StudentMoney studentMoney = new StudentMoney();
		//Date now = new Date();
        BigDecimal discountmoney = submoney.add(couponmoney); //所有的减免费用

        //财务费用
        FinanceFeeItem item = new FinanceFeeItem();
        item.setStudentid(student.getId());
        item.setMoney(discountmoney);
        item.setSubject1(FeeSubject.PAY.getStatus());
        item.setSubject2(FeeSubject.REDUCE_FEE.getStatus());
        item.setApplyexam(FeeSubject.SIGNUP_FEE.getStatus());
        if (discountmoney.compareTo(favorPrice) == 1) { //当欠免大于营销活动配置的金额时 插入一条欠费记录
            studentMoney.setOwesubmoney(discountmoney);
            studentMoney.setOwestatus(OweFee.OWE.getStatus());
            item.setCheckstatus(0);
        } else {
            studentMoney.setOwesubmoney(BigDecimal.ZERO);
            studentMoney.setOwestatus(OweFee.UNOWE.getStatus());
            item.setCheckstatus(2);
        }
        financeFeeService.saveFinanceItem(item, student);

		System.out.println("############################");
		System.out.println("减免费用:" + submoney + "@全价格" + fullPrice + "@全部优惠" + discountmoney + "@优惠金额" + favorPrice);

		//插入财务票据
        BigDecimal cashmoney = ObjectUtils.defaultIfNull(student.getCashmoney(),BigDecimal.ZERO);
        BigDecimal posmoney = ObjectUtils.defaultIfNull(student.getPosmoney(),BigDecimal.ZERO);
        BigDecimal paymoney = cashmoney.add(posmoney);
        FinanceReceipt financeReceipt = new FinanceReceipt();
        financeReceipt.setStudentid(student.getId());
        financeReceipt.setStuname(student.getName());
        financeReceipt.setStuidcard(student.getIdcard());
        financeReceipt.setClassinfo(classinfo.getName());
        financeReceipt.setTraintype(student.getTraintype());
        financeReceipt.setSignupdate(student.getApplydate());
        financeReceipt.setSignupcost(paymoney);
        financeReceipt.setType(FeeSubject.SIGNUP_FEE.getStatus());
        financeReceipt.setCashmoney(cashmoney);
        FinancePos pos = new FinancePos();
        pos.setId(student.getPosid());
        pos = financeService.getFinancePos(pos);
        if (pos != null) {
            financeReceipt.setPosid(student.getPosid());
            financeReceipt.setPosnum(pos.getPosnum());
            financeReceipt.setPoscompany(pos.getPoscompany());
        }
        financeReceipt.setPosmoney(posmoney);
        financeReceipt.setInvoicetype(student.getBilltype().byteValue());
        financeReceipt.setInvoicename(student.getBillname());
        financeReceipt.setReceiptmoney(paymoney);
        financeReceipt.setReceiptdate(student.getPaydate());
        financeReceipt.setInvoicename(student.getBillname());
        financeService.addFinanceReceipt(financeReceipt);

        if (paymoney.compareTo(contractmoney) == -1) { //支付金额小于合同金额
            studentMoney.setOwestatus(OweFee.OWE.getStatus());
            //studentMoney.setOwemoney();
        }
		
		studentMoney.setStudentid(student.getId());
		studentMoney.setSignmoney(fullPrice);
		studentMoney.setContractmoney(contractmoney);
		studentMoney.setPaymoney(BigDecimal.ZERO);
		studentMoney.setOwemoney(contractmoney);
		studentMoney.setOweresitmoney(BigDecimal.ZERO);
		studentMoney.setSubmoney(discountmoney);
		studentMoney.setOwestatus(OweFee.OWE.getStatus());
		studentMoney.setReturnstatus(0);
		//插入学员账户相关信息
		studentMoneyMapper.insertSelective(studentMoney);

		//插入办证科目
		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setStudentid(student.getId());
		studentStatusLog.setIdcard(student.getIdcard());
		studentStatusLog.setSubject(ApplyExam.SIGNUP_STUDENT_INFO.getSubject());
		studentStatusLog.setSubjectname(ApplyExam.SIGNUP_STUDENT_INFO.getName());
	    saveStudentStatusLog(studentStatusLog);

		return new ResultBean();
    }

	@Override
	public ResultBean deleteStudent(Student student) {
		String [] ids = student.getIds().split(",");
		for (String id : ids) {
			student.setId(Integer.valueOf(id));
			studentMapper.deleteByPrimaryKey(student);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean updateStudent(Student student) {
		Student exist = studentMapper.selectByPrimaryKey(student);
		if(exist!=null){
			studentMapper.updateByPrimaryKeySelective(student);
			return new ResultBean();
		}else{
			return new ResultBean(ConstantUtil.STUDENT_NOTEXIST);
		}
	}

	@Override
	public Student getStudent(Student student) {
		return studentMapper.selectOne(student);
	}

	@Override
	public List<Student> getAllList(Student student) {
		return studentMapper.selectList(student);
	}

	@Override
	public ResultBean getStudentList(Student student) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(student);
		
		List<Student> list = studentMapper.selectList(student);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean getEnrolStudentList(Student student) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(student);
		List<Student> list = studentMapper.selectEnrolList(student);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public Integer getClassStudentOne(Student student) {
		Integer id = studentMapper.getClassStudentCount(student);
		return id;
	}

	@Override
	public List<CountDTO> get7count(Student student) {
		return studentMapper.getCountBy7Days(student);
	}

	@Override
	public Integer getCount(Student student) {
		return studentMapper.getCount(student);
	}

	@Override
	public List<Student> getList(Student student) {
		PageUtil.startPage(student);
		return studentMapper.selectList(student);
	}

	@Override
	public ResultBean getTheoryList(TheoryLesson theoryLesson) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);

		TheoryLesson temp = new TheoryLesson();
		temp.setState(5);
		updateTheory(temp);
		//每次获取List的时候，读取上次更新列表时间，超过半小时则再次更新。
//		File file = ResourceUtils.getFile("classpath:../lastupdatetime");
//		FileInputStream fis = new FileInputStream(file);
//		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
//		BufferedReader br = new BufferedReader(isr);
//		String line = null, lastupdatetimestr = "";
//		while ((line = br.readLine()) != null) {
//			lastupdatetimestr += line;
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date lastupdatetime = sdf.parse(lastupdatetimestr);
//		Date date = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(lastupdatetime);
//		calendar.add(Calendar.MINUTE, 30);
//		if (calendar.getTime().before(date)) {
//			TheoryLessonExample tle = new TheoryLessonExample();
//			tle.createCriteria().andStateEqualTo(4).andIsdelEqualTo((byte) 0).andEndtimeLessThan(date);
//			TheoryLesson tl = new TheoryLesson();
//			tl.setState(5);
//			theoryLessonMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), tl, tle);
//			FileWriter fw = new FileWriter(file);
//			fw.write(sdf.format(date));
//			fw.close();
//		}
		
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int privilege = user.getLevel(); //2门店、1片区、0驾校
		//门店帐号，只能看本门店数据，片区帐号，只能看本片区的
		TheoryLessonStoreDto dto = new TheoryLessonStoreDto();
		dto.setAreaid(privilege==0?theoryLesson.getAreaid():user.getAreaid());
		dto.setStoreid(privilege==2?user.getStoreid():null);
		dto.setIsdel((byte) 0);
		dto.setState(theoryLesson.getState());
		dto.setStarttime(theoryLesson.getStarttime());
		dto.setEndtime(theoryLesson.getEndtime());
		dto.setPageNo(theoryLesson.getPageNo());
		dto.setPageSize(theoryLesson.getPageSize());
		dto.setOrders(theoryLesson.getOrders());
		

		List<TheoryLessonStoreDto> theoryLessons = null;
		PageUtil.startPage(dto);
		if (privilege == 2)
			theoryLessons = theoryLessonMapper.selectLessonStore(dto);
		else {
			theoryLessons = theoryLessonMapper.selectLesson(dto);
		}

		if (privilege == 2 && theoryLessons != null) {
			List<TheoryLessonStoreDto> tlsds = new ArrayList<>();
			for (TheoryLessonStoreDto tlsd : theoryLessons) {
				if (tlsd.getState() == 1 && "2".equals(tlsd.getExtra()))
					tlsd.setState(2);
				else if (tlsd.getState() == 9 && !"9".equals(tlsd.getExtra()))
					tlsd.setState(2);
				tlsds.add(tlsd);
			}
			theoryLessons = tlsds;
		}
		PageInfo<TheoryLessonStoreDto> pagedResult = new PageInfo<>(theoryLessons);
		r.setResult(pagedResult);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean getTheory(TheoryLessonStoreDto theoryLesson,  boolean isReview) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		
		if (theoryLesson.getTheoryid() == null && (theoryLesson.getTransactionid() == null || theoryLesson.getTransactionid().trim().equals(""))) {
			r.setCode(HttpConstant.ERROR_CODE);
			r.setMsg(HttpConstant.ERROR_MSG);
			return r;
		}
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int privilege = user.getLevel(); //2门店、1片区、0驾校

		if (theoryLesson.getTheoryid() == null && theoryLesson.getTransactionid() != null && !theoryLesson.getTransactionid().trim().equals("")) {
			TheoryLessonStoreDto tl = new TheoryLessonStoreDto();
			tl.setTransactionid(theoryLesson.getTransactionid());
			
			List<TheoryLessonStoreDto> list = theoryLessonMapper.selectLesson(tl);
			if (list != null && list.size() > 0) {
				theoryLesson.setTheoryid(list.get(0).getTheoryid());
			}
		}
		
		if (isReview) {
			if (privilege == 2){
				r.setCode(HttpConstant.NO_AUTH_COCE);
				r.setMsg("没有权限!");
				return r;
			} else {
				TheoryLesson tl = theoryLessonMapper.selectByPrimaryKey(theoryLesson.getTheoryid());
				if (tl == null)
					return r;
			}

		}

		TheoryLessonStoreDto dto = new TheoryLessonStoreDto();
		dto.setTheoryid(theoryLesson.getTheoryid());
		dto.setIsdel((byte) 0);
		TheoryLessonStoreDto theory = null;
		if (privilege == 2) {
			dto.setAreaid(user.getAreaid());
			dto.setStoreid(user.getStoreid());
			dto.setDblink(user.getDblink());
			dto.setMgrdb(user.getMgrdb());
			List<TheoryLessonStoreDto> theorys = theoryLessonMapper.selectLessonStore(dto);
			if (theorys != null && theorys.size() > 0) {
				theory = theorys.get(0);
				if ((theory.getState() == 1 || theory.getState() == 9) && "2".equals(theory.getExtra()))
					theory.setState(2);
				theory.setRecomnum(theory.getRecomnumStore());
				theory.setArrangenum(theory.getArrangenumStore());
			} else {
				return r;
			}
		} else {
			dto.setAreaid(theoryLesson.getAreaid());
			dto.setStoreid(theoryLesson.getStoreid());
			dto.setDblink(user.getDblink());
			dto.setMgrdb(user.getMgrdb());
			List<TheoryLessonStoreDto> theorys = theoryLessonMapper.selectLessonStore(dto);
			if (theorys != null && theorys.size() > 0) {
				theory = theorys.get(0);
				theory.setStoreid(null);
			} else {
				return r;
			}
		}

		List<TheoryStoreDto> tss = null;
		List<Student> ss = null;
		if (isReview && privilege < 2) {
			TheoryStore ts = new TheoryStore();
			ts.setTheoryid(theoryLesson.getTheoryid());
			ts.setDblink(user.getDblink());
			ts.setMgrdb(user.getMgrdb());
			tss = theoryStoreMapper.selectTheoryStore(ts);
		} else {
			TheoryStudentExample example = new TheoryStudentExample();
			example.setDblink(user.getDblink());
			example.setMgrdb(user.getMgrdb());
			cn.com.liliyun.theory.model.TheoryStudentExample.Criteria rc = example.createCriteria().andTheoryidEqualTo(theoryLesson.getTheoryid());
			if (privilege == 2)
				rc.andStoreidEqualTo(user.getStoreid());
			else if (privilege < 2 && theoryLesson.getStoreid() != null && !isReview)
				rc.andStoreidEqualTo(theoryLesson.getStoreid());
			List<TheoryStudent> tStudents = theoryStudentMapper.selectByExample(example);
			StringBuilder ids = new StringBuilder();
			for (TheoryStudent tStudent : tStudents) {
				ids.append(tStudent.getStudentid().toString() + ", ");
			}
			Student s = new Student();
			s.setStunum(ids.toString());
			s.setDblink(user.getDblink());
			s.setMgrdb(user.getMgrdb());
			ss = studentMapper.selectStudentInIds(s);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("theory", theory);
		result.put("list", tss!=null?tss:ss);
		r.setResult(result);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean getTheoryStores() {
		ResultBean r = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		List<TheoryStoreDto> tsds = theoryStoreMapper.selectStoreByAreaId(user.getDblink(), user.getMgrdb(), user.getAreaid());
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		r.setResult(tsds);
		
		return r;
	}

	@Override
	public ResultBean getTheoryStudents(Student student) {
		ResultBean r = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		student.setAreaid(user.getAreaid());
		student.setStoreid(user.getStoreid());
		
		PageUtil.startPage(student);
		List<Student> students = studentMapper.selectTheoryStudents(student);
		r.setResult(new PageInfo<>(students));
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean addTheory(TheoryLesson theoryLesson, String stores) {
		ResultBean r = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int privilege = user.getLevel(); //2门店、1片区、0驾校
		if (privilege != 1) {
			r.setCode(HttpConstant.NO_AUTH_COCE);
			r.setMsg("没有权限!");
			return r;
		}

		Gson gson = new GsonBuilder().create();
		String numberRegex = "[0-9]*";
		/*
		 * stores=[{"num":10,"storeId":1},{"num":20,"storeId":2},{"num":30,"storeId":3}]
		 */
		List<Map<String, String>> list = gson.fromJson(stores, new TypeToken<List<Map<String, String>>>() {
			private static final long serialVersionUID = 1L;}.getType());
		theoryLesson.setAreaid(user.getAreaid());
		theoryLesson.setState(1);
		theoryLesson.setCuid(user.getId());
		
		theoryLessonMapper.insertSelective(theoryLesson);
		int total = 0;

		for (Map<String, String> map : list) {
			String num = map.get("num");
			String storeid = map.get("storeId");
			if (storeid != null && num != null && storeid.matches(numberRegex) && num.matches(numberRegex) && !"0".equals(num.trim())) {
				TheoryStore ts = new TheoryStore();
				ts.setTheoryid(theoryLesson.getTheoryid());
				ts.setStoreid(Integer.parseInt(storeid));
				ts.setRecomnum(Integer.parseInt(num));
				
				theoryStoreMapper.insertSelective(ts);
			}
			total += Integer.parseInt(num);
		}
		theoryLesson.setRecomnum(total);
		
		theoryLessonMapper.updateByPrimaryKeySelective(theoryLesson);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}


	@Override
	public ResultBean editTheoryStudent(Integer theoryId, String[] ids,  boolean isDel) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int privilege = user.getLevel(); //2门店、1片区、0驾校

		TheoryLesson theory = theoryLessonMapper.selectByPrimaryKey(theoryId);

		if (theory == null)
			return r;
		else if (privilege == 2) { //提交名单之后，privilege==2即门店客服，不允许再修改名单
			TheoryStoreExample example = new TheoryStoreExample();
			example.createCriteria().andTheoryidEqualTo(theoryId).andStoreidEqualTo(user.getStoreid());
			
			List<TheoryStore> tStores = theoryStoreMapper.selectByExample(example);
			if (tStores == null || tStores.size() != 1 || "2".equals(tStores.get(0).getExtra()))
				return r;
		}

		if (privilege == 2 && !isDel) {
			//更新理论课学员列表
			List<TheoryStudent> theoryStudents = new ArrayList<>();
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					TheoryStudent s = new TheoryStudent();
					
					s.setStudentid(Integer.parseInt(id));
					s.setStoreid(user.getStoreid());
					s.setTheoryid(theoryId);
					s.setRemark("0");
					theoryStudents.add(s);
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("list", theoryStudents);
			
			theoryStudentMapper.insertSelectiveBatch(map);
			//更新理论课对应门店的学员人数数据
			TheoryStore theoryStore = new TheoryStore();
			theoryStore.setTheoryid(theoryId);
			theoryStore.setStoreid(user.getStoreid());
			
			theoryStoreMapper.updateStoreArrangedNum(theoryStore);
			//更新理论课学员人数数据
			theoryLessonMapper.updateLessonArrangedNum(theoryId);
		} else if (isDel) {
			//业务规定，删除每次只删除一个，ids长度不为1则数据有问题
			if (ids.length != 1)
				return r;
			TheoryStudentExample example = new TheoryStudentExample();
			example.createCriteria().andTheoryidEqualTo(theoryId).andStudentidEqualTo(Integer.parseInt(ids[0]));
			
			List<TheoryStudent> tStudents = theoryStudentMapper.selectByExample(example);
			if (tStudents == null || tStudents.size() > 1)
				return r;
			TheoryStudent tStudent = tStudents.get(0);
			if (tStudent.getRemark() != null && "1".equals(tStudent.getRemark())) {
				//reamrk=1 ， 已发送过开课短信，删除该学员则发送取消短信

				//发送取消短信，后期添加
				System.out.println("发送取消短信：studentid=" + tStudent.getStudentid().toString() + ", theoryid=" + tStudent.getTheoryid().toString());
			}
			theoryStudentMapper.deleteByExample(example);

			//更新理论课对应门店的学员人数数据
			TheoryStore theoryStore = new TheoryStore();
			theoryStore.setTheoryid(theoryId);
			theoryStore.setStoreid(tStudent.getStoreid());
			
			theoryStoreMapper.updateStoreArrangedNum(theoryStore);
			//更新理论课学员人数数据
			theoryLessonMapper.updateLessonArrangedNum( theoryId);
		}
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean updateTheory(TheoryLesson theoryLesson) {
		ResultBean r = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String businessid = this.getContextValue(ConstantUtil.SESSION_BUSINESS);
		if (theoryLesson.getState() == 5) {
			TheoryLessonExample tle = new TheoryLessonExample();
			tle.createCriteria().andStateEqualTo(4).andIsdelEqualTo((byte) 0).andEndtimeLessThan(new Date());
			TheoryLesson tl = new TheoryLesson();
			tl.setState(5);
			theoryLessonMapper.updateByExampleSelective( tl, tle);
			return r;
		}
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		
		int privilege = user.getLevel(); //2门店、1片区、0驾校

		
		int state = theoryLesson.getState();
		TheoryLesson theory = theoryLessonMapper.selectByPrimaryKey(theoryLesson.getTheoryid());
		switch (state) {
			case 0:
				if (theory == null || theory.getState() != 4)
					return r;
				TheoryStudentExample example = new TheoryStudentExample();
				example.createCriteria().andRemarkEqualTo("1").andTheoryidEqualTo(theoryLesson.getTheoryid());
				List<TheoryStudent> list = theoryStudentMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					//短信通知，后期再添加

					TheoryStudent ts = new TheoryStudent();
					//把已发送取消短信的学员，reamrk置为2
					ts.setRemark("2");
					theoryStudentMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), ts, example);
				}
				theoryLessonMapper.updateByPrimaryKeySelective(theoryLesson);
				break;
			case 1:
				if (theory == null || theory.getState() != 3 && theory.getState() != 4)
					return r;
				TheoryLesson tl = new TheoryLesson();
				
				tl.setTheoryid(theoryLesson.getTheoryid());
				tl.setState(1);
				theoryLessonMapper.updateByPrimaryKeySelective(tl);
				TheoryStoreExample tsExample = new TheoryStoreExample();
				tsExample.createCriteria().andTheoryidEqualTo(theoryLesson.getTheoryid());
				TheoryStore ts1 = new TheoryStore();
				ts1.setExtra("1");
				theoryStoreMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), ts1, tsExample);
				break;
			case 2:
				if (theory == null || theory.getState() != 1 && theory.getState() != 9 || privilege != 2)
					return r;
				TheoryStoreExample tsexample = new TheoryStoreExample();
				tsexample.createCriteria().andTheoryidEqualTo(theoryLesson.getTheoryid()).andStoreidEqualTo(user.getStoreid());;
				TheoryStore theoryStore = new TheoryStore();
				theoryStore.setExtra("2");
				theoryStoreMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), theoryStore, tsexample);
				//若都添加了学员，更新理论课状态为待审核
				tsexample.clear();
				tsexample.createCriteria().andTheoryidEqualTo(theoryLesson.getTheoryid());
				
				List<TheoryStore> tStores = theoryStoreMapper.selectByExample(tsexample);
				boolean isCompleted = true;
				for (TheoryStore tStore : tStores) {
					if (!"2".equals(tStore.getExtra())) {
						isCompleted = false;
						break;
					}
				}
				if (isCompleted) {
					TheoryLesson temp = new TheoryLesson();
					temp.setTheoryid(theory.getTheoryid());
					temp.setState(2);
					
					
					String desc = "理论课[" + theory.getLessonname() + "]学员安排审核";
					String transactionid = flowService.addFlow(businessid, user.getId(), desc);
					temp.setBusinessid(businessid);
					temp.setTransactionid(transactionid);
					theoryLessonMapper.updateByPrimaryKeySelective(temp);
				}
				break;
			case 3:
				if (theory == null || theory.getState() != 2)
					return r;
				Flow flow_accept = flowService.getFlow(theory.getTransactionid());
				
				boolean next = (flow_accept != null) && flowService.auditFlow(flow_accept, user.getId(), ConstantUtil.AUDIT_ACCEPT);
				if (!next) {
					//设置审核人等信息
					theoryLesson.setReviewerid(user.getId());
					theoryLesson.setReviewstate(1);
					theoryLesson.setReviewtime(new Date());
					theoryLessonMapper.updateByPrimaryKeySelective(theoryLesson);
				}
				break;
			case 4:
				if (theory == null || theory.getState() != 3)
					return r;
				//短信通知，后期再添加，找到未发送短信学员发送短信并把对应字段remark置为1
				TheoryStudentExample example4 = new TheoryStudentExample();
				
				example4.createCriteria().andRemarkNotEqualTo("1").andTheoryidEqualTo(theoryLesson.getTheoryid());
				List<TheoryStudent> tslist = theoryStudentMapper.selectByExample(example4);
				if (tslist != null && tslist.size() > 0) {
					//短信通知，后期再添加
					
					//给学员插入一条排课记录
					for (TheoryStudent ts : tslist) {
						StudentStatusLog studentStatusLog = new StudentStatusLog();
						studentStatusLog.setStudentid(ts.getStudentid());
						Student student = new Student();
						
						student.setId(ts.getStudentid());
						Student s = studentMapper.selectByPrimaryKey(student);
						studentStatusLog.setIdcard(s!=null?s.getIdcard():"");
						studentStatusLog.setSubject(ApplyExam.SUBJECT1_CLASS_CLASS.getSubject());
						studentStatusLog.setSubjectname(ApplyExam.SUBJECT1_CLASS_CLASS.getName());
						saveStudentStatusLog(studentStatusLog);
						
						student.setApplyexam(ApplyExam.SUBJECT1_CLASS_CLASS.getApplyexam());
						student.setApplystatus(ApplyExam.SUBJECT1_CLASS_CLASS.getApplystatus());
						updateStudent( student);
					}
					
					TheoryStudent ts4 = new TheoryStudent();
					//把已发送上课短信的学员，reamrk置为1
					ts4.setRemark("1");
					theoryStudentMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), ts4, example4);
				}
				theoryLessonMapper.updateByPrimaryKeySelective(theoryLesson);
				break;
			case 9:
				if (theory == null || theory.getState() != 2)
					return r;
				//审核拒绝，理论课状态改为拒绝并把对应的门店记录也记录为拒绝
				TheoryStoreExample exampleS = new TheoryStoreExample();
				exampleS.createCriteria().andTheoryidEqualTo(theoryLesson.getTheoryid());
				TheoryStore tStore = new TheoryStore();
				tStore.setExtra("9");
				theoryStoreMapper.updateByExampleSelective(user.getDblink(), user.getMgrdb(), tStore, exampleS);
				//设置审核人等信息
				theoryLesson.setReviewerid(user.getId());
				theoryLesson.setReviewstate(9);
				theoryLesson.setReviewtime(new Date());
				theoryLessonMapper.updateByPrimaryKeySelective(theoryLesson);
				
				Flow flow_reject = flowService.getFlow(theory.getTransactionid());
				if (flow_reject != null)
					flowService.auditFlow(flow_reject, user.getId(), ConstantUtil.AUDIT_REJECT);
				break;

			default:
				return r;
		}
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean addCoachStudent(CoachStudent coachStudent, Boolean isreview) {
		ResultBean r=new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String businessid = this.getContextValue(ConstantUtil.SESSION_BUSINESS);
		if (!isreview) {
			Student student = new Student();
			student.setId(coachStudent.getStudentid());
			
			student = studentMapper.selectByPrimaryKey(student);
			coachStudent.setCuid(user.getId());
			if (student.getApplyexam() != null && (student.getApplyexam() > 3 || (student.getApplyexam() == 2 && student.getApplystatus() > 1))) {
				//已科目二报考，需要审核才能更改教练
				coachStudent.setIsvalid(0);
				coachStudent.setState(1);
				coachStudent.setReviewid(-1);
				
				String desc = "学员[" + student.getName() + "]教练变更申请";
				String transactionid = flowService.addFlow(businessid, user.getId(), desc);
				coachStudent.setBusinessid(businessid);
				coachStudent.setTransactionid(transactionid);
			} else {
				coachStudent.setIsvalid(1);
				coachStudent.setState(0);
			}
		}

		
		CoachStudent exist= coachStudentMapper.get(coachStudent);
		if(exist!=null){
			if (isreview && coachStudent.getState() != null) {
				coachStudent.setIsvalid(coachStudent.getState()==2?1:0);
				coachStudent.setReviewid(user.getId());
				
				Flow flow = flowService.getFlow(exist.getTransactionid());
				
				if (coachStudent.getState() == 2) { //申请通过
					boolean next = (flow != null) && flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_ACCEPT);
					if (next)
						return r;
				} else if (flow != null) { //申请拒绝
						flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_REJECT);
				}
			}
			coachStudentMapper.updateSelective(coachStudent);
		}else{
			coachStudentMapper.insert(coachStudent);
			//调用coachservice 增加教练负荷学员数
			coachService.incrementCoachStudent(coachStudent.getCoachid());
		}
		
		if (coachStudent.getIsvalid() == 1) {
			StudentStatusLog studentStatusLog = new StudentStatusLog();
			studentStatusLog.setStudentid(coachStudent.getStudentid());
			Student student = new Student();
			student.setId(coachStudent.getStudentid());
			
			Student s = studentMapper.selectByPrimaryKey(student);
			studentStatusLog.setIdcard(s!=null?s.getIdcard():"");
			if (exist != null) {
				studentStatusLog.setSubject(ApplyExam.OTHER_CHANGE_COACH.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.OTHER_CHANGE_COACH.getName());
			} else {
				studentStatusLog.setSubject(ApplyExam.SUBJECT2_CAOCH_ASSIGN.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT2_CAOCH_ASSIGN.getName());
				
				student.setApplyexam(ApplyExam.SUBJECT2_CAOCH_ASSIGN.getApplyexam());
				student.setApplystatus(ApplyExam.SUBJECT2_CAOCH_ASSIGN.getApplystatus());
				updateStudent( student);
			}
			saveStudentStatusLog( studentStatusLog);
		}
		
		return r;
	}

	@Override
	public CoachStudent getCoachStudent(CoachStudent coachStudent) {
		return coachStudentMapper.get(coachStudent);
	}


	@Override
	public ResultBean addStudentPauseApply(StudentPauseApply apply) {
		ResultBean r=new ResultBean();

		StudentPauseApply param = new StudentPauseApply();
		param.setStudentid(apply.getStudentid());
		

		StudentPauseApply eapply = studentPauseApplyMapper.getApplyByStudentid(param);
		if (eapply != null) {
			r.setCode(ResultCode.ERRORCODE.PAUSEAPPLYEXIST);
			r.setMsg(ResultCode.ERRORINFO.PAUSEAPPLYEXIST);
			return r;
		}
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String businessid = this.getContextValue(ConstantUtil.SESSION_BUSINESS);
		apply.setApplyuserid(user.getId());

		apply.setApplyuserid(user.getId());
		apply.setApplyuser(user.getUsername());
		apply.setBusinessid(businessid);
		String typestr=null;
		if(apply.getType()==1){
			typestr="暂停学车";
		}else{
			typestr="恢复学车";
		}
		Student sp=new Student();
		sp.setId(apply.getStudentid());
		
		Student exist=studentMapper.selectByPrimaryKey(sp);
		String desc = "学员[" + exist.getName() + "]"+typestr+"申请";
		String transactionid = flowService.addFlow(businessid, user.getId(),
				desc);


		apply.setTransactionid(transactionid);

		studentPauseApplyMapper.insert(apply);

		return r;
	}


	@Override
	public String theoryLessonText(Integer theoryid, Integer type) {
		String r = null;
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String cancelText = "尊敬的学员，由于临时的特殊原因，驾校取消了{1}的{3}培训，给您带来的不便，十分抱歉，如有问题请联系客服！";
		String classText = "尊敬的喱喱学员，请您于{1}携带{2}到{3}参加{5}培训，课程时间为{6}，培训为指纹签到，请您提前十分钟到达现场签到";
		TheoryLesson theoryLesson = theoryLessonMapper.selectByPrimaryKey(theoryid);
		if (theoryLesson == null)
			return r;
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		if (type == 4) {
			//开课短信
			r = classText.replace("{1}", sdfDate.format(theoryLesson.getStarttime()))
					.replace("{2}", theoryLesson.getIdcard())
					.replace("{3}", theoryLesson.getPlace())
//					.replace("{4}", theoryLesson.getContactname())
					.replace("{5}", theoryLesson.getLessonname())
					.replace("{6}", sdfTime.format(theoryLesson.getStarttime()) + "-" + sdfTime.format(theoryLesson.getEndtime()));
//					.replace("{7}", theoryLesson.getContactphone());
		} else if (type == 0) {
			//取消短信
			r = cancelText.replace("{1}", sdfDate.format(theoryLesson.getStarttime()))
//					.replace("{2}", theoryLesson.getContactname())
					.replace("{3}", theoryLesson.getLessonname());
		}
		return r;
	}

	@Override
	public List<TheoryStudentExport> theoryStudentExport(TheoryStudent theoryStudent) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		theoryStudent.setStoreid(user.getStoreid());
		return studentMapper.selectStudentExport(theoryStudent);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> importFlownum(Map <String,Object> params) {
		Map<String,Object> rtnData = new HashMap<>();
		List <Flownum> list = (List<Flownum>) params.get("list");
		
		Student query = new Student();
		
		Student update = new Student();
		
		Student result = new Student();
		
		StudentStatusLog studentStatusLog = new StudentStatusLog();
		
		StringBuilder sb = new StringBuilder();
		int errorCount = 0 , total = list.size();
		Iterator<Flownum> f = list.iterator();
		List <Flownum> successlist = new ArrayList<>();
		while (f.hasNext()) {
			sb.setLength(0);
			Flownum flownum = f.next();
			query.setIdcard(flownum.getIdcard());
			result = getStudent(query);
			if (result == null) {
				sb.append("学员信息不存在;");
			} else {
				if (!result.getName().equals(flownum.getName())) {
					sb.append("学员姓名与系统中学员姓名不一致;");
				}
				if (!result.getTraintype().equals(flownum.getTraintype())) {
					sb.append("学员所学车型不匹配");
				}
			}
			if (sb.length() > 0) {
				flownum.setErrormsg(sb.toString());
				errorCount++;
				continue;
			} else {
				//更新流水号
				update.setFlownum(flownum.getFlownum());
				update.setIdcard(flownum.getIdcard());
				update.setApplyexam(ApplyExam.SIGNUP_ACCEPT_FLOWNUM.getApplyexam());
				update.setApplystatus(ApplyExam.SIGNUP_ACCEPT_FLOWNUM.getApplystatus());
				studentMapper.updateByPrimaryKeySelective(update);

				//更新学员进度
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setSubject(ApplyExam.SIGNUP_ACCEPT_FLOWNUM.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SIGNUP_ACCEPT_FLOWNUM.getName());
				saveStudentStatusLog(studentStatusLog);
				successlist.add(flownum);
				f.remove();
			}
		}
		rtnData.put("total", total);
		rtnData.put("success", total - errorCount);
		rtnData.put("error", errorCount);
		rtnData.put("errorlist", list); //有问题的数据
		rtnData.put("successlist", successlist); //成功导入的数据
		return rtnData;
	}

	@Override
	public ResultBean updateStudentPauseApply(StudentPauseApply apply) {
		ResultBean r=new ResultBean();
		StudentPauseApply exist= studentPauseApplyMapper.get(apply);
		if(exist.getStatus()!=0){
			r.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
			r.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
			return r;
		}
		studentPauseApplyMapper.update(apply);
		return r;
	}

	@Override
	public ResultBean updateStudentPauseApplyStatus(StudentPauseApply apply) {
		ResultBean r=new ResultBean();
		StudentPauseApply exist= studentPauseApplyMapper.get(apply);
		if(exist.getStatus()!=0){
			r.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
			r.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
			return r;
		}
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		apply.setAudituserid(user.getId());
		apply.setAudituser(user.getUsername());
		//apply.setAuditdate(new Date());

		Student student=new Student();
		student.setId(exist.getStudentid());
		
		Student existstu= studentMapper.selectByPrimaryKey(student);

		if(apply.getType()==PAUSE_TYPE&& apply.getStatus()==1){//暂停审核通过
			if(existstu.getLearnstatus()==1){//学员已经是暂停状态
				r.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
				r.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
				return r;
			}

		}else if(apply.getType()==RESUME_TYPE&& apply.getStatus()==1){//恢复审核通过
			if(existstu.getLearnstatus()==0){//学员已经是正常状态
				r.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
				r.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
				return r;
			}

		}

		Flow flow = flowService.getFlow(exist.getTransactionid());
		
		if (apply.getStatus() == 1) {
			boolean next = (flow != null)
					&& flowService.auditFlow(flow, user.getId(),
					ConstantUtil.AUDIT_ACCEPT);

			if (!next) {
				studentPauseApplyMapper.updateStatus(apply);


				if(apply.getType()==PAUSE_TYPE&& apply.getStatus()==1){//暂停审核通过

					student.setLearnstatus(1);
					studentMapper.updateLearnstatus(student);
				}else if(apply.getType()==RESUME_TYPE&& apply.getStatus()==1){//恢复审核通过

					student.setLearnstatus(0);
					studentMapper.updateLearnstatus(student);
				}
			}
		} else if(apply.getStatus()==2){//审核不通过
			if(flow!=null)
				flowService.auditFlow(flow, user.getId(),
						ConstantUtil.AUDIT_REJECT);
			studentPauseApplyMapper.updateStatus(apply);
		} else if(apply.getStatus()==3){
			if(flow!=null)
				flowService.auditFlow(flow, user.getId(),
						ConstantUtil.AUDIT_CANCEL);
			studentPauseApplyMapper.updateStatus(apply);
		}

		return r;
	}



	private static final int PAUSE_TYPE=1;
	private static final int RESUME_TYPE=2;

	@Override
	public ResultBean listStudentPauseApply(StudentPauseApplyParam param) {
		if(param.getEtime()!=null){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(param.getEtime());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.add(Calendar.SECOND, -1);
			param.setEtime(calendar.getTime());
		}
		ResultBean rb = new ResultBean();
		PageUtil.startPage(param);
		List<StudentPauseApply> list = studentPauseApplyMapper.list(param);
		
		/**
		 * @todo
		 * user module need to route to mgrdb
		 */
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		List<User> users= userService.selectSchoolUser(null);
		for(StudentPauseApply apply:list){

			for(User s:users){
				if(apply.getApplyuserid()==s.getId()){
					apply.setApplyuser(s.getUsername());
				}
				if(apply.getAudituserid()==s.getId()){
					apply.setAudituser(s.getUsername());
				}
			}
			if(apply.getApplyuserid()==user.getId()){
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			}
		}

		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean getStudentPauseApply(StudentPauseApply apply) {
		StudentPauseApply exist=studentPauseApplyMapper.get(apply);
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		if (exist.getApplyuserid() == user.getId().intValue()) {// 当前用户是发起人
			if (exist.getStatus() == 0) {// 业务还在等待审核中
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				Flow flow = new Flow();
				
				flow.setTransactionid(exist.getTransactionid());
				FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
				if(fs==null){//说明没有审批流
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				}else{
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
				
			}
		} else {
			Flow flow = new Flow();
			
			flow.setTransactionid(exist.getTransactionid());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//说明没有审批流
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}

		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}

	@Override
	public ResultBean getStudentPauseApplyByTransaction(StudentPauseApply apply) {
		StudentPauseApply exist=studentPauseApplyMapper.getByTransaction(apply);
		User up=new User();
		up.setMgrdb(true);
		up.setId(exist.getApplyuserid());
		User applyuser= userService.getUser(up);
		exist.setApplyuser(applyuser.getUsername());
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		if (exist.getApplyuserid() == user.getId().intValue()) {// 当前用户是发起人
			if (exist.getStatus() == 0) {// 业务还在等待审核中
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
			}
		} else {
			Flow flow = new Flow();
			
			flow.setTransactionid(exist.getTransactionid());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//说明没有审批流
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}

		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}

	@Override
	public ResultBean getTransferList(TransferStudent transferStudent, Boolean isChosen) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int level = user.getLevel();
		transferStudent.setTargetareaid(level!=0?user.getAreaid():null);
		transferStudent.setTargetstoreid(level==2?user.getStoreid():null);
		

		List<TransferStudent> list = null;
		PageUtil.startPage(transferStudent);
		if (!isChosen) {
			transferStudent.setFromareaid(level!=0?user.getAreaid():null);
			transferStudent.setFromstoreid(level==2?user.getStoreid():null);
		} else {
			transferStudent.setIsChosen(1);
		}
		list = transferStudentMapper.selectTransferList(transferStudent);
		r.setResult(new PageInfo<>(list));
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean getTransfer(TransferStudent transferStudent) {
		ResultBean r = new ResultBean();
		
		Map<String, Object> result = new HashMap<>();
		
		TransferStudent ts = transferStudentMapper.selectByPrimaryKey(transferStudent);
		Student student = new Student();
		student.setId(ts.getStudentid());
		
		Student s = studentMapper.selectByPrimaryKey(student);
		Store store = new Store();
		store.setId(ts.getTargetstoreid());
		
		Store ss = storeService.selectOne(store);
		result.put("transfer", ts);
		result.put("student", s);
		result.put("store", ss);
		r.setResult(result);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean addTransfer(TransferStudent transferStudent) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		
		Student ss = new Student();
		ss.setId(transferStudent.getStudentid());
		
		Student s = studentMapper.selectByPrimaryKey(ss);
		if (s.getStoreid() == null ||
				transferStudent.getTargetareaid() == null || transferStudent.getTargetstoreid() == null)
			return r;
		if (transferStudent.getTargetstoreid() == s.getStoreid().intValue()) {
			r.setMsg("不能把原门店设置为目标门店！");
			return r;
		}
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		Store storetemp = new Store();
		storetemp.setId(s.getStoreid());
		Store store = storeService.selectOne(storetemp);
		transferStudent.setFromareaid(s.getAreaid());
		transferStudent.setFromstoreid(s.getStoreid());
		transferStudent.setFromstorenum(store.getStorenum());
		transferStudent.setName(s.getName());
		transferStudent.setStudentnum(s.getStunum());
		transferStudent.setIdcard(s.getIdcard());
		transferStudent.setIsoutarea(transferStudent.getTargetareaid() != s.getAreaid()?(byte)1:(byte)0);
		transferStudent.setState(1);
		transferStudent.setCuid(user.getId());
		transferStudent.setCname(user.getRealname());
		
		
//			String desc = "学员[" + s.getName() + "]转店审核";
//			String transactionid = flowService.addFlow(businessid, user.getId(), desc);
//			transferStudent.setBusinessid(businessid);
//			transferStudent.setTransactionid(transactionid);
		
		transferStudentMapper.insertSelective(transferStudent);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		return r;
	}

	@Override
	public ResultBean editTransfer(TransferStudent transferStudent) {
		ResultBean r = new ResultBean();
		r.setCode(HttpConstant.DATA_ERROR_COCE);
		r.setMsg(HttpConstant.DATA_ERROR_MSG);
		
		Integer state = transferStudent.getState();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		TransferStudent transfer = transferStudentMapper.selectByPrimaryKey(transferStudent);
		if (transfer.getState() != 1)
			return r;
		if (state != null) {
			TransferStudent ts = new TransferStudent();
			ts.setId(transferStudent.getId());
			ts.setState(state);
			
			if (state == 2 || state == 9) {
				ts.setReviewid(user.getId());
				ts.setReviewname(user.getRealname());
				ts.setReviewtime(new Date());
				ts.setReviewremark(transferStudent.getReviewremark());
				
				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(transfer.getStudentid());
				studentStatusLog.setIdcard(transfer.getIdcard());
				studentStatusLog.setSubject(ApplyExam.OTHER_TRANSFER_STORE.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.OTHER_TRANSFER_STORE.getName());
				saveStudentStatusLog( studentStatusLog);
			} else if (state != 0) {
				return r;
			}
			transferStudentMapper.updateByPrimaryKeySelective(ts);
			if (state == 2) {
				Student s = new Student();
				s.setId(transfer.getStudentid());
				s.setAreaid(transfer.getTargetareaid());
				s.setStoreid(transfer.getTargetstoreid());
				studentMapper.updateByPrimaryKeySelective(s);
			}
		} else {
			if (transferStudent.getStudentid() != null) {
				Student ss = new Student();
				ss.setId(transferStudent.getStudentid());
				
				Student s = studentMapper.selectByPrimaryKey(ss);
				if (s == null || s.getStoreid() == null)
					return r;
				if (transferStudent.getTargetstoreid() != null && transferStudent.getTargetstoreid().intValue()
						== s.getStoreid().intValue()) {
					r.setMsg("不能把原门店设置为目标门店！");
					return r;
				}
				transferStudent.setName(s.getName());
				transferStudent.setStudentnum(s.getStunum());
				transferStudent.setIdcard(s.getIdcard());
				transferStudent.setIsoutarea(transferStudent.getTargetareaid().intValue()
						!= s.getAreaid().intValue()?(byte)1:(byte)0);
			}
			transferStudentMapper.updateByPrimaryKeySelective(transferStudent);
		}
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean getTStudentList(Student student) {
		ResultBean r = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		int level = user.getLevel();
		student.setAreaid(level!=0?user.getAreaid():null);
		student.setStoreid(level==2?user.getStoreid():null);
		
		r.setResult(new PageInfo<>(getList(student)));
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		
		return r;
	}

	@Override
	public ResultBean getStudentPauseApplyByStuId(StudentPauseApply apply) {
		StudentPauseApply exist=studentPauseApplyMapper.getByStudentId(apply);
		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}

	@Override
	public List<Student> getCoachStudentList(CoachStudent coachStudent) {
		PageUtil.startPage(coachStudent);
		List<Student> list = studentMapper.selectCoachList(coachStudent);
		User param=new User();
		param.setSchoolid(coachStudent.getSchoolid());
		List<User> users= userService.selectSchoolUser(param);
		
		Coach pc=new Coach();
		pc.setCoachid(coachStudent.getCoachid());
		pc.setDblink(coachStudent.getDblink());
		Coach coach= coachService.getCoachById(pc);
		for(Student s:list){
			for(User user:users){
				if(s.getAssignuserid()!=null&&s.getAssignuserid().intValue()==user.getId().intValue()){
					s.setAssignuser(user.getUsername());
				}
			}
			if(coach!=null){
				s.setCoachname(coach.getName());
			}
		}
		return list;
	}

	@Override
	public ResultBean getStudentCoach(StudentCoachDTO studentCoachDTO) {
		ResultBean r = new ResultBean();
		PageUtil.startPage(studentCoachDTO);
		List<StudentCoachDTO> list = coachStudentMapper.selectStudentCoach(studentCoachDTO);
		List<Integer> ids = new ArrayList<>();
		for (StudentCoachDTO scd : list) {
			if (scd.getCoachid() != null) {
				ids.add(scd.getCoachid());
			}
		}
		if (ids.size() > 0) {
			Map<Integer, CoachClassinfo> coachClassMap = coachService.selectCoachClassBatch(ids);
			for (StudentCoachDTO scd : list) {
				CoachClassinfo coachClassinfo = coachClassMap.get(scd.getCoachid());
				
				if (scd.getCoachid() != null && coachClassinfo != null) {
					scd.setCoachclasses(coachClassinfo.getClassName());
					scd.setCoachclassids(coachClassinfo.getIds());
				}
			}
		}
		r.setResult(new PageInfo<>(list));
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		return r;
	}

	@Override
	public void saveStudentStatusLog(StudentStatusLog studentStatusLog) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		studentStatusLog.setCuid(user.getId());
		studentStatusLog.setCname(user.getRealname());
		studentStatusLog.setCtime(new Date());
		studentStatusLog.setStoreid(user.getStoreid());
		studentStatusLog.setAreaid(user.getAreaid());
		studentStatusLogMapper.insertSelective(studentStatusLog);
	}

    @Override
    public void saveLogBatch( List<StudentStatusLog> list) {
        Map <String,Object> params = new HashMap<>();
        
        params.put("list",list);
        studentStatusLogMapper.insertBatch(params);
    }

    @Override
    public void updateStudentBatch( List<Student> list) {
        Map <String,Object> params = new HashMap<>();
        
        params.put("list",list);
        studentMapper.updateBatch(params);
    }

    @Override
	@SuppressWarnings({"rawtypes","unchecked"})
	public ResultBean updateStudentWithCheck(Student student) {
		try {
			StudentUpdateDTO modefiedObj = new StudentUpdateDTO();
			BeanUtils.copyProperties(modefiedObj, student);
			student = studentMapper.selectOne(student);
			StudentUpdateDTO orginalObj = new StudentUpdateDTO();
			BeanUtils.copyProperties(orginalObj, student);
			Map s1 = new BeanMap(modefiedObj);
			Map s2 = new BeanMap(orginalObj);
			List <String> modifiedKeys = new ArrayList<>();
			Set<String> keys  = s1.keySet();
			for (String key : keys) {
				if (s1.get(key) == null) {
					if (s2.get(key) != null) {
						modifiedKeys.add(key);
					}
				} else if (!s1.get(key).equals(s2.get(key))) {
					modifiedKeys.add(key);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultBean doUpdateStudentAfterCheck(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultBean getChangeLogList(StudentStatusLog statusLog) {
		ResultBean rb = new ResultBean();
		List <StudentStatusLog> list = studentStatusLogMapper.selectList(statusLog);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean checkStudent( Student student) {
//		user.getLevel()

		return null;
	}

	@Override
	public StudentMoney getStudentMoney(StudentMoney studentMoney) {
		return studentMoneyMapper.selectByPrimaryKey(studentMoney);
	}

	@Override
	public ResultBean updateStudentMoney(StudentMoney studentMoney) {
		studentMoneyMapper.updateByPrimaryKeySelective(studentMoney);
		return new ResultBean();
	}

	@Override
	public List <StudentMoneyDTO> selectOweList( StudentMoneyDTO dto) {
		PageUtil.startPage(dto);
		return studentMoneyMapper.selectOweList(dto);
	}


	@Override
	public ResultBean calcMoney( StudentCalcMoneyDTO dto) {
		ResultBean rb = new ResultBean();
		
		Classinfo classinfo = new Classinfo();
		
		classinfo.setId(dto.getClassinfoid());
		classinfo = classinfoService.get(classinfo);
		if (classinfo == null) {
			rb.setResult(0);
			return rb;
		}
		Integer fullPrice = "C1".equals(dto.getTraintype()) ? classinfo.getC1amount() : classinfo.getC2amount();
		fullPrice = ObjectUtils.defaultIfNull(fullPrice,0);
		Integer submoney = ObjectUtils.defaultIfNull(dto.getSubmoney(),0);
		Integer couponmoney = ObjectUtils.defaultIfNull(dto.getCouponmoney(),0);
		Integer contractmoney = fullPrice - submoney - couponmoney;
		rb.setResult(contractmoney);
		return rb;
	}

	@Override
	public List<StudentApplyStat> selectApplyStat(StudentApplyStat studentApplyStat) {
		
		return studentMapper.selectApplyStat(studentApplyStat);
	}

	@Override
	public void updateReceiptStudentMoney(List<StudentMoney> list, String ids) {
		if (list.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			
			map.put("list", list);
			studentMoneyMapper.updateReceiptOweMoney(map);
		}
		if (ids.length() > 0) {
			StudentMoney studentMoney = new StudentMoney();
			
			studentMoney.setIds(ids.substring(1));
			studentMoneyMapper.updateReceiptOweStatus(studentMoney);
		}
	}

}
