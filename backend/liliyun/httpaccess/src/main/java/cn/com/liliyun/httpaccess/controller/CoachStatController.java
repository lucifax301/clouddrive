package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.coach.model.stat.AreaStat;
import cn.com.liliyun.coach.model.stat.AreaStatParam;
import cn.com.liliyun.coach.model.stat.AreaTeachCarTypeStat;
import cn.com.liliyun.coach.model.stat.AreaTeachTypeStat;
import cn.com.liliyun.coach.model.stat.CoachAreaStat;
import cn.com.liliyun.coach.model.stat.CoachStat;
import cn.com.liliyun.coach.model.stat.CoachStatParam;
import cn.com.liliyun.coach.model.stat.CoachStoreStat;
import cn.com.liliyun.coach.model.stat.HeadCoachAreaStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStatParam;
import cn.com.liliyun.coach.model.stat.TeachTypeAreaStat;
import cn.com.liliyun.coach.model.stat.TeachTypeCarTypeStat;
import cn.com.liliyun.coach.model.stat.TeachTypeStat;
import cn.com.liliyun.coach.service.CoachStatService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.DateUtil;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/coachreport")
public class CoachStatController extends ExportController{
	
	//private static Logger log=Logger.getLogger(CoachStatController.class);

	@Autowired
	private CoachStatService coachStatService;
	
	/**
	 * 按片区统计
	 * @param param
	 * @return
	 */
	@Description("片区统计")
	@RequestMapping(value="/areastat")
	public ResultBean areaStat(AreaStatParam param) {
		ResultBean rb = new ResultBean();
		
			List<AreaStat> areastat=coachStatService.statByArea(param);
			rb.setResult(areastat);
		
		return rb;
	}
	
	@RequestMapping(value="/areastat/export")
	public ResponseEntity<byte[]> areaStatExport(AreaStatParam param,HttpServletResponse response) throws IOException {
		
			List<AreaStat> areastat=coachStatService.statByArea(param);
			
			Workbook wb = getAreaStatWorkbook(areastat);
			return this.export("片区统计", wb);
			
		
	}
	
	protected void sendDataExcel(HttpServletResponse response, Workbook wb,String name) throws IOException {
		String fileName =  new String(name.getBytes("UTF-8"),"ISO8859-1")  
											+ DateUtil.getCurrentMillis() + ".xls";
		sendExcel(response, wb, fileName);
	}
	
	public static final String[] areaHeader = { "片区", "带教车型", "带教类型", "学员数量","教练数量","车辆数量", "人均负荷","单车负荷"};  
	
