package com.tutorialsdesk.context;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class TransformationProcessorRoute extends RouteBuilder{

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
			.choice()
				.when(header("CamelFileName").endsWith(".txt"))
					.process(new OrderToCsvProcessor())
					.to("file:data/outbox?fileName=report-${header.Date}.csv")
			.end();
	}

	
}
