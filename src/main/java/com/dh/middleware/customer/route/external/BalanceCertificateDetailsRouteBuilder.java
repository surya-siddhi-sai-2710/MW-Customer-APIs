package com.dh.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetBalanceCertificateDetails;
import com.dh.middleware.customer.models.GetBalanceCertificateDetailsRequest;

@Component
public class BalanceCertificateDetailsRouteBuilder extends RouteBuilder{

	@Override
	public void configure() {
		
		restConfiguration().bindingMode(RestBindingMode.json);
		
		rest("/api/customer")
		.post("/v1/GetBalanceCertificateDetails")
		.consumes("application/json")
		.produces("application/json")
		.type(GetBalanceCertificateDetails.class)
		.to("direct:getBalanceCertificateDetailsRoute");

		onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"BalanceCertificateDetailsResponse\",${header.system})")
		.handled(true);
		
		from("direct:getBalanceCertificateDetailsRoute").routeId("GetBalanceCertificateDetails")
		.setHeader("system",constant("MW"))
		.to("bean:balanceCertificateDetailsService?method=setBalanceCertificateDetailsRequestIn")
		.to("bean:balanceCertificateDetailsService?method=prepareBalanceCertificateDetailsRequest")
		
		.marshal(new JacksonDataFormat(GetBalanceCertificateDetailsRequest.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=GetBalanceCertificateDetails")
		
		.setHeader("system",constant("ODS"))
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetBalanceCertificateDetails?bridgeEndpoint=true")
		
		.choice()
			.when().simple("${body} != null")
				.to("bean:balanceCertificateDetailsService?method=prepareResponseBackend")
				.to("bean:balanceCertificateDetailsService?method=prepareBalanceCertificateDetailsResponse")
				.setHeader("Content-Type", constant("application/json"))
		
			.otherwise()
					.to("bean:oUtils?method=prepareFaultNodeStr(\"BalanceCertificateDetailsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();
		
	}
	
}
