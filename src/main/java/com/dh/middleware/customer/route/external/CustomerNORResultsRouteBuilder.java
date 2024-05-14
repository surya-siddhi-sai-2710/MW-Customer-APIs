
package com.dh.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetCustomerNORResultsType;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsRequest;

@Component
public class CustomerNORResultsRouteBuilder extends RouteBuilder{

	@Override
	public void configure() {
		
		restConfiguration().bindingMode(RestBindingMode.json);
		
		 rest("/api/customer")
		.post("/v1/GetCustomerNORRestuls")
		.consumes("application/json")
		.produces("application/json")
		.type(GetCustomerNORResultsType.class)
		.to("direct:getCustomerNORRestulsRoute");
		 
		 onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"CustomerNORResultsResponse\",${header.system})")
		.handled(true);
		 
		from("direct:getCustomerNORRestulsRoute").routeId("GetCustomerNORRestuls")
		.setHeader("system",constant("MW"))
		.to("bean:customerNORResultsService?method=setCustomerNORResultsRequestIn")
		.to("bean:customerNORResultsService?method=prepareCustomerNORResultsRequest")
		
		.marshal(new JacksonDataFormat(CustomerNORResultsRequest.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=GetCustomerNORREsults")
		.setHeader("system",constant("ODS"))
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetCustomerNORREsults?bridgeEndpoint=true")
		
		.choice()
			.when().simple("${body} != null")
				.to("bean:customerNORResultsService?method=prepareResponseBackend")
				.to("bean:customerNORResultsService?method=prepareCustomerNORResultsResponse")
				.setHeader("Content-Type", constant("application/json"))
		
			.otherwise()
					.to("bean:oUtils?method=prepareFaultNodeStr(\"CustomerNORResultsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();
	}
}
