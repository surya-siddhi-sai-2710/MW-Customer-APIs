package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"cif",
	"accountNumber"
})
public class EmployerDetailsRequest {

	@JsonProperty("cif")
	protected String cif;

	@JsonProperty("accountNumber")
	protected String accountNumber;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "EmployerDetailsRequest [cif=" + cif + ", accountNumber=" + accountNumber + "]";
	}

}
