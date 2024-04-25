package com.dh.middleware.customer.models.backend.ods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordBackend {

	@JsonProperty("month")
	private String month;
	
	@JsonProperty("yearId")
	private String yearId;
	
	@JsonProperty("quarter")
	private String quarter;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("subType")
	private String subType;
	
	@JsonProperty("monthActual")
	private String monthActual;
	
	@JsonProperty("ytdActual")
	private String ytdActual;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getMonthActual() {
		return monthActual;
	}

	public void setMonthActual(String monthActual) {
		this.monthActual = monthActual;
	}

	public String getYtdActual() {
		return ytdActual;
	}

	public void setYtdActual(String ytdActual) {
		this.ytdActual = ytdActual;
	}

	@Override
	public String toString() {
		return "Record [month=" + month + ", yearId=" + yearId + ", quarter=" + quarter + ", category=" + category
				+ ", subType=" + subType + ", monthActual=" + monthActual + ", ytdActual=" + ytdActual + "]";
	}
}
