
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetBalanceCertificateDetailsResponse" })
public class GetBalanceCertificateDetailsResponse {

	@JsonProperty("success")
	protected BalanceCertificateSuccess success;

	public BalanceCertificateSuccess getSuccess() {
		return success;
	}

	public void setSuccess(BalanceCertificateSuccess value) {
		this.success = value;
	}

	@Override
	public String toString() {
		return "GetBalanceCertificateDetailsResponse [success=" + success + "]";
	}

}
