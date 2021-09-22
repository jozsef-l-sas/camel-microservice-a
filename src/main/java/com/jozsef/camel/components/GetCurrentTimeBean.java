package com.jozsef.camel.components;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }
}
