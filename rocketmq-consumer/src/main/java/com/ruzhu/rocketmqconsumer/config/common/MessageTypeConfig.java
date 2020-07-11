package com.ruzhu.rocketmqconsumer.config.common;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ToString
@ConfigurationProperties(prefix = "message.type")
@PropertySource("classpath:messageType.properties")
@Data
public class MessageTypeConfig {
    private String ordinary;
    private String sos;
    private String equipment;
}  