	public Workbook getAreaStatWorkbook(List<AreaStat> list){

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
				int firstendrow=1;
				for (AreaStat vo:list) {  
				      
				    int rowNum=0;
				    List<AreaTeachCarTypeStat> list2=vo.getData();
				    if(list2!=null){
				    	for(AreaTeachCarTypeStat s:list2){
				    		List<AreaTeachTypeStat> list3=s.getData();
				    		rowNum+=(list3==null)?0:list3.size();
				    	}
				    }
				      
				    if(list2!=null){
				    	for(AreaTeachCarTypeStat s:list2){
				    		List<AreaTeachTypeStat> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(AreaTeachTypeStat d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getArea());
							    row.createCell(1).setCellValue(s.getCartype());
				    			row.createCell(2).setCellValue(d.getTeachtype());
				    			row.createCell(3).setCellValue(d.getStudentcount());
				    			row.createCell(4).setCellValue(d.getCoachcount());
				    			row.createCell(5).setCellValue(d.getCarcount());
				    			row.createCell(6).setCellValue(d.getAvgcoachstudentcount());
				    			row.createCell(7).setCellValue(d.getAvgcoachstudentcount());
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
	
	/**
	 * 按带教类型
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/teachtypestat")
	public ResultBean teachtypestat(AreaStatParam param) {
		ResultBean rb = new ResultBean();
		
			List<TeachTypeStat> areastat=coachStatService.statByTeachType(param);
			rb.setResult(areastat);
		
		return rb;
	}
	
	public static final String[] teachHeader = { "带教类型", "带教车型", "片区", "学员数量","教练数量","车辆数量", "人均负荷","单车负荷"};  
	
	@RequestMapping(value="/teachtypestat/export")
	public ResponseEntity<byte[]> teachtypestatExport(AreaStatParam param,HttpServletResponse response) throws IOException {
		
		List<TeachTypeStat> areastat=coachStatService.statByTeachType(param);
		Workbook wb = getTeachStatWorkbook(areastat);
		return this.export("带教类型统计", wb);
		
	}
	
	public Workbook getTeachStatWorkbook(List<TeachTypeStat> list){

		 Workbook wb = null; 
			try {
				wb = new SXSSFWorkbook(100);  //内存里一次只留多少行,几十万行无压力，不怕OOM
				Sheet sheet = wb.createSheet("test");  //设置工作表标题
				Row row = sheet.createRow((int) 0);  
				CellStyle style = wb.createCellStyle();  
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   // 水平居中

				// 产生表格标题行  
				for (int i = 0; i < teachHeader.length; i++) {  
				    Cell cell = row.createCell(i);  
				    cell.setCellValue(teachHeader[i]);  
				    cell.setCellStyle(style);  
				}  
				
				// 遍历集合数据，产生数据行 
				int i=1;
				int firststartrow=1;
				int firstendrow=1;
				for (TeachTypeStat vo:list) {  
				      
				    int rowNum=0;
				    List<TeachTypeCarTypeStat> list2=vo.getData();
				    if(list2!=null){
				    	for(TeachTypeCarTypeStat s:list2){
				    		List<TeachTypeAreaStat> list3=s.getData();
				    		rowNum+=(list3==null)?0:list3.size();
				    	}
				    }
				      
				    if(list2!=null){
				    	for(TeachTypeCarTypeStat s:list2){
				    		List<TeachTypeAreaStat> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(TeachTypeAreaStat d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getTeachtype());
							    row.createCell(1).setCellValue(s.getCartype());
				    			row.createCell(2).setCellValue(d.getArea());
				    			row.createCell(3).setCellValue(d.getStudentcount());
				    			row.createCell(4).setCellValue(d.getCoachcount());
				    			row.createCell(5).setCellValue(d.getCarcount());
				    			row.createCell(6).setCellValue(d.getAvgcoachstudentcount());
				    			row.createCell(7).setCellValue(d.getAvgcoachstudentcount());
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
	
	/**
	 * 按教练统计
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/coachstat")
	public ResultBean coachstat(CoachStatParam param, HttpServletRequest request) {
		User user = AccessWebUtil.getSessionUser(request);
		ResultBean rb = new ResultBean();
		
			List<CoachAreaStat> areastat=coachStatService.statByCoach(param);
			rb.setResult(areastat);
		
		return rb;
	}
	
	public static final String[] coachHeader = { "片区", "门店", "教练姓名", "阶段一","阶段二","阶段三", "阶段四","合计"};
	
	@RequestMapping(value="/coachstat/export")
	public ResponseEntity<byte[]> coachstatExport(CoachStatParam param, HttpServletRequest request) throws IOException {
		List<CoachAreaStat> areastat=coachStatService.statByCoach(param);
		Workbook wb = getCoachStatWorkbook(areastat);
		
		return this.export("教练统计", wb);
		
	}
	
	public Workbook getCoachStatWorkbook(List<CoachAreaStat> list){

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
				for (CoachAreaStat vo:list) {  
				      
				    int rowNum=0;
				    List<CoachStoreStat> list2=vo.getData();
				    if(list2!=null){
				    	for(CoachStoreStat s:list2){
				    		List<CoachStat> list3=s.getData();
				    		rowNum+=(list3==null)?0:list3.size();
				    	}
				    }
				      
				    if(list2!=null){
				    	for(CoachStoreStat s:list2){
				    		List<CoachStat> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(CoachStat d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getArea());
							    row.createCell(1).setCellValue(s.getStorename());
				    			row.createCell(2).setCellValue(d.getCoachname());
				    			row.createCell(3).setCellValue(d.getStep1());
				    			row.createCell(4).setCellValue(d.getStep2());
				    			row.createCell(5).setCellValue(d.getStep3());
				    			row.createCell(6).setCellValue(d.getStep4());
				    			row.createCell(7).setCellValue(d.getSum());
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
	
	/**
	 * 按组长统计
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/headcoachstat")
	public ResultBean headcoachstat(HeadCoachStatParam param, HttpServletRequest request) {
		
		ResultBean rb = new ResultBean();
		
			List<HeadCoachAreaStat> areastat=coachStatService.statByHeadCoach(param);
			rb.setResult(areastat);
		
		return rb;
	}
	
	public static final String[] headcoachHeader = { "片区", "教学组长", "教练姓名", "阶段一","阶段二","阶段三", "阶段四","合计"};
	
	@RequestMapping(value="/headcoachstat/export")
	public ResponseEntity<byte[]> headcoachstatExport(HeadCoachStatParam param, HttpServletRequest request) throws IOException {
		List<HeadCoachAreaStat> areastat=coachStatService.statByHeadCoach(param);
		Workbook wb = getHeadCoachStatWorkbook(areastat);
			
		return this.export("组长统计", wb);
		
	}
	
	public Workbook getHeadCoachStatWorkbook(List<HeadCoachAreaStat> list){

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
				for (HeadCoachAreaStat vo:list) {  
				      
				    int rowNum=0;
				    List<HeadCoachStat> list2=vo.getData();
				    if(list2!=null){
				    	for(HeadCoachStat s:list2){
				    		List<CoachStat> list3=s.getData();
				    		rowNum+=(list3==null)?0:list3.size();
				    	}
				    }
				      
				    if(list2!=null){
				    	for(HeadCoachStat s:list2){
				    		List<CoachStat> list3=s.getData();
				    		
				    		int secondstartrow=i;
				    		firstendrow=i;
				    		for(CoachStat d:list3){
				    			row = sheet.createRow(i++);  
							    
							    row.setRowStyle(style);
							    row.createCell(0).setCellValue(vo.getArea());
							    row.createCell(1).setCellValue(s.getCoachname());
				    			row.createCell(2).setCellValue(d.getCoachname());
				    			row.createCell(3).setCellValue(d.getStep1());
				    			row.createCell(4).setCellValue(d.getStep2());
				    			row.createCell(5).setCellValue(d.getStep3());
				    			row.createCell(6).setCellValue(d.getStep4());
				    			row.createCell(7).setCellValue(d.getSum());
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
