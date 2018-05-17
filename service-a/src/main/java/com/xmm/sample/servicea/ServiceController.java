package com.xmm.sample.servicea;

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

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.b.url}")
    private String url;

    @Value("${app.version}")
    private String version;

    @GetMapping("/info")
    public String info() {
        String rsp = "A Service version = " + this.version + "  ===> " + notify(url);
        log.info(rsp);
        return rsp;
    }
    private static void extractHeader(HttpHeaders headers, HttpHeaders extracted, String key) {
        List<String> vals = headers.get(key);
        if (vals != null && !vals.isEmpty()) {
            extracted.put(key, Arrays.asList(vals.get(0)));
        }
    }
    /*@GetMapping("/info")
    public String info(@RequestHeader HttpHeaders headers) {
        HttpHeaders tracingHeaders = new HttpHeaders();
        extractHeader(headers, tracingHeaders, "x-request-id");
        extractHeader(headers, tracingHeaders, "x-b3-traceid");
        extractHeader(headers, tracingHeaders, "x-b3-spanid");
        extractHeader(headers, tracingHeaders, "x-b3-parentspanid");
        extractHeader(headers, tracingHeaders, "x-b3-sampled");
        extractHeader(headers, tracingHeaders, "x-b3-flags");
        extractHeader(headers, tracingHeaders, "x-ot-span-context");
        List<String> v = tracingHeaders.get("x-b3-spanid");
        if(v !=null && !v.isEmpty()) {
            tracingHeaders.put("x-b3-parentspanid", Arrays.asList(v.get(0)));
        }
        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<>(tracingHeaders), String.class);

        return headers.get("x-b3-traceid")+"==="+headers.get("x-b3-parentspanid")+"==="+headers.get("x-b3-spanid")+" A Service version =  "+this.version+"===========>" + response.getBody();

//        String rsp = "A Service version = " + this.version + "  ===> " + notify(url);
//        log.info(rsp);
//        return rsp;
    }*/

    public String notify(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }


    }
}
