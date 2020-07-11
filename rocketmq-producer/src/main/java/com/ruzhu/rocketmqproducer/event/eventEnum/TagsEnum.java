package com.ruzhu.rocketmqproducer.event.eventEnum;

import com.ruzhu.rocketmqproducer.event.eventEnum.eventInterface.EventInterface;

/**
 *  消息类型
 */
public enum TagsEnum implements EventInterface {
    URGENT("urgent","紧急事件"),
    OTHERS("others","其它事件");

    private String tagName;
    private String describe;

    TagsEnum(String tagName, String describe){
        this.tagName = tagName;
        this.describe = describe;
    }

    @Override
    public String getEvent() {
        return this.tagName;
    }
}
