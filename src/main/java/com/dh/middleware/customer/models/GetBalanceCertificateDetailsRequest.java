
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetBalanceCertificateDetailsRequest" })
public class GetBalanceCertificateDetailsRequest {

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
		return "GetBalanceCertificateDetailsRequest [cif=" + cif + ", balanceDate=" + balanceDate + "]";
	}
    
    

}
