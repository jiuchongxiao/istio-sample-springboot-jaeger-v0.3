package com.xmm.sample.serviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource({"classpath:applicationContext.xml"})
//@EnableCaching
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println(Ignite.class);
        SpringApplication.run(App.class, args);
    }
}
