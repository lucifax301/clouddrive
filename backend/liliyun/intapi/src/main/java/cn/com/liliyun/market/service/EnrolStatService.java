package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.EnrolChannelStat;
import cn.com.liliyun.market.model.EnrolClassStat;
import cn.com.liliyun.market.model.EnrolDetailParam;

public interface EnrolStatService {

	List<EnrolClassStat> statByClass(EnrolDetailParam param);
	
	List<EnrolChannelStat> statByChannel(EnrolDetailParam param);
}
