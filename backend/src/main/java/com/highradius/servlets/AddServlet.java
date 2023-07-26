package com.highradius.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/addData")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InvoiceDaoImpl invoiceDao;

    @Override
    public void init() throws ServletException {
        super.init();
        invoiceDao = new InvoiceDaoImpl();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Enable CORS headers
        setAccessControlHeaders(response);

        try {
            // Read the JSON data from the request body
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String jsonData = jsonBuilder.toString();

            // Parse the JSON data into a invoice using Gson
            Gson gson = new Gson();
            Invoice invoiceObject = gson.fromJson(jsonData, Invoice.class);

            // Extract the data from the Invoice Object
            int customerOrderId = invoiceObject.getCustomerOrderId();
            int salesOrg = invoiceObject.getSalesOrg();
            String distributionChannel = invoiceObject.getDistributionChannel();
//            String division = invoiceObject.getDivision();
//            double releasedCreditValue = invoiceObject.getReleasedCreditValue();
//            String purchaseOrderType = invoiceObject.getPurchaseOrderType();
            int companyCode = invoiceObject.getCompanyCode();
            String orderCreationDate = invoiceObject.getOrderCreationDate();
//            String orderCreationTime = invoiceObject.getOrderCreationTime();
//            String creditControlArea = invoiceObject.getCreditControlArea();
//            int soldToParty = invoiceObject.getSoldToParty();
//            double orderAmount = invoiceObject.getOrderAmount();
//            String requestedDeliveryDate = invoiceObject.getRequestedDeliveryDate();
            String orderCurrency = invoiceObject.getOrderCurrency();
//            String creditStatus = invoiceObject.getCreditStatus();
            int customerNumber = invoiceObject.getCustomerNumber();
            double amountInUsd = invoiceObject.getAmountInUsd();

            String uniqueCustIdStr = String.valueOf(customerNumber) + String.valueOf(companyCode);
            long uniqueCustId = Long.parseLong(uniqueCustIdStr);

            // Create an instance of Invoice using the extracted data
//            Invoice newInvoice = new Invoice(customerOrderId, salesOrg, distributionChannel, division,
//                    releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime,
//                    creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus,
//                    customerNumber, amountInUsd, uniqueCustId);
            
            Invoice newInvoice = new Invoice(customerOrderId, salesOrg, distributionChannel,
            		companyCode, orderCreationDate, orderCurrency,
                    customerNumber, amountInUsd, uniqueCustId);
            
            invoiceDao.insertInvoice(newInvoice);
            response.getWriter().append("{\"message\": \"Invoice added successfully!\"}");
        } catch (JsonSyntaxException e) {
            response.getWriter().append("{\"error\": \"Invalid JSON format.\"}");
        } catch (Exception e) {
            response.getWriter().append("{\"error\": \"An error occurred while adding the invoice.\"}");
        }
    }
}
