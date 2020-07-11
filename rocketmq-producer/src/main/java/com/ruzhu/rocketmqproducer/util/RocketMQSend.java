package com.ruzhu.rocketmqproducer.util;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * MQ发util
 */
@Component
public class RocketMQSend {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    private static RocketMQSend rocketMQSend;

    @PostConstruct
    public void init() {
        rocketMQSend = this;
    }

    /**
     * 同步消息
     * @param group 组名  组名和主题保持一致
     * @param topic 主题
     * @param tag   标签
     * @param msg   消息
     * @return
     * @throws MQClientException  MQ客户端异常
     * @throws RemotingException  远程异常
     * @throws InterruptedException 中断异常
     * @throws MQBrokerException  MQ Broker异常
     */
    public static SendResult syncSend(String group, String topic, String tag, Object msg ) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        rocketMQSend.defaultMQProducer.setProducerGroup(group);

        Message message = new Message(topic, tag, JsonTransformation.transformationObject(msg).getBytes());

         //发送消息  同步队列 有返回
         SendResult response = rocketMQSend.defaultMQProducer.send(message);

         return response;
    }

    //默认group
    public static SendResult syncDefaultSend(String groupAndTopic, String tag, Object msg ) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        Message message = new Message(groupAndTopic, tag, JsonTransformation.transformationObject(msg).getBytes());

        rocketMQSend.defaultMQProducer.setProducerGroup(groupAndTopic);

        //发送消息  同步队列 有返回
        SendResult response = rocketMQSend.defaultMQProducer.send(message);

        return response;
    }

    //默认group
    public static SendResult syncSend(String topic, String tag, Object msg ) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        Message message = new Message(topic, tag, JsonTransformation.transformationObject(msg).getBytes());

        //发送消息  同步队列 有返回
        SendResult response = rocketMQSend.defaultMQProducer.send(message);

        return response;
    }

    //单向消息
    public static void sendOneway(String group, String topic, String tag, Object msg ) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        Message message = new Message(topic, tag, JsonTransformation.transformationObject(msg).getBytes());

        rocketMQSend.defaultMQProducer.setProducerGroup(group);

        //发送消息
        rocketMQSend.defaultMQProducer.sendOneway(message);
    }

    //异步消息
    public static void sendAsyn(String group, String topic, String tag, Object msg ) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        Message message = new Message(topic, tag, JsonTransformation.transformationObject(msg).getBytes());

        rocketMQSend.defaultMQProducer.setProducerGroup(group);

        //发送消息
        rocketMQSend.defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {
            }
        });
    }
}

