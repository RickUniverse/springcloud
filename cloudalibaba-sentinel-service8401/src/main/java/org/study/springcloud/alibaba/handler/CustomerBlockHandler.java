package org.study.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.study.springcloud.entities.CommonResult;
import org.study.springcloud.entities.Payment;

/**
 * 全局限流处理
 * @author lijichen
 * @date 2021/2/17 - 14:50
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return  new CommonResult(444,"按照客户自定义的Glogal 全局异常处理 ---- 1",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return  new CommonResult(444,"按照客户自定义的Glogal 全局异常处理 ---- 2",new Payment(2020L,"serial003"));
    }

    public static String handlerException3(BlockException exception) {
        return "w";
    }
}
