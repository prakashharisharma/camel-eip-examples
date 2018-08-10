package com.tutorialsdesk.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.tutorialsdesk.bean.DynamicRouterBean;

public class DynamicRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.dynamicRouter(bean(DynamicRouterBean.class, "route"))
			.to("mock:result");
		
		
	}

}
