package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerResponse {

	@JsonProperty("GetEmployerDetailsResponse")
	public EmployerDetailsResponse oGetEmployerDetailsResponse;

	public EmployerDetailsResponse getoGetEmployerDetailsResponseBackend() {
		return oGetEmployerDetailsResponse;
	}

	public void setoGetEmployerDetailsResponseBackend(EmployerDetailsResponse oGetEmployerDetailsResponse) {
		this.oGetEmployerDetailsResponse = oGetEmployerDetailsResponse;
	}

	@Override
	public String toString() {
		return "GetEmployerResponseBackend [oGetEmployerDetailsResponseBackend=" + oGetEmployerDetailsResponse + "]";
	}

}
