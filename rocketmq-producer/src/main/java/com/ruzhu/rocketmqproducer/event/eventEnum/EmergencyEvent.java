package com.ruzhu.rocketmqproducer.event.eventEnum;

import com.ruzhu.rocketmqproducer.event.eventEnum.eventInterface.EventInterface;

/**
 * 紧急事件
 */
public enum EmergencyEvent implements EventInterface {
    ;
    private String bizCode;
    private String describe;

    @Override
    public String getEvent() {
        return this.bizCode;
    }
}
