package cn.com.liliyun.httpaccess.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ExcelUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.trainorg.model.Personnel;
import cn.com.liliyun.trainorg.service.TrainOrgService;


@Controller
@ResponseBody
public class TrainOrgController extends BaseController {

	@Autowired
	private TrainOrgService trainOrgService;
	
		//新增
		@RequestMapping(value="/personnel/add")
		public ResultBean addPersonnel(Personnel personnel){
			return trainOrgService.addPersonnel(personnel);
		}
		
		//删除
		@RequestMapping(value="/personnel/delete")
		public ResultBean delPersonnel(Personnel personnel){
			return trainOrgService.deletePersonnel(personnel);
		}
		
		//更新
		@RequestMapping(value="/personnel/update")
		public ResultBean updatePersonnel(Personnel personnel){
			return trainOrgService.updatePersonnel(personnel);
		}
		
		//列表
		@RequestMapping(value="/personnel/list")
		public ResultBean getList(Personnel personnel) {
			return trainOrgService.getPersonnelList(personnel);
			
		}
		
		//条件查询
		@RequestMapping(value="/personnel/listByers")
		public ResultBean getPersonnelers(Personnel personnel) {
			return trainOrgService.getPersonnelByers(personnel);
		}
		
		
		////
		@RequestMapping(value="/personnel/uploadExcel",method=RequestMethod.POST)
		public ResultBean importUser(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
			//String [] header = {"stunum:str","inscode:str","phone:str","address:str"};
			String [] header = {"examnum:str",
					"name:str",
					"sex:str",
					"idcard:str",
					"mobile:str",
					"address:str",
					"photo:int",
					"fingerprint:int",
					"drilicence:str",
					"fstdrilicdate:str",
					"occupationno:str",
					"hiredate:str",
					"post:str"};
			String uploadPath = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
			ResultBean rb = ExcelUtil.excelToList(header, file, uploadPath, Personnel.class,2,null);
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
		@RequestMapping(value="/personnel/importDB",method=RequestMethod.POST)
		public ResultBean importExcel(String filename,String type,HttpServletRequest request) throws IOException, ClassNotFoundException {
			ResultBean rb = new ResultBean();
			String targetDir = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
			List <Student> list = null;
			File file = new File(targetDir + filename);
			try {
				if (file.exists()) {
					if("1".equals(type)){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						list = (List<Student>) ois.readObject();
						trainOrgService.improtExcel(list);
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
		
		
		//获取excel
		@RequestMapping(value = "/personnel/getExcel")
		public Map getData(HttpServletRequest request, HttpServletResponse response){
			
			   //文件存放地址
		    String tagFileName = request.getSession().getServletContext().getRealPath("WEB-INF")+"/uploadExcel";
		    return trainOrgService.getExcelData(tagFileName);
		}
		
		//excel导入数据库
		@RequestMapping(value = "/personnel/importExcel")
		public Map importData(@RequestBody Personnel[] personnel,HttpServletRequest request, HttpServletResponse response){
			List<Personnel> list = new ArrayList<>(Arrays.asList(personnel));
			trainOrgService.improtExcel(list);
		    return null;
		}
		
		
}
