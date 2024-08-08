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
		
		JsonNode oRecordArrayNode = BalanceCertificateDetailsNode.get("account");
		
		if (oRecordArrayNode.isArray()) {
			
			for (JsonNode recordNode : oRecordArrayNode) {
				
				AccountBackend oRecordBackend = new AccountBackend();
				
				oRecordBackend.setCif(recordNode.get("CIF").asText());
				oRecordBackend.setAccountId(recordNode.get("ACCOUNTID").asText());
				oRecordBackend.setBalanceSAR(recordNode.get("BALANCESAR").asDouble());
				oRecordBackend.setBalanceFCY(recordNode.get("BALANCEFCY").asDouble());
				oRecordBackend.setAccountType(recordNode.get("ACCOUNTTYPE").asText());
				oRecordBackend.setProductCategory(recordNode.get("PRODUCTCATEGORY").asText());
				oRecordBackend.setCurrency(recordNode.get("CURRENCY").asText());
				oRecordBackend.setAccountStatusCode(recordNode.get("ACCOUNTSTATUSCODE").asText());
				oRecordBackend.setAccountStatusDescription(recordNode.get("ACCOUNTSTATUSDESCRIPTION").asText());
				oRecordBackend.setBalanceDate(recordNode.get("BALANCEDATE").asText());
				oRecordBackend.setBalanceStatus(recordNode.get("BALANCESTATUS").asText());
				
				oRecordList.add(oRecordBackend);
			}
		}
		
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
		
		for(AccountBackend accountBackend : oRecordList) {
			
			oRecord = new Account();
			oRecord.setCif(accountBackend.getCif());
			oRecord.setAccountId(accountBackend.getAccountId());
			oRecord.setBalanceSAR(accountBackend.getBalanceSAR());
			oRecord.setBalanceFCY(accountBackend.getBalanceFCY());
			oRecord.setAccountType(accountBackend.getAccountType());
			oRecord.setProductCategory(accountBackend.getProductCategory());
			oRecord.setCurrency(accountBackend.getCurrency());
			oRecord.setAccountStatusCode(accountBackend.getAccountStatusCode());
			oRecord.setAccountStatusDescription(accountBackend.getAccountStatusDescription());
			oRecord.setBalanceDate(accountBackend.getBalanceDate());
			oRecord.setBalanceStatus(accountBackend.getBalanceStatus());
			
			account.add(oRecord);
		}
		
		
		success.setAccount(account);
		getBalanceCertificateDetailsResponse.setSuccess(success);
		getBalanceCertificateDetails.setBalanceCertificateDetailsResponse(getBalanceCertificateDetailsResponse);
		
		return getBalanceCertificateDetails;
	}
	
}
