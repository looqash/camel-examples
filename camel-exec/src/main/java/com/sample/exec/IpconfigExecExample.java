package com.sample.exec;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class IpconfigExecExample extends RouteBuilder {
    public void configure() throws Exception {
        from("rest:get:ipconfig")
                .to("direct:ipconfig");


        from("direct:ipconfig")
                .to("exec:cmd?args=/C ipconfig");
    }
}
