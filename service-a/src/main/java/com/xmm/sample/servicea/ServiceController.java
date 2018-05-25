package com.xmm.sample.servicea;

import org.fluentd.logger.FluentLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ljb on 17/5/18.
 */
@RestController
public class ServiceController {
    //定义log 指向fluent remote地址，不指定则默认为本地
//    private static FluentLogger log = FluentLogger.getLogger("service-a.ServiceController","192.168.181.99",30224);
    //istio 线上配置
    private static FluentLogger log = FluentLogger.getLogger("service-a.ServiceController","fluentd-es.logging",24224);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.b.url}")
    private String url;

    @Value("${app.version}")
    private String version;

    @GetMapping("/info")
    public String info() {
        String rsp = "A Service version = " + this.version + "  ===> " + notify(url);
        log.log("info","data",rsp);
        return rsp;
    }
    public String notify(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            log.log("notify","error",e.getMessage());
            return e.getMessage();
        }


    }
}
