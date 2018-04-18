package com.xmm.sample.servicea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon on 17/04/2018.
 */
@RestController
public class ServiceAController {

    private static final Logger log = LoggerFactory.getLogger(ServiceAController.class);

    @Value("${service.b.url}")
    private String url;

    @GetMapping("/info")
    public String info() {
        return "A version = v1, " + notify(url);
    }

    public String notify(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }
}
