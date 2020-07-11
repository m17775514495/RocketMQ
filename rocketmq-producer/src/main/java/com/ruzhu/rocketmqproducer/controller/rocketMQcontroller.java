package com.ruzhu.rocketmqproducer.controller;

import com.ruzhu.rocketmqproducer.event.eventEnum.TagsEnum;
import com.ruzhu.rocketmqproducer.util.RocketMQSend;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mq")
@Controller

public class rocketMQcontroller {

    private RocketMQSend rocketMQSend;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @PostMapping("/test")
    public String  producer(String group, String topic, String tag, Object msg){
        System.out.println(group);
        System.out.println(topic);
        System.out.println(tag);

        try {
            SendResult sendResult = rocketMQSend.syncSend(group, topic, tag, msg);
            System.out.println(sendResult);
        }catch (Exception e){

        }
        return "ok";
    }
}
