package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerNORResultsResponseType {

	@JsonProperty("GetCustomerNORResultsResponse")
	 protected CustomerNORResultsResponse customerNORResultsResponse;

	public CustomerNORResultsResponse getCustomerNORResultsResponse() {
		return customerNORResultsResponse;
	}

	public void setCustomerNORResultsResponse(CustomerNORResultsResponse customerNORResultsResponse) {
		this.customerNORResultsResponse = customerNORResultsResponse;
	}

	@Override
	public String toString() {
		return "CustomerNORResultsResponseType [customerNORResultsResponse=" + customerNORResultsResponse + "]";
	}
	
	
}
