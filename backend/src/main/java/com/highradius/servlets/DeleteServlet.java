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


@WebServlet("/deleteInvoice")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
    
    
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Enable CORS headers
        setAccessControlHeaders(response);
       
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()){
    	
        	String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            
        }catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error reading request body.");
            return;
        }
        
     // Parse JSON data using Gson
        Gson gson = new Gson();
        Invoice deletedInvoice = gson.fromJson(sb.toString(), Invoice.class);
        
        int customerOrderId = deletedInvoice.getCustomerOrderId();
       
       //Delete invoice from database
       deleteInvoiceFunction(customerOrderId, response);

	}
    
   
	
	public void deleteInvoiceFunction(int customerOrderId, HttpServletResponse response) throws IOException
	{
		InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
		try {
			invoiceDao.deleteInvoice(customerOrderId);
			 response.getWriter().append("{\"message\": \"Invoice deleted successfully!\"}");
		}catch (Exception e) {
            response.getWriter().append("{\"error\": \"An error occurred while deleting the invoice.\"}");
        }
		
	}

}
