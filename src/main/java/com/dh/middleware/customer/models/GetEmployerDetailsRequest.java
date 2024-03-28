
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetEmployerDetailsRequest" })
public class GetEmployerDetailsRequest {

	@JsonProperty("cif")
	protected String cif;

	@JsonProperty("accountNumber")
	protected String accountNumber;

	public String getCif() {
		return cif;
	}

	public void setCif(String value) {
		this.cif = value;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String value) {
		this.accountNumber = value;
	}

	@Override
	public String toString() {
		return "GetEmployerDetailsRequest [cif=" + cif + ", accountNumber=" + accountNumber + "]";
	}

}
