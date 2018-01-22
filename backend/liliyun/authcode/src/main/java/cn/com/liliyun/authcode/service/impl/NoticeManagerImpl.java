package cn.com.liliyun.authcode.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import cn.com.liliyun.authcode.service.NoticeManager;
import cn.com.liliyun.common.model.ResultBean;

public class NoticeManagerImpl implements NoticeManager {
	private static Logger logger = LoggerFactory.getLogger(NoticeManagerImpl.class);

//	@Autowired
//	NoticeMapper noticeMapper;
//
//	@Autowired
//	NoticeVoMapper noticeVoMapper;

//	@Resource(name = "jpushProducer")
//	DefaultMQProducer jpushProducer;

	@Override
	public ResultBean getNoticesByUserId(String userId, String schoolId, String pageNo, String pageSize) {
		return null;
	}


}
