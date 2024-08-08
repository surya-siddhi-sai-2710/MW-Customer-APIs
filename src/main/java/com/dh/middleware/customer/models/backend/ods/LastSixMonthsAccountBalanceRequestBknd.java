package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastSixMonthsAccountBalanceRequestBknd {

	@JsonProperty("shortCIF")
    protected String shortCIF;

	public String getShortCIF() {
		return shortCIF;
	}

	public void setShortCIF(String shortCIF) {
		this.shortCIF = shortCIF;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceRequestBknd [shortCIF=" + shortCIF + "]";
	}

	
	
}
