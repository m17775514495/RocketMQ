package com.ruzhu.rocketmqproducer;

import com.alibaba.fastjson.JSONObject;
import com.ruzhu.rocketmqproducer.config.ProducerConfig;
import com.ruzhu.rocketmqproducer.event.eventEnum.TagsEnum;
import com.ruzhu.rocketmqproducer.pojo.User;
import com.ruzhu.rocketmqproducer.util.RocketMQSend;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class RocketmqProducerApplicationTests {

    @Autowired
    private ProducerConfig producerConfig;

    private  RocketMQSend rocketMQSend;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void test1(){

        /**
         * 主题
         * 内容
         */
        /*rocketMQTemplate.convertAndSend("www:s","hello springboot rocketmq");
        rocketMQTemplate.syncSend("www:s","hello springboot rocketmq");
        log.info("消息发送成功");*/
        //rocketMQTemplateSend.syncProducer();
    }

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("wx");
        //rocketMQTemplate.convertAndSend("springboot-mq",user);
        log.info("消息发送成功");
    }

    @Test
    void test2() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建消息生产者producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("gg");
        //指定nameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //启动producer
        producer.start();
        User wx = new User("wx", 12);
        Object object = wx;
        JSONObject.toJSONString(object);
        Message message = new Message("www","wx",("hello wx").getBytes());
        SendResult response = producer.send(message);
        producer.shutdown();
    }

    @Test
    void testConfig() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
       //System.out.println(rocketMQConfig.getProducer());
        //RocketMQSend.syncProducer(rocketMQConfig.getProducer(),rocketMQConfig.getNameServer(),"www","s",new User("wx",18));
        System.out.println(TagsEnum.OTHERS.getEvent());
        System.out.println(producerConfig);
    }

    @Test
    void test() throws RemotingException, MQClientException, InterruptedException, MQBrokerException {
       // Message message = new Message("base1", "x", JSONObject.toJSONString("hellow").getBytes());
        // 这里用到了这个mq的异步处理，类似ajax，可以得到发送到mq的情况，并做相应的处理
        //不过要注意的是这个是异步的
        /*defaultMQProducer.setProducerGroup("group1");
        System.out.println(defaultMQProducer.getProducerGroup());
        SendResult send = defaultMQProducer.send(message);
        System.out.println(send);*/

        SendResult sendResult = rocketMQSend.syncSend(TagsEnum.URGENT.getEvent(), "1001", TagsEnum.URGENT.getEvent(), "我是紧急事件 房间号1001");
        System.out.println(sendResult);
    }

    @Test
    void test3() throws RemotingException, MQClientException, InterruptedException, MQBrokerException {

        SendResult sendResult = rocketMQSend.syncSend("equipment", "equipment",  "others", "newequipment");
        System.out.println(sendResult);
    }
}
