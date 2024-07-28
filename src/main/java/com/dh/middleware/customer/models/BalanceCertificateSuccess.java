
package com.dh.middleware.customer.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "BalanceCertificateDetailsResponseType" })
public class BalanceCertificateSuccess {

	@JsonProperty("account")
	protected List<Account> account;

	public List<Account> getAccount() {
		if (account == null) {
			account = new ArrayList<Account>();
		}
		return this.account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "BalanceCertificateDetailsResponseType [account=" + account + "]";
	}

}
