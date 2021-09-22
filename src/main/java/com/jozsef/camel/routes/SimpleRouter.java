package com.jozsef.camel.routes;

import com.jozsef.camel.processors.SimpleLoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouter extends RouteBuilder {

    public static final String ROUTE_NAME = "SIMPLE_ROUTE";

    @Override
    public void configure() throws Exception {
        from("timer:first-timer?period=10s")
                .routeId(ROUTE_NAME)
                .log("body: ${body}")
                .transform().constant("My Constant Message")
                .log("body: ${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }
}
