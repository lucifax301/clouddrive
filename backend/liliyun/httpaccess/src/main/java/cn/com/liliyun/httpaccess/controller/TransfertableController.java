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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Transfertable;
import cn.com.liliyun.student.model.TransfertableItem;
import cn.com.liliyun.student.service.TransfertableService;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/transfertable")
public class TransfertableController extends BaseController {
	
	Logger logger = Logger.getLogger(TransfertableController.class);

	@Autowired
	private TransfertableService transfertableService;
	
	@RequestMapping(value="/list")
	public ResultBean list(HttpServletRequest request, Transfertable transfertable) {
		ResultBean rb = new ResultBean();
		List<Transfertable> list = transfertableService.list(transfertable);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist")
	public ResultBean itemlist(HttpServletRequest request,TransfertableItem transfertableItem) {
		ResultBean rb = new ResultBean();
		List<TransfertableItem> list = transfertableService.listItem(transfertableItem);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/arealist")
	public ResultBean storelist(HttpServletRequest request,String json) {
		ResultBean rb = new ResultBean();
		List <Transfertable> list = JSONObject.parseArray(json, Transfertable.class);
		List <TransfertableItem> dataList = transfertableService.listAreaTransferItem( list);
		rb.setResult(new PageInfo<>(dataList));
		return rb;
	}
	
	@RequestMapping(value="/licenselist")
	public ResultBean licenselist(HttpServletRequest request,String json) {
		ResultBean rb = new ResultBean();
		List <Transfertable> list = JSONObject.parseArray(json, Transfertable.class);
		List <TransfertableItem> dataList = transfertableService.listLicenseTransferItem( list);
		rb.setResult(new PageInfo<>(dataList));
		return rb;
	}
	
	/**
	 * 门店交表
	 * @return
	 */
	@RequestMapping(value="/storetransfer")
	public ResultBean storetransfer(HttpServletRequest request,String json) {
		List <TransfertableItem> list = JSONObject.parseArray(json, TransfertableItem.class);
		return transfertableService.doStoreTransfer( list);
	}
	
	/**
	 * 片区交表
	 * @return
	 */
	@RequestMapping(value="/areatransfer")
	public ResultBean areatransfer(HttpServletRequest request,String json) {
		ResultBean rb = new ResultBean();
		List <Transfertable> list = JSONObject.parseArray(json, Transfertable.class);
		transfertableService.doAreaTranfer( list);
		return rb;
	}
	
	/**
	 * 牌证交表
	 * @return
	 */
	@RequestMapping(value="/licensetransfer")
	public ResultBean licensetransfer(HttpServletRequest request,String json) {
		List <TransfertableItem> list = JSONObject.parseArray(json, TransfertableItem.class);
		return transfertableService.doStoreTransfer( list);
	}
	
	/**
	 * 片区退表
	 * @return
	 */
	@RequestMapping(value="/areareturn")
	public ResultBean areareturn(HttpServletRequest request,String json,String rtnreason) {
		List <TransfertableItem> list = JSONObject.parseArray(json, TransfertableItem.class);
		return transfertableService.doAreaReturn( list, rtnreason);
	}
	
	/**
	 *  牌证退表
	 * @return
	 */
	@RequestMapping(value="/licensereturn")
	public ResultBean licensereturn(HttpServletRequest request,String json,String rtnreason) {
		List <TransfertableItem> list = JSONObject.parseArray(json, TransfertableItem.class);
		return transfertableService.doLicenseReturn( list, rtnreason);
	}

	/**
	 * 牌证收表
	 * @return
	 */
	@RequestMapping(value="/licensereceive")
	public ResultBean receive(HttpServletRequest request,String json) {
		List <Transfertable> list = JSONObject.parseArray(json, Transfertable.class);
		return transfertableService.doLicenseReceive(list);	
	}

	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Transfertable transfertable) throws IOException {
		TransfertableItem item  = new TransfertableItem();
//		item.setTableid(transfertable.getId());
	  	//List<TransfertableItem> list = transfertableService.itemList(item);
		ExportParams params = new ExportParams("导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, TransfertableItem.class, null);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
	    HttpHeaders headers = new HttpHeaders();
	    String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String fileName = new String(("导出数据" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	    headers.setContentDispositionFormData("attachment", fileName);
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);
	}

}
