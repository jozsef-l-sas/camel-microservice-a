package com.jozsef.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        configureWithFile("json");
        configureWithFile("xml");
    }

    private void configureWithFile(String type) {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\" + type)
                .log("Body: ${body}")
                .to("activemq:my-activemq-" + type + "-queue");
    }

    private void configureWithTimer() {
        from("timer:active-mq-timer?period=10000")
                .transform().constant("My message form ActiveMQ")
                .log("ActiveMQ message sent: ${body}")
                .to("activemq:my-activemq-queue");
    }

}
