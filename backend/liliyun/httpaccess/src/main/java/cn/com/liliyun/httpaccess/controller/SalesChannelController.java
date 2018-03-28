package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.market.service.SalesChannelService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/saleschannel")
public class SalesChannelController extends BaseController{

	private static Logger logger=Logger.getLogger(SalesChannelController.class);
	
	@Autowired
	private SalesChannelService salesChannelService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResultBean listChannel(SalesChannel channel) {
		ResultBean rb = new ResultBean();
		try{
			List<SalesChannel> list = salesChannelService.selectChannels(channel);
			rb.setResult(new PageInfo<>(list));
		}catch(Exception ex){
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/listall")
	@ResponseBody
	public ResultBean listAllChannel(SalesChannel channel) {
		ResultBean rb = new ResultBean();
		try{
			List<SalesChannel> list = salesChannelService.selectAllChannels(channel);
			rb.setResult(new PageInfo<>(list));
		}catch(Exception ex){
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ResultBean addChannel(HttpServletRequest request){
		ResultBean rb = new ResultBean();
		try{
			
			
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
		}catch(Exception ex){
			logger.error(ex);
			ex.printStackTrace();
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultBean deleteChannel(SalesChannel channel) {
		ResultBean rb = null;
		try{
			rb=salesChannelService.delChannel(channel);
		}catch(Exception ex){
			ex.printStackTrace();
			rb=new ResultBean();
			rb.setCode(1);
		}
		return rb;
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultBean updateChannel(HttpServletRequest request) {
		
		try{
			
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
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb = new ResultBean("更新渠道出错");
			return rb;
		}
		
	}
	
	@RequestMapping(value="/updateChannelStatus")
	@ResponseBody
	public ResultBean updateChannelStatus(SalesChannel channel,HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		try{
			
			salesChannelService.updateChannelStatus(channel);
		}catch(Exception ex){
			rb.setCode(1);
		}
		return rb;
	}
}
