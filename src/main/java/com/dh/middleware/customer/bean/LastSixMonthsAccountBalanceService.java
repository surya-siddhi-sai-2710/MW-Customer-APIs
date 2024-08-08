package com.dh.middleware.customer.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alahli.middleware.utility.Utils.StringUtil;
import com.dh.middleware.customer.models.AccountBalanceType;
import com.dh.middleware.customer.models.LastSixMonthsAccountBalance;
import com.dh.middleware.customer.models.LastSixMonthsAccountBalanceRequest;
import com.dh.middleware.customer.models.LastSixMonthsAccountBalanceResponse;
import com.dh.middleware.customer.models.LastSixMonthsAccountBalanceResponseType;
import com.dh.middleware.customer.models.MonthlyBalanceType;
import com.dh.middleware.customer.models.ServiceHeader;
import com.dh.middleware.customer.models.backend.ods.AccountBalanceTypeBknd;
import com.dh.middleware.customer.models.backend.ods.LastSixMonthsAccountBalanceRequestBknd;
import com.dh.middleware.customer.models.backend.ods.LastSixMonthsAccountBalanceRequestTypeBknd;
import com.dh.middleware.customer.models.backend.ods.LastSixMonthsAccountBalanceResponseBknd;
import com.dh.middleware.customer.models.backend.ods.LastSixMonthsAccountBalanceResponseTypeBknd;
import com.dh.middleware.customer.models.backend.ods.MonthlyBalanceTypeBknd;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LastSixMonthsAccountBalanceService {

	public LastSixMonthsAccountBalanceRequest accountBalanceRequest;
	
	@Autowired
	StringUtil oStringUtil;

	private ServiceHeader oServiceHeader;

	@Autowired
	ObjectMapper objectMapper;
	
	public void setLastSixMonthsAccountBalanceRequestIn( LastSixMonthsAccountBalance oLastSixMonthsAccountBalance,
			@Header("ServiceHeader") String serviceHeader, Exchange ex) throws Exception{
		
		this.accountBalanceRequest = oLastSixMonthsAccountBalance.getLastSixMonthsAccountBalanceRequest();
		
		oServiceHeader = objectMapper.readValue(serviceHeader, ServiceHeader.class);
		
	}
	
	public LastSixMonthsAccountBalanceRequestTypeBknd prepareLastSixMonthsAccountBalanceBackendRequest(Exchange ex)
			throws Exception{
		
		LastSixMonthsAccountBalanceRequestTypeBknd oLastSixMonthsAccountBalanceRequestTypeBknd = new LastSixMonthsAccountBalanceRequestTypeBknd();
		
		LastSixMonthsAccountBalanceRequestBknd oAccountBalanceRequestBknd = new LastSixMonthsAccountBalanceRequestBknd();
		
		oAccountBalanceRequestBknd.setShortCIF(oStringUtil.setDefaultValue(accountBalanceRequest.getShortCIF()," "));
		oLastSixMonthsAccountBalanceRequestTypeBknd.setoAccountBalanceRequestBknd(oAccountBalanceRequestBknd);
		return oLastSixMonthsAccountBalanceRequestTypeBknd;
		
	}
	
	
	public LastSixMonthsAccountBalanceResponseBknd prepareResponseBackend(@Body JsonNode body, Exchange ex) 
			throws Exception{
		
		JsonNode oLastSixMonthsAccountBalanceNode = body.get("LastSixMonthsAccountBalanceResponse");

		LastSixMonthsAccountBalanceResponseBknd oLastSixMonthsAccountBalanceResponseBknd = new LastSixMonthsAccountBalanceResponseBknd();
		LastSixMonthsAccountBalanceResponseTypeBknd olastSixMonthsAccountBalanceResponseTypeBknd = new LastSixMonthsAccountBalanceResponseTypeBknd();
		AccountBalanceTypeBknd oAccountBalanceTypeBknd = new AccountBalanceTypeBknd();
		List<MonthlyBalanceTypeBknd> oMonthlyBalanceTypeBknd = new ArrayList<MonthlyBalanceTypeBknd>();

		olastSixMonthsAccountBalanceResponseTypeBknd
				.setAverageSixMonthsBalance(oLastSixMonthsAccountBalanceNode.get("AVGSIXMONTHSBALANCE").asDouble());

		JsonNode oRecordArrayNode = oLastSixMonthsAccountBalanceNode.get("monthlyBalance");

		if (oRecordArrayNode.isArray()) {

			for (JsonNode recordNode : oRecordArrayNode) {

				MonthlyBalanceTypeBknd oMonthlyBalanceBknd = new MonthlyBalanceTypeBknd();

				oMonthlyBalanceBknd.setCif(recordNode.get("CIF").asText());
				oMonthlyBalanceBknd.setBalance(recordNode.get("BALANCE").asDouble());
				oMonthlyBalanceBknd.setCurrency(recordNode.get("CURRENCY").asText());
				oMonthlyBalanceBknd.setBalanceDate(recordNode.get("BALANCEDATE").asText());

				oMonthlyBalanceTypeBknd.add(oMonthlyBalanceBknd);

			}
		}
		oAccountBalanceTypeBknd.setMonthlyBalance(oMonthlyBalanceTypeBknd);
		olastSixMonthsAccountBalanceResponseTypeBknd.setAccountBalance(oAccountBalanceTypeBknd);
		oLastSixMonthsAccountBalanceResponseBknd.setSuccess(olastSixMonthsAccountBalanceResponseTypeBknd);

		return oLastSixMonthsAccountBalanceResponseBknd;
		
	}
	
	
	public LastSixMonthsAccountBalance prepareLastSixMonthsAccountBalanceResponse(LastSixMonthsAccountBalanceResponseBknd oLastSixMonthsAccountBalanceResponseBknd)
		throws Exception{
		
		LastSixMonthsAccountBalance accountBalance = new LastSixMonthsAccountBalance();
		
		LastSixMonthsAccountBalanceResponse accountBalanceResponse = new LastSixMonthsAccountBalanceResponse();
		LastSixMonthsAccountBalanceResponseType accountBalanceResponseType = new LastSixMonthsAccountBalanceResponseType();
		AccountBalanceType accountBalanceType  = new AccountBalanceType();
		List<MonthlyBalanceType> balanceTypes = new ArrayList<MonthlyBalanceType>();
		
		MonthlyBalanceType monthlyBalance = null;
		List<MonthlyBalanceTypeBknd> oMonthlyBalanceTypeBknd = oLastSixMonthsAccountBalanceResponseBknd.getSuccess().getAccountBalance().getMonthlyBalance();
		
		for(MonthlyBalanceTypeBknd balanceTypeBknd : oMonthlyBalanceTypeBknd) {
			
			monthlyBalance = new MonthlyBalanceType();
			monthlyBalance.setCif(balanceTypeBknd.getCif());
			monthlyBalance.setBalance(balanceTypeBknd.getBalance());
			monthlyBalance.setCurrency(balanceTypeBknd.getCurrency());
			monthlyBalance.setBalanceDate(balanceTypeBknd.getBalanceDate());
			balanceTypes.add(monthlyBalance);
		}
		
		
		accountBalanceType.setMonthlyBalance(balanceTypes);
		accountBalanceResponseType.setAverageSixMonthsBalance(oLastSixMonthsAccountBalanceResponseBknd.getSuccess().getAverageSixMonthsBalance());
		accountBalanceResponseType.setAccountBalance(accountBalanceType);
		accountBalanceResponse.setSuccess(accountBalanceResponseType);
		accountBalance.setLastSixMonthsAccountBalanceResponse(accountBalanceResponse);
		
		return accountBalance;

	}
}
