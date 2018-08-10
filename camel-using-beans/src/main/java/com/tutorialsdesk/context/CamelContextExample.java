package com.tutorialsdesk.context;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.tutorialsdesk.routes.HelloBeanRoute;



public class CamelContextExample {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		
		CamelContext context = new DefaultCamelContext();
		
		context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		context.addRoutes(new HelloBeanRoute());
		
		//context.addRoutes(new CamelCSVRoute());
		
		//context.addRoutes(new CamelBindyCsvRoute());
		
		context.start();
		
	}
}
