package com.jozsef.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\json")
                .log("Body: ${body}")
                .to("kafka:my-kafka-topic");
    }

}
