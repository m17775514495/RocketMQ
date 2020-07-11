package com.ruzhu.rocketmqconsumer.service.impl;

import com.ruzhu.rocketmqconsumer.config.common.ManageConsumers;
import com.ruzhu.rocketmqconsumer.pojo.RocketMQVO;
import com.ruzhu.rocketmqconsumer.service.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RocketMQServiceImpl implements  RocketMQService {

    @Override
    public String dynamicSub(RocketMQVO rocketMQVO) {

        ConcurrentHashMap<String,DefaultMQPushConsumer> instace = ManageConsumers.getInstace();
        DefaultMQPushConsumer consumer = instace.get(rocketMQVO.getGroup());

        if (consumer == null){
            log.info("未开启该订阅组");
            return "未开启该订阅组";
        }

        if (rocketMQVO.getTopic() == null){
            log.info("topic为null");
            return "topic为null";
        }

        try {
           if (rocketMQVO.getTag() == null){
               log.info("tag为null");
               consumer.subscribe(rocketMQVO.getTopic(), "*");

        } else {
            consumer.subscribe(rocketMQVO.getTopic(),rocketMQVO.getTag());
           }
         } catch (MQClientException e) {

            log.info("订阅失败");
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    public String unsubscribe(RocketMQVO rocketMQVO) {
        //获取消费者组
        ConcurrentHashMap<String,DefaultMQPushConsumer> instace = ManageConsumers.getInstace();
        DefaultMQPushConsumer consumer = instace.get(rocketMQVO.getGroup());
        if (consumer == null){
            log.info("未开启该订阅组");
            return "未开启该订阅组";
        }

        if (rocketMQVO.getTopic() == null) {
            log.info("topic为null");
            return "topic为null";
        }

        consumer.unsubscribe(rocketMQVO.getTopic());

        return "ok";
    }

   /* public DefaultMQPushConsumer addsubGroup(RocketMQVO rocketMQVO) throws MQClientException {
        log.info(rocketMQVO.getGroup());
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQVO.getGroup());

        consumer.setNamesrvAddr(rocketMQVO.getGroup());

        //consumer.setMessageModel(MessageModel.BROADCASTING);


        consumer.subscribe(rocketMQVO.getTopic(), rocketMQVO.getTag());

        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                log.info("进入新创group");
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
        });

        consumer.start();

        log.info("rocketmq启动成功---------------------------------------" + rocketMQVO.getGroup());
       // manageConsumers(rocketMQVO.getGroup(),consumer);
        return consumer;
    }*/

}
