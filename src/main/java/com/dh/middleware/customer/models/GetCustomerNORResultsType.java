package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetCustomerNORResultsRequest",
    "GetCustomerNORResultsResponse"
})
public class GetCustomerNORResultsType {

	@JsonProperty("GetCustomerNORResultsRequest")
	private GetCustomerNORResultsRequest customerNORResultsRequest;
	
	@JsonProperty("GetCustomerNORResultsResponse")
	 protected GetCustomerNORResultsResponse customerNORResultsResponse;

	public GetCustomerNORResultsRequest getCustomerNORResultsRequest() {
		return customerNORResultsRequest;
	}

	public void setCustomerNORResultsRequest(GetCustomerNORResultsRequest customerNORResultsRequest) {
		this.customerNORResultsRequest = customerNORResultsRequest;
	}

	public GetCustomerNORResultsResponse getCustomerNORResultsResponse() {
		return customerNORResultsResponse;
	}

	public void setCustomerNORResultsResponse(GetCustomerNORResultsResponse customerNORResultsResponse) {
		this.customerNORResultsResponse = customerNORResultsResponse;
	}
	
	

}
