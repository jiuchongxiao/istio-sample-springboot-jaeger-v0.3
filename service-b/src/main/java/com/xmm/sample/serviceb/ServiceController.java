package com.xmm.sample.serviceb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon on 17/04/2018.
 */
@RestController
public class ServiceController {

    @GetMapping("/info")
    public String info() {
        return "B version = v1";
    }
}
