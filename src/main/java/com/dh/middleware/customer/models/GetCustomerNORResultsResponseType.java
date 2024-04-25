
package com.dh.middleware.customer.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GetCustomerNORResultsResponseType" })
public class GetCustomerNORResultsResponseType {
	
	 @JsonProperty("GetCustomerNORResultsResponse")
	 protected GetCustomerNORResultsResponse customerNORResultsResponse;

	public GetCustomerNORResultsResponse getCustomerNORResultsResponse() {
		return customerNORResultsResponse;
	}

	public void setCustomerNORResultsResponse(GetCustomerNORResultsResponse customerNORResultsResponse) {
		this.customerNORResultsResponse = customerNORResultsResponse;
	}

	@Override
	public String toString() {
		return "GetCustomerNORResultsResponseType [customerNORResultsResponse=" + customerNORResultsResponse + "]";
	}
	
	

//    protected List<GetCustomerNOResultsRecordType> record;
//
//
//    public List<GetCustomerNOResultsRecordType> getRecord() {
//        if (record == null) {
//            record = new ArrayList<GetCustomerNOResultsRecordType>();
//        }
//        return this.record;
//   }

}
