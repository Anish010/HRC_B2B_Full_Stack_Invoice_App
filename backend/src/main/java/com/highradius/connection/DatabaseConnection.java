package com.highradius.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//private Connection connection;
    //private PreparedStatement preparedStatement;
	
    //private List<Invoice> invoiceList;
	
	private static final String url = "jdbc:mysql://localhost:3306/highradius";
	private static final String username = "root";
	private static final String password = "12345678";

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
    /*
    public DatabaseConnection() {
    	invoiceList = new ArrayList<>();
        try {
            // Register the Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            //Create the Connection object
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    public Connection getConnection() {
        return connection;
    }
    */
    /*public List<Invoice> getInvoices() {
        try {
            String query = "SELECT * FROM highradius.h2h_oap;";

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and create Invoice objects
            while (resultSet.next()) {
                int slNo = resultSet.getInt("Sl_no");
                int customerOrderId = resultSet.getInt("CUSTOMER_ORDER_ID");
                int salesOrg = resultSet.getInt("SALES_ORG");
                String distributionChannel = resultSet.getString("DISTRIBUTION_CHANNEL");
                String division = resultSet.getString("DIVISION");
                double releasedCreditValue = resultSet.getDouble("RELEASED_CREDIT_VALUE");
                String purchaseOrderType = resultSet.getString("PURCHASE_ORDER_TYPE");
                int companyCode = resultSet.getInt("COMPANY_CODE");
                String orderCreationDate = resultSet.getString("ORDER_CREATION_DATE");
                String orderCreationTime = resultSet.getString("ORDER_CREATION_TIME");
                String creditControlArea = resultSet.getString("CREDIT_CONTROL_AREA");
                int soldToParty = resultSet.getInt("SOLD_TO_PARTY");
                double orderAmount = resultSet.getDouble("ORDER_AMOUNT");
                String requestedDeliveryDate = resultSet.getString("REQUESTED_DELIVERY_DATE");
                String orderCurrency = resultSet.getString("ORDER_CURRENCY");
                String creditStatus = resultSet.getString("CREDIT_STATUS");
                int customerNumber = resultSet.getInt("CUSTOMER_NUMBER");
                double amountInUsd = resultSet.getDouble("AMOUNT_IN_USD");
                String uniqueCustNumber = resultSet.getString("UNIQUE_CUST_ID");

                // Create Invoice object and add it to the list
                Invoice invoice = new Invoice(customerOrderId, salesOrg, distributionChannel, division,
                        releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime,
                        creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus,
                        customerNumber, amountInUsd);
                invoiceList.add(invoice);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return invoiceList;
    }
    
    public double getAmountInUsd(int customerOrderId) {
        double amountInUsd = 0;

        try {

            String sql = "SELECT AMOUNT_IN_USD FROM highradius.h2h_oap WHERE CUSTOMER_ORDER_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                amountInUsd = resultSet.getDouble("AMOUNT_IN_USD");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amountInUsd;
    }
    
    public void addInvoice(Invoice invoice) {
        try {
            // Prepare the statement with the query and set the parameters
            String query = "INSERT INTO highradius.h2h_oap ( CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, " +
                    "DIVISION, RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, " +
                    "ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, " +
                    "ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_NUMBER) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(3, invoice.getSalesOrg());
            statement.setString(4, invoice.getDistributionChannel());
            statement.setString(5, invoice.getDivision());
            statement.setDouble(6, invoice.getReleasedCreditValue());
            statement.setString(7, invoice.getPurchaseOrderType());
            statement.setInt(8, invoice.getCompanyCode());
            statement.setString(9, invoice.getOrderCreationDate());
            statement.setString(10, invoice.getOrderCreationTime());
            statement.setString(11, invoice.getCreditControlArea());
            statement.setInt(12, invoice.getSoldToParty());
            statement.setDouble(13, invoice.getOrderAmount());
            statement.setString(14, invoice.getRequestedDeliveryDate());
            statement.setString(15, invoice.getOrderCurrency());
            statement.setString(16, invoice.getCreditStatus());
            statement.setInt(17, invoice.getCustomerNumber());
            statement.setDouble(18, invoice.getAmountInUsd());
            statement.setString(19, invoice.getUniqueCustNumber());

            // Execute the query to add the invoice
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
