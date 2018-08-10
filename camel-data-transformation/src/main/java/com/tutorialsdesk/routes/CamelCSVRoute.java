package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelCSVRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:data/inbox/csv?noop=true")
			.unmarshal()
			.csv()
			.to("jms:queue.order.record");

		from("jms:queue.order.record").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				
				System.out.println("From QUEUE " + exchange.getIn().getBody());	
			}
		});
		
		
	}

}
