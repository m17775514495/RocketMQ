package com.ruzhu.rocketmqproducer.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取yml文件中配置类
 */
@Component
@ConfigurationProperties(prefix = "rocketmq.producer")
@Data
@ToString
public class ProducerConfig {
    private String namesrvAddr;
    private String groupName;
}
