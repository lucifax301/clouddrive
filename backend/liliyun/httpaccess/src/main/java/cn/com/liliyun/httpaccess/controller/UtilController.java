package cn.com.liliyun.httpaccess.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.io.PutRet;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PicUtil;

@Controller
@ResponseBody
public class UtilController {

	@RequestMapping(value="/qiniuToken")
	public ResultBean getQiliuToken(){
		ResultBean r = new ResultBean();
		String token = PicUtil.getPublicToken();
		r.setResult(token);
		return r;
	}
	
	//excel下周
	@RequestMapping(value = "/excel/download/{filename}", method=RequestMethod.GET)    
    public ResponseEntity<byte[]> download(HttpServletRequest request,@PathVariable String filename) throws IOException {    
        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/" + filename + ".xlsx";
        File file = new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName = new String("错误数据.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }
    
    @RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public ResultBean uploadImg(@RequestParam("file") MultipartFile mfile, @RequestParam(value = "id", required = false) String id) {
    	ResultBean r = new ResultBean();
    	try {
    		//生成随机数列作为文件命名，避免高并发时文件互相覆盖
    		Long timestamp = new Date().getTime();
    		Integer random = new Random().nextInt(1000);
    		File file = new File(timestamp.toString() + random.toString());
    		FileUtils.copyInputStreamToFile(mfile.getInputStream(), file);
    		PutRet ret = PicUtil.uploadFileWithRandomName(file);
    		System.out.println(file.getAbsolutePath());
    		Map<String, Object> result = new HashMap<>();
    		result.put("pic", ret.getKey());
    		result.put("id", id);
    		result.put("download", 0);
    		r.setResult(result);
    		file.delete();
    	} catch(Exception e) {
    		e.printStackTrace();
    		r.setCode(HttpConstant.ERROR_CODE);
    		r.setMsg(HttpConstant.ERROR_MSG);
    	}
    	return r;
    }
	
    @RequestMapping(value="/uploadfile", method=RequestMethod.POST)
    public ResultBean uploadfile(@RequestParam("file") MultipartFile mfile) {
    	ResultBean r = new ResultBean();
    	try {
    		//生成随机数列作为文件命名，避免高并发时文件互相覆盖
    		Long timestamp = new Date().getTime();
    		Integer random = new Random().nextInt(1000);
    		String suffix = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
    		File file = new File(timestamp.toString() + random.toString() + suffix);
    		FileUtils.copyInputStreamToFile(mfile.getInputStream(), file);
    		PutRet ret = PicUtil.uploadFile(file);
    		System.out.println(file.getAbsolutePath());
    		Map<String, Object> result = new HashMap<>();
    		result.put("filename", ret.getKey());
    		result.put("ofilename",mfile.getOriginalFilename() );
    		result.put("download", 0);
    		r.setResult(result);
    		file.delete();
    	} catch(Exception e) {
    		e.printStackTrace();
    		r.setCode(HttpConstant.ERROR_CODE);
    		r.setMsg(HttpConstant.ERROR_MSG);
    	}
    	return r;
    }
}
