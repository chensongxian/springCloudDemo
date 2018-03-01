package com.csx.eurekaconsumerribbonhystrix.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/03/01
 */
@RestController
public class DcController {
    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc() {
        return consumerService.consumer();
    }

    @Service
    class ConsumerService {

        @Autowired
        RestTemplate restTemplate;

        /**
         * Hystrix服务降级
         * @return
         */
        @HystrixCommand(fallbackMethod = "fallback")
        public String consumer() {
            return restTemplate.getForObject("http://eureka-client/dc", String.class);
        }

        /**
         * 服务消费方触发了服务请求超时异常，服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行,
         * 返回fallbck
         * @return
         */
        public String fallback() {
            return "fallbck";
        }

    }
}
