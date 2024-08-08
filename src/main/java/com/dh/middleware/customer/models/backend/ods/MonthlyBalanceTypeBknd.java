package com.dh.middleware.customer.models.backend.ods;

public class MonthlyBalanceTypeBknd {

	protected String cif;
	protected Double balance;
	protected String currency;
	protected String balanceDate;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	@Override
	public String toString() {
		return "MonthlyBalanceTypeBknd [cif=" + cif + ", balance=" + balance + ", currency=" + currency
				+ ", balanceDate=" + balanceDate + "]";
	}

}
