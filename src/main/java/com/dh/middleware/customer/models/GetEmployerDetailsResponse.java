
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetEmployerDetailsResponse" })
public class GetEmployerDetailsResponse {

	@JsonProperty("success")
	protected GetEmployerDetailsSuccessType success;

	public GetEmployerDetailsSuccessType getSuccess() {
		return success;
	}

	public void setSuccess(GetEmployerDetailsSuccessType value) {
		this.success = value;
	}

	@Override
	public String toString() {
		return "GetEmployerDetailsResponse [success=" + success + "]";
	}

}
