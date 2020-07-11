package com.ruzhu.rocketmqproducer.util;

import com.alibaba.fastjson.JSONObject;

public class JsonTransformation {

    public static String transformationObject(Object object){
        return JSONObject.toJSONString(object);
    }
}
