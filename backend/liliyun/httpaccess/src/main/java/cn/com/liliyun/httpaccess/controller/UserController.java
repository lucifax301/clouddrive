package cn.com.liliyun.httpaccess.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ExcelUtil;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@Controller
@ResponseBody
@RequestMapping(value="/user")
public class UserController extends BaseController {

	protected final Logger access = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取数据列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/list")
	public ResultBean userlist(User user) {
		return userService.getList(user);
	}
	
	@RequestMapping(value="/listsome")
	public ResultBean userlistsome(User user) {
		return userService.getListsome(user);
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add")
	public ResultBean addUser(User user) {
		if (user.getId() == null) {
			return userService.saveUser(user);
		} else {
			return userService.updateUser(user);
		}
	} 
	
	
	/**
	 * 启用停用用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/status")
	public ResultBean status(User user,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		User userQuery = new User();
		userQuery.setId(user.getId());
		userQuery = userService.getUser(userQuery);
		if (userQuery.getIssuper() == 1) {
			rb.setCode(200);
			rb.setMsg("根账户不能停用!");
			return rb;
		}
		user.setStatus((byte)(user.getStatus() == 0 ? 1 : 0));
		return userService.updateUser(user);
	}
	
	@RequestMapping(value="/updatepwd")
	public ResultBean updatepwd(User user,HttpServletRequest request) {
		User curuser =  AccessWebUtil.getSessionUser(request);
		return userService.changepwd(user, curuser);
		
		
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/update")
	public ResultBean update(User user,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		if (user.getId() == null) {
			rb.setCode(200);
			rb.setMsg("获取主键信息错误!");
			return rb;
		}
		User userQuery = new User();
		userQuery.setId(user.getId());
		userQuery = userService.getUser(userQuery);
		if (userQuery == null) {
			rb.setCode(200);
			rb.setMsg("获取主键信息错误!");
		} else {
			rb = userService.updateUser(user);
		}
		return rb;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updatePassword")
	public ResultBean updatePassword(User user,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		if (StringUtils.isBlank(user.getPassword())) {
			rb.setCode(200);
			rb.setMsg("密码不能为空!!");
			return rb;
		}
		User currentUser = getUser(request);
		user.setId(currentUser.getId());
		rb = userService.updateUser(user);
		return rb;
	}
	
	/**
	 * 导入用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ResultBean importUser(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String [] header = {"username:str","realname:str","mobile:str"};
		String uploadPath = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
		try {
			rb = ExcelUtil.excelToList(header, file, uploadPath, User.class,2,null);
		} catch (IOException e) {
			e.printStackTrace();
			rb.setCode(200);
			rb.setMsg("导入文件出错,请检查导入文件数据是否正确!");
			return rb;
		}
		return rb;
	}
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResultBean delUser(User user) {
		return userService.deleteUser(user);
	}
	
	/**
	 * 
	 * @param filename
	 * @param type 1确定导入 2放弃导入
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultBean importExcel(String filename,String type,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String targetDir = request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
		File file = new File(targetDir + filename);
		if ("1".equals(type)) {
			List <User> list = null;
			if (file.exists()) {
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					list = (List<User>) ois.readObject();
					ois.close();
					rb = userService.importUser(getUser(request), list);
					file.delete();
				} catch (Exception e) {
					rb.setCode(200);
					rb.setMsg("读取上传临时文件出错!");
					return rb;
				}
			} else {
				rb.setCode(200);
				rb.setMsg("请重新上传文件!");
				return rb;
			}
		} else {
			file.delete();
		}
		return rb;
	}
	
	/**
	 * 获取用户菜单
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/menu")
	public ResultBean menu(User user) {
		return userService.getList(user);
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/export")
	public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/downloadExcel/user.xlsx";
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName = new String("用户导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
	}
	
	/**
	 * 下载excel上传模板
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/downloadExcel/user.xlsx";
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName = new String("用户导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
	}
	
	@RequestMapping(value = "/listWithRole", method = RequestMethod.GET)
	public ResultBean getListWithRole (HttpServletRequest request, User user){
		return userService.getUserListWithRole(user);
	}
	
	
}
