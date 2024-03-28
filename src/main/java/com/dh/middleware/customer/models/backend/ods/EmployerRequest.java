package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"GetEmployerDetailsRequest"
})
public class EmployerRequest {

	@JsonProperty("GetEmployerDetailsRequest")
	public EmployerDetailsRequest oEmployerDetailsRequest;

	public EmployerDetailsRequest getoEmployerDetailsRequest() {
		return oEmployerDetailsRequest;
	}

	public void setoEmployerDetailsRequest(EmployerDetailsRequest oEmployerDetailsRequest) {
		this.oEmployerDetailsRequest = oEmployerDetailsRequest;
	}

	@Override
	public String toString() {
		return "EmployerRequest [oEmployerDetailsRequest=" + oEmployerDetailsRequest + "]";
	}

}
