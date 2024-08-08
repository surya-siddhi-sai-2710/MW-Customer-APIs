
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "LastSixMonthsAccountBalanceResponse" })
public class LastSixMonthsAccountBalanceResponse {

	@JsonProperty("success")
    protected LastSixMonthsAccountBalanceResponseType success;
    
    public LastSixMonthsAccountBalanceResponseType getSuccess() {
        return success;
    }

    public void setSuccess(LastSixMonthsAccountBalanceResponseType value) {
        this.success = value;
    }

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceResponse [success=" + success + "]";
	}

   

}
