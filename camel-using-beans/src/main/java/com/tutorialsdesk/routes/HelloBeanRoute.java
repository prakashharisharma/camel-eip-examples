package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.tutorialsdesk.bean.HelloBean;

public class HelloBeanRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("direct:hello")
		.setHeader("someHeader", simple("some header value"))
		.bean(HelloBean.class, "hello(${header.someHeader})")
		.to("log:reply ${body}");
		
		
	}

}
