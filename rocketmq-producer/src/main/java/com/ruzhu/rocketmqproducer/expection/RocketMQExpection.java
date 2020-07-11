package com.ruzhu.rocketmqproducer.expection;

import com.ruzhu.rocketmqproducer.expection.expectionEnum.RocketMQErrCode;

public class RocketMQExpection extends RuntimeException {
    private int code;

    public RocketMQExpection(String message, RocketMQErrCode rocketMQErrCode) {
        this.code = rocketMQErrCode.getCode();

    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
