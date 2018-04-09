package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.EnrolDetail;
import cn.com.liliyun.market.model.EnrolDetailParam;

public interface EnrolDetailService {

	List<EnrolDetail> stat(EnrolDetailParam param);
}
