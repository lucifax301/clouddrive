package cn.com.liliyun.trainorg.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.PersonnelMapper;
import cn.com.liliyun.trainorg.model.Personnel;
import cn.com.liliyun.trainorg.service.TrainOrgService;

@Service
public class TrainOrgServiceImpl implements TrainOrgService {
	Logger logger = Logger.getLogger(TrainOrgServiceImpl.class);
	
	@Autowired
	private PersonnelMapper personnelMapper;

	@Override
	public ResultBean addPersonnel(Personnel personnel) {
		ResultBean r = new ResultBean();
		Personnel per = getPersonnelByid(personnel);
		if(per!=null){
			r.setCode(3);
			r.setMsg("添加失败,身份证号码重复");
			return r; //
		}
		personnelMapper.savePersonnel(personnel);
		r.setCode(0);
		r.setMsg("添加成功");
		return r; 
	}

	@Override
	public ResultBean deletePersonnel(Personnel personnel) {
		ResultBean r = new ResultBean();
		personnelMapper.deletePersonnel(personnel);
		r.setCode(0);
		r.setMsg("删除成功");
		return r; 
	}

	@Override
	public ResultBean updatePersonnel(Personnel personnel) {
		ResultBean r = new ResultBean();
		personnelMapper.updatePersonnel(personnel);
		r.setCode(0);
		r.setMsg("更新成功");
		return r;
	}

