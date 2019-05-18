package com.sample.servlet;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class GreetingsServletExample extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest("/greeting").produces("application/json")
                .get("/{name}")
                .to("direct:greeting");

        from("direct:greeting")
                .process(new Processor() {
                    public void process(Exchange exchange) {
                        final String name = exchange.getIn().getHeader("name", String.class);
                        exchange.getIn().setBody("Hello " + name);
                    }
                });
    }
}
