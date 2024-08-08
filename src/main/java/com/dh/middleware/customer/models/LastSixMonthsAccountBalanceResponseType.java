
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "averageSixMonthsBalance",
    "accountBalance"
})
public class LastSixMonthsAccountBalanceResponseType {

	@JsonProperty("averageSixMonthsBalance")
    protected Double averageSixMonthsBalance;
	
	@JsonProperty("accountBalance")
    protected AccountBalanceType accountBalance;

    public Double getAverageSixMonthsBalance() {
        return averageSixMonthsBalance;
    }


    public void setAverageSixMonthsBalance(Double value) {
        this.averageSixMonthsBalance = value;
    }

    public AccountBalanceType getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(AccountBalanceType value) {
        this.accountBalance = value;
    }

}
