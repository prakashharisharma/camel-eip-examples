package com.tutorialsdesk.routes;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class AggreagateRouteTests extends CamelTestSupport {

	@Override
	public RoutesBuilder createRouteBuilder() throws Exception {
		// return new AggregateRoute();
		//return new AggregateRouteTimeOut();
		//return new SplittorRoute();
		//return new DynamicRoute();
		return new LoadBalancerRoute();

	}
	
	@Test
	public void test() {
		MockEndpoint mock = getMockEndpoint("mock:result");
		List<String> body = new ArrayList<String>();
		body.add("A");
		body.add("B");
		body.add("C");
		
		template.sendBody("direct:start", body);
	}
	
	//@Test
	public void testSplitABC() throws InterruptedException {
		MockEndpoint mock = getMockEndpoint("mock:split");
		mock.expectedBodiesReceived("A", "B", "C");
		
		List<String> body = new ArrayList<String>();
		body.add("A");
		body.add("B");
		body.add("C");
		
		template.sendBody("direct:start", body);
		assertMockEndpointsSatisfied();
	}

	// @Test
	public void testABC() throws Exception {

		MockEndpoint mock = getMockEndpoint("mock:result");

		mock.expectedBodiesReceived("ABC");

		template.sendBodyAndHeader("direct:start", "A", "myId", 1);
		template.sendBodyAndHeader("direct:start", "B", "myId", 1);
		template.sendBodyAndHeader("direct:start", "F", "myId", 2);
		template.sendBodyAndHeader("direct:start", "C", "myId", 1);

		assertMockEndpointsSatisfied();

	}

	//@Test
	public void testXML() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:result");
		mock.expectedMessageCount(2);
		template.sendBody("direct:start", "<order name=\"motor\" amount=\"1000\" customer=\"honda\"/>");
		template.sendBody("direct:start", "<order name=\"motor\" amount=\"500\" customer=\"toyota\"/>");
		template.sendBody("direct:start", "<order name=\"gearbox\" amount=\"200\" customer=\"toyota\"/>");
		assertMockEndpointsSatisfied();
	}
}
