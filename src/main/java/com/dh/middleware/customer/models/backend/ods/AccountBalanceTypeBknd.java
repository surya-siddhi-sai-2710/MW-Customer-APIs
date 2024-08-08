package com.dh.middleware.customer.models.backend.ods;

import java.util.List;

public class AccountBalanceTypeBknd {

	protected List<MonthlyBalanceTypeBknd> monthlyBalance;

	public List<MonthlyBalanceTypeBknd> getMonthlyBalance() {
		return monthlyBalance;
	}

	public void setMonthlyBalance(List<MonthlyBalanceTypeBknd> monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}

	@Override
	public String toString() {
		return "AccountBalanceTypeBknd [monthlyBalance=" + monthlyBalance + "]";
	}
	
	
}
