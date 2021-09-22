package com.jozsef.camel.routes;

import com.jozsef.camel.components.GetCurrentTimeBean;
import com.jozsef.camel.components.SimpleLoggingProcessingComponent;
import com.jozsef.camel.processors.SimpleLoggingProcessor;
import org.apache.camel.builder.RouteBuilder;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    private final GetCurrentTimeBean getCurrentTimeBean;
    private final SimpleLoggingProcessingComponent loggingProcessingComponent;

    public MyFirstTimerRouter(GetCurrentTimeBean getCurrentTimeBean, SimpleLoggingProcessingComponent loggingProcessingComponent) {
        this.getCurrentTimeBean = getCurrentTimeBean;
        this.loggingProcessingComponent = loggingProcessingComponent;
    }

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log

        from("timer:first-timer")
                .log("body: ${body}")
                .transform().constant("My Constant Message")
                .log("body: ${body}")
//                .transform().constant("Time now is " + LocalDateTime.now())
                .bean(getCurrentTimeBean)
                .log("body: ${body}")
                .bean(loggingProcessingComponent)
                .log("body: ${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }
}
