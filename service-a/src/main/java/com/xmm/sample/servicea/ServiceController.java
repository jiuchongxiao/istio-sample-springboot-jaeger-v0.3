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
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Value("${service.b.url}")
    private String url;

    @GetMapping("/info")
    public String info() {
        String rsp = "A version = v1,   ===> " + notify(url);
        log.info(rsp);
        return rsp;
    }

    public String notify(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {

//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return e.getMessage();
        }


    }
}
