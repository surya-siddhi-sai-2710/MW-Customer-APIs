package com.dh.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetEmployerDetails;
import com.dh.middleware.customer.models.backend.ods.EmployerRequest;

@Component
public class EmployerDetailsRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		restConfiguration().bindingMode(RestBindingMode.json);
		
		 rest("/api/customer")
		.post("/v1/GetEmployerDetails")
		.consumes("application/json")
		.type(GetEmployerDetails.class)
		.to("direct:getEmployerDetailsRoute");

		onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"EmployerDetailsResponse\",${header.system})")
		.handled(true);

		from("direct:getEmployerDetailsRoute").routeId("GetEmployerDetails")
		.setHeader("system",constant("MW"))
		.to("bean:employerDetailsService?method=setEmployerDetailsRequestIn")
		.to("bean:employerDetailsService?method=prepareGetEmployerDetailsBackendRequest")
		
		.marshal(new JacksonDataFormat(EmployerRequest.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=GetEmployerDetails")
		.setHeader("system",constant("ODS"))
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetEmployerDetails?bridgeEndpoint=true")
		
		.choice()
			.when().simple("${body} != null")
				.to("bean:employerDetailsService?method=prepaResponseBackend")
				.to("bean:employerDetailsService?method=prepareEmployerDetailsResponse")
				.setHeader("Content-Type", constant("application/json"))
			
			.otherwise()
				.to("bean:oUtils?method=prepareFaultNodeStr(\"EmployerDetailsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();


	}
}
