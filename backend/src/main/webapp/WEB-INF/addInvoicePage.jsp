<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/addInvoice" method="post">
<label>CustomerOrder ID</label><input type="number" name="custNumberID"><br>
<label>Sales Org</label><input type="number" name="salesOrg"><br>
<label>Distribution Channel</label><input type="text" name="distributionChannel"><br>
<label>Division</label><input type="text" name="division"><br>
<label>Released Credit Value</label><input type="text" name="releasedCreditValue"><br>
<label>Purchase Order Type</label><input type="text" name="purchaseOrderType"><br>
<label>Company Code</label><input type="number" name="companyCode"><br>
<label>Order Creation Date</label><input type="text" name="orderCreationDate"><br>
<label>Order Creation Time</label><input type="text" name="orderCreationTime"><br>
<label>Credit Control Area</label><input type="text" name="creditControlArea"><br>
<label>SoldToParty</label><input type="number" name="soldToParty"><br>
<label>Order Amount</label><input type="text" name="orderAmount"><br>
<label>Requested Delivery Date</label><input type="text" name="requestedDeliveryDate"><br>
<label>Order Currency</label><input type="text" name="orderCurrency"><br>
<label>Credit Status</label><input type="text" name="creditStatus"><br>
<label>Customer Number</label><input type="number" name="customerNumber"><br>
<label>Amount In Usd</label><input type="text" name="amountInUsd"><br>

<button type="submit">Submit</button>
</form>
</body>
</html>