package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.report.model.KpiAreaStat;
import cn.com.liliyun.report.model.KpiAreaStatParam;
import cn.com.liliyun.report.model.KpiAreaStatRecord;
import cn.com.liliyun.report.model.KpiClassAreaStat;
import cn.com.liliyun.report.model.KpiClassStat;
import cn.com.liliyun.report.model.KpiClassStatParam;
import cn.com.liliyun.report.model.KpiClassStatRecord;
import cn.com.liliyun.report.model.KpiCoachStat;
import cn.com.liliyun.report.model.KpiCoachStatParam;
import cn.com.liliyun.report.model.KpiCoachStatRecord;
import cn.com.liliyun.report.model.KpiCoachStoreStat;
import cn.com.liliyun.report.model.KpiExamStat;
import cn.com.liliyun.report.model.KpiExamStatParam;
import cn.com.liliyun.report.model.KpiExamStatRecord;
import cn.com.liliyun.report.model.KpiHeadCoachStat;
import cn.com.liliyun.report.model.KpiHeadCoachStatParam;
import cn.com.liliyun.report.model.KpiHeadCoachStatRecord;
import cn.com.liliyun.report.model.KpiStoreStat;
import cn.com.liliyun.report.model.KpiStoreStatParam;
import cn.com.liliyun.report.model.KpiStoreStatRecord;
import cn.com.liliyun.report.service.KpiStatService;

@Controller
@ResponseBody
@RequestMapping(value="/report/kpi")
public class KpiStatController extends ExportController{

	@Autowired
	private KpiStatService kpiStatService;
	
