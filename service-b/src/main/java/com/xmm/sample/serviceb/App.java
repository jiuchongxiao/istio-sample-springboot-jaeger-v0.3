package com.xmm.sample.serviceb;

import com.xmm.istio.plugin.zipkin.IstioHttpSpanExtractor;
import com.xmm.istio.plugin.zipkin.IstioHttpSpanInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.instrument.web.HttpSpanExtractor;
import org.springframework.cloud.sleuth.instrument.web.HttpSpanInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@Configuration
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    HttpSpanInjector istioHttpSpanInjector() {
        return new IstioHttpSpanInjector();
    }

    @Bean
    HttpSpanExtractor istioHttpSpanExtractor() {
        return new IstioHttpSpanExtractor();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
