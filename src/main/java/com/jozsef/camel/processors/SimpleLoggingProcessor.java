package com.jozsef.camel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class SimpleLoggingProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getMessage().setHeader("test_header", "test_value");
        exchange.getMessage().setBody(exchange.getMessage().getBody() + " - processed");
        log.info("SimpleLoggingProcessor: {}", exchange.getMessage().getBody());
    }

}
