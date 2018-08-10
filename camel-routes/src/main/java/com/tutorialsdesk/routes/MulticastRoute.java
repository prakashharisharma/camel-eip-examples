package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MulticastRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

/*		from("jms:continuedProcessing").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("CONTINUE processing with: "
						+ exchange.getIn().getHeader("CamelFileName"));
				
			}
		}).multicast().to("jms:accoutingQueue", "jms:operationsQueue");*/
		
		from("jms:continuedProcessing").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("CONTINUE processing with: "
						+ exchange.getIn().getHeader("CamelFileName"));
				
			}
		}).multicast().parallelProcessing().to("jms:accoutingQueue", "jms:operationsQueue");

		from("jms:accoutingQueue").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("ACCOUTING " + exchange.getIn().getHeader("CamelFileName"));

			}
		}).log("ACC");

		from("jms:operationsQueue").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("OPERATIONS " + exchange.getIn().getHeader("CamelFileName"));

			}
		}).log("OPS");
	}

}
