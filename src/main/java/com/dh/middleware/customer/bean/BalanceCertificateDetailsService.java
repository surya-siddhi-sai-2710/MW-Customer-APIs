package com.dh.middleware.customer.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alahli.middleware.utility.Utils.StringUtil;
import com.dh.middleware.customer.models.Account;
import com.dh.middleware.customer.models.BalanceCertificateSuccess;
import com.dh.middleware.customer.models.GetBalanceCertificateDetails;
import com.dh.middleware.customer.models.GetBalanceCertificateDetailsRequest;
import com.dh.middleware.customer.models.GetBalanceCertificateDetailsResponse;
import com.dh.middleware.customer.models.ServiceHeader;
import com.dh.middleware.customer.models.backend.ods.AccountBackend;
import com.dh.middleware.customer.models.backend.ods.BalanceCertificateRequest;
import com.dh.middleware.customer.models.backend.ods.BalanceCertificateRequestType;
import com.dh.middleware.customer.models.backend.ods.BalanceCertificateResponse;
import com.dh.middleware.customer.models.backend.ods.BalanceCertificateResponseType;
import com.dh.middleware.customer.models.backend.ods.BalanceCertificateSuccessBknd;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BalanceCertificateDetailsService {

	private ServiceHeader oServiceHeader;
	
	@Autowired
	StringUtil oStringUtil;
	
	public GetBalanceCertificateDetailsRequest balanceCertificateDetailsRequest;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void setBalanceCertificateDetailsRequestIn(GetBalanceCertificateDetails getBalanceCertificateDetails,
			@Header("ServiceHeader") String serviceHeader, Exchange ex) throws Exception{
		
		this.balanceCertificateDetailsRequest = getBalanceCertificateDetails.getBalanceCertificateDetailsRequest();
		oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
		
	}
	
	public BalanceCertificateRequestType prepareBalanceCertificateDetailsRequest(Exchange ex) throws Exception{
		
		
		BalanceCertificateRequestType oBalanceCertificateDetailsRequestType = new BalanceCertificateRequestType();
		BalanceCertificateRequest oBalanceCertificateDetailsRequestBknd = new BalanceCertificateRequest();
		
		oBalanceCertificateDetailsRequestBknd.setCif(oStringUtil.setDefaultValue(balanceCertificateDetailsRequest.getCif(), " "));
		oBalanceCertificateDetailsRequestBknd.setBalanceDate(oStringUtil.setDefaultValue(balanceCertificateDetailsRequest.getBalanceDate(), " "));
		
		oBalanceCertificateDetailsRequestType.setBalanceCertificateDetailsRequestBknd(oBalanceCertificateDetailsRequestBknd);
		
		return oBalanceCertificateDetailsRequestType;
		
	}
	
	public BalanceCertificateResponseType prepareResponseBackend(@Body JsonNode body, Exchange ex) throws Exception{
		
		JsonNode BalanceCertificateDetailsNode = body.get("GetBalanceCertificateDetailsResponse");
		
		BalanceCertificateResponseType oBalanceCertificateDetailsResponseType = new BalanceCertificateResponseType();
		BalanceCertificateResponse oBalanceCertificateDetailsResponseBknd  = new BalanceCertificateResponse();
		
		BalanceCertificateSuccessBknd success = new BalanceCertificateSuccessBknd();
		List<AccountBackend> oRecordList = new ArrayList<AccountBackend>();
		
		AccountBackend oRecordBackend = new AccountBackend();
		
		oRecordBackend.setCif(BalanceCertificateDetailsNode.get("CIF").asText());
		oRecordBackend.setAccountId(BalanceCertificateDetailsNode.get("ACCOUNTID").asText());
		oRecordBackend.setBalanceSAR(BalanceCertificateDetailsNode.get("BALANCESAR").asInt());
		oRecordBackend.setBalanceFCY(BalanceCertificateDetailsNode.get("BALANCEFCY").asInt());
		oRecordBackend.setAccountType(BalanceCertificateDetailsNode.get("ACCOUNTTYPE").asText());
		oRecordBackend.setProductCategory(BalanceCertificateDetailsNode.get("PRODUCTCATEGORY").asText());
		oRecordBackend.setCurrency(BalanceCertificateDetailsNode.get("CURRENCY").asInt());
		oRecordBackend.setAccountStatusCode(BalanceCertificateDetailsNode.get("ACCOUNTSTATUSCODE").asText());
		oRecordBackend.setAccountStatusDescription(BalanceCertificateDetailsNode.get("ACCOUNTSTATUSDESCRIPTION").asText());
		oRecordBackend.setBalanceDate(BalanceCertificateDetailsNode.get("BALANCEDATE").asText());
		oRecordBackend.setBalanceStatus(BalanceCertificateDetailsNode.get("BALANCESTATUS").asText());
		
		oRecordList.add(oRecordBackend);
		success.setAccount(oRecordList);
		oBalanceCertificateDetailsResponseBknd.setSuccess(success);
		oBalanceCertificateDetailsResponseType.setBalanceCertificateDetailsResponseBknd(oBalanceCertificateDetailsResponseBknd);
		
		return oBalanceCertificateDetailsResponseType;
	}
	
	
	public GetBalanceCertificateDetails prepareBalanceCertificateDetailsResponse(
			BalanceCertificateResponseType oBalanceCertificateDetailsResponseType) throws Exception{
		
		GetBalanceCertificateDetails getBalanceCertificateDetails = new GetBalanceCertificateDetails();
		GetBalanceCertificateDetailsResponse getBalanceCertificateDetailsResponse = new GetBalanceCertificateDetailsResponse();
		BalanceCertificateSuccess success = new BalanceCertificateSuccess();
		List<Account> account = new ArrayList<Account>();
		Account oRecord = null;
		
		List<AccountBackend> oRecordList = oBalanceCertificateDetailsResponseType.getBalanceCertificateDetailsResponseBknd().getSuccess().getAccount();
		
		for(AccountBackend recordBackend : oRecordList) {
			
			oRecord = new Account();
			oRecord.setCif(recordBackend.getCif());
			oRecord.setAccountId(recordBackend.getAccountId());
			oRecord.setBalanceSAR(recordBackend.getBalanceSAR());
			oRecord.setBalanceFCY(recordBackend.getBalanceFCY());
			oRecord.setAccountType(recordBackend.getAccountType());
			oRecord.setProductCategory(recordBackend.getProductCategory());
			oRecord.setCurrency(recordBackend.getCurrency());
			oRecord.setAccountStatusCode(recordBackend.getAccountStatusCode());
			oRecord.setAccountStatusDescription(recordBackend.getAccountStatusDescription());
			oRecord.setBalanceDate(recordBackend.getBalanceDate());
			oRecord.setBalanceStatus(recordBackend.getBalanceStatus());
			
		}
		
		account.add(oRecord);
		success.setAccount(account);
		getBalanceCertificateDetailsResponse.setSuccess(success);
		getBalanceCertificateDetails.setBalanceCertificateDetailsResponse(getBalanceCertificateDetailsResponse);
		
		return getBalanceCertificateDetails;
	}
	
}
