
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetEmployerDetailsSuccessType" })
public class GetEmployerDetailsSuccessType {

	@JsonProperty("cif")
	protected String cif;

	@JsonProperty("employerAccountNumber")
	protected String employerAccountNumber;

	@JsonProperty("employerCode")
	protected String employerCode;

	@JsonProperty("employerName")
	protected String employerName;

	@JsonProperty("annualFeeAmount")
	protected Integer annualFeeAmount;

	public String getCif() {
		return cif;
	}

	public void setCif(String value) {
		this.cif = value;
	}

	public String getEmployerAccountNumber() {
		return employerAccountNumber;
	}

	public void setEmployerAccountNumber(String value) {
		this.employerAccountNumber = value;
	}

	public String getEmployerCode() {
		return employerCode;
	}

	public void setEmployerCode(String value) {
		this.employerCode = value;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String value) {
		this.employerName = value;
	}

	public Integer getAnnualFeeAmount() {
		return annualFeeAmount;
	}

	public void setAnnualFeeAmount(Integer value) {
		this.annualFeeAmount = value;
	}

	@Override
	public String toString() {
		return "GetEmployerDetailsSuccessType [cif=" + cif + ", employerAccountNumber=" + employerAccountNumber
				+ ", employerCode=" + employerCode + ", employerName=" + employerName + ", annualFeeAmount="
				+ annualFeeAmount + "]";
	}

}
