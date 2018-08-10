package com.tutorialsdesk.routes;

import org.apache.camel.builder.RouteBuilder;

import com.tutorialsdesk.context.MyAggregationStrategy;

public class AggregateRouteTimeOut extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.log("Sending ${body}")
			.aggregate(xpath("/order/@customer"), new MyAggregationStrategy())
			.completionSize(2)
			.completionTimeout(5000)
			.log("Sending out ${body}")
			.to("mock:result");
	}

}
