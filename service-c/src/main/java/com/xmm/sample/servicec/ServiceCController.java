package com.xmm.sample.servicec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon on 17/04/2018.
 */
@RestController
public class ServiceCController {

    @GetMapping("/info")
    public String info() {
        return "C version = v3";
    }
}
