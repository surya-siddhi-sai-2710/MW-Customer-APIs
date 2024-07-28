package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCertificateRequestType {

	@JsonProperty("GetBalanceCertificateDetailsRequest")
	public BalanceCertificateRequest balanceCertificateDetailsRequestBknd;

	public BalanceCertificateRequest getBalanceCertificateDetailsRequestBknd() {
		return balanceCertificateDetailsRequestBknd;
	}

	public void setBalanceCertificateDetailsRequestBknd(
			BalanceCertificateRequest balanceCertificateDetailsRequestBknd) {
		this.balanceCertificateDetailsRequestBknd = balanceCertificateDetailsRequestBknd;
	}

	@Override
	public String toString() {
		return "BalanceCertificateDetailsRequestType [balanceCertificateDetailsRequestBknd="
				+ balanceCertificateDetailsRequestBknd + "]";
	}
	
	
}
