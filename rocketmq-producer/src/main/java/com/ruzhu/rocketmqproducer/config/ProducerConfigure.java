package com.ruzhu.rocketmqproducer.config;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者注入
 */
@Log4j2
@Configuration
public class ProducerConfigure {

    @Autowired
    private ProducerConfig producerConfig;

    /**
     * 创建普通消息发送者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQProducer defaultProducer() throws MQClientException {
        log.info("defaultProducer 正在创建---------------------------------------");
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server开启成功---------------------------------.");
        return producer;
    }

}
