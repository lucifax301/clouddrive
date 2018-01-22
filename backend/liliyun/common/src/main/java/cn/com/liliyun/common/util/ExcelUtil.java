package cn.com.liliyun.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.common.model.ResultBean;


@SuppressWarnings("rawtypes")
public class ExcelUtil {
	
	private static final SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final DecimalFormat df = new DecimalFormat("0");

	
	/**
	 * 
	 * @param header 某一列不需要解析header中配置字符串"skip"
	 * @param file 文件流
	 * @param uploadPath 上传路径
	 * @param c 返回数据对象类
	 * @param resolveLine 从第几行开始解析 1表示从第1行开始解析
	 * @param handler excel验证处理函数
	 * @return name上传文件名称   title上传文件第一行文字内容 filename序列化文件名称 data列表数据 
	 * @throws IOException
	 */
	public static ResultBean excelToList(String [] header, MultipartFile file, String uploadPath, Class c,
			int resolveLine,ExcelCheckHandler handler) throws IOException {
		ResultBean rb = new ResultBean();
		if (file == null) {
			rb.setCode(200);
			rb.setMsg("请选择上传文件!");
			return rb;
		}
		String fileName = file.getOriginalFilename();
		Workbook wb = null; 
		//检查文件类型
		if (fileName.endsWith(".xls")) {
			wb = new HSSFWorkbook(file.getInputStream());
		} else if (fileName.endsWith(".xlsx")) {
			wb = new XSSFWorkbook(file.getInputStream()); 
		} else {
			rb.setCode(200);
			rb.setMsg("请选择.xls或.xlsx格式文件");
			return rb;
		}
		
		try{
			String title = wb.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
			List <Object> list = resolveExcel(wb.getSheetAt(0),resolveLine,header,c);
			System.out.println(list);
			Map <String, Object> result = null;
			//验证列表数据
			if (handler != null) {
				result = handler.check(list,title);
			} else {
				result = new HashMap<>();
			}
			//序列化list到磁盘 后面替换redis实现
			String serFileName = DateUtil.currentDateTime() + ".ser";
		    String targetFileName = uploadPath + serFileName;
		    FileOutputStream fos = new FileOutputStream(new File(targetFileName));
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(list);
		    oos.flush();
		    oos.close();
			result.put("filename", serFileName);
			result.put("name", fileName.split("\\.")[0]);
			result.put("list", list);
			result.put("title", title);
			rb.setResult(result);
			return rb;
			
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(200);
			rb.setMsg("文件内容有错误，请核对!");
		}
		return rb;
	}

	private static List <Object> resolveExcel(Sheet sheet,int resolveLine,String [] header,Class c) {
		Iterator <Row> rows = sheet.rowIterator(); 
		List <Object> list = new ArrayList<>();
		resolveLine -= 2;
		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getRowNum() > resolveLine) {
				Iterator <Cell> cells = row.cellIterator(); 
				Object obj = null;
				try {
					obj = c.newInstance();
				} catch (InstantiationException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				int flag = 0;
				for (String h : header) {
					//跳过该列数据不解析
					if ("skip".equals(h)) {
						cells.next();
						continue;
					}
					Cell cell = null;
					try {
						cell = cells.next();
					} catch (NoSuchElementException e) {
						break;
					}
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						flag++;
						continue;
					}
					String tt [] = h.split(":");
					String dataName = tt[0];
					String dataType = tt[1];
					//数据格式转换
					Object value = null;
					if (dataType.equals("date")) {
						try {
							value = cell.getDateCellValue();
						} catch (Exception e) {
							value = cell.getStringCellValue();
							SimpleDateFormat sdf = null;
							if (value.toString().length() > 10) {
								sdf = timeSdf;
							} else {
								sdf = dateSdf;
							}
							try {
								value = sdf.parse(value.toString());
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
					} else if (dataType.equals("int")) {
						//字符串或数字格式可能不明确 try catch 兼容转换数据类型
						try {
							value = cell.getNumericCellValue();
							value = df.format(value);
						} catch (IllegalStateException e) {
							value = cell.getStringCellValue();
						}
						value = Integer.valueOf(value.toString());
					} else if (dataType.equals("long")) {
						//字符串或数字格式可能不明确 try catch 兼容转换数据类型
						try {
							value = cell.getNumericCellValue();
							value = df.format(value);
						} catch (IllegalStateException e) {
							value = cell.getStringCellValue();
						}
						value = Long.valueOf(value.toString());
					} else if (dataType.equals("double")) {
						value = cell.getNumericCellValue();
					} else if (dataType.equals("str")) {
						//字符串或数字格式可能不明确 try catch 兼容转换数据类型
						try {
							value = cell.getStringCellValue();
						} catch (IllegalStateException e) {
							value = cell.getNumericCellValue();
							value = df.format(value);
						}
						value = value == null ? "" : value.toString().trim();
					} else {
						//其他
					}
					ReflectUtils.setPropertyUtilByName(obj, dataName, value);
				}
				if (flag != header.length) {
					list.add(obj);
				}
			}
		}
		return list;
	}

	
}
