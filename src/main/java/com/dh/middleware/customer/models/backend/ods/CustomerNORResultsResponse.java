package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerNORResultsResponse {

	@JsonProperty("success")
	private SuccessBackend success;

	public SuccessBackend getSuccesBackend() {
		return success;
	}

	public void setSuccessBackend(SuccessBackend success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "CustomerNORResultsResponse [success=" + success + "]";
	}
}
