package com.xmm.sample.portal;

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

    @Value("${service.a.url}")
    private String aSrvUrl;

    @Value("${service.c.url}")
    private String cSrvUrl;

    @Value("${app.version}")
    private String version;
    private static void extractHeader(HttpHeaders headers, HttpHeaders extracted, String key,String value) {
        List<String> vals = headers.get(key);
        if (vals != null && !vals.isEmpty()) {
            extracted.put(key, Arrays.asList(vals.get(0)));
        }else if(value !=null && !value.isEmpty()){
            extracted.put(key,Arrays.asList(value));
        }
    }
    @GetMapping("/info")
//    @RequestHeader HttpHeaders headers, String xreq, String xtraceid, String xspanid,
//    String xparentspanid, String xsampled, String xflags, String xotspan
    public String info() {

        String srvArsp = callAService();
        String srvCrsp = callCService();

        log.info(srvArsp);
        log.info(srvCrsp);

        return String.format("<html>" +
                "<h3>Portal version="+ this.version +"</h3><br/>" +
                "<h3>%s</h3><br/>" +
                "<h3>%s</h3><br/>" +
                "</html>", srvArsp, srvCrsp);
    }


    public String callAService(){
        try {
            return restTemplate.getForObject(aSrvUrl, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }


    public String callCService(){
        try {
            return restTemplate.getForObject(cSrvUrl, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }
    /*public String callCService(HttpHeaders headers, String xreq, String xtraceid, String xspanid,
                               String xparentspanid, String xsampled, String xflags, String xotspan){
        try {
            HttpHeaders tracingHeaders = new HttpHeaders();
            extractHeader(headers, tracingHeaders, "x-request-id",xreq);
            extractHeader(headers, tracingHeaders, "x-b3-traceid",xtraceid);
            extractHeader(headers, tracingHeaders, "x-b3-spanid",xspanid);
            extractHeader(headers, tracingHeaders, "x-b3-parentspanid",xparentspanid);
            extractHeader(headers, tracingHeaders, "x-b3-sampled",xsampled);
            extractHeader(headers, tracingHeaders, "x-b3-flags",xflags);
            extractHeader(headers, tracingHeaders, "x-ot-span-context",xotspan);

            List<String> v = tracingHeaders.get("x-b3-spanid");
            if(v !=null && !v.isEmpty()) {
                tracingHeaders.put("x-b3-parentspanid", Arrays.asList(v.get(0)));
            }

            ResponseEntity<String> response = restTemplate
                    .exchange(cSrvUrl, HttpMethod.GET, new HttpEntity<>(tracingHeaders), String.class);
//            return restTemplate.getForObject(cSrvUrl, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            return e.getMessage();
        }
    }*/


}
