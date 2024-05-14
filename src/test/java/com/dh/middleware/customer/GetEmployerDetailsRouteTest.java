package com.dh.middleware.customer;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWith;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.apache.camel.component.mock.MockEndpoint;

import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpointsAndSkip;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.dh.middleware.customer.models.GetEmployerDetails;
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
public class GetEmployerDetailsRouteTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

//	@EndpointInject("mock:{{employersDetailsMock.host}}{{employersDetailsMock.contextPath}}GetEmployerDetails?bridgeEndpoint=true")
	@EndpointInject("mock://http:localhost:8084/api/connector/db/odsdb/v1/GetEmployerDetails")
	private MockEndpoint cdmockEndpoint;

//	@EndpointInject("mock:{{configStoreConnector.host}}{{configStoreConnector.contextPath}}")
	@EndpointInject("mock://http:localhost:8081/api/connector/configstore")
	private MockEndpoint configStore;

	@Test
	public void getEmplolyerDetailsSuccessTest() throws Exception {

		String getDetailsRequest = Resources.toString(
				Resources.getResource("mock/frontend/GetEmployerDetails/SuccessRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getDetailsResponse = Resources.toString(
				Resources.getResource("mock/backend/GetEmployerDetails/SuccessResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "getEmployerDetailsRoute", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getEmployerDetailsRoute");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getDetailsResponse);
			}
		});

		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});

		camelContext.start();

		GetEmployerDetails oGetEmployerRequest = objectMapper.readValue(getDetailsRequest, GetEmployerDetails.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		GetEmployerDetails successResponse = producerTemplate.requestBodyAndHeaders("direct:getEmployerDetailsRoute", oGetEmployerRequest,  headers, GetEmployerDetails.class);

//		String successResponse = producerTemplate.requestBodyAndHeaders("direct:getEmployerDetailsRoute",
//				oGetEmployerRequest, headers, String.class);

		System.out.println("GetEmployerDetailsResponse " + successResponse.getoGetEmployerDetailsResponse().getSuccess().getCif());
		
//		System.out.println(successResponse);

		Assertions.assertNotNull(successResponse.getoGetEmployerDetailsResponse().getSuccess().getCif());
//		Assertions.assertNotNull(successResponse.contains("success"));
	}

	@Test
	public void getEmployerDetailsFaultTest() throws Exception {

		String getDetailsRequest = Resources
				.toString(Resources.getResource("mock/frontend/GetEmployerDetails/FaultRequest.json"), Charsets.UTF_8);

		String ApplicationErrorConfigStore = Resources.toString(
				Resources.getResource("mock/frontend/configStore/ConfigStoreResponse_Application_Errors.json"),
				Charsets.UTF_8);

		String getDetailsResponse = Resources
				.toString(Resources.getResource("mock/backend/GetEmployerDetails/FaultResponse.json"), Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "getEmployerDetailsRoute", routeBuilder ->

		{
			routeBuilder.replaceFromWith("direct:getEmployerDetailsRoute");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getDetailsResponse);
			}
		});

		configStore.expectedMessageCount(1);
		configStore.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(ApplicationErrorConfigStore);
			}
		});

		camelContext.start();

		GetEmployerDetails oGetEmployerRequest = objectMapper.readValue(getDetailsRequest, GetEmployerDetails.class);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("ServiceHeader", "{  \"tellerId\": \"T123\", \"branchId\": \"B001\",\"channelId\": \"WEB\"}");

		String faultResponse = producerTemplate.requestBodyAndHeaders("direct:getEmployerDetailsRoute",
				oGetEmployerRequest, headers, String.class);

		System.out.println("Fault response: " + faultResponse);

		Assertions.assertNotNull(faultResponse.contains("Record not found"));

	}
}
