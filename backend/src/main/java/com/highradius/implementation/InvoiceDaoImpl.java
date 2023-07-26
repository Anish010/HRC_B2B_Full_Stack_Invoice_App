package com.highradius.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {
	private static final String ADD_INVOICE = "INSERT INTO highradius.h2h_oap (CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, "
			+ "RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, "
			+ "CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, "
			+ "CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_ALL_INVOICE = "SELECT * FROM highradius.h2h_oap LIMIT 1000";

	 private static final String SELECT_BY_ID = "SELECT * FROM highradius.h2h_oap WHERE CUSTOMER_ORDER_ID = ?";
	 private static final String UPDATE_INVOICE = "UPDATE highradius.h2h_oap SET order_currency = ?, company_code = ?, distribution_channel = ? WHERE customer_order_id = ?";
	 private static final String DELETE_INVOICE = "DELETE FROM highradius.h2h_oap WHERE CUSTOMER_ORDER_ID = ?";


	public InvoiceDaoImpl() {
	}

	@Override
	public List<JsonObject> getInvoices() {
		List<JsonObject> invoiceList = new ArrayList<>();

		try {
			Connection connection = DatabaseConnection.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_INVOICE);

			while (resultSet.next()) {
				int slNo = resultSet.getInt("Sl_no");
				int customerOrderId = resultSet.getInt("CUSTOMER_ORDER_ID");
				int salesOrg = resultSet.getInt("SALES_ORG");
				String distributionChannel = resultSet.getString("DISTRIBUTION_CHANNEL");
//				String division = resultSet.getString("DIVISION");
//				double releasedCreditValue = resultSet.getDouble("RELEASED_CREDIT_VALUE");
//				String purchaseOrderType = resultSet.getString("PURCHASE_ORDER_TYPE");
				int companyCode = resultSet.getInt("COMPANY_CODE");
				String orderCreationDate = resultSet.getString("ORDER_CREATION_DATE");
//				String orderCreationTime = resultSet.getString("ORDER_CREATION_TIME");
//				String creditControlArea = resultSet.getString("CREDIT_CONTROL_AREA");
//				int soldToParty = resultSet.getInt("SOLD_TO_PARTY");
				double orderAmount = resultSet.getDouble("ORDER_AMOUNT");
//				String requestedDeliveryDate = resultSet.getString("REQUESTED_DELIVERY_DATE");
				String orderCurrency = resultSet.getString("ORDER_CURRENCY");
//				String creditStatus = resultSet.getString("CREDIT_STATUS");
				int customerNumber = resultSet.getInt("CUSTOMER_NUMBER");
				double amountInUsd = resultSet.getDouble("AMOUNT_IN_USD");
//				long uniqueCustId = resultSet.getLong("UNIQUE_CUST_ID");

				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("id", slNo);
				jsonObject.addProperty("customerOrderId", customerOrderId);
				jsonObject.addProperty("salesOrg", salesOrg);
				jsonObject.addProperty("distributionChannel", distributionChannel);
//								jsonObject.addProperty("division", division);
//								jsonObject.addProperty("releasedCreditValue", releasedCreditValue);
//								jsonObject.addProperty("purchaseOrderType", purchaseOrderType);
				jsonObject.addProperty("companyCode", companyCode);
				jsonObject.addProperty("orderCreationDate", orderCreationDate);
//								jsonObject.addProperty("orderCreationTime", orderCreationTime);
//								jsonObject.addProperty("creditControlArea", creditControlArea);
//								jsonObject.addProperty("soldToParty", soldToParty);
				jsonObject.addProperty("orderAmount", orderAmount);
//								jsonObject.addProperty("requestedDeliveryDate", requestedDeliveryDate);
				jsonObject.addProperty("orderCurrency", orderCurrency);
//								jsonObject.addProperty("creditStatus", creditStatus);
				jsonObject.addProperty("customerNumber", customerNumber);
				jsonObject.addProperty("amountInUsd", amountInUsd);
//								jsonObject.addProperty("uniqueCustId", uniqueCustId);

				invoiceList.add(jsonObject);
			}
			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return invoiceList;
	}

	@Override
	public void insertInvoice(Invoice invoice) throws ClassNotFoundException {
		Connection connection = DatabaseConnection.connect();
		try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE)) {

			preparedStatement.setInt(1, invoice.getCustomerOrderId());
			preparedStatement.setInt(2, invoice.getSalesOrg());
			preparedStatement.setString(3, invoice.getDistributionChannel());
			preparedStatement.setString(4, invoice.getDivision());
			preparedStatement.setDouble(5, invoice.getReleasedCreditValue());
			preparedStatement.setString(6, invoice.getPurchaseOrderType());
			preparedStatement.setInt(7, invoice.getCompanyCode());
			preparedStatement.setString(8, invoice.getOrderCreationDate());
			preparedStatement.setString(9, invoice.getOrderCreationTime());
			preparedStatement.setString(10, invoice.getCreditControlArea());
			preparedStatement.setInt(11, invoice.getSoldToParty());
			preparedStatement.setDouble(12, invoice.getOrderAmount());
			preparedStatement.setString(13, invoice.getRequestedDeliveryDate());
			preparedStatement.setString(14, invoice.getOrderCurrency());
			preparedStatement.setString(15, invoice.getCreditStatus());
			preparedStatement.setInt(16, invoice.getCustomerNumber());
			preparedStatement.setDouble(17, invoice.getAmountInUsd());
			preparedStatement.setLong(18, invoice.getUniqueCustId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateInvoice(int id, Invoice invoice) {
	    Connection connection = DatabaseConnection.connect();
	    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE)) {
	        preparedStatement.setString(1, invoice.getOrderCurrency());
	        preparedStatement.setInt(2, invoice.getCompanyCode());
	        preparedStatement.setString(3, invoice.getDistributionChannel());
	        preparedStatement.setInt(4, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteInvoice(int custOrderId) {
	    // Implement the logic to delete an invoice based on the provided ID
	    Connection connection = DatabaseConnection.connect();
	    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE)) {
	        preparedStatement.setInt(1, custOrderId);

	        // Execute the delete query to delete the invoice
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Invoice with ID " + custOrderId + " deleted successfully!");
	        } else {
	            System.out.println("No invoice found with ID " + custOrderId + ". Nothing deleted.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Make sure to close the connection and statement in the finally block
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public List<JsonObject> getInvoicesById(int custOrderId) {
		List<JsonObject> invoiceList = new ArrayList<>();
		Connection connection = DatabaseConnection.connect();
		try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
			statement.setInt(1, custOrderId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int slNo = resultSet.getInt("Sl_no");
				int customerOrderId = resultSet.getInt("CUSTOMER_ORDER_ID");
				int salesOrg = resultSet.getInt("SALES_ORG");
				String distributionChannel = resultSet.getString("DISTRIBUTION_CHANNEL");
//				String division = resultSet.getString("DIVISION");
//				double releasedCreditValue = resultSet.getDouble("RELEASED_CREDIT_VALUE");
//				String purchaseOrderType = resultSet.getString("PURCHASE_ORDER_TYPE");
				int companyCode = resultSet.getInt("COMPANY_CODE");
				String orderCreationDate = resultSet.getString("ORDER_CREATION_DATE");
//				String orderCreationTime = resultSet.getString("ORDER_CREATION_TIME");
//				String creditControlArea = resultSet.getString("CREDIT_CONTROL_AREA");
//				int soldToParty = resultSet.getInt("SOLD_TO_PARTY");
				double orderAmount = resultSet.getDouble("ORDER_AMOUNT");
//				String requestedDeliveryDate = resultSet.getString("REQUESTED_DELIVERY_DATE");
				String orderCurrency = resultSet.getString("ORDER_CURRENCY");
//				String creditStatus = resultSet.getString("CREDIT_STATUS");
				int customerNumber = resultSet.getInt("CUSTOMER_NUMBER");
				double amountInUsd = resultSet.getDouble("AMOUNT_IN_USD");
//				long uniqueCustId = resultSet.getLong("UNIQUE_CUST_ID");

				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("id", slNo);
				jsonObject.addProperty("customerOrderId", customerOrderId);
				jsonObject.addProperty("salesOrg", salesOrg);
				jsonObject.addProperty("distributionChannel", distributionChannel);
//								jsonObject.addProperty("division", division);
//								jsonObject.addProperty("releasedCreditValue", releasedCreditValue);
//								jsonObject.addProperty("purchaseOrderType", purchaseOrderType);
				jsonObject.addProperty("companyCode", companyCode);
				jsonObject.addProperty("orderCreationDate", orderCreationDate);
//								jsonObject.addProperty("orderCreationTime", orderCreationTime);
//								jsonObject.addProperty("creditControlArea", creditControlArea);
//								jsonObject.addProperty("soldToParty", soldToParty);
				jsonObject.addProperty("orderAmount", orderAmount);
//								jsonObject.addProperty("requestedDeliveryDate", requestedDeliveryDate);
				jsonObject.addProperty("orderCurrency", orderCurrency);
//								jsonObject.addProperty("creditStatus", creditStatus);
				jsonObject.addProperty("customerNumber", customerNumber);
				jsonObject.addProperty("amountInUsd", amountInUsd);
//								jsonObject.addProperty("uniqueCustId", uniqueCustId);


				invoiceList.add(jsonObject);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return invoiceList;
	}

}
