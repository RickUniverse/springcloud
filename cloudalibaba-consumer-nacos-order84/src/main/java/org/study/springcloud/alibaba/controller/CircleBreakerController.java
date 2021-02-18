package org.study.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.study.springcloud.alibaba.service.PaymentService;
import org.study.springcloud.entities.CommonResult;
import org.study.springcloud.entities.Payment;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lijichen
 * @date 2021/2/17 - 19:04
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static  final  String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback",fallback ="handlerFallback") // fallback只负责业务异常
    // blockHandler 处理sentinel违规异常
    @SentinelResource(value = "fallback",
            fallback ="handlerFallback", /*fallbackClass = */
            blockHandler = "blockHandler" /*, blockHandlerClass = */,

            // 忽略 blockHandler 异常
            exceptionsToIgnore = {IOException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class,id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }

        return  result;
    }


    // 处理：@SentinelResource(value = "fallback",fallback ="handlerFallback") 服务降级，友好处理
    // Throwable e 可以获取异常信息
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }


    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }

    //======= OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult< Payment > paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
