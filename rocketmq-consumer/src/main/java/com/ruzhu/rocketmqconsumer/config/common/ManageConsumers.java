package com.ruzhu.rocketmqconsumer.config.common;

import lombok.Data;
import lombok.ToString;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

import java.util.concurrent.ConcurrentHashMap;

@Data
@ToString
public class ManageConsumers {
    private volatile static ConcurrentHashMap<String,DefaultMQPushConsumer> manageConsumers;

    private static Integer lock = 1;

    public static ConcurrentHashMap getInstace(){
        if (manageConsumers == null){
            synchronized (lock){
                if (manageConsumers == null) manageConsumers = new ConcurrentHashMap<>();
            }
        }
      return manageConsumers;
    }
}
