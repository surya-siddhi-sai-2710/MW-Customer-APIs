
package com.dh.middleware.customer.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "monthlyBalance"
})
public class AccountBalanceType {

    protected List<MonthlyBalanceType> monthlyBalance;

    public List<MonthlyBalanceType> getMonthlyBalance() {
        if (monthlyBalance == null) {
            monthlyBalance = new ArrayList<MonthlyBalanceType>();
        }
        return this.monthlyBalance;
    }

	public void setMonthlyBalance(List<MonthlyBalanceType> monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}

	@Override
	public String toString() {
		return "AccountBalanceType []";
	}

    
}