	@RequestMapping(value="/areastat")
	public ResultBean areaStat(KpiAreaStatParam param) {
		ResultBean rb = new ResultBean();
		
		List<KpiAreaStat> areastat=kpiStatService.statByArea(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	
	@RequestMapping(value="/areastat/export")
	public ResponseEntity<byte[]> areaStatExport(KpiAreaStatParam param) throws IOException {
		List<KpiAreaStat> areastat=kpiStatService.statByArea(param);
		Workbook wb = getAreaStatWorkbook(areastat);
		return this.export("片区成绩统计", wb);
	}
	
	public static final String[] areaHeader = { "片区", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"}; 
	
	public Workbook getAreaStatWorkbook(List<KpiAreaStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < areaHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(areaHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				//int firstendrow=1;
				for (KpiAreaStat vo:list) {  
				      
				    List<KpiAreaStatRecord> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiAreaStatRecord s:list2){
				    		row = sheet.createRow(i++);  
						    
						    row.setRowStyle(style);
						    row.createCell(0).setCellValue(vo.getArea());
						    row.createCell(1).setCellValue(s.getSubject());
			    			row.createCell(2).setCellValue(s.getReachcount());
			    			row.createCell(3).setCellValue(s.getPasscount());
			    			row.createCell(4).setCellValue(s.getUnreachcount());
			    			row.createCell(5).setCellValue(s.getCancelcount());
			    			row.createCell(6).setCellValue(s.getOthercount());
			    			row.createCell(7).setCellValue(s.getQualifiedrate());
			    			row.createCell(8).setCellValue(s.getPassrate());
			    			row.createCell(9).setCellValue(s.getUnreachrate());
			    			row.createCell(10).setCellValue(s.getCancelrate());
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
	
	@RequestMapping(value="/classstat")
	public ResultBean classStat(KpiClassStatParam param) {
		ResultBean rb = new ResultBean();
		
		List<KpiClassStat> areastat=kpiStatService.statByClass(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/classstat/export")
	public ResponseEntity<byte[]> classStatExport(KpiClassStatParam param) throws IOException {
		List<KpiClassStat> areastat=kpiStatService.statByClass(param);
		Workbook wb = getClassStatWorkbook(areastat);
		return this.export("班别成绩统计", wb);
		
	}
	
	public static final String[] classHeader = { "班别","片区", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"};
	
	public Workbook getClassStatWorkbook(List<KpiClassStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < classHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(classHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (KpiClassStat vo:list) {  
				      
				    int rowNum=0;
				    List<KpiClassAreaStat> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiClassAreaStat s:list2){
				    		List<KpiClassStatRecord> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(KpiClassStatRecord d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getClassname());
							    row.createCell(1).setCellValue(s.getArea());
							    row.createCell(2).setCellValue(d.getSubject());
				    			row.createCell(3).setCellValue(d.getReachcount());
				    			row.createCell(4).setCellValue(d.getPasscount());
				    			row.createCell(5).setCellValue(d.getUnreachcount());
				    			row.createCell(6).setCellValue(d.getCancelcount());
				    			row.createCell(7).setCellValue(d.getOthercount());
				    			row.createCell(8).setCellValue(d.getQualifiedrate());
				    			row.createCell(9).setCellValue(d.getPassrate());
				    			row.createCell(10).setCellValue(d.getUnreachrate());
				    			row.createCell(11).setCellValue(d.getCancelrate());
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
	
	@RequestMapping(value="/coachstat")
	public ResultBean coachStat(KpiCoachStatParam param, HttpServletRequest request) {
		
		ResultBean rb = new ResultBean();
		
		List<KpiCoachStat> areastat=kpiStatService.statByCoach(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/coachstat/export")
	public ResponseEntity<byte[]> coachStatExport(KpiCoachStatParam param, HttpServletRequest request) throws IOException {
		List<KpiCoachStat> areastat=kpiStatService.statByCoach(param);
		
		Workbook wb = getCoachStatWorkbook(areastat);
			
		return this.export("教练统计", wb);
	}
	
	public static final String[] coachHeader = { "教练","带教类型","片区","门店", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"};  
	
	public Workbook getCoachStatWorkbook(List<KpiCoachStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < coachHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(coachHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (KpiCoachStat vo:list) {  
				      
				    List<KpiCoachStoreStat> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiCoachStoreStat s:list2){
				    		List<KpiCoachStatRecord> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(KpiCoachStatRecord d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getCoachname());
							    row.createCell(1).setCellValue(vo.getTeachtype());
							    row.createCell(2).setCellValue(vo.getArea());
							    row.createCell(3).setCellValue(s.getStore());
							    row.createCell(4).setCellValue(d.getSubject());
				    			row.createCell(5).setCellValue(d.getReachcount());
				    			row.createCell(6).setCellValue(d.getPasscount());
				    			row.createCell(7).setCellValue(d.getUnreachcount());
				    			row.createCell(8).setCellValue(d.getCancelcount());
				    			row.createCell(9).setCellValue(d.getOthercount());
				    			row.createCell(10).setCellValue(d.getQualifiedrate());
				    			row.createCell(11).setCellValue(d.getPassrate());
				    			row.createCell(12).setCellValue(d.getUnreachrate());
				    			row.createCell(13).setCellValue(d.getCancelrate());
				    		}
				    		int secondendrow=i-1;
				    		
				    		if(secondendrow>firstendrow){
				    		CellRangeAddress secondcra=new CellRangeAddress(secondstartrow, secondendrow, 3, 3);
				    		sheet.addMergedRegion(secondcra);
				    		}
				    	}
				    	
				    	       
				    }
				    
				    CellRangeAddress cra=new CellRangeAddress(firststartrow, i-1, 0, 0);      
				    CellRangeAddress cra2=new CellRangeAddress(firststartrow, i-1, 1, 1);
				    CellRangeAddress cra3=new CellRangeAddress(firststartrow, i-1, 2, 2);

                    //在sheet里增加合并单元格           

			    	sheet.addMergedRegion(cra); 
			    	sheet.addMergedRegion(cra2);
			    	sheet.addMergedRegion(cra3);
			    	firststartrow=i;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	@RequestMapping(value="/storestat")
	public ResultBean storeStat(KpiStoreStatParam param, HttpServletRequest request) {
		
		ResultBean rb = new ResultBean();
		
		List<KpiStoreStat> areastat=kpiStatService.statByStore(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/storestat/export")
	public ResponseEntity<byte[]> storeStatExport(KpiStoreStatParam param, HttpServletRequest request) throws IOException {
		List<KpiStoreStat> areastat=kpiStatService.statByStore(param);
			
		Workbook wb = getStoreStatWorkbook(areastat);
		return this.export("门店成绩统计", wb);
	}
	
	public static final String[] storeHeader = { "门店","片区", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"}; 
	
	public Workbook getStoreStatWorkbook(List<KpiStoreStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < storeHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(storeHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (KpiStoreStat vo:list) {  
				      
				    List<KpiStoreStatRecord> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiStoreStatRecord s:list2){
				    		row = sheet.createRow(i++);  
						    
						    row.setRowStyle(style);
						    row.createCell(0).setCellValue(vo.getStore());
						    row.createCell(1).setCellValue(vo.getArea());
						    row.createCell(2).setCellValue(s.getSubject());
			    			row.createCell(3).setCellValue(s.getReachcount());
			    			row.createCell(4).setCellValue(s.getPasscount());
			    			row.createCell(5).setCellValue(s.getUnreachcount());
			    			row.createCell(6).setCellValue(s.getCancelcount());
			    			row.createCell(7).setCellValue(s.getOthercount());
			    			row.createCell(8).setCellValue(s.getQualifiedrate());
			    			row.createCell(9).setCellValue(s.getPassrate());
			    			row.createCell(10).setCellValue(s.getUnreachrate());
			    			row.createCell(11).setCellValue(s.getCancelrate());
				    		}
				    	}
				    	       
				    
				    CellRangeAddress cra=new CellRangeAddress(firststartrow, i-1, 0, 0);      
				    CellRangeAddress cra2=new CellRangeAddress(firststartrow, i-1, 1, 1); 	
                   //在sheet里增加合并单元格           

			    	sheet.addMergedRegion(cra); 
			    	sheet.addMergedRegion(cra2);
			    	firststartrow=i;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	@RequestMapping(value="/headcoachstat")
	public ResultBean headCoachStat(KpiHeadCoachStatParam param) {
		ResultBean rb = new ResultBean();
		
		List<KpiHeadCoachStat> areastat=kpiStatService.statByHeadCoach(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/headcoachstat/export")
	public ResponseEntity<byte[]> headCoachStatExport(KpiHeadCoachStatParam param) throws IOException {
		
		List<KpiHeadCoachStat> areastat=kpiStatService.statByHeadCoach(param);
			
		Workbook wb = getHeadCoachStatWorkbook(areastat);
		return this.export("片区成绩统计", wb);
	}
	
	public static final String[] headcoachHeader = { "教学组长姓名","片区", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"}; 
	
	public Workbook getHeadCoachStatWorkbook(List<KpiHeadCoachStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < headcoachHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(headcoachHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (KpiHeadCoachStat vo:list) {  
				      
				    List<KpiHeadCoachStatRecord> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiHeadCoachStatRecord s:list2){
				    		row = sheet.createRow(i++);  
						    
						    row.setRowStyle(style);
						    row.createCell(0).setCellValue(vo.getCoachname());
						    row.createCell(1).setCellValue(vo.getArea());
						    row.createCell(2).setCellValue(s.getSubject());
			    			row.createCell(3).setCellValue(s.getReachcount());
			    			row.createCell(4).setCellValue(s.getPasscount());
			    			row.createCell(5).setCellValue(s.getUnreachcount());
			    			row.createCell(6).setCellValue(s.getCancelcount());
			    			row.createCell(7).setCellValue(s.getOthercount());
			    			row.createCell(8).setCellValue(s.getQualifiedrate());
			    			row.createCell(9).setCellValue(s.getPassrate());
			    			row.createCell(10).setCellValue(s.getUnreachrate());
			    			row.createCell(11).setCellValue(s.getCancelrate());
				    		}
				    	}
				    	       
				    
				    CellRangeAddress cra=new CellRangeAddress(firststartrow, i-1, 0, 0);      
				    CellRangeAddress cra2=new CellRangeAddress(firststartrow, i-1, 1, 1); 	
                   //在sheet里增加合并单元格           

			    	sheet.addMergedRegion(cra); 
			    	sheet.addMergedRegion(cra2);
			    	firststartrow=i;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
	
	@RequestMapping(value="/examstat")
	public ResultBean examStat(KpiExamStatParam param) {
		ResultBean rb = new ResultBean();
		
		List<KpiExamStat> areastat=kpiStatService.statByExam(param);
		rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/examstat/export")
	public ResponseEntity<byte[]> examStatExport(KpiExamStatParam param) throws IOException {
		List<KpiExamStat> areastat=kpiStatService.statByExam(param);
			
		Workbook wb = getExamStatWorkbook(areastat);
		return this.export("考场成绩统计", wb);
	}
	
	public static final String[] examHeader = { "考场","片区", "考试科目", "实到人数", "合格人数","未到人数","取消人数", "其他","合格率","及格率","未到率","取消率"}; 
	
	public Workbook getExamStatWorkbook(List<KpiExamStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < headcoachHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(headcoachHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (KpiExamStat vo:list) {  
				      
				    List<KpiExamStatRecord> list2=vo.getData();
				      
				    if(list2!=null){
				    	for(KpiExamStatRecord s:list2){
				    		row = sheet.createRow(i++);  
						    
						    row.setRowStyle(style);
						    row.createCell(0).setCellValue(vo.getExam());
						    row.createCell(1).setCellValue(vo.getArea());
						    row.createCell(2).setCellValue(s.getSubject());
			    			row.createCell(3).setCellValue(s.getReachcount());
			    			row.createCell(4).setCellValue(s.getPasscount());
			    			row.createCell(5).setCellValue(s.getUnreachcount());
			    			row.createCell(6).setCellValue(s.getCancelcount());
			    			row.createCell(7).setCellValue(s.getOthercount());
			    			row.createCell(8).setCellValue(s.getQualifiedrate());
			    			row.createCell(9).setCellValue(s.getPassrate());
			    			row.createCell(10).setCellValue(s.getUnreachrate());
			    			row.createCell(11).setCellValue(s.getCancelrate());
				    		}
				    	}
				    	       
				    
				    CellRangeAddress cra=new CellRangeAddress(firststartrow, i-1, 0, 0);      
				    CellRangeAddress cra2=new CellRangeAddress(firststartrow, i-1, 1, 1); 	
                   //在sheet里增加合并单元格           

			    	sheet.addMergedRegion(cra); 
			    	sheet.addMergedRegion(cra2);
			    	firststartrow=i;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}  
		
		return wb;
	}
}
