package com.ruzhu.rocketmqconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RocketMQVO {
    private String group;
    private String topic;
    private String tag;
}
