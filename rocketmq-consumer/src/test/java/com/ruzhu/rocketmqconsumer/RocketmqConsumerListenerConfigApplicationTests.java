package com.ruzhu.rocketmqconsumer;


import com.ruzhu.rocketmqconsumer.config.common.MessageTypeConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class RocketmqConsumerListenerConfigApplicationTests {

    @Autowired
    private MessageTypeConfig messageTypeConfig;


    @Test
    void contextLoads() {
       /* System.out.println(otherSettingConfig.getGroup());*/
    }

    /**
     * 手动订阅
     */
    @Test
    void test4(){
        System.out.println(messageTypeConfig);
    }
}
