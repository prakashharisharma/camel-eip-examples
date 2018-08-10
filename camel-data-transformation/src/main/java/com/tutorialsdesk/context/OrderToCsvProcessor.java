package com.tutorialsdesk.context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderToCsvProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String custom = exchange.getIn().getBody(String.class);
		
		System.out.println("BEFORE TRANSFORMATION " + custom);
		
		String id = custom.substring(0, 9);
		String customerId = custom.substring(10, 19);
		String date = custom.substring(20, 29);
		String items = custom.substring(30);
		
		String[] itemIds = items.split("@");
		StringBuilder csv = new StringBuilder();
		csv.append(id.trim());
		csv.append(",").append(date.trim());
		csv.append(",").append(customerId.trim());
		for (String item : itemIds) {
			csv.append(",").append(item.trim());
		}
		
		System.out.println("AFTER TRANSFORMATION " + csv.toString());
		
		exchange.getIn().setHeader("Date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		exchange.getIn().setBody(csv.toString());
		
	}

}
