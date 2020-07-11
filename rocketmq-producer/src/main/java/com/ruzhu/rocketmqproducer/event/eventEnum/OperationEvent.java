package com.ruzhu.rocketmqproducer.event.eventEnum;

import com.ruzhu.rocketmqproducer.event.eventEnum.eventInterface.EventInterface;

/**
 * 操作事件
 */
public enum OperationEvent implements EventInterface {
    ONLINE("online","设备上线"),
    OFFLINE("offline","设备离线"),
    NAMEUPDATE("nameUpdate","修改设备名称"),
    DPNAMEUPDATE("dpNameUpdate","修改设备功能点名称"),
    BINDUSER("bindUser","设备绑定用户"),
    DELETE("delete","删除设备"),
    UPGRADESTATUS("upgradeStatus","设备升级状态");

    private String bizCode;
    private String describe;

    /**
     *
     * @param bizCode bizCode
     * @param describe 描述
     */
    OperationEvent(String bizCode, String describe){
        this.bizCode = bizCode;
        this.describe = describe;
    }

    @Override
    public String getEvent() {
        return this.bizCode;
    }
}
