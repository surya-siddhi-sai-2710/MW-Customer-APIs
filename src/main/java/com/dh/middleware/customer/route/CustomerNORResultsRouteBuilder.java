
package com.dh.middleware.customer.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetCustomerNORResultsRequest;
import com.dh.middleware.customer.models.GetCustomerNORResultsRequestType;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsRequest;

@Component
public class CustomerNORResultsRouteBuilder extends RouteBuilder{

	@Override
	public void configure() {
		
		restConfiguration().bindingMode(RestBindingMode.json);
		
		 rest("/api/customer")
		.post("/v1/GetCustomerNORRestuls/")
		.consumes("application/json")
		.produces("application/json")
		.type(GetCustomerNORResultsRequestType.class)
		.to("direct:getCustomerNORRestulsRoute");
		 
		 onException(Exception.class).log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"CustomerNORResultsResponse\",\"MW\")")
		.log("Exception" + "${exception}")
		.handled(true).setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));
		 
		 from("direct:getCustomerNORRestulsRoute").routeId("getCustomerNORRestulsRoute")
		.to("bean:customerNORResultsService?method=setCustomerNORResultsRequestIn")
		.to("bean:customerNORResultsService?method=prepareCustomerNORResultsRequest")
		
		.marshal(new JacksonDataFormat(CustomerNORResultsRequest.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=GetCustomerNORREsults")
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetCustomerNORREsults?bridgeEndpoint=true")
		
		.choice()
			.when().simple("${body} != null")
				.to("bean:customerNORResultsService?method=prepareResponseBackend")
				.to("bean:customerNORResultsService?method=prepareCustomerNORResultsResponse")
				.setHeader("Content-Type", constant("application/json"))
		
			.otherwise()
					.to("bean:utils?method=prepareFaultNodeStr(\"CustomerNORResultsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();
	}
}
