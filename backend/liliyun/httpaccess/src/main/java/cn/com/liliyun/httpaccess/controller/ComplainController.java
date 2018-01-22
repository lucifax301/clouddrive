package cn.com.liliyun.httpaccess.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ExcelUtil;
import cn.com.liliyun.trainorg.model.Complain;
import cn.com.liliyun.trainorg.model.ComplainReturn;
import cn.com.liliyun.trainorg.service.ComplainService;

@Controller
@ResponseBody
@RequestMapping(value="/complain")
public class ComplainController extends BaseController{

	@Autowired
	private ComplainService complainService;
	
	@RequestMapping(value="/list")
	public ResultBean getList(Complain complain) {
		PageInfo<Complain> pagedResult = complainService.getComplainList(complain);
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(pagedResult);
		return resultBean;
	}
	
	@RequestMapping(value="/deleteComplain")
	public ResultBean deleteById(Complain complain) {
		complainService.deleteById(complain);
		return new ResultBean();
	}

	@RequestMapping(value="/getComplainById")
	public ResultBean getComaplainById(Complain complain) {
		ResultBean resultBean = new ResultBean();
		Complain complainResult = complainService.getComplainById(complain);
		resultBean.setResult(complainResult);
		return resultBean;
	}
	
	@RequestMapping(value="/addComplain")
	public ResultBean addComplain(Complain complain, HttpServletRequest request){

		complain.setStatus(new Integer(0));
		complain.setCusername(getUser(request).getRealname());
		complain.setCtime(new Date());
		complainService.addComplain(complain);
		return new ResultBean();
	}
	
	@RequestMapping(value="/updateComplain")
	public ResultBean updateComplain(Complain complain, HttpServletRequest request) {
		
		complain.setMusername(getUser(request).getRealname());
		complain.setCtime(new Date());
		complainService.updateComplain(complain);
		return new ResultBean();
	}
	
	
	@RequestMapping(value="/returnList")
	public ResultBean getReturnList(ComplainReturn complainReturn) {
		PageInfo<ComplainReturn> pagedResult = complainService.getReturnList(complainReturn);
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(pagedResult);
		return resultBean;
	}

	@RequestMapping(value="/addComplainReturn")
	public ResultBean addComplainReturn(ComplainReturn complainReturn, HttpServletRequest request){
		
		complainReturn.setCusername(getUser(request).getRealname());
		complainReturn.setCtime(new Date());
		complainService.addComplainReturn(complainReturn);
		return new ResultBean();
	}

	/**
	 * 导入用户
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean importUser(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
		
		String [] header = {"summary:str","content:str", "complainuser:str", "complainmobile:str", "remark:str"};
		String uploadPath = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
		ResultBean rb = ExcelUtil.excelToList(header, file, uploadPath, Complain.class,2,null);
		return rb;
	}
	
	/**
	 * 
	 * @param filename
	 * @param type 1确定导入 2放弃导入
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/importExcel",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean importExcel(String filename,String type,HttpServletRequest request,Complain complain) throws IOException, ClassNotFoundException {
		ResultBean rb = new ResultBean();
		String targetDir = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
		List <Complain> list = null;
		File file = new File(targetDir + filename);

		try {
			if (file.exists()) {
				if("1".equals(type)){
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					list = (List<Complain>) ois.readObject();
					complainService.importComplain(complain, list);
					ois.close();
				}else if("2".equals(type)){
					file.delete();
				}
				rb.setResult(list);
				rb.setCode(0);
				rb.setMsg("操作成功");
				return rb;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rb.setCode(2);
		rb.setMsg("操作失败");
		return rb;

	}
	

	//下载
	 @RequestMapping(value = "/download")
   public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {    
       String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/downloadExcel/complain.xlsx";
       File file=new File(path);  
       HttpHeaders headers = new HttpHeaders();    
       String fileName=new String("投诉导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
       headers.setContentDispositionFormData("attachment", fileName);   
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                         headers, HttpStatus.CREATED);    
   }

   
}
