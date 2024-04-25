package com.dh.middleware.customer.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dh.middleware.customer.models.GetCustomerNORResultsRequest;
import com.dh.middleware.customer.models.GetCustomerNORResultsRequestType;
import com.dh.middleware.customer.models.GetCustomerNORResultsResponse;
import com.dh.middleware.customer.models.GetCustomerNORResultsResponseType;
import com.dh.middleware.customer.models.ServiceHeader;
import com.dh.middleware.customer.models.Success;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsRequest;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsRequestType;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsResponse;
import com.dh.middleware.customer.models.backend.ods.CustomerNORResultsResponseType;
import com.dh.middleware.customer.models.backend.ods.RecordBackend;
import com.dh.middleware.customer.models.Record;
import com.dh.middleware.customer.models.backend.ods.SuccessBackend;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerNORResultsService {

	private ServiceHeader oServiceHeader;
	
	public  GetCustomerNORResultsRequest oGetCustomerNORResultsRequest;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void setCustomerNORResultsRequestIn( GetCustomerNORResultsRequestType getCustomerNORResultsRequestType,
			@Header("ServiceHeader") String serviceHeader, Exchange ex) throws Exception{
		
		oGetCustomerNORResultsRequest = getCustomerNORResultsRequestType.getCustomerNORResultsRequest();
		
		oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
	}
	
	public CustomerNORResultsRequestType prepareCustomerNORResultsRequest(Exchange ex) throws Exception{
		
		CustomerNORResultsRequestType oCustomerNORResultsRequestType = new CustomerNORResultsRequestType();
		CustomerNORResultsRequest oCustomerNORResultsRequest = new CustomerNORResultsRequest();
		
		oCustomerNORResultsRequest.setEmployeeId(oGetCustomerNORResultsRequest.getEmployeeId());
		oCustomerNORResultsRequest.setRmPosition(oGetCustomerNORResultsRequest.getRmPosition());
		oCustomerNORResultsRequest.setGroupedBy(oGetCustomerNORResultsRequest.getGroupedBy());
		oCustomerNORResultsRequest.setSelectedCategory(oGetCustomerNORResultsRequest.getSelectedCategory());
		oCustomerNORResultsRequest.setCif(oGetCustomerNORResultsRequest.getCif());
		
		oCustomerNORResultsRequestType.setCustomerNORResultsRequest(oCustomerNORResultsRequest);
		return oCustomerNORResultsRequestType;
	}
	
	
	public CustomerNORResultsResponseType prepareResponseBackend(@Body JsonNode body, Exchange ex) throws Exception{
		
		JsonNode oEmployerDetailsNode = body.get("GetCustomerNORResultsResponse");
//		JsonNode oSuccessNode = oEmployerDetailsNode.get("success");
//		JsonNode orecordArrayNode = oSuccessNode.get("record");
		
		CustomerNORResultsResponseType oCustomerNORResultsResponseType = new CustomerNORResultsResponseType();
		CustomerNORResultsResponse oCustomerNORResultsResponse = new CustomerNORResultsResponse();
		SuccessBackend oSuccessBackend = new SuccessBackend();
		List<RecordBackend> oRecordList = new ArrayList<RecordBackend>();
		
		RecordBackend oRecordBackend = new RecordBackend();
		
		oRecordBackend.setMonth(oEmployerDetailsNode.get("MONTH").asText());
		oRecordBackend.setYearId(oEmployerDetailsNode.get("YEAR_ID").asText());
		oRecordBackend.setQuarter(oEmployerDetailsNode.get("QUARTER").asText());
		oRecordBackend.setCategory(oEmployerDetailsNode.get("CATEGORY").asText());
		oRecordBackend.setSubType(oEmployerDetailsNode.get("SUB_TYPE").asText());
		oRecordBackend.setMonthActual(oEmployerDetailsNode.get("MONTH_ACTUAL").asText());
		oRecordBackend.setYtdActual(oEmployerDetailsNode.get("YTD_ACTUAL").asText());
		
		oRecordList.add(oRecordBackend);
		oSuccessBackend.setRecord(oRecordList);
		oCustomerNORResultsResponse.setSuccessBackend(oSuccessBackend);
		oCustomerNORResultsResponseType.setCustomerNORResultsResponse(oCustomerNORResultsResponse);
		
		return oCustomerNORResultsResponseType;
	}
	
	
	public GetCustomerNORResultsResponseType prepareCustomerNORResultsResponse(CustomerNORResultsResponseType oCustomerNORResultsResponseType) throws Exception{
		
		GetCustomerNORResultsResponseType oGetCustomerNORResultsResponseType = new GetCustomerNORResultsResponseType();
		GetCustomerNORResultsResponse oGetCustomerNORResultsResponse = new GetCustomerNORResultsResponse();
		Success oSuccess = new Success();
		List<Record> oRecordList = new ArrayList<Record>();
		Record oRecord = null;
		
		List<RecordBackend> oList = oCustomerNORResultsResponseType.getCustomerNORResultsResponse().getSuccesBackend().getRecord();
		
		for(RecordBackend recordBackend : oList) {
			
			oRecord = new Record();
			oRecord.setMonth(recordBackend.getMonth());
			oRecord.setYearId(recordBackend.getYearId());
			oRecord.setQuarter(recordBackend.getQuarter());
			oRecord.setCategory(recordBackend.getCategory());
			oRecord.setSubType(recordBackend.getSubType());
			oRecord.setMonthActual(recordBackend.getMonthActual());
			oRecord.setYtdActual(recordBackend.getYtdActual());
		}
		
		oRecordList.add(oRecord);
		oSuccess.setRecord(oRecordList);
		oGetCustomerNORResultsResponse.setSuccess(oSuccess);
		oGetCustomerNORResultsResponseType.setCustomerNORResultsResponse(oGetCustomerNORResultsResponse);
		
		return oGetCustomerNORResultsResponseType;
		
	}
}

