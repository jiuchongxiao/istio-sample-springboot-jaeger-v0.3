package com.xmm.sample.portal;

import com.uber.jaeger.metrics.NoopMetricsFactory;
import com.uber.jaeger.reporters.Reporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.uber.jaeger.metrics.Metrics;
import com.uber.jaeger.metrics.NullStatsReporter;
import com.uber.jaeger.metrics.StatsFactoryImpl;
import com.uber.jaeger.propagation.B3TextMapCodec;
import com.uber.jaeger.reporters.RemoteReporter;
import com.uber.jaeger.samplers.ConstSampler;
import com.uber.jaeger.senders.HttpSender;
import io.opentracing.propagation.Format;
import com.uber.jaeger.Tracer.Builder;
/**
 * Created by simon (simon.meng@fox.mal.com) on 17/04/2018.
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
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public io.opentracing.Tracer jaegerTracer() {

        Reporter reporter = new RemoteReporter.Builder().withFlushInterval(10)
                .withMaxQueueSize(65000)
                .withSender(new HttpSender("http://192.168.181.99:30668/api/traces"))
                .withMetrics(new Metrics(new NoopMetricsFactory()))
                .build();

        Builder builder = new Builder("portal")
                .withReporter(reporter)
                .withSampler(new ConstSampler(true))
                .registerInjector(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec())
                .registerExtractor(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec());

        return builder.build();

    }
}
