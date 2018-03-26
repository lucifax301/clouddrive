package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.EnrolChannelStat;
import cn.com.liliyun.market.model.EnrolClassStat;
import cn.com.liliyun.market.model.EnrolDetailParam;
import cn.com.liliyun.user.model.User;

public interface EnrolStatService {

	public List<EnrolClassStat> statByClass(EnrolDetailParam param);
	
	public List<EnrolChannelStat> statByChannel(EnrolDetailParam param);
}
