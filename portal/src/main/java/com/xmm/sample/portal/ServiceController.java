package com.xmm.sample.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon on 17/04/2018.
 */
@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.a.url}")
    private String aSrvUrl;

    @Value("${service.c.url}")
    private String cSrvUrl;

    @GetMapping("/info")
    public String info() {

        String srvArsp = callAService();
        String srvCrsp = callCService();

        log.info(srvArsp);
        log.info(srvCrsp);

        return String.format("<html>" +
                "<h3>Portal version=v1</h3><br/>" +
                "<h3>%s</h3><br/>" +
                "<h3>%s</h3><br/>" +
                "</html>", srvArsp, srvCrsp);
    }


    public String callAService() {
        try {
            return restTemplate.getForObject(aSrvUrl, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }


    public String callCService() {
        try {
            return restTemplate.getForObject(cSrvUrl, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }
}
