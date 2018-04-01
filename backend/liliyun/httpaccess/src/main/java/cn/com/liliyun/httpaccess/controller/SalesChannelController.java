package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.annotation.ActionDescription;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.market.service.SalesChannelService;

import com.alibaba.fastjson.JSONObject;

@Controller
@ResponseBody
@RequestMapping(value="/saleschannel")
public class SalesChannelController extends BaseController{
	
	
	@Autowired
	private SalesChannelService salesChannelService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResultBean listChannel(SalesChannel channel) {
		List<SalesChannel> list = salesChannelService.selectChannels(channel);
		return this.<SalesChannel>buildListResult(list);
	}
	
	@RequestMapping(value="/listall")
	@ResponseBody
	public ResultBean listAllChannel(SalesChannel channel) {
		List<SalesChannel> list = salesChannelService.selectAllChannels(channel);
		return this.<SalesChannel>buildListResult(list);
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ResultBean addChannel(HttpServletRequest request){
		
			String channel=request.getParameter("channel");
			String coachflag=request.getParameter("coachflag");
			String staffflag=request.getParameter("staffflag");
			String data=request.getParameter("channels");
			List<SalesChannel> channels=JSONObject.parseArray(data,SalesChannel.class);
			SalesChannel cct=new SalesChannel();
			cct.setChannel(channel);
			
			cct.setData(channels);
			if(coachflag!=null&&!"".equals(coachflag)){
				cct.setCoachflag(Integer.parseInt(coachflag));
			}
			if(staffflag!=null&&!"".equals(staffflag)){
				cct.setStaffflag(Integer.parseInt(staffflag));
			}
			
		salesChannelService.addChannel(cct);
		return new ResultBean();
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultBean deleteChannel(SalesChannel channel) {
		return salesChannelService.delChannel(channel);
		
	}
	
	@ActionDescription(description="",error="更新渠道出错")
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultBean updateChannel(HttpServletRequest request) {
		String channel=request.getParameter("channel");
			String data=request.getParameter("channels");
			String coachflag=request.getParameter("coachflag");
			String staffflag=request.getParameter("staffflag");
			String id=request.getParameter("id");
			List<SalesChannel> classinfo=JSONObject.parseArray(data,SalesChannel.class);
			SalesChannel cct=new SalesChannel();
			cct.setChannel(channel);
			
			cct.setData(classinfo);
			cct.setId(Integer.parseInt(id));
			if(coachflag!=null&&!"".equals(coachflag)){
				cct.setCoachflag(Integer.parseInt(coachflag));
			}
			if(staffflag!=null&&!"".equals(staffflag)){
				cct.setStaffflag(Integer.parseInt(staffflag));
			}
			
			salesChannelService.updateChannel(cct);
			return new ResultBean();
		
		
	}
	
	@RequestMapping(value="/updateChannelStatus")
	@ResponseBody
	public ResultBean updateChannelStatus(SalesChannel channel,HttpServletRequest request) {
		return	salesChannelService.updateChannelStatus(channel);
		
	}
}
