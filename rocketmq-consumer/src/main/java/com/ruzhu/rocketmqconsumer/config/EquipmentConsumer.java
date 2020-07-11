package com.ruzhu.rocketmqconsumer.config;

import com.ruzhu.rocketmqconsumer.config.common.DefaultConsumerConfigure;
import com.ruzhu.rocketmqconsumer.config.common.MessageTypeConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Log4j2
@Configuration
public class EquipmentConsumer extends DefaultConsumerConfigure implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MessageTypeConfig messageTypeConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        try {
            super.listenerProperties(messageTypeConfig.getEquipment(),messageTypeConfig.getEquipment(), MessageModel.CLUSTERING);
        } catch (MQClientException e) {
            log.error("消费者监听器启动失败", e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs)  {
        log.info("进入equipment消费者");
        for(MessageExt msg : msgs) {
            try {
                String msgStr = new String(msg.getBody(), "utf-8");
                log.info(msgStr);
            } catch (UnsupportedEncodingException e) {
                log.error("body转字符串解析失败");
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
