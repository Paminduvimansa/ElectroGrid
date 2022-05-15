package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eg", "root", "admin");
			// For testing
			System.out.print("Successfully connected to the Database");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
     
	//Insert method
	public String insert(int id,String name, String email, String type, String contact){

		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database ";
			}

			// create a prepared statement
			String query = "insert into customer (CustomerID, CustomerName, CustomerEmail, CustomerType, CustomerContact)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, id); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, email); 
			 preparedStmt.setString(4, type); 
			 preparedStmt.setString(5, contact);
			System.out.println(name);

			preparedStmt.execute();
			con.close();

			String newCustomers = readCustomers();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
     
	private String readCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	//Read Customer
	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Customers.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'>"
			 		 + "<tr><th>Customer ID</th>" 
					 +"<th>Customer Name</th>"
					 + "<th>Customer Email</th>"
					 + "<th>Customer Type</th>" 
					 +"<th>Customer Contact</th>"; 
			
			String query = "select * from customers";
			
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				 String CustomerID = Integer.toString(rs.getInt("CustomerID")); 
				 String CustomerName = rs.getString("CustomerName"); 
				 String CustomerEmail = rs.getString("CustomerEmail"); 
				 String CustomerType = rs.getString("CustomerType"); 
				 String CustomerContact = rs.getString("CustomerContact");
				 
				// Add a row into the html table
				output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='" + CustomerID
						+ "'>" + CustomerName + "</td>";
				output += "<td>" + CustomerEmail + "</td>";
				output += "<td>" + CustomerType + "</td>";
				output += "<td>" + CustomerContact + "</td>";
				
			
				// buttons
				output += "<td><input name='btnUpdate' "
						+ " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-customerid='" + CustomerID
						+ "'></td>" + "<td><form method='post' action='Customers.jsp'>" + "<input name='btnRemove' "
						+ " type='button' value='Remove' class='btnRemove btn btn-danger' data-Customerid='" + CustomerID + "'>"
						+ "<input name='hidCustomerIDDelete' type='hidden' " + " value='" + CustomerID + "'>"
						+ "</form></td></tr>";
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
     
	//Delete Customer
	public String deleteCustomer(String CustomerID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from Customer where CustomerID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(CustomerID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
 
	//Update Customer
	public String updateCustomer(String ID,String name, String email, String Type, String contact, String CustomerName, String CustomerEmail, String CustomerType, String CustomerContac)
	{
		String output = "";
		try {
			Connection conn = connect();
			if (conn == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE Customer SET Name=?,Email=?, CustomerEmail = ?,CustomerType = ?,CustomerContact = ?  WHERE CustomerID=? ";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, email); 
			 preparedStmt.setString(3, Type); 
			 preparedStmt.setString(4, contact); 
			 preparedStmt.setInt(9, Integer.parseInt(ID)); 
			 
			// execute the statement
			preparedStmt.execute();
			conn.close();
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating the Customer.\"}";

			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertCustomer(String CustomerName, String CustomerEmail, String CustomerType, String CustomerContact)
		 {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateCustomer(String hidCustomerIDSave, String CustomerName, String CustomerEmail, String CustomerType, String CustomerContact) {
		// TODO Auto-generated method stub
		return null;
	}

}

