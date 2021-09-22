package com.jozsef.camel.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DeciderBean {

    public boolean isThisConditionMet(@Body String body, @Headers Map<String, String> headers,
                                      @Header("CamelFileAbsolute") String isFileAbsolute,
                                      @ExchangeProperties Map<String, String> exchangeProperties) {
        log.info("DeciderBean body: {}, headers: {}", body, headers);
        log.info("DeciderBean isFileAbsolute: {}, exchangeProperties: {}", isFileAbsolute, exchangeProperties);
        return true;
    }

}
