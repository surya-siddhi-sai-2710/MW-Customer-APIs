package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerNORResultsRequest {

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
