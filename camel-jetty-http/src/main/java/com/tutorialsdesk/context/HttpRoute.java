package com.tutorialsdesk.context;

import org.apache.camel.builder.RouteBuilder;

public class HttpRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
	//	from("jetty:http//0.0.0.0:8080/ping").transform(constant("PONG\n"));

		from("jetty://http://localhost:8080/greeting")  
		.log("Received a request")  
		.setBody(simple("Hello, world!"));
		
	}

}
