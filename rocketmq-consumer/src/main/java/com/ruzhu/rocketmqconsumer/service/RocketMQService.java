package com.ruzhu.rocketmqconsumer.service;

import com.ruzhu.rocketmqconsumer.pojo.RocketMQVO;

public interface RocketMQService {


    String dynamicSub(RocketMQVO rocketMQVO);

    String unsubscribe(RocketMQVO rocketMQVO);
}
