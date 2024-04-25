
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetCustomerNORResultsResponse" })
public class GetCustomerNORResultsResponse {

	@JsonProperty("success")
	private Success success;

	public Success getSuccess() {
		return success;
	}

	public void setSuccess(Success success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "GetCustomerNORResultsResponse [success=" + success + "]";
	}
	
	

    
}
