package com.xmm.sample.servicec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon (simon.meng@fox.mal.com) on 17/04/2018.
 */
@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Value("${app.version}")
    private String version;

    @GetMapping("/info")
    public String info() {
        String version = "C Service version = " + this.version;
        log.info(version);
        return version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
