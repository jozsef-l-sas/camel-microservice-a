package com.jozsef.camel.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DynamicRouterBean {

    private int invocations;

    public String decideTheNextEndpoint(@ExchangeProperties Map<String, String> exchangeProperties,
                                        @Headers Map<String, String> headers,
                                        @Body String body) {
        log.info("Exchange props: {}", exchangeProperties);
        log.info("Headers: {}", headers);
        log.info("Body: {}", body);

        invocations++;
        if (invocations % 3 == 0) {
            return "direct:endpoint1";
        } else if (invocations % 3 == 1) {
            return "direct:endpoint2,direct:endpoint3";
        } else {
            return null;
        }
    }

}
