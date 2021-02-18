package org.study.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.springcloud.service.IMessageProvider;

import javax.annotation.Resource;

/**
 * @author lijichen
 * @date 2021/2/16 - 15:30
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
