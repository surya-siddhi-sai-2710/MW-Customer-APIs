package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastSixMonthsAccountBalanceResponseBknd {

	@JsonProperty("success")
    protected LastSixMonthsAccountBalanceResponseTypeBknd success;

	public LastSixMonthsAccountBalanceResponseTypeBknd getSuccess() {
		return success;
	}

	public void setSuccess(LastSixMonthsAccountBalanceResponseTypeBknd success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceResponseBknd [success=" + success + "]";
	}
	
	
	
}
