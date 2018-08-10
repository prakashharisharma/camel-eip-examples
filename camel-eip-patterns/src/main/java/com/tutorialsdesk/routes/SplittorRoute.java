package com.tutorialsdesk.routes;

import org.apache.camel.builder.RouteBuilder;

public class SplittorRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.split(body())
			.log("Split line ${body}")
			.to("mock:split");

	}

}
