package com.ruzhu.rocketmqconsumer.config.common;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Log4j2
public abstract class DefaultConsumerConfigure {

    @Autowired
    private ConsumerConfig consumerConfig;

    // 开启消费者监听服务(配置文件)
    public DefaultMQPushConsumer listenerProperties(String group, String topic, MessageModel messageModel) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);

        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());

        consumer.setMessageModel(messageModel);

        consumer.subscribe(topic,"*");

        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                return DefaultConsumerConfigure.this.dealBody(msgs);
            }
        });

        consumer.start();

        log.info(group);
        log.info("rocketmq启动成功---------------------------------------");
        manageConsumers(group,consumer);
        return consumer;
    }

    // 处理body的业务
    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);

    //将当前consumer对象管理
    public static void manageConsumers(String group, DefaultMQPushConsumer defaultMQPullConsumer){

       ManageConsumers.getInstace().put(group, defaultMQPullConsumer);
       log.info(ManageConsumers.getInstace()+"--------------------------------------");
    }
}