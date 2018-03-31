package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.annotation.RequestAction;
import cn.com.liliyun.common.dto.SelectDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.service.SalesService;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 营销活动
 * @author devil
 *
 */
@Controller
@ResponseBody
@RequestMapping(value="/sales")
public class SalesController extends ExportController{

	@Autowired
	private SalesService salesService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@Autowired
	private CoachSettingService coachSettingService;
	
	@RequestMapping(value="/listAllClassType")
	public ResultBean listAllClassType(SalesActivity activity,HttpServletRequest request){
		Classinfo cp=new Classinfo();
		cp.setStatus(0);
		
		List<Classinfo> clss= classinfoService.selectAllList(cp);
		CoachClassType pcct= new CoachClassType();
		
		List<CoachClassType> list = coachSettingService.listAllClassType(pcct);
		List<SalesActivityClassinfo> result=new ArrayList<SalesActivityClassinfo>();
		for(Classinfo cls:clss){
			SalesActivityClassinfo info=new SalesActivityClassinfo();
			info.setC1flag(cls.getC1flag());
			info.setC2flag(cls.getC2flag());
			info.setClassinfoid(cls.getId());
			info.setC1amount(cls.getC1amount());
			info.setC2amount(cls.getC2amount());
			info.setName(cls.getName());
			for(CoachClassType type:list){
				if(type.getId()==cls.getClasstypeid().intValue()){
					info.setClasstype(type.getType());
				}
			}
			result.add(info);
		}
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<>(result));
		return rb;
		
	}
	
	@RequestMapping(value="/listEditAllClassType")
	public ResultBean listEditAllClassType(SalesActivity activity,HttpServletRequest request){
			List<Classinfo> clss= classinfoService.selectAllList(null);
			
			List<CoachClassType> list = coachSettingService.listAllClassType(null);
			
			
			SalesActivity one= salesService.getSalesActivity(activity);
			List<SalesActivityClassinfo> result=one.getClassinfo();
			for(SalesActivityClassinfo info:result){
				for(Classinfo cls:clss){
					if(info.getClassinfoid().intValue()==cls.getId()){
						info.setC1flag(cls.getC1flag());
						info.setC2flag(cls.getC2flag());
						
						if(info.getC1price()==null)info.setC1price(0);
						if(info.getC2price()==null)info.setC2price(0);
						
						info.setC1amount(cls.getC1amount());
						info.setC2amount(cls.getC2amount());
						info.setName(cls.getName());
						for(CoachClassType type:list){
							if(type.getId()==cls.getClasstypeid().intValue()){
								info.setClasstype(type.getType());
							}
						}
					}
				}
			}
			ResultBean rb = new ResultBean();
			rb.setResult(new PageInfo<>(result));
			return rb;
		
	}
	
	//新增
	@RequestAction(type=RequestAction.RequestActionType.ADD)
	@RequestMapping(value="/add")
	public ResultBean addSales(SalesActivity activity,HttpServletRequest request){
		
		String data=request.getParameter("classinfos");
		List<SalesActivityClassinfo> classinfo=JSONObject.parseArray(data,SalesActivityClassinfo.class);
		
		activity.setClassinfo(classinfo);
		
		return salesService.addSalesActivity(activity, null);
	}
	
	
	
	//更新
	@RequestMapping(value="/update")
	public ResultBean updateSales(SalesActivity activity,HttpServletRequest request){
		String data=request.getParameter("classinfos");
		
		List<SalesActivityClassinfo> classinfo=JSONObject.parseArray(data,SalesActivityClassinfo.class);
		activity.setClassinfo(classinfo);
		return salesService.updateSalesActivity(activity);
	}
	
	//列表
		@RequestMapping(value="/list")
		public ResultBean getSalesList(SalesActivity activity) {
			List<SalesActivity> list= salesService.listActivity(activity);
			return this.<SalesActivity>buildListResult(list);
		}
		
		@RequestMapping(value="/list/export")
		public ResponseEntity<byte[]> getSalesListExport(SalesActivity activity) throws IOException {
			List<SalesActivity> list= salesService.listActivity(activity);
			return this.export("营销活动数据", "营销活动数据", "导出数据", list, SalesActivity.class);
		}
		
		@RequestMapping(value="/listmatch")
		public ResultBean listMatchSalesList(SalesActivityClassinfo activity) {
			ResultBean resultBean = new ResultBean();
			if (activity.getClassinfoid() == null) {
				return resultBean;
			}
			List<SalesActivity> list= salesService.listMatchActivity(activity);
			SelectDTO [] datas =  new SelectDTO [list.size()];
			for (int i = 0; i < list.size(); i++) {
				SelectDTO dto = new SelectDTO();
				dto.setValue(list.get(i).getId());
				dto.setLabel(list.get(i).getActivityname());
				datas[i] = dto;
			}
			resultBean.setResult(datas);
			return resultBean;
		}
		
		
		/**
		 * 根据活动id 和班别id 获取一个优惠信息
		 * @param activity
		 * @return
		 */
		@RequestMapping(value="/getmatch")
		public ResultBean getMatchSalesClass(SalesActivityClassinfo activity) {
			ResultBean resultBean = new ResultBean();
			SalesActivityClassinfo one= salesService.getMatchActivityClass(activity);
			
			resultBean.setResult(one);
			resultBean.setCode(0);
			return resultBean;
		}
		
		
		@RequestMapping(value="/get")
		public ResultBean getSales(SalesActivity activity){
			SalesActivity market= salesService.getSalesActivity(activity);
			return this.<SalesActivity>buildResult(market);
		}

}
