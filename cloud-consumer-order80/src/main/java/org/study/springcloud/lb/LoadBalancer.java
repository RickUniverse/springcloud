package org.study.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author lijichen
 * @date 2021/2/13 - 17:47
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
