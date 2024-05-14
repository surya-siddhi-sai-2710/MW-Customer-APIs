package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetEmployerDetailsRequest",
    "GetEmployerDetailsResponse"
})
public class GetEmployerDetails {

	@JsonProperty("GetEmployerDetailsRequest")
	public GetEmployerDetailsRequest employerDetailsRequest;
	
	@JsonProperty("GetEmployerDetailsResponse")
	public GetEmployerDetailsResponse oGetEmployerDetailsResponse;

	
	public GetEmployerDetailsRequest getEmployerDetailsRequest() {
		return employerDetailsRequest;
	}


	public void setEmployerDetailsRequest(GetEmployerDetailsRequest employerDetailsRequest) {
		this.employerDetailsRequest = employerDetailsRequest;
	}


	public GetEmployerDetailsResponse getoGetEmployerDetailsResponse() {
		return oGetEmployerDetailsResponse;
	}


	public void setoGetEmployerDetailsResponse(GetEmployerDetailsResponse oGetEmployerDetailsResponse) {
		this.oGetEmployerDetailsResponse = oGetEmployerDetailsResponse;
	}


	@Override
	public String toString() {
		return "GetEmployerDetails [employerDetailsRequest=" + employerDetailsRequest + ", oGetEmployerDetailsResponse="
				+ oGetEmployerDetailsResponse + "]";
	}

	
}
