package cn.com.liliyun.common.util;

import java.util.HashMap;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class MQListener implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		System.out.println("ssssssssssssssssssss init");
		for (MessageExt mes: msgs) {
			HashMap<String, Object> params = SerializableUtil.unserialize(mes.getBody());
			String sql = MQSQLUtil.getSQL(params);
			System.out.println("############################## sql");
			System.out.println(sql);
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
