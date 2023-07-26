package com.highradius.implementation;
import com.highradius.model.Invoice;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.List;
public interface InvoiceDao {
	
	public List<JsonObject> getInvoices();
	
	public void insertInvoice(Invoice invoice)  throws ClassNotFoundException;
	
	public void updateInvoice(int id, Invoice invoice);
	
	public void deleteInvoice(int id) throws SQLException;
	
	public List<JsonObject> getInvoicesById(int custOrderId);
	
	
}
