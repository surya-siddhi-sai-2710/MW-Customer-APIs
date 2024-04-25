package com.dh.middleware.customer.models.backend.ods;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessBackend {

	@JsonProperty("record")
	private List<RecordBackend> record = new ArrayList<RecordBackend>();

	public List<RecordBackend> getRecord() {
		return record;
	}

	public void setRecord(List<RecordBackend> record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "Success [record=" + record + "]";
	}
}
