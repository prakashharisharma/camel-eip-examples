package com.tutorialsdesk.routes;

import org.apache.camel.builder.RouteBuilder;

import com.tutorialsdesk.context.MyAggregationStrategy;

public class AggregateRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.log("Sending ${body} with correlation key ${header.myId}")
			.aggregate(header("myId"), new MyAggregationStrategy())
			.completionSize(3)
			.log("Sending out ${body}")
			.to("mock:result");

	}

}
