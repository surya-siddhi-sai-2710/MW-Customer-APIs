package com.dh.middleware.customer.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.language.simple.Simple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import oracle.jdbc.OracleTypes;

@Component
public class ODSDBConnectorImplDao {
	
	@Autowired(required = true)
	private DataSource dataSource;

	@Autowired
	private ObjectMapper objectMapper;

	public ObjectNode GetEmployerDetails(@Simple("${body[GetEmployerDetailsRequest][cif]}") String cif,
			@Simple("${body[GetEmployerDetailsRequest][accountNumber]}") String accountNumber, 
			Exchange ex)
			throws Exception {


//	public ObjectNode GetEmployerDetails( @Body JsonNode body,
//				Exchange ex) throws Exception{	
//
//		JsonNode EmployerDetailsNode = body.get("GetEmployerDetailsRequest");

		Connection conn = null;
		CallableStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();
			String strProcedure = "CALL GET_EMPLOYER_DETAILS(?,?,?)";
			pstmt = conn.prepareCall(strProcedure);

			pstmt.setString(1, cif);
			pstmt.setString(2, accountNumber);
//			pstmt.setString(1, EmployerDetailsNode.path("cif").asText());
//			pstmt.setString(2, EmployerDetailsNode.path("accountNumber").asText());
			pstmt.registerOutParameter(3, OracleTypes.CURSOR);

			pstmt.execute();
			rs = (ResultSet) pstmt.getObject(3);
			ResultSetMetaData rsMetadata = null;
			rsMetadata = rs.getMetaData();
			int noOfColumns = rsMetadata.getColumnCount();

			ObjectNode getEmployerDetails = JsonNodeFactory.instance.objectNode();
			
			while (rs.next()) {
				ObjectNode oGetEmployerDetailsNode = getEmployerDetails.putObject("GetEmployerDetailsResponse");

				for (int i = 1; i <= noOfColumns; i++) {

					String columnName = rsMetadata.getColumnName(i);
					String columnValue = rs.getString(i);

					oGetEmployerDetailsNode.put(columnName, columnValue);

				}
			}

			// checking weather the requested data is retrieved or not
			if (getEmployerDetails.size() < 1) {
				return null;
			} else {
				return getEmployerDetails;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ex.getIn().setBody(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
	
				if (null != conn)
					conn.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				ex.getIn().setBody(e.getMessage());
				}
			}
		return null;
	}
	
	
	public ObjectNode GetCustomerNORREsults(@Simple("${body[GetCustomerNORResultsRequest][employeeId]}") String employeeId,
			@Simple("${body[GetCustomerNORResultsRequest][rmPosition]}") String rmPosition, 
			@Simple("${body[GetCustomerNORResultsRequest][groupedBy]}") String groupedBy,
			@Simple("${body[GetCustomerNORResultsRequest][selectedCategory]}") String selectedCategory,
			@Simple("${body[GetCustomerNORResultsRequest][cif]}") String cif,
			Exchange ex)
			throws Exception {


//	public ObjectNode GetCustomerNORREsults( @Body JsonNode body,
//				Exchange ex) throws Exception{	
//
//		JsonNode EmployerDetailsNode = body.get("GetCustomerNORResultsRequest");
		
		Connection conn = null;
		CallableStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String strProcedure = "CALL GET_CUSTOMER_NOR_RESULTS(?,?,?,?,?,?)";
			pstmt = conn.prepareCall(strProcedure);
			
			pstmt.setString(1, employeeId);
			pstmt.setString(2, rmPosition);
			pstmt.setString(3, groupedBy);
			pstmt.setString(4, selectedCategory);
			pstmt.setString(5, cif);

//			pstmt.setString(1, EmployerDetailsNode.path("employeeId").asText());
//			pstmt.setString(2, EmployerDetailsNode.path("rmPosition").asText());
//			pstmt.setString(3, EmployerDetailsNode.path("groupedBy").asText());
//			pstmt.setString(4, EmployerDetailsNode.path("selectedCategory").asText());
//			pstmt.setString(5, EmployerDetailsNode.path("cif").asText());
			pstmt.registerOutParameter(6, OracleTypes.CURSOR);
			
			pstmt.execute();
			rs = (ResultSet) pstmt.getObject(6);
			ResultSetMetaData rsMetadata = null;
			rsMetadata = rs.getMetaData();
			int noOfColumns = rsMetadata.getColumnCount();
			
			ObjectNode getCustomerNORResultsDetails = JsonNodeFactory.instance.objectNode();
			
			while (rs.next()) {
				ObjectNode oGetCustomerNORResultsNode = getCustomerNORResultsDetails.putObject("GetCustomerNORResultsResponse");

				for (int i = 1; i <= noOfColumns; i++) {

					String columnName = rsMetadata.getColumnName(i);
					String columnValue = rs.getString(i);

					oGetCustomerNORResultsNode.put(columnName, columnValue);

				}
			}
			
			// checking weather the requested data is retrieved or not
			if (getCustomerNORResultsDetails.size() < 1) {
				return null;
			} else {
				return getCustomerNORResultsDetails;
			}
			
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				ex.getIn().setBody(e.getMessage());
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
		
					if (null != conn)
						conn.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					ex.getIn().setBody(e.getMessage());
					}
				}
			return null;
	}
}
