package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBackend {

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
	
	@JsonProperty("accountStatusDescription")
    protected String balanceDate;
	
	@JsonProperty("balanceStatus")
    protected String balanceStatus;

	
    public String getCif() {
        return cif;
    }

    public void setCif(String value) {
        this.cif = value;
    }

    public String getAccountId() {
        return accountId;
    }


    public void setAccountId(String value) {
        this.accountId = value;
    }


    public Double getBalanceSAR() {
        return balanceSAR;
    }


    public void setBalanceSAR(Double value) {
        this.balanceSAR = value;
    }


    public Double getBalanceFCY() {
        return balanceFCY;
    }


    public void setBalanceFCY(Double value) {
        this.balanceFCY = value;
    }


    public String getAccountType() {
        return accountType;
    }


    public void setAccountType(String value) {
        this.accountType = value;
    }


    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String value) {
        this.productCategory = value;
    }

    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String value) {
        this.currency = value;
    }

    public String getAccountStatusCode() {
        return accountStatusCode;
    }


    public void setAccountStatusCode(String value) {
        this.accountStatusCode = value;
    }

    public String getAccountStatusDescription() {
        return accountStatusDescription;
    }


    public void setAccountStatusDescription(String value) {
        this.accountStatusDescription = value;
    }

    public String getBalanceDate() {
        return balanceDate;
    }


    public void setBalanceDate(String value) {
        this.balanceDate = value;
    }

    public String getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(String value) {
        this.balanceStatus = value;
    }

	@Override
	public String toString() {
		return "BalanceCertificateAccountsTypeBknd [cif=" + cif + ", accountId=" + accountId + ", balanceSAR=" + balanceSAR
				+ ", balanceFCY=" + balanceFCY + ", accountType=" + accountType + ", productCategory=" + productCategory
				+ ", currency=" + currency + ", accountStatusCode=" + accountStatusCode + ", accountStatusDescription="
				+ accountStatusDescription + ", balanceDate=" + balanceDate + ", balanceStatus=" + balanceStatus + "]";
	}
}
