package com.tutorialsdesk.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hawtdb.HawtDBAggregationRepository;
import org.apache.camel.spi.AggregationRepository;

import com.tutorialsdesk.context.MyAggregationStrategy;

public class AggregateRepositoryRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		AggregationRepository myRepo = new HawtDBAggregationRepository("myrepo", "data/myrepo.dat");
		from("file://data/inbox")
			.log("Consuming ${file:name}")
			.aggregate(constant(true), new MyAggregationStrategy())
				.aggregationRepository(myRepo)
				.completionSize(3)
				.log("Sending out ${body}").to("mock:result");
	}

}
