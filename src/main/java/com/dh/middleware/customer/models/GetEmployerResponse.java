package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEmployerResponse {

	@JsonProperty("GetEmployerDetailsResponse")
	public GetEmployerDetailsResponse oGetEmployerDetailsResponse;

	public GetEmployerDetailsResponse getoGetEmployerDetailsResponse() {
		return oGetEmployerDetailsResponse;
	}

	public void setoGetEmployerDetailsResponse(GetEmployerDetailsResponse oGetEmployerDetailsResponse) {
		this.oGetEmployerDetailsResponse = oGetEmployerDetailsResponse;
	}

	@Override
	public String toString() {
		return "GetEmployerResponse [oGetEmployerDetailsResponse=" + oGetEmployerDetailsResponse + "]";
	}

}
