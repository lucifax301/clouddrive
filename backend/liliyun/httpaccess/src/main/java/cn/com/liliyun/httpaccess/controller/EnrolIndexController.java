package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;
import cn.com.liliyun.market.model.StoreEnrolIndex;
import cn.com.liliyun.market.service.EnrolIndexService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/enrolindex")
public class EnrolIndexController extends ExportController {

	Logger logger = Logger.getLogger(EnrolIndexController.class);

	@Autowired
	private EnrolIndexService enrolIndexService;
	
	//新增
		@RequestMapping(value="/addarea")
		public ResultBean addMarketing(AreaEnrolIndex index,HttpServletRequest request){
			
			User user = AccessWebUtil.getSessionUser(request);
			
			java.util.Enumeration<String> e= request.getParameterNames();
			List<StoreEnrolIndex> indexs=new ArrayList();
			while(e.hasMoreElements()){
				String key=e.nextElement();
				if(key.startsWith("storeid_")){
					String storeid= request.getParameter(key);
					StoreEnrolIndex sindex=new StoreEnrolIndex();
					sindex.setStoreid(Integer.parseInt(storeid));
					sindex.setEnrolindex(Integer.parseInt(request.getParameter("enrolindex_"+storeid)));
					sindex.setHighrate(Integer.parseInt(request.getParameter("highrate_"+storeid)));
					sindex.setAreaid(index.getAreaid());
					sindex.setYear(index.getYear());
					sindex.setMonth(index.getMonth());
					indexs.add(sindex);
				}
			}
			index.setData(indexs);
			
			return enrolIndexService.addAreaEnrollIndex(index);
		}
		
		
		//更新
		@RequestMapping(value="/updatearea")
		public ResultBean updateArea(AreaEnrolIndex index,HttpServletRequest request){
			
			User user = AccessWebUtil.getSessionUser(request);
			
			java.util.Enumeration<String> e= request.getParameterNames();
			List<StoreEnrolIndex> indexs=new ArrayList();
			while(e.hasMoreElements()){
				String key=e.nextElement();
				if(key.startsWith("storeid_")){
					String storeid= request.getParameter(key);
					StoreEnrolIndex sindex=new StoreEnrolIndex();
					sindex.setStoreid(Integer.parseInt(storeid));
					sindex.setEnrolindex(Integer.parseInt(request.getParameter("enrolindex_"+storeid)));
					sindex.setHighrate(Integer.parseInt(request.getParameter("highrate_"+storeid)));
					sindex.setAreaid(index.getAreaid());
					sindex.setYear(index.getYear());
					sindex.setMonth(index.getMonth());
					indexs.add(sindex);
				}
			}
			index.setData(indexs);
			
			return enrolIndexService.updateAreaEnrollIndex(index);
		}
		
		@RequestMapping(value="/delarea")
		public ResultBean delArea(AreaEnrolIndex index,HttpServletRequest request){
			
			User user = AccessWebUtil.getSessionUser(request);
			return enrolIndexService.delAreaEnrollIndex(index);
		}
		
		//列表
		@RequestMapping(value="/listarea")
		public ResultBean listArea(AreaEnrolIndex index) {
			ResultBean resultBean = new ResultBean();
			List<AreaEnrolIndex> list= enrolIndexService.listAreaEnrollIndex(index);
			PageInfo<AreaEnrolIndex> pagedResult = new PageInfo<>(list);
			resultBean.setResult(pagedResult);
			resultBean.setCode(0);
			return resultBean;
		}
		
		@RequestMapping(value="/listarea/export")
		public ResponseEntity<byte[]> listAreaExport(AreaEnrolIndex index) throws IOException {
			
			List<AreaEnrolIndex> list= enrolIndexService.listAllAreaEnrollIndex(index);
			return this.export("片区招生指标", "片区招生指标", "导出数据", list, AreaEnrolIndex.class);
		}
		
		@RequestMapping(value="/getarea")
		public ResultBean getArea(AreaEnrolIndex index){
			ResultBean resultBean = new ResultBean();
			AreaEnrolIndex market= enrolIndexService.getAreaEnrollIndex(index);
			resultBean.setResult(market);
			return resultBean;
		}
		
		/**
		 * 编辑员工招生指标
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/editcs")
		public ResultBean editcs(CSEnrolIndex index) {
			ResultBean resultBean = enrolIndexService.editCSEnrolIndex(index);
			return resultBean;
		}
		
		/**
		 * 获取员工招生指标
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/getcs")
		public ResultBean getcs(CSEnrolIndex index){
			ResultBean resultBean = enrolIndexService.getCSEnrolIndex(index);
			
			return resultBean;
		}
		
		
		/**
		 * 编辑教练招生指标
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/editcoach")
		public ResultBean editcoach(CoachEnrolIndex index) {
			return enrolIndexService.editCoachEnrolIndex(index);
			
		}
		
		/**
		 * 获取教练招生指标
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/getcoach")
		public ResultBean getcoach(CoachEnrolIndex index){
			return enrolIndexService.getCoachEnrolIndex(index);
			
		}
}
