package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ContentBasedRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("file:data/inbox?noop=true").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("Downloaded: "
						+ exchange.getIn().getHeader("CamelFileName"));
			}
		}).to("jms:IncomingFile");
		
		from("jms:IncomingFile")
			.wireTap("jms:orderAudit")
			.choice()
				.when(header("CamelFileName").endsWith(".xml"))
					.to("jms:xmlOrders")
				.when(header("CamelFileName").endsWith(".csv"))
					.to("jms:csvOrders")
				.otherwise()
					.to("jms:badOrders").stop()
			.end()
			.to("jms:continuedProcessing");
		

		
		from("jms:csvOrders").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("CSV Order: "
						+ exchange.getIn().getHeader("CamelFileName"));
				
			}
		});
		
		from("jms:badOrders").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("BAD Order: "
						+ exchange.getIn().getHeader("CamelFileName"));
				
			}
		});
		
		from("jms:orderAudit").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("AUDIT Order: " + exchange.getIn().getHeader("CamelFileName"));
				System.out.println("AUDIT Order BODY: " + exchange.getIn().getBody());
				
			}
		});
		
	}

}
