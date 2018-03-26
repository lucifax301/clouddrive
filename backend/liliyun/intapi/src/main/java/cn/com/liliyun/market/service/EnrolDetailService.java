package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.EnrolDetail;
import cn.com.liliyun.market.model.EnrolDetailParam;
import cn.com.liliyun.user.model.User;

public interface EnrolDetailService {

	public List<EnrolDetail> stat(EnrolDetailParam param);
}
