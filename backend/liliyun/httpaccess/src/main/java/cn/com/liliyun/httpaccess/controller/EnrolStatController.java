package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.CoachItemPerformanceStat;
import cn.com.liliyun.market.model.CoachPerformanceStat;
import cn.com.liliyun.market.model.CoachStorePerformanceStat;
import cn.com.liliyun.market.model.EnrolChannelStat;
import cn.com.liliyun.market.model.EnrolClassStat;
import cn.com.liliyun.market.model.EnrolDetail;
import cn.com.liliyun.market.model.EnrolDetailParam;
import cn.com.liliyun.market.model.PerformanceParam;
import cn.com.liliyun.market.model.PerformanceStat;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.market.model.StaffPerformanceStat;
import cn.com.liliyun.market.service.EnrolDetailService;
import cn.com.liliyun.market.service.EnrolStatService;
import cn.com.liliyun.market.service.PerformanceService;
import cn.com.liliyun.market.service.SalesChannelService;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/enrolstat")
public class EnrolStatController extends BaseController{

	private static Logger log=Logger.getLogger(EnrolStatController.class);
	
	@Autowired
	private EnrolDetailService enrolDetailService;
	@Autowired
	private EnrolStatService enrolStatService;
	
	@Autowired
	private ClassinfoService classinfoService;
	@Autowired
	private SalesChannelService salesChannelService;
	@Autowired
	private PerformanceService performanceService;
	/*
	 * 招生明细按片区统计
	 */
	@RequestMapping(value="/detail/area")
	public ResultBean detailAreaStat(EnrolDetailParam param,HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_ADD);
		User user = AccessWebUtil.getSessionUser(request);
		Map data=new HashMap();
		List<EnrolDetail> list= enrolDetailService.stat(param, user);
		
		
		//String showzero=request.getParameter("showzero");
		Map<Integer,Integer> containkey=new HashMap();
		List rlist=new ArrayList();
		for(EnrolDetail detail:list){
//			if(!"1".equals(showzero)){
//				if(detail.getEnrolsum()==0){
//					continue;
//				}
//			}
			Map map=new HashMap();
			map.put("areaid", detail.getAreaid());
			map.put("enrolindex", detail.getEnrolindex());
			map.put("enrolsum", detail.getEnrolsum());
			map.put("returnsum", detail.getReturnsum());
			map.put("realenrolsum", detail.getRealenrolsum());
			map.put("enrolrate", detail.getEnrolrate());
			map.put("avgprice", detail.getAvgprice());
			map.put("outavgprice", detail.getOutavgprice());
			map.put("highrate", detail.getHighrate());
			Map clss= detail.getClassinfo();
			java.util.Iterator<Integer> it= clss.keySet().iterator();
			while(it.hasNext()){
				Integer key=it.next();
				map.put("cls_"+key, clss.get(key));
				containkey.put(key, 1);
			}
			rlist.add(map);
		}
		data.put("list", rlist);
		Classinfo ci=new Classinfo();
		ci.setDblink(user.getDblink());
		List<Classinfo> clss= classinfoService.selectAllList(ci);
		
		if(param.getShowzero()==null||param.getShowzero()==0){//不显示0
			for(int i=clss.size()-1;i>=0;i--){
				if(!containkey.containsKey(clss.get(i).getId())){
					clss.remove(i);
				}
			}
		}
		
