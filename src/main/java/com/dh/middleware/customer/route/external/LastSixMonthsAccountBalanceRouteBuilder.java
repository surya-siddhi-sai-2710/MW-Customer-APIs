package com.dh.middleware.customer.route.external;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.LastSixMonthsAccountBalance;

@ Component
public class LastSixMonthsAccountBalanceRouteBuilder extends RouteBuilder{

	@Override
	public void configure() {

		restConfiguration().bindingMode(RestBindingMode.json);
		
		rest("/api/customer")
		.post("/v1/GetLastSixMonthsAccountBalance")
		.consumes("application/json")
		.type(LastSixMonthsAccountBalance.class)
		.to("direct:getLastSixMonthsAccountBalanceRoute");
		
		onException(Exception.class)
		.to("bean:oUtils?method=onException(${exchange},\"LastSixMonthsAccountBalanceResponse\",${header.system})")
		.handled(true);

		from("direct:getLastSixMonthsAccountBalanceRoute").routeId("GetLastSixMonthsAccountBalance")
		.setHeader("system",constant("MW"))
		.to("bean:lastSixMonthsAccountBalanceService?method=setLastSixMonthsAccountBalanceRequestIn")
		.to("bean:lastSixMonthsAccountBalanceService?method=prepareLastSixMonthsAccountBalanceBackendRequest")
		
		.marshal(new JacksonDataFormat(LastSixMonthsAccountBalance.class))
		
//		.to("bean:ODSDBConnectorImplDao?method=LastSixMonthsAccountBalance")
		
		.setHeader("system",constant("ODS"))
		.to("{{ODSDBConnector.host}}{{ODSDBConnector.contextPath}}"+"/v1/GetLastSixMonthsAccountBalance?bridgeEndpoint=true")
		
		.choice()
			.when().simple("${body} != null")
				.to("bean:lastSixMonthsAccountBalanceService?method=prepareResponseBackend")
				.to("bean:lastSixMonthsAccountBalanceService?method=prepareLastSixMonthsAccountBalanceResponse")
				.setHeader("Content-Type", constant("application/json"))
				
			.otherwise()
					.to("bean:oUtils?method=prepareFaultNodeStr(\"LastSixMonthsAccountBalanceResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.endChoice();
	}
}
