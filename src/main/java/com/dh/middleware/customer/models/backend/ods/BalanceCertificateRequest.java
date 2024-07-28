package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCertificateRequest {

	@JsonProperty("cif")
    protected String cif;
	
	@JsonProperty("balanceDate")
    protected String balanceDate;

    public String getCif() {
        return cif;
    }

    public void setCif(String value) {
        this.cif = value;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String value) {
        this.balanceDate = value;
    }

	@Override
	public String toString() {
		return "BalanceCertificateDetailsRequestBknd [cif=" + cif + ", balanceDate=" + balanceDate + "]";
	}
}
