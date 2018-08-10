package com.tutorialsdesk.routes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;

import com.tutorialsdesk.model.Order;

public class CamelBindyCsvRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("file:data/inbox/csv?noop=true")
			.unmarshal().bindy(BindyType.Csv, Order.class)
			.to("jms:queue.order.record");

		from("jms:queue.order.record").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				
				@SuppressWarnings("unchecked")
				List<Order> orderList = (List<Order>) exchange.getIn().getBody();
				
				for(Order order : orderList) {
					System.out.println(order);
				}
			}
		});
		
		
	}
}
