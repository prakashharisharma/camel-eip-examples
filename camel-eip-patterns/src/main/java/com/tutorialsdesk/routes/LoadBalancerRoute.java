package com.tutorialsdesk.routes;

import org.apache.camel.builder.RouteBuilder;

public class LoadBalancerRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:start")
			.loadBalance()
				.roundRobin()
					.to("seda:a")
					.to("seda:b")
			.end();
		
		from("seda:a").log("A received: ${body}").to("mock:a");
		
		from("seda:b").log("B received: ${body}").to("mock:b");
		

	}

}
