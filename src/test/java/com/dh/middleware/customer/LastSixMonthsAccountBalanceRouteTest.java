package com.dh.middleware.customer;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpointsAndSkip;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dh.middleware.customer.models.LastSixMonthsAccountBalance;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@UseAdviceWith
@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

@MockEndpointsAndSkip("http:localhost:8081/api/connector/configstore")

@ImportResource({ "classpath:spring/camel-context.xml" })
@PropertySource("classpath:application-test.properties")
@Configuration
@ComponentScan("com.dh.middleware.customer.*")
public class LastSixMonthsAccountBalanceRouteTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;
	
	@EndpointInject("mock://http:localhost:8088/api/connector/db/odsdb/v1/GetLastSixMonthsAccountBalance")
	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://http:localhost:8081/api/connector/configstore")
	private MockEndpoint configStore;
	
	
	
	@Test
	public void lastSixMonthsAccountBalanceSuccessTest() throws Exception {
		
		String getLastSixMonthsAccountBalanceRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetLastSixMonthsAccountBalance/SuccessRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getLastSixMonthsAccountBalanceResponse = Resources.toString(
				Resources.getResource("mock/backend/GetLastSixMonthsAccountBalance/SuccessResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetLastSixMonthsAccountBalance", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getLastSixMonthsAccountBalanceRoute");
		});
		
		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLastSixMonthsAccountBalanceResponse);
			}
		});
		
		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});
		
		camelContext.start();

		LastSixMonthsAccountBalance oLastSixMonthsAccountBalanceRequest = objectMapper.readValue(getLastSixMonthsAccountBalanceRequest, LastSixMonthsAccountBalance.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		LastSixMonthsAccountBalance successResponse = producerTemplate.requestBodyAndHeaders("direct:getLastSixMonthsAccountBalanceRoute", oLastSixMonthsAccountBalanceRequest,  headers, LastSixMonthsAccountBalance.class);

		System.out.println("LastSixMonthsAccountBalanceResponse " + successResponse.getLastSixMonthsAccountBalanceResponse().getSuccess().getAverageSixMonthsBalance());

		Assertions.assertNotNull(successResponse.getLastSixMonthsAccountBalanceResponse().getSuccess().getAverageSixMonthsBalance());
		
	}
	
	@Test
	public void lastSixMonthsAccountBalanceFaultTest() throws Exception {
		
		String getLastSixMonthsAccountBalanceRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetLastSixMonthsAccountBalance/FaultRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getLastSixMonthsAccountBalanceResponse = Resources.toString(
				Resources.getResource("mock/backend/GetLastSixMonthsAccountBalance/FaultResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetLastSixMonthsAccountBalance", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getLastSixMonthsAccountBalanceRoute");
		});
		
		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLastSixMonthsAccountBalanceResponse);
			}
		});
		
		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});
		
		camelContext.start();
		
		LastSixMonthsAccountBalance oLastSixMonthsAccountBalanceRequest = objectMapper.readValue(getLastSixMonthsAccountBalanceRequest, LastSixMonthsAccountBalance.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:getLastSixMonthsAccountBalanceRoute", oLastSixMonthsAccountBalanceRequest,  headers, String.class);
		
		System.out.println("Fault response: " + faultResponse);
		
		Assertions.assertNotNull(faultResponse.contains("Record not found"));
	}
}
