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

import com.dh.middleware.customer.models.GetBalanceCertificateDetails;
import com.dh.middleware.customer.models.GetCustomerNORResultsType;
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
public class BalanceCertificateDetailsRouteTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;
	
	@EndpointInject("mock://http:localhost:8086/api/connector/db/odsdb/v1/GetBalanceCertificateDetails")
	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://http:localhost:8081/api/connector/configstore")
	private MockEndpoint configStore;
	
	@Test
	public void balanceCertificateDetailsSuccessTest() throws Exception {
		
		String getbalanceCertificateRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetBalanceCertificateDetails/SuccessRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getbalanceCertificateResponse = Resources.toString(
				Resources.getResource("mock/backend/GetBalanceCertificateDetails/SuccessResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetBalanceCertificateDetails", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getBalanceCertificateDetailsRoute");
		});
		
		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getbalanceCertificateResponse);
			}
		});

		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});
		
		camelContext.start();

		GetBalanceCertificateDetails oGetBalanceCertificateDetailsRequest = objectMapper.readValue(getbalanceCertificateRequest, GetBalanceCertificateDetails.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		GetBalanceCertificateDetails successResponse = producerTemplate.requestBodyAndHeaders("direct:getBalanceCertificateDetailsRoute", oGetBalanceCertificateDetailsRequest,  headers, GetBalanceCertificateDetails.class);

		System.out.println("GetBalanceCertificateDetailsResponse " + successResponse.getBalanceCertificateDetailsResponse().getSuccess().getAccount());

		Assertions.assertNotNull(successResponse.getBalanceCertificateDetailsResponse().getSuccess().getAccount());
		
	}
	
	@Test
	public void getBalanceCertificateDetailsFaultTest() throws Exception {
		
		String getDetailsRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetBalanceCertificateDetails/FaultRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getDetailsResponse = Resources.toString(
				Resources.getResource("mock/backend/GetBalanceCertificateDetails/FaultResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "GetBalanceCertificateDetails", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getBalanceCertificateDetailsRoute");
		});
		
		
		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getDetailsResponse);
			}
		});
		
		camelContext.start();

		GetBalanceCertificateDetails oGetBalanceCertificateDetailsRequest = objectMapper.readValue(getDetailsRequest, GetBalanceCertificateDetails.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:getBalanceCertificateDetailsRoute",
				oGetBalanceCertificateDetailsRequest, headers, String.class);

		System.out.println("Fault response: " + faultResponse);

		Assertions.assertNotNull(faultResponse.contains("Record not found"));
	}
	
}
