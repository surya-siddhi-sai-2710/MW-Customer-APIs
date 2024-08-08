package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "LastSixMonthsAccountBalanceRequest",
    "LastSixMonthsAccountBalanceResponse"
})
public class LastSixMonthsAccountBalance {

	@JsonProperty("LastSixMonthsAccountBalanceRequest")
	private LastSixMonthsAccountBalanceRequest lastSixMonthsAccountBalanceRequest;
	
	@JsonProperty("LastSixMonthsAccountBalanceResponse")
	private LastSixMonthsAccountBalanceResponse lastSixMonthsAccountBalanceResponse;

	public LastSixMonthsAccountBalanceRequest getLastSixMonthsAccountBalanceRequest() {
		return lastSixMonthsAccountBalanceRequest;
	}

	public void setLastSixMonthsAccountBalanceRequest(
			LastSixMonthsAccountBalanceRequest lastSixMonthsAccountBalanceRequest) {
		this.lastSixMonthsAccountBalanceRequest = lastSixMonthsAccountBalanceRequest;
	}

	public LastSixMonthsAccountBalanceResponse getLastSixMonthsAccountBalanceResponse() {
		return lastSixMonthsAccountBalanceResponse;
	}

	public void setLastSixMonthsAccountBalanceResponse(
			LastSixMonthsAccountBalanceResponse lastSixMonthsAccountBalanceResponse) {
		this.lastSixMonthsAccountBalanceResponse = lastSixMonthsAccountBalanceResponse;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalance [lastSixMonthsAccountBalanceRequest=" + lastSixMonthsAccountBalanceRequest
				+ ", lastSixMonthsAccountBalanceResponse=" + lastSixMonthsAccountBalanceResponse + "]";
	}
	
	
}
