package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEmployerRequest {

	@JsonProperty("GetEmployerDetailsRequest")
	public GetEmployerDetailsRequest employerDetailsRequest;

	public GetEmployerDetailsRequest getEmployerDetailsRequest() {
		return employerDetailsRequest;
	}

	public void setEmployerDetailsRequest(GetEmployerDetailsRequest employerDetailsRequest) {
		this.employerDetailsRequest = employerDetailsRequest;
	}

	@Override
	public String toString() {
		return "GetEmployerRequest [employerDetailsRequest=" + employerDetailsRequest + "]";
	}

}
