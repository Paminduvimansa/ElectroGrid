package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Buyer;
import model.Customer;
/**
 * Servlet implementation class CustomerAPI
 */
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
    
	Customer CustomerObj = new Customer();

	 /**
     * @see HttpServlet#HttpServlet()
     */
	public CustomerAPI() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//Insert Customer
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = CustomerObj.insertCustomer(request.getParameter("CustomerName"), request.getParameter("CustomerEmail"),
				request.getParameter("CustomerType"), request.getParameter("CustomerContact"));
		response.getWriter().write(output);

	}
    
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
	
	//Update Customer
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = CustomerObj.updateCustomer(paras.get("hidCustomerIDSave").toString(), paras.get("CustomerName").toString(),
				paras.get("CustomerEmail").toString(), paras.get("CustomerType").toString(),paras.get("CustomerContact").toString());
		response.getWriter().write(output);
	}
    
	//Delete Customer
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = CustomerObj.deleteCustomer(paras.get("CustomerID").toString());
		response.getWriter().write(output);
	}

}
