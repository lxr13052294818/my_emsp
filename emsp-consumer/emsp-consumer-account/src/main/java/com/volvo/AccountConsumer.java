package com.volvo;

import com.volvo.constant.AccountMsgConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/3/2
 */
@RocketMQMessageListener(topic = AccountMsgConstant.ACCOUNT_TOPIC, consumerGroup = AccountMsgConstant.ACCOUNT_CONSUMER_GROUP)
@Service
public class AccountConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        // 处理消息
        System.out.println("Received message=======: " + message);
    }
}
