package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.annotation.RequestAction;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Assessor;
import cn.com.liliyun.staff.service.AssessorService;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/assessor")
public class AssessorController extends ExportController {

	@Autowired
	private AssessorService assessorService;
	
	@RequestAction(type=RequestAction.RequestActionType.ADD)
	@RequestMapping(value="/add")
	public ResultBean add(Assessor assessor, HttpServletRequest request) {
		return assessorService.add(assessor);
	}
	
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value="/update")
	public ResultBean update(Assessor assessor, HttpServletRequest request) {
		return assessorService.update(assessor);
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Assessor assessor, HttpServletRequest request) {
		return assessorService.del(assessor);
	}
	
	@RequestMapping(value="/Officer")
	public ResultBean get(Assessor assessor, HttpServletRequest request) {
		Assessor exist= assessorService.get(assessor);
		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Assessor assessor, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
		List <Assessor> list = assessorService.list(assessor);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Assessor assessor, HttpServletRequest request) throws IOException {
		List <Assessor> list = assessorService.list(assessor);
		return this.export("审核员导出数据", "审核员导出数据", "导出数据", list, Assessor.class);
	}
}
