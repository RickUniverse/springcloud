package org.study.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lijichen
 * @date 2021/2/17 - 14:51
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }

    /**
     * RT: 200ms    时间窗口：3s
     * 压测：一秒10个请求
     * 官网要求，一秒内进入5个请求以上（我是10个请求达到），每个请求的相应时间需要是RT设置的200ms以内，
     *      我TimeUnit.SECONDS.sleep(1); 达到了，就会触发熔断，在设置的时间窗口3秒内不会接收任何请求（熔断打开）
     *      三秒后熔断结束，没有half open 半开状态
     * @return
     */
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "----testD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常数");
        int age = 10 / 0;
        return "----testE 测试异常数";
    }

    /**
     * @SentinelResource 开启热点key
     *      value = "testHotKey" 对应的是，配置时的资源路径跟，value = "testHotKey" 一致,没有 / 线
     *      blockHandler = "deal_testHotKey" 出错后调用的handler，如果去掉就返回error page
     * 实例：
     *      资源名：testHotKey  参数索引：0 （表示第一个参数p1），单机阈值：1（qps次数），统计窗口时常：1（表示统计时间）
     *
     *      连起来则为：如果请求了：http://localhost:8401/testHotKey?p1=a&p2=b  只要带有p1=xxx
     *          如果是一秒钟请求了一次则正常显示，如果一秒内访问了超过单机阈值就
     *          执行 blockHandler = "deal_testHotKey" 方法
     * 参数例外项：
     *      参数类型（只能是8 种基本数据类型）：java.long.String, 参数值：5 限流阈值：200
     *
     *      表示：如果p1=5 的话，阈值就变成了：200
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        return "----testHotKey";
    }

    // 出现错误后的方法
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "----deal_testHotKey, o(╥﹏╥)o" + exception.getMessage(); // sentinel的默认提示都是： Blocked by Sentinel (flow limiting)
    }
}
