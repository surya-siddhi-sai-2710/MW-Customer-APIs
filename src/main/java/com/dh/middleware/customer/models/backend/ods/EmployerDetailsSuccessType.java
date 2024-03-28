package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDetailsSuccessType {

	@JsonProperty("cif")
	protected String cif;

	@JsonProperty("accountNumber")
	protected String employerAccountNumber;

	@JsonProperty("employerCode")
	protected String employerCode;

	@JsonProperty("employerName")
	protected String employerName;

	@JsonProperty("annualFeeAmount")
	protected Integer annualFeeAmount;

	public String getCifBackend() {
		return cif;
	}

	public void setCifBackend(String cif) {
		this.cif = cif;
	}

	public String getEmployerAccountNumberBackend() {
		return employerAccountNumber;
	}

	public void setEmployerAccountNumberBackend(String employerAccountNumber) {
		this.employerAccountNumber = employerAccountNumber;
	}

	public String getEmployerCodeBackend() {
		return employerCode;
	}

	public void setEmployerCodeBackend(String employerCode) {
		this.employerCode = employerCode;
	}

	public String getEmployerNameBackend() {
		return employerName;
	}

	public void setEmployerNameBackend(String employerName) {
		this.employerName = employerName;
	}

	public Integer getAnnualFeeAmountBackend() {
		return annualFeeAmount;
	}

	public void setAnnualFeeAmountBackend(Integer annualFeeAmount) {
		this.annualFeeAmount = annualFeeAmount;
	}

	@Override
	public String toString() {
		return "GetEmployerDetailsSuccessTypeBackend [cifBackend=" + cif + ", employerAccountNumberBackend="
				+ employerAccountNumber + ", employerCodeBackend=" + employerCode + ", employerNameBackend="
				+ employerName + ", annualFeeAmountBackend=" + annualFeeAmount + "]";
	}

}
