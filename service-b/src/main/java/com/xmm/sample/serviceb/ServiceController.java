package com.xmm.sample.serviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon on 17/04/2018.
 */
@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @GetMapping("/info")
    public String info() {
        String version = "B version = v1";
        log.info(version);
        return version;
    }
}
