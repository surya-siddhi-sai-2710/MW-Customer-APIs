
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cif",
    "balance",
    "currency",
    "balanceDate"
})
public class MonthlyBalanceType {

    protected String cif;
    protected Double balance;
    protected String currency;
    protected String balanceDate;


    public String getCif() {
        return cif;
    }

    public void setCif(String value) {
        this.cif = value;
    }


    public Double getBalance() {
        return balance;
    }


    public void setBalance(Double value) {
        this.balance = value;
    }

    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String value) {
        this.currency = value;
    }


    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String value) {
        this.balanceDate = value;
    }

	@Override
	public String toString() {
		return "MonthlyBalanceType [cif=" + cif + ", balance=" + balance + ", currency=" + currency + ", balanceDate="
				+ balanceDate + "]";
	}
    

}
