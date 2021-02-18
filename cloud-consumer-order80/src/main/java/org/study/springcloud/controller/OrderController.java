package org.study.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.study.springcloud.entities.CommonResult;
import org.study.springcloud.entities.Payment;
import org.study.springcloud.lb.LoadBalancer;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author lijichen
 * @date 2021/2/12 - 14:40
 */
@RestController
@Slf4j
public class OrderController {

    // 通过供给者的controller 地址远程调用获取
//    public static final String PAYMENT_URL = "http://localhost:8001";
    // 集群
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    // 可能导错包！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult< Payment > getPayment(@PathVariable("id") Long id) {
        /**
         * getForObject 是直接获取json字符串
         */
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult< Payment > getPayment2(@PathVariable("id") Long id) {
        /**
         * forEntity 更多详细信息，状态码，头信息等等。。
         */
        ResponseEntity<CommonResult> forEntity =
                restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getHeaders());

        if(forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<>(444,"操作失败！");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        // 通过容器中的 discoveryClient和服务名来获取服务集群
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0) {
            return null;
        }
        // 传入服务集群来计算出获取具体的服务实例
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return  restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @GetMapping(value="/consumer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject( "http://localhost:8001/payment/zipkin/",String.class);
    }
}