		StringBuilder builder=new StringBuilder();
		data.put("header", clss);
		resultBean.setResult(data);
		resultBean.setCode(0);
		return resultBean;
	}
	
	@RequestMapping(value="/detail/area/export")
	public ResponseEntity<byte[]> detailAreaStatExport(EnrolDetailParam param,HttpServletRequest request) {
		try {
			
			User user = AccessWebUtil.getSessionUser(request);
			
			List<EnrolDetail> list= enrolDetailService.stat(param, user);
			Classinfo ci=new Classinfo();
			ci.setDblink(user.getDblink());
			List<Classinfo> clss= classinfoService.selectAllList(ci);
			
			Workbook wb = getAreaDetailWorkbook(list,clss);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("片区统计" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
			HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	public static final String[] detailHeader = { "招生指标", "招生小计", "退费人数", "实际招生","招生完成率","均价", "外地班均价","高端班占比"};
	
	public Workbook getAreaDetailWorkbook(List<EnrolDetail> list,List<Classinfo> clss){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				Cell cell0 = row.createCell(0);  
			    cell0.setCellValue("片区");  
			    cell0.setCellStyle(style);
				
			    for (int i = 0; i < clss.size(); i++) {  
				    Cell cell = row.createCell(i+1);  
				    cell.setCellValue(clss.get(i).getName());  
				    cell.setCellStyle(style);  
				} 
			    
				// 产生表格标题行  
				for (int i = 0; i < detailHeader.length; i++) {  
				    Cell cell = row.createCell(i+clss.size()+1);  
				    cell.setCellValue(detailHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				
				for (EnrolDetail vo:list) {  
					int column=1;
					row = sheet.createRow(i++);  
					Map<Integer,Integer> map= vo.getClassinfo();
				    
					row.setRowStyle(style);
				    row.createCell(0).setCellValue(vo.getArea());
				    
				    for(Classinfo info:clss){
				    	row.createCell(column++).setCellValue(map.get(info.getId()));
				    }
				    
				    row.createCell(column+0).setCellValue(vo.getEnrolindex());
	    			row.createCell(column+1).setCellValue(vo.getEnrolsum());
	    			row.createCell(column+2).setCellValue(vo.getReturnsum());
	    			row.createCell(column+3).setCellValue(vo.getRealenrolsum());
	    			row.createCell(column+4).setCellValue(vo.getEnrolrate());
	    			row.createCell(column+5).setCellValue(vo.getAvgprice());
	    			row.createCell(column+6).setCellValue(vo.getOutavgprice());
	    			row.createCell(column+7).setCellValue(vo.getHighrate());
				   
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	/**
	 * 招生明细按门店统计
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail/store")
	public ResultBean detailAreaStore(EnrolDetailParam param,HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		
		User user = AccessWebUtil.getSessionUser(request);
		
		List<EnrolDetail> list= enrolDetailService.stat(param, user);
		Map data=new HashMap();
		
		List rlist=new ArrayList();
		for(EnrolDetail detail:list){
			Map map=new HashMap();
			map.put("storeid", detail.getStoreid());
			map.put("enrolindex", detail.getEnrolindex());
			map.put("enrolsum", detail.getEnrolsum());
			map.put("returnsum", detail.getReturnsum());
			map.put("realenrolsum", detail.getRealenrolsum());
			map.put("enrolrate", detail.getEnrolrate());
			map.put("avgprice", detail.getAvgprice());
			map.put("outavgprice", detail.getOutavgprice());
			map.put("highrate", detail.getHighrate());
			Map clss= detail.getClassinfo();
			java.util.Iterator<Integer> it= clss.keySet().iterator();
			while(it.hasNext()){
				Integer key=it.next();
				map.put("cls_"+key, clss.get(key));
				
			}
			rlist.add(map);
		}
		data.put("list", rlist);
		Classinfo ci=new Classinfo();
		ci.setDblink(user.getDblink());
		List<Classinfo> clss= classinfoService.selectAllList(ci);
		data.put("header", clss);
		resultBean.setResult(data);
		resultBean.setCode(0);
		return resultBean;
	}
	
	@RequestMapping(value="/detail/store/export")
	public ResponseEntity<byte[]> detailAreaStoreExport(EnrolDetailParam param,HttpServletRequest request) {
		
		User user = AccessWebUtil.getSessionUser(request);
		try{
			List<EnrolDetail> list= enrolDetailService.stat(param, user);
			Classinfo ci=new Classinfo();
			ci.setDblink(user.getDblink());
			List<Classinfo> clss= classinfoService.selectAllList(ci);
			
			Workbook wb = getStoreDetailWorkbook(list,clss);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("片区门店统计" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
			HttpStatus.CREATED);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Workbook getStoreDetailWorkbook(List<EnrolDetail> list,List<Classinfo> clss){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				Cell cell0 = row.createCell(0);  
			    cell0.setCellValue("门店");  
			    cell0.setCellStyle(style);
				
			    for (int i = 0; i < clss.size(); i++) {  
				    Cell cell = row.createCell(i+1);  
				    cell.setCellValue(clss.get(i).getName());  
				    cell.setCellStyle(style);  
				} 
			    
				// 产生表格标题行  
				for (int i = 0; i < detailHeader.length; i++) {  
				    Cell cell = row.createCell(i+clss.size()+1);  
				    cell.setCellValue(detailHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (EnrolDetail vo:list) {  
					int column=1;
					row = sheet.createRow(i++);  
					Map<Integer,Integer> map= vo.getClassinfo();
				    
					row.setRowStyle(style);
				    row.createCell(0).setCellValue(vo.getStore());
				    
				    for(Classinfo info:clss){
				    	
				    	if(map!=null&&map.get(info.getId())!=null)
				    		row.createCell(column++).setCellValue(map.get(info.getId()));
				    	else
				    		row.createCell(column++).setCellValue("");
				    }
				    
				    row.createCell(column+0).setCellValue(vo.getEnrolindex());
	    			row.createCell(column+1).setCellValue(vo.getEnrolsum());
	    			row.createCell(column+2).setCellValue(vo.getReturnsum());
	    			row.createCell(column+3).setCellValue(vo.getRealenrolsum());
	    			row.createCell(column+4).setCellValue(vo.getEnrolrate());
	    			row.createCell(column+5).setCellValue(vo.getAvgprice());
	    			row.createCell(column+6).setCellValue(vo.getOutavgprice());
	    			row.createCell(column+7).setCellValue(vo.getHighrate());
				   
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	/**
	 * 招生统计按班别
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/stat/class")
	public ResultBean classStat(EnrolDetailParam param,HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		
		User user = AccessWebUtil.getSessionUser(request);
		Map data=new HashMap();
		List<EnrolClassStat> list= enrolStatService.statByClass(param, user);
		
		
		List rlist=new ArrayList();
		Map<Integer,Integer> containkey=new HashMap();
		for(EnrolClassStat detail:list){
			Map map=new HashMap();
			map.put("areaid", detail.getAreaid());
			map.put("enrolindex", detail.getEnrolindex());
			map.put("enrolsum", detail.getEnrolsum());
			map.put("returnsum", detail.getReturnsum());
			map.put("realenrolsum", detail.getRealenrolsum());
			map.put("enrolrate", detail.getEnrolrate());
			map.put("avgprice", detail.getAvgprice());
			map.put("outavgprice", detail.getOutavgprice());
			map.put("highrate", detail.getHighrate());
			
			map.put("c1", detail.getC1());
			map.put("c2", detail.getC2());
			map.put("c2rate", detail.getC2rate());
			map.put("highindex", detail.getHighindex());
			map.put("highindexrate", detail.getHighindexrate());
			
			
			Map clss= detail.getClassinfo();
			java.util.Iterator<Integer> it= clss.keySet().iterator();
			while(it.hasNext()){
				Integer key=it.next();
				map.put("cls_"+key, clss.get(key));
				containkey.put(key, 1);
			}
			rlist.add(map);
		}
		data.put("list", rlist);
		
		Classinfo ci=new Classinfo();
		ci.setDblink(user.getDblink());
		List<Classinfo> clss= classinfoService.selectAllList(ci);
		if(param.getShowzero()==null||param.getShowzero()==0){//不显示0
			for(int i=clss.size()-1;i>=0;i--){
				if(!containkey.containsKey(clss.get(i).getId())){
					clss.remove(i);
				}
			}
		}
		StringBuilder builder=new StringBuilder();
		data.put("header", clss);
		resultBean.setResult(data);
		resultBean.setCode(0);
		return resultBean;
	}
	
	public static final String[] classHeader = { "招生指标", "招生小计", "退费人数", "实际招生","招生完成率","均价", "外地班均价","高端班指标","高端班完成率","高端班占比","手动挡","自动挡","自动挡占比"};
	
	@RequestMapping(value="/stat/class/export")
	public ResponseEntity<byte[]> classStatExport(EnrolDetailParam param,HttpServletRequest request) {
		
		User user = AccessWebUtil.getSessionUser(request);
		Map data=new HashMap();
		try{
			List<EnrolClassStat> list= enrolStatService.statByClass(param, user);
			Classinfo ci=new Classinfo();
			ci.setDblink(user.getDblink());
			List<Classinfo> clss= classinfoService.selectAllList(ci);
			
			Workbook wb = getClassStatWorkbook(list,clss);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("片区统计" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
			HttpStatus.CREATED);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Workbook getClassStatWorkbook(List<EnrolClassStat> list,List<Classinfo> clss){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				Cell cell0 = row.createCell(0);  
			    cell0.setCellValue("片区");  
			    cell0.setCellStyle(style);
				
			    for (int i = 0; i < clss.size(); i++) {  
				    Cell cell = row.createCell(i+1);  
				    cell.setCellValue(clss.get(i).getName());  
				    cell.setCellStyle(style);  
				} 
			    
				// 产生表格标题行  
				for (int i = 0; i < classHeader.length; i++) {  
				    Cell cell = row.createCell(i+clss.size()+1);  
				    cell.setCellValue(classHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (EnrolClassStat vo:list) {  
					int column=1;
					row = sheet.createRow(i++);  
					Map<Integer,Integer> map= vo.getClassinfo();
				    
					row.setRowStyle(style);
				    row.createCell(0).setCellValue(vo.getArea());
				    
				    for(Classinfo info:clss){
				    	
				    	if(map!=null&&map.get(info.getId())!=null)
				    		row.createCell(column++).setCellValue(map.get(info.getId()));
				    	else
				    		row.createCell(column++).setCellValue("");
				    }
				    
				    row.createCell(column+0).setCellValue(vo.getEnrolindex());
	    			row.createCell(column+1).setCellValue(vo.getEnrolsum());
	    			row.createCell(column+2).setCellValue(vo.getReturnsum());
	    			row.createCell(column+3).setCellValue(vo.getRealenrolsum());
	    			row.createCell(column+4).setCellValue(vo.getEnrolrate());
	    			row.createCell(column+5).setCellValue(vo.getAvgprice());
	    			row.createCell(column+6).setCellValue(vo.getOutavgprice());
	    			
	    			row.createCell(column+7).setCellValue(vo.getHighindex());
	    			row.createCell(column+8).setCellValue(vo.getHighindexrate());
	    			row.createCell(column+9).setCellValue(vo.getHighrate());
	    			row.createCell(column+10).setCellValue(vo.getC1());
	    			row.createCell(column+11).setCellValue(vo.getC2());
	    			row.createCell(column+12).setCellValue(vo.getC2rate());
				   
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	/**
	 * 招生统计按渠道
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/stat/channel")
	public ResultBean chanelStat(EnrolDetailParam param,HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_ADD);
		User user = AccessWebUtil.getSessionUser(request);
		Map data=new HashMap();
		List<EnrolChannelStat> list= enrolStatService.statByChannel(param, user);
		
		
		List rlist=new ArrayList();
		for(EnrolChannelStat detail:list){
			Map map=new HashMap();
			map.put("areaid", detail.getAreaid());
			map.put("enrolindex", detail.getEnrolindex());
			map.put("enrolsum", detail.getEnrolsum());
			map.put("returnsum", detail.getReturnsum());
			map.put("realenrolsum", detail.getRealenrolsum());
			map.put("enrolrate", detail.getEnrolrate());
			map.put("avgprice", detail.getAvgprice());
			map.put("outavgprice", detail.getOutavgprice());
			map.put("highrate", detail.getHighrate());
			
			
			
			Map clss= detail.getChannelinfo();
			java.util.Iterator<Integer> it= clss.keySet().iterator();
			while(it.hasNext()){
				Integer key=it.next();
				map.put("cls_"+key, clss.get(key));
				
			}
			rlist.add(map);
		}
		data.put("list", rlist);
		
		SalesChannel sc=new SalesChannel();
		sc.setDblink(user.getDblink());
		List<SalesChannel> clss= salesChannelService.selectChannels(sc);
		
		StringBuilder builder=new StringBuilder();
		data.put("header", clss);
		resultBean.setResult(data);
		resultBean.setCode(0);
		return resultBean;
	}
	
	public static final String[] channelHeader = { "招生指标", "招生小计", "退费人数", "实际招生","高端班占比"};
	
	@RequestMapping(value="/stat/channel/export")
	public ResponseEntity<byte[]>  chanelStatExport(EnrolDetailParam param,HttpServletRequest request) {
		
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_ADD);
		User user = AccessWebUtil.getSessionUser(request);
		Map data=new HashMap();
		try{
			List<EnrolChannelStat> list= enrolStatService.statByChannel(param, user);
			
			SalesChannel sc=new SalesChannel();
			sc.setDblink(user.getDblink());
			List<SalesChannel> clss= salesChannelService.selectChannels(sc);
			
			Workbook wb = getChannelStatWorkbook(list,clss);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("片区统计" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Workbook getChannelStatWorkbook(List<EnrolChannelStat> list,List<SalesChannel> clss){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				Cell cell0 = row.createCell(0);  
			    cell0.setCellValue("片区");  
			    cell0.setCellStyle(style);
				
			    for (int i = 0; i < clss.size(); i++) {  
				    Cell cell = row.createCell(i+1);  
				    cell.setCellValue(clss.get(i).getChannel());  
				    cell.setCellStyle(style);  
				} 
			    
				// 产生表格标题行  
				for (int i = 0; i < channelHeader.length; i++) {  
				    Cell cell = row.createCell(i+clss.size()+1);  
				    cell.setCellValue(channelHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (EnrolChannelStat vo:list) {  
					int column=1;
					row = sheet.createRow(i++);  
					Map<Integer,Integer> map= vo.getChannelinfo();
				    
					row.setRowStyle(style);
				    row.createCell(0).setCellValue(vo.getArea());
				    
				    for(SalesChannel info:clss){
				    	
				    	if(map!=null&&map.get(info.getId())!=null)
				    		row.createCell(column++).setCellValue(map.get(info.getId()));
				    	else
				    		row.createCell(column++).setCellValue("");
				    }
				    
				    row.createCell(column+0).setCellValue(vo.getEnrolindex());
	    			row.createCell(column+1).setCellValue(vo.getEnrolsum());
	    			row.createCell(column+2).setCellValue(vo.getReturnsum());
	    			row.createCell(column+3).setCellValue(vo.getRealenrolsum());
	    			row.createCell(column+4).setCellValue(vo.getHighrate());
	    			
				   
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	@RequestMapping(value="/performance/staff")
	public ResultBean performanceStaff(PerformanceParam param,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User user = AccessWebUtil.getSessionUser(request);
		try{
			List<StaffPerformanceStat> areastat=performanceService.staffstat(param, user);
			rb.setResult(areastat);
		}catch(Exception ex){
			ex.printStackTrace();
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/performance/coach")
	public ResultBean performanceCoach(PerformanceParam param,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User user = AccessWebUtil.getSessionUser(request);
		try{
			List<CoachPerformanceStat> areastat=performanceService.coachstat(param, user);
			rb.setResult(areastat);
		}catch(Exception ex){
			ex.printStackTrace();
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/performance/coach/export")
	public ResponseEntity<byte[]> performanceCoachExport(PerformanceParam param,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User user = AccessWebUtil.getSessionUser(request);
		try{
			List<CoachPerformanceStat> areastat=performanceService.coachstat(param, user);
			Workbook wb = getPerformanceCoachWorkbook(areastat);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			HttpHeaders headers = new HttpHeaders();
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = new String(
			("片区统计" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
			HttpStatus.CREATED);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static final String[] performanceCoachHeader = { "片区", "门店", "员工姓名", "员工指标","招生人数","超额人数"};  
	
	public Workbook getPerformanceCoachWorkbook(List<CoachPerformanceStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < performanceCoachHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(performanceCoachHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (CoachPerformanceStat vo:list) {  
				      
				    int rowNum=0;
				    List<CoachStorePerformanceStat> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(CoachStorePerformanceStat s:list2){
				    		List<CoachItemPerformanceStat> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(PerformanceStat d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getArea());
							    row.createCell(1).setCellValue(s.getStore());
				    			row.createCell(2).setCellValue(d.getName());
				    			row.createCell(3).setCellValue(d.getIndex());
				    			row.createCell(4).setCellValue(d.getEnrolcount());
				    			row.createCell(5).setCellValue(d.getExceedcount());
				    			
				    		}
				    		int secondendrow=i-1;
				    		
				    		if(secondendrow>firstendrow){
				    		CellRangeAddress secondcra=new CellRangeAddress(secondstartrow, secondendrow, 1, 1);
				    		sheet.addMergedRegion(secondcra);
				    		}
				    	}
				    	
				    	       
				    }
				    
				    CellRangeAddress cra=new CellRangeAddress(firststartrow, i-1, 0, 0);      

                    //在sheet里增加合并单元格           

			    	sheet.addMergedRegion(cra); 
			    	firststartrow=i;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
}
