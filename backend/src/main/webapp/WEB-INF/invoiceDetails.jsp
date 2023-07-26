<%@ page language="java" contentType="text/html; charesultSetet=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.highradius.connection.DatabaseConnection" %>

<%@ page import="java.sql.*" %>

<%@ page import="com.highradius.model.Invoice"%>
<!DOCTYPE html>
<html>
<head>
<title>Data List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">



</head>
<body>

<div class="outer_body_table1">
	<table class="table1">
		<tr class="column_heading">
			<th>Sl No</th>
			<th>Customer Order ID</th>
			<th>Sales Org</th>
			<th>Distribution Channel</th>
			<th>Company Code</th>
			<th>Order Creation Date</th>
			<th>Order Amount</th>
			<th>Order Currency</th>
			<th>Customer Number</th>
			<th>Amount in USD</th>
		</tr>
		 <%
		Connection connection = DatabaseConnection.connect();
		String SELECT_ALL_INVOICE = "SELECT * FROM highradius.h2h_oap";
		Statement st = connection.createStatement();
		ResultSet resultSet = st.executeQuery(SELECT_ALL_INVOICE);
		
		int count = 0;
		while(resultSet.next() && count <= 5)
		{
			
		%>
		<tr>
			<td><%=resultSet.getInt("Sl_no")%></td>
			<td><%=resultSet.getInt("CUSTOMER_ORDER_ID")%></td>
			<td><%=resultSet.getInt("SALES_ORG")%></td>
			<td><%=resultSet.getString("DISTRIBUTION_CHANNEL")%></td>
			<td><%=resultSet.getInt("COMPANY_CODE")%></td>
			<td><%=resultSet.getString("ORDER_CREATION_DATE")%></td>
			<td><%=resultSet.getDouble("ORDER_AMOUNT")%></td>
			<td><%=resultSet.getString("ORDER_CURRENCY")%></td>
			<td><%=resultSet.getInt("CUSTOMER_NUMBER")%></td>
			<td><%=resultSet.getDouble("AMOUNT_IN_USD")%></td>
		</tr>
		<%
		count++;
		}
		%>
	</table>
</div>
<div class="outer_body_table2">
	<table class="table2">
		<tr class="column_heading">
			<th>Sl No</th>
			<th>Customer Order ID</th>
			<th>Sales Org</th>
			<th>Distribution Channel</th>
			<th>Company Code</th>
			<th>Order Creation Date</th>
			<th>Order Currency</th>
			<th>Customer Number</th>
			<th>Amount in USD</th>
		</tr>
		 <%
		 ResultSet resultSet2 = st.executeQuery(SELECT_ALL_INVOICE);
		
		count = 0;
		while(resultSet2.next() && count <= 10)
		{
			
		%>
		<tr>
			<td><%=resultSet2.getInt("Sl_no")%></td>
			<td><%=resultSet2.getInt("CUSTOMER_ORDER_ID")%></td>
			<td><%=resultSet2.getInt("SALES_ORG")%></td>
			<td><%=resultSet2.getString("DISTRIBUTION_CHANNEL")%></td>
			<td><%=resultSet2.getInt("COMPANY_CODE")%></td>
			<td><%=resultSet2.getString("ORDER_CREATION_DATE")%></td>
			<td><%=resultSet2.getString("ORDER_CURRENCY")%></td>
			<td><%=resultSet2.getInt("CUSTOMER_NUMBER")%></td>
			<td><%=resultSet2.getDouble("AMOUNT_IN_USD")%></td>
		</tr>
		<%
		count++;
		}
		%>
	</table>
</div>
</body>
</html>
