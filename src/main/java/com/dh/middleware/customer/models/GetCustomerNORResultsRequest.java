
package com.dh.middleware.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetCustomerNORResultsRequest" })
public class GetCustomerNORResultsRequest {

	@JsonProperty("employeeId")
    protected String employeeId;
	
	@JsonProperty("rmPosition")
    protected String rmPosition;
	
	@JsonProperty("groupedBy")
    protected String groupedBy;
	
	@JsonProperty("selectedCategory")
    protected String selectedCategory;
	
	@JsonProperty("cif")
    protected String cif;

    public String getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(String value) {
        this.employeeId = value;
    }


    public String getRmPosition() {
        return rmPosition;
    }


    public void setRmPosition(String value) {
        this.rmPosition = value;
    }


    public String getGroupedBy() {
        return groupedBy;
    }


    public void setGroupedBy(String value) {
        this.groupedBy = value;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }


    public void setSelectedCategory(String value) {
        this.selectedCategory = value;
    }


    public String getCif() {
        return cif;
    }


    public void setCif(String value) {
        this.cif = value;
    }

}
