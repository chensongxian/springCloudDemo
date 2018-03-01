package com.csx.eurekaconsumerfeign.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/03/01
 */
@FeignClient("eureka-client")
public interface DcClient {
    @GetMapping("/dc")
    String consumer();
}
