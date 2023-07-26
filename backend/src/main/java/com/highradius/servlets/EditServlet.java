package com.highradius.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/update")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDaoImpl invoiceDao;

    public void init() {
        invoiceDao = new InvoiceDaoImpl();
    }
    

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Enable CORS headers
    	response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000/");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        

     // Read JSON data from the request body
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error reading request body.");
            return;
        }
        
     // Parse JSON data using Gson
        Gson gson = new Gson();
        Invoice updatedInvoice = gson.fromJson(sb.toString(), Invoice.class);
       
        int customerOrderId = updatedInvoice.getCustomerOrderId();
        // Call the DAO's update method to persist the changes
        invoiceDao.updateInvoice(customerOrderId, updatedInvoice);

        // Send a success response
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Invoice with customerOrderId " + customerOrderId + " updated successfully.");
        

    }


}
