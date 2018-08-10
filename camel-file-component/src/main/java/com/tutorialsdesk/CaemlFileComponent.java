package com.tutorialsdesk;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CaemlFileComponent {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {
			
			//reading files
			public void configure() {
				//reading files
				from("file:data/inbox?noop=true").to("stream:out");
				
				//writing to file
				from("stream:in?promptMessage=Enter Something:").to("file:data/outbox?fileName=prompt.txt");
				
				//Unique file name
				from("stream:in?promptMessage=Enter Something:").to("file:data/outbox?fileName=${date:now:yyyyMMdd-hh:mm:ss}.txt");
				
				//copy files
				from("file:data/inbox?noop=true").to("file:data/outbox");
			}
			
			
			
			//copy files
			/*public void configure() {
				from("file:data/inbox?noop=true").to("file:data/outbox");
			}*/
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
