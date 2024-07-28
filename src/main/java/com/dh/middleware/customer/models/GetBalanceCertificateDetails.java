package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBalanceCertificateDetails {

	@JsonProperty("GetBalanceCertificateDetailsRequest")
	private GetBalanceCertificateDetailsRequest balanceCertificateDetailsRequest;
	
	@JsonProperty("GetBalanceCertificateDetailsResponse")
	private GetBalanceCertificateDetailsResponse balanceCertificateDetailsResponse;

	public GetBalanceCertificateDetailsRequest getBalanceCertificateDetailsRequest() {
		return balanceCertificateDetailsRequest;
	}

	public void setBalanceCertificateDetailsRequest(
			GetBalanceCertificateDetailsRequest balanceCertificateDetailsRequest) {
		this.balanceCertificateDetailsRequest = balanceCertificateDetailsRequest;
	}

	public GetBalanceCertificateDetailsResponse getBalanceCertificateDetailsResponse() {
		return balanceCertificateDetailsResponse;
	}

	public void setBalanceCertificateDetailsResponse(
			GetBalanceCertificateDetailsResponse balanceCertificateDetailsResponse) {
		this.balanceCertificateDetailsResponse = balanceCertificateDetailsResponse;
	}

	@Override
	public String toString() {
		return "GetBalanceCertificateDetails [balanceCertificateDetailsRequest=" + balanceCertificateDetailsRequest
				+ ", balanceCertificateDetailsResponse=" + balanceCertificateDetailsResponse + "]";
	}

}
