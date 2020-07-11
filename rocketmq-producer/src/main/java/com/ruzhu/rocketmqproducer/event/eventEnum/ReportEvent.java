package com.ruzhu.rocketmqproducer.event.eventEnum;

import com.ruzhu.rocketmqproducer.event.eventEnum.eventInterface.EventInterface;

/**
 * 上报事件
 */
public enum ReportEvent implements EventInterface {
    online("online","设备上线"),
    offline("offline","设备离线"),
    nameUpdate("nameUpdate","修改设备名称"),
    dpNameUpdate("dpNameUpdate","修改设备功能点名称"),
    bindUser("bindUser","设备绑定用户"),
    delete("delete","删除设备"),
    upgradeStatus("upgradeStatus","设备升级状态");

    private String bizCode;
    private String describe;

    ReportEvent(String bizCode, String describe){
        this.bizCode = bizCode;
        this.describe = describe;
    }

    @Override
    public String getEvent(){
        return this.bizCode;
    }

    /**
     *  根据事件名称返回事件
     */
}
