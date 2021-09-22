package com.jozsef.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleRouterTest {

    @EndpointInject(MOCK_RESULT)
    private MockEndpoint resultEndpoint;

    @Autowired
    private CamelContext camelContext;

    @EndpointInject(MOCK_TIMER)
    private ProducerTemplate producer;

    private static final String MOCK_RESULT = "mock:result";
    private static final String MOCK_TIMER = "direct:mock-timer";

    @BeforeEach
    public void setup() throws Exception {

        AdviceWith.adviceWith(camelContext, SimpleRouter.ROUTE_NAME, routeBuilder -> {
            routeBuilder.replaceFromWith(MOCK_TIMER);
        });
        camelContext.start();
    }

    @Test
    public void sendMessage() throws Exception {
        producer.sendBody("A message");
        resultEndpoint.assertIsSatisfied();
        resultEndpoint.expectedHeaderReceived("test_header", "test_value");
        resultEndpoint.expectedBodyReceived().body().isEqualTo("My Constant Message - processed");
    }


}
