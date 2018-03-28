package cn.com.liliyun.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.CommonService;
import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.market.mapper.SalesChannelMapper;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.market.service.SalesChannelService;
import cn.com.liliyun.user.model.User;

@Service
public class SalesChannelServiceImpl extends CommonService implements SalesChannelService {
	
	private static Logger logger=Logger.getLogger(SalesChannelServiceImpl.class);
	
	
	@Autowired
	private SalesChannelMapper salesChannelMapper;
	
	//private StudentService studentService;
	
	@Override
	public ResultBean addChannel(SalesChannel channel) {
		User user = RequestContext.<User>get(ConstantUtil.USER_SESSION);
		
		channel.setUserid(user.getId());
		channel.setCuser(user.getUsername());
		channel.setParentid(0);
		channel.setStatus(0);
		
		salesChannelMapper.addChannel(channel);
		List<SalesChannel> channels= channel.getData();
		if(channels!=null&&channels.size()>0){
			for(SalesChannel info:channels){
				info.setParentid(channel.getId());
				info.setUserid(user.getId());
				info.setCuser(user.getUsername());
			}
				
			Map params=new HashMap();
			params.put("list", channels);
			
			salesChannelMapper.batchAddChannel(params);
		}
			
		return new ResultBean();
		
	}

	@Override
	public ResultBean updateChannel(SalesChannel channel) {
		User user = RequestContext.<User>get(ConstantUtil.USER_SESSION);
		channel.setUserid(user.getId());
		SalesChannel oldone= salesChannelMapper.getChannel(channel);
		
		salesChannelMapper.updateChannel(channel);
		List<SalesChannel> classinfo= channel.getData();
		SalesChannel cf=new SalesChannel();
		cf.setParentid(channel.getId());
		
		List<SalesChannel> eclassinfo=salesChannelMapper.listChannel(cf);
		
		List<SalesChannel> updates=new ArrayList();
		List<SalesChannel> addes=new ArrayList();
		List<SalesChannel> deles=new ArrayList();
		for(SalesChannel e: eclassinfo){
			boolean find=false;
			for(SalesChannel info:classinfo){
				
				if(info.getId()!=null&&e.getId().intValue()==info.getId().intValue()){
					find=true;
					if(e.getStatus().intValue()!=info.getStatus().intValue()||!e.getChannel().equals(info.getChannel())){
						updates.add(info);
					}else{
						if((e.getCoachflag()==null&&info.getCoachflag()!=null)||(e.getCoachflag()!=null&&info.getCoachflag()==null)){
							updates.add(info);
						}else if((e.getStaffflag()==null&&info.getStaffflag()!=null)||(e.getStaffflag()!=null&&info.getStaffflag()==null)){
							updates.add(info);
						}
					}
				}
			}
			
			if(!find){
				deles.add(e);
			}
		}
		
		for(SalesChannel info:classinfo){
			if(info.getId()==null){
				addes.add(info);
				info.setParentid(channel.getId());
				info.setUserid(user.getId());
				info.setCuser(user.getUsername());
			}
		}
		
		if(addes.size()>0){
			Map params=new HashMap();
			params.put("list", addes);
			
			salesChannelMapper.batchAddChannel(params);
		}
		if(deles.size()>0){
			Map params=new HashMap();
			params.put("list", deles);
			
			salesChannelMapper.batchDelChannel(params);
		}
		if(updates.size()>0){
			for(SalesChannel u:  updates){
				salesChannelMapper.updateChannel(u);
			}
		}
		
		return new ResultBean();
	}

	@Override
	public ResultBean updateChannelStatus(SalesChannel channel) {
		
		salesChannelMapper.updateChannelStatus(channel);
		return new ResultBean();
	}

	@Override
	public ResultBean delChannel(SalesChannel channel) {
		if(channel.getIds()!=null){
			String ids[]=channel.getIds().split(",");
			for(String id:ids){
				SalesChannel sc=new SalesChannel();
				sc.setId(Integer.parseInt(id));
				sc.setDblink(channel.getDblink());
				SalesChannel exist=salesChannelMapper.getChannel(sc);
				salesChannelMapper.delChannel(sc);
				if(exist!=null){
					//删除二级渠道，如果当前就是二级渠道,子渠道为空
					SalesChannel cf=new SalesChannel();
					cf.setParentid(exist.getId());
					cf.setDblink(channel.getDblink());
					List<SalesChannel> eclassinfo=salesChannelMapper.listChannel(cf);
					if(eclassinfo.size()>0){
						Map params=new HashMap();
						params.put("list", eclassinfo);
						params.put("dblink", channel.getDblink());
						salesChannelMapper.batchDelChannel(params);
					}
				}
			}
		}else{
			SalesChannel exist=salesChannelMapper.getChannel(channel);
			salesChannelMapper.delChannel(channel);
			if(exist!=null){
				//删除二级渠道，如果当前就是二级渠道,子渠道为空
				SalesChannel cf=new SalesChannel();
				cf.setParentid(channel.getId());
				cf.setDblink(channel.getDblink());
				List<SalesChannel> eclassinfo=salesChannelMapper.listChannel(cf);
				if(eclassinfo.size()>0){
					Map params=new HashMap();
					params.put("list", eclassinfo);
					params.put("dblink", channel.getDblink());
					salesChannelMapper.batchDelChannel(params);
				}
			}
		}
		return new ResultBean();
	}

	@Override
	public List<SalesChannel> selectChannels(SalesChannel channel) {
		PageUtil.startPage(channel);
		List<SalesChannel> list= salesChannelMapper.listChannel(channel);
		
		return list;
	}
	
	public List<SalesChannel> selectAllChannels(SalesChannel channel) {
		//PageUtil.startPage(channel);
		List<SalesChannel> list= salesChannelMapper.listChannel(channel);
		SalesChannel sc= new SalesChannel();
		sc.setDblink(channel.getDblink());
		List<SalesChannel> allchannels= salesChannelMapper.listChannel(sc);
		List<SalesChannel> result=new ArrayList();
		for(SalesChannel c:list){
			for(SalesChannel c2:allchannels){
				if(c2.getParentid()!=null&& c.getId().intValue()==c2.getParentid()){
					if(c.getData()==null){
						c.setData(new ArrayList());
					}
					c.getData().add(c2);
				}
			}
			
		}
		
		return list;
	}

	@Override
	public SalesChannel getChannel(SalesChannel channel) {
		
		return salesChannelMapper.getChannel(channel);
	}

}
