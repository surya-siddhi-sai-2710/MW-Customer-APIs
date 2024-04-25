package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerNORResultsRequestType {

	@JsonProperty("GetCustomerNORResultsRequest")
	private CustomerNORResultsRequest customerNORResultsRequest;

	public CustomerNORResultsRequest getCustomerNORResultsRequest() {
		return customerNORResultsRequest;
	}

	public void setCustomerNORResultsRequest(CustomerNORResultsRequest customerNORResultsRequest) {
		this.customerNORResultsRequest = customerNORResultsRequest;
	}

	@Override
	public String toString() {
		return "CustomerNORResultsRequestType [customerNORResultsRequest=" + customerNORResultsRequest + "]";
	}

	
}
