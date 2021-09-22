package com.jozsef.camel.routes;

import com.jozsef.camel.components.DeciderBean;
import org.apache.camel.builder.RouteBuilder;

//@Component
public class MyFileRouter extends RouteBuilder {

    private final DeciderBean deciderBean;

    public MyFileRouter(DeciderBean deciderBean) {
        this.deciderBean = deciderBean;
    }

    @Override
    public void configure() throws Exception {
        from("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml'"))
                        .log("XML file")
//                    .when(simple("${body} contains 'USD'"))
                    .when(method(deciderBean))
                        .log("Not an XML file BUT contains 'USD'")
                    .otherwise()
                        .log("Not an XML file")
                .end()
                .to("direct:log-file-values")
                .to("file:E:\\Work\\Stuff\\tutorials\\Apache Camel\\files\\output");

        from("direct:log-file-values")
                .log("${messageHistory} ${file:absolute.path}")
                .log("${file:name} ${file:name.ext}")
                .log("${file:size} ${file:modified}")
                .log("${routeId} ${camelId} ${body}");
    }

}
