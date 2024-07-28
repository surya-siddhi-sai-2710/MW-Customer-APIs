package com.dh.middleware.customer.models.backend.ods;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCertificateSuccessBknd {

	@JsonProperty("account")
	protected List<AccountBackend> account;

	public List<AccountBackend> getAccount() {
		if (account == null) {
			account = new ArrayList<AccountBackend>();
		}
		return this.account;
	}

	public void setAccount(List<AccountBackend> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "BalanceCertificateDetailsResponseTypeBknd [account=" + account + "]";
	}
}
