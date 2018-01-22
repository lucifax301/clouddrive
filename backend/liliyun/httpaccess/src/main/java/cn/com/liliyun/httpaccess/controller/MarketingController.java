package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.ExcelUtil;
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.MarketActivity;
import cn.com.liliyun.market.service.MarketService;
import cn.com.liliyun.trainorg.model.Marketing;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/marketing")
public class MarketingController extends BaseController{
	Logger logger = Logger.getLogger(MarketingController.class);

	@Autowired
	private MarketService marketService;
	
	//新增
	@RequestMapping(value="/addmarket")
	public ResultBean addMarketing(MarketActivity activity,HttpServletRequest request){
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		return marketService.addMarketActivity(activity,log,user,bussinessid);
	}
	
	
	
	//更新
	@RequestMapping(value="/updatemarket")
	public ResultBean updateMarketing(MarketActivity activity,HttpServletRequest request){
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		return marketService.updateMarketActivity(activity,log,user);
	}
	
	@RequestMapping(value="/auditmarket")
	public ResultBean auditMarketing(MarketActivity activity,HttpServletRequest request){
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		return marketService.auditMarketActivity(activity, log, user);
	}
	
	@RequestMapping(value="/batchauditmarket")
	public ResultBean batchauditmarket(MarketActivity activity,HttpServletRequest request){
		LogCommon log = initLogParams(request, 0, LogConstant.ACTION_PASS);
		User user = AccessWebUtil.getSessionUser(request);
		String applyids= request.getParameter("ids");
		String state = request.getParameter("status");
		String ids[]= applyids.split(",");
		return marketService.batchAuditMarketActivity(ids, Integer.parseInt(state), log, user);
	}
	
	//列表
	@RequestMapping(value="/listmarket")
	public ResultBean geMarketList(MarketActivity activity,HttpServletRequest request) {
		User user = AccessWebUtil.getSessionUser(request);
		ResultBean resultBean = new ResultBean();
		List<MarketActivity> list= marketService.listActivity(activity,user);
		PageInfo<MarketActivity> pagedResult = new PageInfo<>(list);
		resultBean.setResult(pagedResult);
		resultBean.setCode(0);
		return resultBean;
	}
	
	@RequestMapping(value="/listmarket/export")
	public ResponseEntity<byte[]> export(MarketActivity activity,HttpServletRequest request)  throws IOException{
		User user = AccessWebUtil.getSessionUser(request);
		List<MarketActivity> list= marketService.listExportActivity(activity, user);
		
		ExportParams params = new ExportParams("市场活动数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, MarketActivity.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("市场活动数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getmarket")
	public ResultBean getMarket(MarketActivity activity,HttpServletRequest request){
		ResultBean resultBean = new ResultBean();
		User user = AccessWebUtil.getSessionUser(request);
		MarketActivity market= marketService.getMarketActivity(activity,user);
		resultBean.setResult(market);
		return resultBean;
	}
	
	@RequestMapping(value="/getByTran")
	public ResultBean getByTran(MarketActivity activity,HttpServletRequest request){
		ResultBean resultBean = new ResultBean();
		User user = AccessWebUtil.getSessionUser(request);
		MarketActivity market= marketService.getMarketActivityByTran(activity,user);
		resultBean.setResult(market);
		return resultBean;
	}
	
	
	//上传资料
	@RequestMapping(value="/marketing/uploadExcel",method=RequestMethod.POST)
	public ResultBean importUser(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
		String [] header = {"name:str","phone:str","traintype:str","address:str","source:str","trackePersonnel:str","progress:str"};
		String uploadPath = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
		ResultBean rb = ExcelUtil.excelToList(header, file, uploadPath, Marketing.class,2,null);
		return rb;
	}
	
	/**
	 * 
	 * @param filename
	 * @param type 1确定导入 2放弃导入
	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value="/marketing/importDB",method=RequestMethod.POST)
//	public ResultBean importExcel(String filename,String type,HttpServletRequest request,Marketing marketing) throws IOException, ClassNotFoundException {
//		ResultBean rb = new ResultBean();
//		String targetDir = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
//		List <Marketing> list = null;
//		File file = new File(targetDir + filename);
//		try {
//			if (file.exists()) {
//				if("1".equals(type)){
//					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//					list = (List<Marketing>) ois.readObject();
//					marketingService.improtExcel(marketing, list);
//					ois.close();
//				}else if("2".equals(type)){
//					file.delete();
//				}
//				rb.setResult(list);
//				rb.setCode(0);
//				rb.setMsg("操作成功");
//				return rb;
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		rb.setCode(2);
//		rb.setMsg("操作失败");
//		return rb;
//	}
//	
//	//下载
//	 @RequestMapping(value = "/marketing/download")    
//    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {    
//        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/downloadExcel/marketing.xlsx";
//        File file=new File(path);  
//        HttpHeaders headers = new HttpHeaders();    
//        String fileName=new String("潜在客户导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
//        headers.setContentDispositionFormData("attachment", fileName);   
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
//                                          headers, HttpStatus.CREATED);    
//    }
	
}
