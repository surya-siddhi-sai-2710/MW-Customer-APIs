package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCertificateResponse {

	@JsonProperty("success")
	protected BalanceCertificateSuccessBknd success;

	public BalanceCertificateSuccessBknd getSuccess() {
		return success;
	}

	public void setSuccess(BalanceCertificateSuccessBknd value) {
		this.success = value;
	}

	@Override
	public String toString() {
		return "BalanceCertificateDetailsResponseTypeBknd [success=" + success + "]";
	}
}
