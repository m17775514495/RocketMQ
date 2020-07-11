package com.ruzhu.rocketmqproducer.expection.expectionEnum;

/**
 * MQ异常
 */
public enum RocketMQErrCode {
    MQ_CLIENT_EXCEPTION(10001,"MQ客户端异常"),
    MQ_REMOTING_EXCEPTION(10002,"远程异常"),
    MQ_INTERRUPTED_EXCEPTION(10003,"中断异常"),
    MQ_BROKER_EXCEPTION(10003,"MQ Broker异常");

    private int code;
    private String msg;

    RocketMQErrCode(int code, String msg){
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}