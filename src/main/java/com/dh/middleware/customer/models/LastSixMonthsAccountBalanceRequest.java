
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "LastSixMonthsAccountBalanceRequest"
})
public class LastSixMonthsAccountBalanceRequest {

	@JsonProperty("shortCIF")
    protected String shortCIF;

    public String getShortCIF() {
        return shortCIF;
    }


    public void setShortCIF(String value) {
        this.shortCIF = value;
    }

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceRequest [shortCIF=" + shortCIF + "]";
	}
    
    

}
