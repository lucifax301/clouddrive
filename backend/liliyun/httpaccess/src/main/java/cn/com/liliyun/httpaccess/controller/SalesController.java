package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.dto.SelectDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.service.SalesService;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.user.model.User;

/**
 * 营销活动
 * @author devil
 *
 */
@Controller
@ResponseBody
@RequestMapping(value="/sales")
public class SalesController extends BaseController{

	@Autowired
	private SalesService salesService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@Autowired
	private CoachSettingService coachSettingService;
	
	@RequestMapping(value="/listAllClassType")
	public ResultBean listAllClassType(SalesActivity activity,HttpServletRequest request){
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		try{
			Classinfo cp=new Classinfo();
			cp.setStatus(0);
			cp.setDblink(user.getDblink());
			List<Classinfo> clss= classinfoService.selectAllList(cp);
			CoachClassType pcct= new CoachClassType();
			pcct.setDblink(user.getDblink());
			List<CoachClassType> list = coachSettingService.listAllClassType(pcct);
			List result=new ArrayList();
			for(Classinfo cls:clss){
				SalesActivityClassinfo info=new SalesActivityClassinfo();
				info.setC1flag(cls.getC1flag());
				info.setC2flag(cls.getC2flag());
				info.setClassinfoid(cls.getId());
				info.setC1amount(cls.getC1amount());
				info.setC2amount(cls.getC2amount());
				info.setName(cls.getName());
				for(CoachClassType type:list){
					if(type.getId()==cls.getClasstypeid().intValue()){
						info.setClasstype(type.getType());
					}
				}
				result.add(info);
			}
			ResultBean rb = new ResultBean();
			rb.setResult(new PageInfo<>(result));
			return rb;
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb = new ResultBean("系统异常");
			return rb;
		}
	}
	
	@RequestMapping(value="/listEditAllClassType")
	public ResultBean listEditAllClassType(SalesActivity activity,HttpServletRequest request){
		
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		try{
			Classinfo cp=new Classinfo();
			cp.setDblink(user.getDblink());
			List<Classinfo> clss= classinfoService.selectAllList(cp);
			
			CoachClassType cct=new CoachClassType();
			cct.setDblink(user.getDblink());
			List<CoachClassType> list = coachSettingService.listAllClassType(cct);
			
			
			SalesActivity one= salesService.getSalesActivity(activity);
			List<SalesActivityClassinfo> result=one.getClassinfo();
			for(SalesActivityClassinfo info:result){
				for(Classinfo cls:clss){
					if(info.getClassinfoid().intValue()==cls.getId()){
						info.setC1flag(cls.getC1flag());
						info.setC2flag(cls.getC2flag());
						
						if(info.getC1price()==null)info.setC1price(0);
						if(info.getC2price()==null)info.setC2price(0);
						
						info.setC1amount(cls.getC1amount());
						info.setC2amount(cls.getC2amount());
						info.setName(cls.getName());
						for(CoachClassType type:list){
							if(type.getId()==cls.getClasstypeid().intValue()){
								info.setClasstype(type.getType());
							}
						}
					}
				}
			}
			ResultBean rb = new ResultBean();
			rb.setResult(new PageInfo<>(result));
			return rb;
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb = new ResultBean("系统异常");
			return rb;
		}
	}
	
	//新增
	@RequestMapping(value="/add")
	public ResultBean addSales(SalesActivity activity,HttpServletRequest request){
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		
		String data=request.getParameter("classinfos");
		List<SalesActivityClassinfo> classinfo=JSONObject.parseArray(data,SalesActivityClassinfo.class);
		
		activity.setClassinfo(classinfo);
		
		return salesService.addSalesActivity(activity, log, user, null);
	}
	
	
	
	//更新
	@RequestMapping(value="/update")
	public ResultBean updateSales(SalesActivity activity,HttpServletRequest request){
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_UPDATE);
		User user=AccessWebUtil.getSessionUser(request);
		
		
		String data=request.getParameter("classinfos");
		String id=request.getParameter("id");
		List<SalesActivityClassinfo> classinfo=JSONObject.parseArray(data,SalesActivityClassinfo.class);
		
		activity.setClassinfo(classinfo);
		
		return salesService.updateSalesActivity(activity, log, user);
	}
	
	//列表
		@RequestMapping(value="/list")
		public ResultBean getSalesList(SalesActivity activity) {
			ResultBean resultBean = new ResultBean();
			List<SalesActivity> list= salesService.listActivity(activity);
			PageInfo<SalesActivity> pagedResult = new PageInfo<>(list);
			resultBean.setResult(pagedResult);
			resultBean.setCode(0);
			return resultBean;
		}
		
		@RequestMapping(value="/list/export")
		public ResponseEntity<byte[]> getSalesListExport(SalesActivity activity) throws IOException {
			ResultBean resultBean = new ResultBean();
			List<SalesActivity> list= salesService.listActivity(activity);
			
			ExportParams params = new ExportParams("营销活动数据", "导出数据", ExcelType.XSSF);// title
			// sheetname
			// 文件格式
			Workbook workbook = ExcelExportUtil.exportExcel(params, SalesActivity.class,
			list);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			workbook.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("营销活动数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
			HttpStatus.CREATED);
		}
		
		@RequestMapping(value="/listmatch")
		public ResultBean listMatchSalesList(SalesActivityClassinfo activity) {
			ResultBean resultBean = new ResultBean();
			if (activity.getClassinfoid() == null) {
				return resultBean;
			}
			List<SalesActivity> list= salesService.listMatchActivity(activity);
			SelectDTO [] datas =  new SelectDTO [list.size()];
			for (int i = 0; i < list.size(); i++) {
				SelectDTO dto = new SelectDTO();
				dto.setValue(list.get(i).getId());
				dto.setLabel(list.get(i).getActivityname());
				datas[i] = dto;
			}
			resultBean.setResult(datas);
			return resultBean;
		}
		
		
		/**
		 * 根据活动id 和班别id 获取一个优惠信息
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/getmatch")
		public ResultBean getMatchSalesClass(SalesActivityClassinfo activity) {
			ResultBean resultBean = new ResultBean();
			SalesActivityClassinfo one= salesService.getMatchActivityClass(activity);
			
			resultBean.setResult(one);
			resultBean.setCode(0);
			return resultBean;
		}
		
		
		@RequestMapping(value="/get")
		public ResultBean getSales(SalesActivity activity){
			ResultBean resultBean = new ResultBean();
			SalesActivity market= salesService.getSalesActivity(activity);
			resultBean.setResult(market);
			return resultBean;
		}

}
