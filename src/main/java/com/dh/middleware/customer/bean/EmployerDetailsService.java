package com.dh.middleware.customer.bean;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.utility.Utils.StringUtil;
import com.dh.middleware.customer.models.GetEmployerDetails;
import com.dh.middleware.customer.models.GetEmployerDetailsRequest;
import com.dh.middleware.customer.models.GetEmployerDetailsResponse;
import com.dh.middleware.customer.models.GetEmployerDetailsSuccessType;
import com.dh.middleware.customer.models.ServiceHeader;
import com.dh.middleware.customer.models.backend.ods.EmployerDetailsRequest;
import com.dh.middleware.customer.models.backend.ods.EmployerDetailsResponse;
import com.dh.middleware.customer.models.backend.ods.EmployerDetailsSuccessType;
import com.dh.middleware.customer.models.backend.ods.EmployerRequest;
import com.dh.middleware.customer.models.backend.ods.EmployerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployerDetailsService {

//	public GetEmployerRequest oGetEmployerRequest;
	public GetEmployerDetailsRequest employerDetailsRequest;
	
	@Autowired
	StringUtil oStringUtil;

	private ServiceHeader oServiceHeader;

	@Autowired
	ObjectMapper objectMapper;

//	initilization request
	public void setEmployerDetailsRequestIn(GetEmployerDetails oGetEmployerRequest,
			@Header("ServiceHeader") String serviceHeader, Exchange ex) throws Exception {

		employerDetailsRequest = oGetEmployerRequest.getEmployerDetailsRequest();

		oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);

//		return employerDetailsRequest;
	}

//	Request mapping from frontend to backend model classes
	public EmployerRequest prepareGetEmployerDetailsBackendRequest(Exchange ex) throws Exception {
		EmployerRequest employerRequestBackend = new EmployerRequest();
		EmployerDetailsRequest detailsRequestBackend = new EmployerDetailsRequest();
		
		detailsRequestBackend.setCif(oStringUtil.setDefaultValue(employerDetailsRequest.getCif(),""));
		detailsRequestBackend.setAccountNumber(employerDetailsRequest.getAccountNumber());
		employerRequestBackend.setoEmployerDetailsRequest(detailsRequestBackend);

		return employerRequestBackend;
	}

//	DAO response mapping to backend response classes
	public EmployerResponse prepaResponseBackend(@Body JsonNode body, Exchange ex) throws Exception {

		JsonNode oEmployerDetailsNode = body.get("GetEmployerDetailsResponse");
//		JsonNode oSuccessNode = oEmployerDetailsNode.get("success");

		EmployerResponse employerResponseBackend = new EmployerResponse();
		EmployerDetailsResponse detailsResponseBackend = new EmployerDetailsResponse();
		EmployerDetailsSuccessType detailsSuccessTypeBackend = new EmployerDetailsSuccessType();

		detailsSuccessTypeBackend.setCifBackend(oEmployerDetailsNode.path("CIF").asText());
		detailsSuccessTypeBackend.setEmployerAccountNumberBackend(oEmployerDetailsNode.path("ACCNO").asText());
		detailsSuccessTypeBackend.setEmployerCodeBackend(oEmployerDetailsNode.path("EMPCODE").asText());
		detailsSuccessTypeBackend.setEmployerNameBackend(oEmployerDetailsNode.path("NAME").asText());
		detailsSuccessTypeBackend.setAnnualFeeAmountBackend(oEmployerDetailsNode.path("ANNUALFEEAMOUNT").asInt());

		detailsResponseBackend.setSuccess(detailsSuccessTypeBackend);
		employerResponseBackend.setoGetEmployerDetailsResponseBackend(detailsResponseBackend);

		return employerResponseBackend;
	}

//	Response mapping from backend to frontend model classes
	public GetEmployerDetails prepareEmployerDetailsResponse(EmployerResponse oGetEmployerResponseBackend)
			throws Exception {

		GetEmployerDetails employerResponse = new GetEmployerDetails();
		GetEmployerDetailsResponse detailsResponse = new GetEmployerDetailsResponse();
		
		GetEmployerDetailsSuccessType detailsSuccessType = new GetEmployerDetailsSuccessType();

		detailsSuccessType.setCif(
				oGetEmployerResponseBackend.getoGetEmployerDetailsResponseBackend().getSuccess().getCifBackend());
		detailsSuccessType.setEmployerAccountNumber(oGetEmployerResponseBackend.getoGetEmployerDetailsResponseBackend()
				.getSuccess().getEmployerAccountNumberBackend());
		detailsSuccessType.setEmployerCode(oGetEmployerResponseBackend.getoGetEmployerDetailsResponseBackend()
				.getSuccess().getEmployerCodeBackend());
		detailsSuccessType.setEmployerName(oGetEmployerResponseBackend.getoGetEmployerDetailsResponseBackend()
				.getSuccess().getEmployerNameBackend());
		detailsSuccessType.setAnnualFeeAmount(oGetEmployerResponseBackend.getoGetEmployerDetailsResponseBackend()
				.getSuccess().getAnnualFeeAmountBackend());

		detailsResponse.setSuccess(detailsSuccessType);
		employerResponse.setoGetEmployerDetailsResponse(detailsResponse);

		return employerResponse;
		
	}
}
