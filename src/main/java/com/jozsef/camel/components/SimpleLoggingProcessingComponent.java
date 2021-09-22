package com.jozsef.camel.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleLoggingProcessingComponent {
    public void process(String message) {
        log.info("SimpleLoggingProcessingComponent: {}", message);
    }
}
