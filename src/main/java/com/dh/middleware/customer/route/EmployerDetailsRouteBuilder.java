package com.dh.middleware.customer.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetEmployerRequest;
import com.dh.middleware.customer.models.backend.ods.EmployerRequest;

@Component
public class EmployerDetailsRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		restConfiguration().bindingMode(RestBindingMode.json);
		
		 rest("/api/customer")
		.post("/v1/GetEmployerDetails/")
		.consumes("application/json")
		.type(GetEmployerRequest.class)
		.to("direct:getEmployerDetailsRoute");

		onException(Exception.class).log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"EmployerDetailsResponse\",\"MW\")")
		.log("Exception" + "${exception}")
		.handled(true).setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));

		from("direct:getEmployerDetailsRoute").routeId("getEmployerDetailsRoute")
		.to("bean:employerDetailsService?method=setEmployerDetailsRequestIn")
		.to("bean:employerDetailsService?method=prepareGetEmployerDetailsBackendRequest")
		
		.marshal(new JacksonDataFormat(EmployerRequest.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=GetEmployerDetails")
		
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetEmployerDetails?bridgeEndpoint=true")

		.choice()
			.when().simple("${body} != null")
				.to("bean:employerDetailsService?method=prepaResponseBackend")
				.to("bean:employerDetailsService?method=prepareEmployerDetailsResponse")
				.setHeader("Content-Type", constant("application/json"))
			
			.otherwise()
				.to("bean:utils?method=prepareFaultNodeStr(\"EmployerDetailsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();


	}
}
