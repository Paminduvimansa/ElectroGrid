package com;

import model.Customer;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customer")
public class CustomerService {
	private static final String Name = null;
	private static final String Email = null;
	private static final String Type = null;
	private static final String Contact = null;

	Customer CustomerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
  
	// inserCustomer() method
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(@FormParam("CustomerName") String CustomerName, 
	 @FormParam("CustomerEmail") String CustomerEmail, 
	 @FormParam("CustomerType") String CustomerType, 
	 @FormParam("CustomerContact") String ContactContact, 

	
	{ 
	 Object customerObj;
	String output = customerObj.insert customer(Name,Email,Type,Contact); 
	 return output; 
	}
	
	//updatecustomer() method
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String CustomerData) 
	{ 
	Object customerData;
	//Convert the input string to a JSON object 
	 JsonObject customerObject = ((Object) new JsonParser().parse(customerData)).getAsJsonObject(); 
	//Read the values from the JSON object
	 String CustomerID = CustomerObject.get("CustomerID").toString(); 
	 String CustomerName = CustomerObject.get("Name").toString(); 
	 String CustomerEmail = CustomerObject.get("Email").toString(); 
	 String CustomerType = CustomerObject.get("Type").toString(); 
	 String CustomerContact = CustomerObject.get("Contact").toString(); 
	 String output = CustomerObj.updateCustomer(CustomerID,Name,Email,Type,Contact); 
	
	 return output; 
	}
	
	// deleteCustomer() method
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String CustomerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(CustomerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String CustomerID = ((Object) doc.select("CustomerID")).text(); 
	 String output = CustomerObj.deleteCustomer(CustomerID); 
	
	 return output; 
	}
}