package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCustomerNORResultsRequestType {

	@JsonProperty("GetCustomerNORResultsRequest")
	private GetCustomerNORResultsRequest customerNORResultsRequest;

	public GetCustomerNORResultsRequest getCustomerNORResultsRequest() {
		return customerNORResultsRequest;
	}

	public void setCustomerNORResultsRequest(GetCustomerNORResultsRequest customerNORResultsRequest) {
		this.customerNORResultsRequest = customerNORResultsRequest;
	}

	@Override
	public String toString() {
		return "GetCustomerNORResultsRequestType [customerNORResultsRequest=" + customerNORResultsRequest + "]";
	}
	
	
}
