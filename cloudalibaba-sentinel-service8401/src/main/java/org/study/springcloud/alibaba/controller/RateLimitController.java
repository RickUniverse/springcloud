package org.study.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.springcloud.alibaba.handler.CustomerBlockHandler;
import org.study.springcloud.entities.CommonResult;
import org.study.springcloud.entities.Payment;

/**
 * @author lijichen
 * @date 2021/2/17 - 14:51
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return  new CommonResult(200,"按照资源名称限流测试",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return  new CommonResult(444,exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    // 也可以按照 /rateLimit/byUrl 进行限流
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return  new CommonResult(200,"按照byUrl限流测试",new Payment(2020L,"serial002"));
    }

    //CustomerBlockHandler
    // SentinelResource 不支持private
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            /**
             * CustomerBlockHandler.class 这个类中配置的都是全局限流异常方法
             * blockHandler = "handlerException2" 调用那个方法
             *
             * 注意
             * 必须使用 customerBlockHandler 进行限流，不能使用 /rateLimit/customerBlockHandler
             * 返回类型要一致 必须是： CommonResult ！！！
            */
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return  new CommonResult(200,"按照客户自定义限流测试-",new Payment(2020L,"serial003"));
    }
}
