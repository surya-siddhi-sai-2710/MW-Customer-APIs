package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCertificateResponseType {

	@JsonProperty("GetBalanceCertificateDetailsResponse")
	public BalanceCertificateResponse balanceCertificateDetailsResponseBknd;

	public BalanceCertificateResponse getBalanceCertificateDetailsResponseBknd() {
		return balanceCertificateDetailsResponseBknd;
	}

	public void setBalanceCertificateDetailsResponseBknd(
			BalanceCertificateResponse balanceCertificateDetailsResponseBknd) {
		this.balanceCertificateDetailsResponseBknd = balanceCertificateDetailsResponseBknd;
	}

	@Override
	public String toString() {
		return "BalanceCertificateDetailsResponseType [balanceCertificateDetailsResponseBknd="
				+ balanceCertificateDetailsResponseBknd + "]";
	}
	
	
}