	@Override
	public ResultBean getPersonnelByers(Personnel personnel) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(personnel);
		List<Personnel> personnelList = personnelMapper.getPersonnelbyers(personnel);
		PageInfo<Personnel> pagedResult = new PageInfo<>(personnelList);
		resultBean.setResult(pagedResult);
		return resultBean;
			
	}


	@Override
	public Personnel getPersonnelByid(Personnel personnel) {
		return personnelMapper.getPersonnelById(personnel);
	}

	@Override
	public ResultBean getPersonnelList(Personnel personnel) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(personnel);
		List<Personnel> personnelList = personnelMapper.getAllPersonnel(personnel);
		PageInfo<Personnel> pagedResult = new PageInfo<>(personnelList);
		resultBean.setResult(pagedResult);
		return resultBean;
		
	}

	@Override
	public void improtExcel(List list) {
		Iterator it = list.iterator();
		while(it.hasNext()){
			Personnel personnel = (Personnel) it.next();
			personnelMapper.savePersonnel(personnel);
			
		}
	}

	@Override
	public Map getExcelData(String path) {
		File file = new File(path);
		Map map = new HashMap<>();
		File[] ary = file.listFiles(new TxtFilter());
		logger.info("*********************目录下excle文件数量：********************"+ary.length+"个");
		if(ary.length>0){
			int lent = ary.length;
			File chiledfile = ary[lent-1];
			map =  getDatas(chiledfile);
			
			chiledfile.delete(); //删掉
			return map;
		}
		 map.put("code", "2");//目录下没有文件
		 return map;
	}
	
	public Map getDatas(File file){
		Map<String,Object> result = new HashMap<>();
		FileInputStream fis = null;
		List<Personnel> list = new ArrayList<Personnel>();
		
		try {
			fis = new FileInputStream(file);
			Workbook wb  = null;
			if(true)
				wb = new XSSFWorkbook(fis);
			else
				wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);		//获得第一个表单
			Iterator<Row> rows = sheet.rowIterator();	//获得第一个表单的迭代器
		
			int rowNum = 0,columnNum = 0;
			boolean flag = false;
			while (rows.hasNext()) {
				Row row = rows.next();	//获得行数据
					Iterator<Cell> cells = row.cellIterator();	//获得第一行的迭代器
					while (cells.hasNext()) {
						Cell cell = cells.next();
						
						if("人员编号".equals(cell.getStringCellValue())){
							rowNum = row.getRowNum()+1;
							columnNum = cell.getColumnIndex();
							System.out.println("Row #" + rowNum+",,"+"Cell #" + columnNum);//正文起始位置
							flag = true;
							break;
						}

					}
					if(flag)
					break;
			}
			if(!flag){
				result.put("code", "0");
				logger.info("*********************excel没有学员内容********************");
				return result;
			}
			///
			Iterator<Row> rows1 = sheet.rowIterator();	
			
			while (rows1.hasNext()) {
				Row row = rows1.next();	//获得行数据
				int tempNum = columnNum;
				if(row.getRowNum()>=rowNum){
					Personnel personnel = new Personnel();
					Cell cell = row.getCell(tempNum); //正文第一个单元格
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setExamnum(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setExamnum(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setExamnum(value);
				                break;
				            default:  
				                break; 
		            	}
					}else{
						 personnel.setExamnum("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setName(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setName(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setName(value);
				                break;
				            default:  
				                break; 
		            	}
					}else{
						personnel.setName("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setSex(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setSex(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setSex(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setSex("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setIdcard(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setIdcard(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setIdcard(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						 personnel.setIdcard("");
					}
					
					////
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setMobile(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setMobile(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setMobile(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setMobile("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setAddress(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setAddress(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setAddress(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						 personnel.setAddress("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setPhoto(Long.valueOf(value));
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setPhoto(Long.valueOf((long) cell.getNumericCellValue()));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setPhoto(0);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setPhoto(0);
					}
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setFingerprint(Long.valueOf(value));
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setFingerprint(Long.valueOf((long) cell.getNumericCellValue()));
				                
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setFingerprint(0);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setFingerprint(0);
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setDrilicence(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setDrilicence(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setDrilicence("");
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setDrilicence("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setFstdrilicdate(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				            	value = String.valueOf(cell.getNumericCellValue()); 
				                personnel.setFstdrilicdate(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setFstdrilicdate("");
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setFstdrilicdate("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setOccupationno(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				            	value = String.valueOf(cell.getNumericCellValue()); 
				                personnel.setOccupationno(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setOccupationno(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						 personnel.setOccupationno("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setHiredate(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setHiredate(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setHiredate(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setHiredate("");
					}
					
					tempNum++;
					cell = row.getCell(tempNum); //
					if(cell!=null){
						switch(cell.getCellType())  
		            	{
		            		
			            	case HSSFCell.CELL_TYPE_STRING://字符串  
				                String value = cell.getRichStringCellValue().getString();
				                personnel.setPost(value);
				                break;  
				            case HSSFCell.CELL_TYPE_NUMERIC://数字  
				                value = String.valueOf(cell.getNumericCellValue());  
				                personnel.setPost(value.substring(0,value.indexOf(".")));
				                break;  
				            case HSSFCell.CELL_TYPE_BLANK:  
				                value = "";  
				                personnel.setPost(value);
				                break;
				            default:  
				                break; 
		            	}
						
					}else{
						personnel.setPost("");
					}
					//如果学员编号 和 证件号码为空的话，视为无效数据将过滤掉
					if( ("".equals(personnel.getExamnum() ) || "".equals(personnel.getExamnum().trim())) &&  ("".equals(personnel.getIdcard() ) || "".equals(personnel.getIdcard().trim())) ){
						continue;
					}
					System.out.println("===="+personnel.getExamnum()+",,"+personnel.getIdcard());
					list.add(personnel);
				}
				
			}  

			logger.info("*********************************uploadExcelDetail Start!,已经读取行数:"+list.size());
			fis.close();
			result.put("code", "1");
			result.put("dataList", list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("*********************************解析excel出现异常");
			result.put("code", "0");
		return result;
		
	}
	
	
	class TxtFilter implements FileFilter {
		 public boolean accept(File file) {
		  if (file.isFile()) {
		   String fileName = file.getName();
		   if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls") ) {
		    return true;
		   }
		  }
		  return false;
		 }
	}
	
	

}
