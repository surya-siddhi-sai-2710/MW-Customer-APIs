package com.dh.middleware.customer.models;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "record"
})
public class Success {

	@JsonProperty("record")
	private List<Record> record = new ArrayList<Record>();

	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "Success [record=" + record + "]";
	}
	
	
}
