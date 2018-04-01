package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.finance.model.FinanceFee;
import cn.com.liliyun.finance.model.FinanceFeeDTO;
import cn.com.liliyun.finance.model.FinanceFeeItem;
import cn.com.liliyun.finance.model.FinancePay;
import cn.com.liliyun.finance.service.FinanceFeeService;
import cn.com.liliyun.student.dto.StudentMoneyDTO;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.service.StudentService;

import com.alibaba.fastjson.JSONObject;

@Controller
@ResponseBody
@RequestMapping(value="/financefee")
public class FinanceFeeController extends BaseController {

	@Autowired
	private FinanceFeeService financeFeeService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/list")
	public ResultBean list(HttpServletRequest request,FinanceFee financeFee) {
		List <FinanceFee> list = financeFeeService.selectList( financeFee);
		return this.<FinanceFee>buildListResult(list);
	}

	@RequestMapping(value="/listitem")
	public ResultBean itemList(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		List <FinanceFeeItem> list = financeFeeService.selectItemList( financeFeeItem);
		return this.<FinanceFeeItem>buildListResult(list);
		
	}

	@RequestMapping(value="/listallitem")
	public ResultBean itemAllList(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		List <FinanceFeeItem> list = financeFeeService.selectAllItemList( financeFeeItem);
		return this.<FinanceFeeItem>buildListResult(list);
		
	}
	
	@RequestMapping(value="/listowe")
	public ResultBean listOwe(HttpServletRequest request,StudentMoneyDTO dto) {
		List <StudentMoneyDTO> list = studentService.selectOweList( dto);
		return this.<StudentMoneyDTO>buildListResult(list);
		
	}
	
	@RequestMapping(value="/listpay")
	public ResultBean listPay(HttpServletRequest request,FinancePay financePay) {
		List <FinancePay> list = financeFeeService.selectPay( financePay);
		return this.<FinancePay>buildListResult(list);
	}
	
	@RequestMapping(value="/delitem")
	public ResultBean delItem(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		return financeFeeService.deleteItem( financeFeeItem);
	}
	
	@RequestMapping(value="/init")
	public ResultBean init(HttpServletRequest request,Student student) {
		return financeFeeService.initData( student);
	}
	
	@RequestMapping(value="/add")
	public ResultBean add(HttpServletRequest request,FinanceFeeDTO dto,String json) {
		List <FinanceFeeItem> list = JSONObject.parseArray(json, FinanceFeeItem.class);
		return financeFeeService.save( dto, list);
	}
	
	@RequestMapping(value="/edit")
	public ResultBean edit(HttpServletRequest request,FinanceFeeDTO dto,String json) {
		List <FinanceFeeItem> list = JSONObject.parseArray(json, FinanceFeeItem.class);
		return financeFeeService.edit( dto, list);
	}
	
	@RequestMapping(value="/additem")
	public ResultBean addItem(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		return financeFeeService.saveItem( financeFeeItem);
	}
	
	@RequestMapping(value="/edititem")
	public ResultBean editItem(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		return financeFeeService.editItem( financeFeeItem);
	}
	
	@RequestMapping(value="/check")
	public ResultBean check(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		return financeFeeService.check( financeFeeItem);
	}
	
	@RequestMapping(value="/checkbatch")
	public ResultBean checkBatch(HttpServletRequest request,FinanceFeeItem financeFeeItem) {
		return financeFeeService.checkBatch( financeFeeItem);
	}
}
