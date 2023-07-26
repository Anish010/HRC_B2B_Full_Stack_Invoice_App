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
@WebServlet("/fetchData")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Enable CORS headers
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
		List<JsonObject> invoiceList = new ArrayList<>();
		invoiceList = fetchInvoiceData();

		Gson gson = new Gson();
		String json = gson.toJson(invoiceList);

		// Set the content type of the response to JSON
		response.setContentType("application/json");

		// Write the JSON response
		response.getWriter().write(json);

	}
	
	 private List<JsonObject> fetchInvoiceData() {
	        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
	        return invoiceDao.getInvoices();
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
