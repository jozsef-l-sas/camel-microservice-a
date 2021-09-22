package com.jozsef.camel.routes;

import com.jozsef.camel.aggregation.ArrayListAggregationStrategy;
import com.jozsef.camel.components.DynamicRouterBean;
import com.jozsef.camel.components.SplitterComponent;
import com.jozsef.camel.domain.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

//@Component
public class EnterpriseIntegrationPatternsRouter extends RouteBuilder {

    private final SplitterComponent splitterComponent;
    private final DynamicRouterBean dynamicRouterBean;

    public EnterpriseIntegrationPatternsRouter(SplitterComponent splitterComponent, DynamicRouterBean dynamicRouterBean) {
        this.splitterComponent = splitterComponent;
        this.dynamicRouterBean = dynamicRouterBean;
    }

    @Override
    public void configure() throws Exception {
        configureDynamicRouting();
    }

    private void configureTimerMulticast() {
        from("timer:multicast?period=10000")
                .multicast()
                .to("log:something1", "log:something2", "log:something3");
    }

    private void configureCSVLineSplit() {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\csv")
                .unmarshal().csv()
                .split(body())
                .to("activemq:split-queue");
    }

    private void configureCSVColumnSplit() {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\csv")
                .convertBodyTo(String.class)
//                .split(body(), ",")
                .split(method(splitterComponent))
                .to("activemq:split-queue");
    }

    private void configureAggregate() {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\aggregate-json")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                .to("log:aggregate-json");
    }

    private void configureRoutingSlip() {
        String routingSlip = "direct:endpoint1,direct:endpoint3";

        from("timer:routing-slip?period=10000")
                .transform().constant("My message is hardcoded")
                .routingSlip(simple(routingSlip));

        from("direct:endpoint1")
                .to("log:direct-endpoint1");

        from("direct:endpoint2")
                .to("log:direct-endpoint2");

        from("direct:endpoint3")
                .to("log:direct-endpoint3");
    }

    private void configureDynamicRouting() {
        from("timer:dynamic-routing?period={{timePeriod}}")
                .transform().constant("My message is hardcoded")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                .to("{{endpoint-for-logging}}");

        from("direct:endpoint2")
                .to("log:direct-endpoint2");

        from("direct:endpoint3")
                .to("log:direct-endpoint3");
    }

}
