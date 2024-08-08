package com.dh.middleware.customer.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.utility.Utils.StringUtil;
import com.dh.middleware.customer.models.GetCustomerNORResultsRequest;
import com.dh.middleware.customer.models.GetCustomerNORResultsResponse;
import com.dh.middleware.customer.models.GetCustomerNORResultsType;
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
	
	@Autowired
	StringUtil oStringUtil;
	
	public  GetCustomerNORResultsRequest oGetCustomerNORResultsRequest;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void setCustomerNORResultsRequestIn( GetCustomerNORResultsType getCustomerNORResultsRequestType,
			@Header("ServiceHeader") String serviceHeader, Exchange ex) throws Exception{
		
		this.oGetCustomerNORResultsRequest = getCustomerNORResultsRequestType.getCustomerNORResultsRequest();
		
		oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
	}
	
	public CustomerNORResultsRequestType prepareCustomerNORResultsRequest(Exchange ex) throws Exception{
		
		CustomerNORResultsRequestType oCustomerNORResultsRequestType = new CustomerNORResultsRequestType();
		CustomerNORResultsRequest oCustomerNORResultsRequest = new CustomerNORResultsRequest();
		
		oCustomerNORResultsRequest.setEmployeeId(oStringUtil.setDefaultValue(oGetCustomerNORResultsRequest.getEmployeeId(), " "));
		oCustomerNORResultsRequest.setRmPosition(oGetCustomerNORResultsRequest.getRmPosition());
		oCustomerNORResultsRequest.setGroupedBy(oGetCustomerNORResultsRequest.getGroupedBy());
		oCustomerNORResultsRequest.setSelectedCategory(oGetCustomerNORResultsRequest.getSelectedCategory());
		oCustomerNORResultsRequest.setCif(oStringUtil.setDefaultValue(oGetCustomerNORResultsRequest.getCif(), " "));
		
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
		
		JsonNode oRecordArrayNode = oEmployerDetailsNode.get("record");
		
		if (oRecordArrayNode.isArray()) {

			for (JsonNode recordNode : oRecordArrayNode) {

				RecordBackend oRecordBackend = new RecordBackend();

				oRecordBackend.setMonth(recordNode.get("MONTH").asText());
				oRecordBackend.setYearId(recordNode.get("YEAR_ID").asText());
				oRecordBackend.setQuarter(recordNode.get("QUARTER").asText());
				oRecordBackend.setCategory(recordNode.get("CATEGORY").asText());
				oRecordBackend.setSubType(recordNode.get("SUB_TYPE").asText());
				oRecordBackend.setMonthActual(recordNode.get("MONTH_ACTUAL").asText());
				oRecordBackend.setYtdActual(recordNode.get("YTD_ACTUAL").asText());

				oRecordList.add(oRecordBackend);
			}
		}
		oSuccessBackend.setRecord(oRecordList);
		oCustomerNORResultsResponse.setSuccessBackend(oSuccessBackend);
		oCustomerNORResultsResponseType.setCustomerNORResultsResponse(oCustomerNORResultsResponse);
		
		return oCustomerNORResultsResponseType;
	}
	
	
	public GetCustomerNORResultsType prepareCustomerNORResultsResponse(CustomerNORResultsResponseType oCustomerNORResultsResponseType) throws Exception{
		
		GetCustomerNORResultsType oGetCustomerNORResultsResponseType = new GetCustomerNORResultsType();
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
			
			oRecordList.add(oRecord);
		}
		
		oRecordList.add(oRecord);
		oSuccess.setRecord(oRecordList);
		oGetCustomerNORResultsResponse.setSuccess(oSuccess);
		oGetCustomerNORResultsResponseType.setCustomerNORResultsResponse(oGetCustomerNORResultsResponse);
		
		return oGetCustomerNORResultsResponseType;
		
	}
}

