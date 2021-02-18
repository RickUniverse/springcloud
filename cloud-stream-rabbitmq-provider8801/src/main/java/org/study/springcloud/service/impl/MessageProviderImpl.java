package org.study.springcloud.service.impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.study.springcloud.service.IMessageProvider;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author lijichen
 * @date 2021/2/16 - 15:28
 */
// import org.springframework.cloud.stream.messaging.Source;
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        // import org.springframework.integration.support.MessageBuilder;
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "  +serial);
        return null;
    }
}
