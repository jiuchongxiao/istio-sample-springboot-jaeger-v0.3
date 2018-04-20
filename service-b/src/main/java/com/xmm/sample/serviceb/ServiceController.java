package com.xmm.sample.serviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon (simon.meng@fox.mal.com) on 17/04/2018.
 */
@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Value("${app.version}")
    private String version;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/info")
    public String info() {
        String version = "B Service version = " + this.version;
        log.info(version);
        return version;
    }


}
