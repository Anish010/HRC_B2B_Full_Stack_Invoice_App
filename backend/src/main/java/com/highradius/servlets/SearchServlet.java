package com.highradius.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.highradius.implementation.InvoiceDaoImpl;


@WebServlet("/searchData")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// Enable CORS headers
        setAccessControlHeaders(response);
        
        
        Gson gson = new Gson();
        List<JsonObject> invoiceList = new ArrayList<>();
        
        // getting the customer id from params
        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
        
        // Fetch data from the database
        invoiceList = fetchInvoiceDataById(customerOrderId);

        // Convert data to JSON format
        String json = gson.toJson(invoiceList);

        // Set the content type of the response to JSON
        response.setContentType("application/json");

        // Write the JSON response
        response.getWriter().write(json);
    }

    private List<JsonObject> fetchInvoiceDataById(int id) {
        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        return invoiceDao.getInvoicesById(id);
    }
}
