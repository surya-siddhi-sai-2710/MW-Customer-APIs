package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastSixMonthsAccountBalanceResponseTypeBknd {

	@JsonProperty("averageSixMonthsBalance")
    protected Double averageSixMonthsBalance;
	
	@JsonProperty("accountBalance")
    protected AccountBalanceTypeBknd accountBalance;

	public Double getAverageSixMonthsBalance() {
		return averageSixMonthsBalance;
	}

	public void setAverageSixMonthsBalance(Double averageSixMonthsBalance) {
		this.averageSixMonthsBalance = averageSixMonthsBalance;
	}

	public AccountBalanceTypeBknd getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(AccountBalanceTypeBknd accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceResponseTypeBknd [averageSixMonthsBalance=" + averageSixMonthsBalance
				+ ", accountBalance=" + accountBalance + "]";
	}
	
	
}
