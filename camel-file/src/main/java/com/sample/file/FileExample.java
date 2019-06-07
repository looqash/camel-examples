package com.sample.file;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileExample extends RouteBuilder {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configure() {
        from("file://{{directory.path}}")
                .process(new Processor() {
                    public void process(Exchange exchange) {
                        LOGGER.info("syncing " + exchange.getIn().getBody());
                    }
                });
    }
}
