package com.xmm.sample.serviceb;

import org.fluentd.logger.FluentLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ljb on 17/5/18.
 */
@RestController
public class ServiceController {

//    private static FluentLogger log = FluentLogger.getLogger("service-b.ServiceController","192.168.181.99",30224);
    private static FluentLogger log = FluentLogger.getLogger("service-b.ServiceController","fluentd-es.logging",24224);

    @Value("${app.version}")
    private String version;

    @Autowired
    private RestTemplate restTemplate;
    private static void extractHeader(HttpHeaders headers, HttpHeaders extracted, String key) {
        List<String> vals = headers.get(key);
        if (vals != null && !vals.isEmpty()) {
            extracted.put(key, Arrays.asList(vals.get(0)));
        }
    }
    @GetMapping("/info")
    public String info() {

        String version =  " B Service version = " + this.version;
        log.log("info","info","version");
        return version;
    }


}
