package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastSixMonthsAccountBalanceRequestTypeBknd {

	@JsonProperty("LastSixMonthsAccountBalanceRequest")
	public LastSixMonthsAccountBalanceRequestBknd oAccountBalanceRequestBknd;

	public LastSixMonthsAccountBalanceRequestBknd getoAccountBalanceRequestBknd() {
		return oAccountBalanceRequestBknd;
	}

	public void setoAccountBalanceRequestBknd(LastSixMonthsAccountBalanceRequestBknd oAccountBalanceRequestBknd) {
		this.oAccountBalanceRequestBknd = oAccountBalanceRequestBknd;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceRequestTypeBknd [oAccountBalanceRequestBknd=" + oAccountBalanceRequestBknd
				+ "]";
	}
	
	
}
