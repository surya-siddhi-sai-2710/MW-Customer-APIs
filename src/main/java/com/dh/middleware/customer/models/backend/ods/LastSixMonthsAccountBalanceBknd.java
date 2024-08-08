package com.dh.middleware.customer.models.backend.ods;

public class LastSixMonthsAccountBalanceBknd {

	private LastSixMonthsAccountBalanceRequestBknd oAccountBalanceRequestBknd;
	private LastSixMonthsAccountBalanceResponseBknd oAccountBalanceResponseBknd;

	public LastSixMonthsAccountBalanceRequestBknd getoAccountBalanceRequestBknd() {
		return oAccountBalanceRequestBknd;
	}

	public void setoAccountBalanceRequestBknd(LastSixMonthsAccountBalanceRequestBknd oAccountBalanceRequestBknd) {
		this.oAccountBalanceRequestBknd = oAccountBalanceRequestBknd;
	}

	public LastSixMonthsAccountBalanceResponseBknd getoAccountBalanceResponseBknd() {
		return oAccountBalanceResponseBknd;
	}

	public void setoAccountBalanceResponseBknd(LastSixMonthsAccountBalanceResponseBknd oAccountBalanceResponseBknd) {
		this.oAccountBalanceResponseBknd = oAccountBalanceResponseBknd;
	}

	@Override
	public String toString() {
		return "LastSixMonthsAccountBalanceBknd [oAccountBalanceRequestBknd=" + oAccountBalanceRequestBknd
				+ ", oAccountBalanceResponseBknd=" + oAccountBalanceResponseBknd + "]";
	}
	
}
