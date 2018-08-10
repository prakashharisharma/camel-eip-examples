package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.tutorialsdesk.util.RecipientListBean;

public class RecipientListRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		/*from("jms:xmlOrders")
			.filter(xpath("/order[not(@test)]"))
			.setHeader("customer", xpath("/order/@customer"))
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					String recipients = "jms:accoutingQueue";
					String customer =
					exchange.getIn().getHeader("customer", String.class);
						if (customer.equals("honda")) {
							recipients += ",jms:operationsQueue";
					}
					exchange.getIn().setHeader("recipients", recipients);
				
				}
			}).recipientList(header("recipients"));*/
		
		//CAN ALSO USE
		from("jms:xmlOrders").bean(RecipientListBean.class);
		
	}

}
