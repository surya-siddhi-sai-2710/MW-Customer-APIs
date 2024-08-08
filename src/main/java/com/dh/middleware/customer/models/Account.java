
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

	@JsonProperty("cif")
	protected String cif;

	@JsonProperty("accountId")
	protected String accountId;

	@JsonProperty("balanceSAR")
	protected Double balanceSAR;

	@JsonProperty("balanceFCY")
	protected Double balanceFCY;

	@JsonProperty("accountType")
	protected String accountType;

	@JsonProperty("productCategory")
	protected String productCategory;

	@JsonProperty("currency")
	protected String currency;

	@JsonProperty("accountStatusCode")
	protected String accountStatusCode;

	@JsonProperty("accountStatusDescription")
	protected String accountStatusDescription;

	@JsonProperty("balanceDate")
	protected String balanceDate;

	@JsonProperty("balanceStatus")
	protected String balanceStatus;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getBalanceSAR() {
		return balanceSAR;
	}

	public void setBalanceSAR(Double balanceSAR) {
		this.balanceSAR = balanceSAR;
	}

	public Double getBalanceFCY() {
		return balanceFCY;
	}

	public void setBalanceFCY(Double balanceFCY) {
		this.balanceFCY = balanceFCY;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountStatusCode() {
		return accountStatusCode;
	}

	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}

	public String getAccountStatusDescription() {
		return accountStatusDescription;
	}

	public void setAccountStatusDescription(String accountStatusDescription) {
		this.accountStatusDescription = accountStatusDescription;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getBalanceStatus() {
		return balanceStatus;
	}

	public void setBalanceStatus(String balanceStatus) {
		this.balanceStatus = balanceStatus;
	}

	@Override
	public String toString() {
		return "BalanceCertificateAccountsType [cif=" + cif + ", accountId=" + accountId + ", balanceSAR=" + balanceSAR
				+ ", balanceFCY=" + balanceFCY + ", accountType=" + accountType + ", productCategory=" + productCategory
				+ ", currency=" + currency + ", accountStatusCode=" + accountStatusCode + ", accountStatusDescription="
				+ accountStatusDescription + ", balanceDate=" + balanceDate + ", balanceStatus=" + balanceStatus + "]";
	}

}
