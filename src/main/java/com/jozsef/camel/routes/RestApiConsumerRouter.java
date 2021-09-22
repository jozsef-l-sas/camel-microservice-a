package com.jozsef.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost").port(8081);

        from("timer:rest-api-consumer?period=10000")
                .to("rest:get:/currency-exchange/from/EUR/to/INR")
                .log("Body: ${body}");
    }

}
