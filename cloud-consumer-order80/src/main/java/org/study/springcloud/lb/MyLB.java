package org.study.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijichen
 * @date 2021/2/13 - 17:46
 */
@Component
public class MyLB implements LoadBalancer {
    // 用来实现自检锁（ACS）
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // 进行自检
    public final int getAndIncrement() {
        int current;
        int next;

        do {
            // 先获取执行前的值
            current = this.atomicInteger.get();
            // 比较当前值是否大于Integer最大值如果大于就从零开始计数，如果不大于就加1
            next = current >= 2147483647 ? 0 : current + 1;
            // 进行自检检查，如果next 跟 current = atomicInteger.get(); // 我的理解，可能理解错了，还没有学CAS
            //      获取的值一致 atomicInteger.compareAndSet(current,next) 就返回false, new AtomicInteger(0); 值不会自增
            //      反过来返回true ，就会进行更改为 next 的值，然后取反跳出循环
        } while (!this.atomicInteger.compareAndSet(current,next));

        System.out.println("****第几次访问，次数next: " + next);

        return next;
    }

    // 负载均衡轮询算法，rest接口第几次请求数 % 服务器集群总数 = 实际调用服务器位置下标
    @Override
    public ServiceInstance instances(List< ServiceInstance > serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
