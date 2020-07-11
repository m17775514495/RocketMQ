package com.ruzhu.rocketmqconsumer.controller;

import com.ruzhu.rocketmqconsumer.pojo.RocketMQVO;
import com.ruzhu.rocketmqconsumer.service.RocketMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@Controller
@RequestMapping("/mq")
public class SubscribeMQController {

    @Autowired
    private RocketMQService rocketMQService;

    @RequestMapping(value = "/dynamicSub", method = RequestMethod.POST)
    @ResponseBody
    public String dynamicSub(@ModelAttribute RocketMQVO rocketMQVO)  {

        HashSet<String> set = new HashSet<>();
        set.contains("s");

        rocketMQVO.setGroup("others");
        rocketMQVO.setTopic("newTopic2");
        rocketMQVO.setTag("others");

        String result = rocketMQService.dynamicSub(rocketMQVO);
        return "ok";
    }

    /**
     * 退订
     * @param rocketMQVO
     * @return
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.DELETE)
    @ResponseBody
    public String unsubscribe(@ModelAttribute RocketMQVO rocketMQVO){
        String result = rocketMQService.unsubscribe(rocketMQVO);
        return result;
    }
}
