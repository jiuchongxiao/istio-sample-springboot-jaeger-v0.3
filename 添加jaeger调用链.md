# spring boot添加jaeger调用链信息

1.添加maven依赖
<dependency>
			<groupId>io.opentracing.contrib</groupId>
			<artifactId>opentracing-spring-cloud-starter</artifactId>
			<version>0.1.8</version>
		</dependency>

		<dependency>
			<groupId>com.uber.jaeger</groupId>
			<artifactId>jaeger-core</artifactId>
			<version>0.26.0</version>
		</dependency>

		<dependency>
			<groupId>com.uber.jaeger</groupId>
			<artifactId>jaeger-b3</artifactId>
			<version>0.26.0</version>
		</dependency>

		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>4.5.5</version>
		</dependency>
2.启动类 application添加bean

注意：http://192.168.181.99:30668/api/traces 为对应的jaeger collector地址
      service-c 为服务名称

@Bean
    public io.opentracing.Tracer jaegerTracer() {

        Reporter reporter = new RemoteReporter.Builder().withFlushInterval(10)
                .withMaxQueueSize(65000)
                .withSender(new HttpSender("http://192.168.181.99:30668/api/traces"))
                .withMetrics(new Metrics(new NoopMetricsFactory()))
                .build();

        Builder builder = new Builder("service-c")
                .withReporter(reporter)
                .withSampler(new ConstSampler(true))
                .registerInjector(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec())
                .registerExtractor(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec());

        return builder.build();

    }