package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDetailsResponse {

	@JsonProperty("success")
	protected EmployerDetailsSuccessType success;

	public EmployerDetailsSuccessType getSuccess() {
		return success;
	}

	public void setSuccess(EmployerDetailsSuccessType success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "GetEmployerDetailsResponseBackend []";
	}

}
