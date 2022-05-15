package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class PowerTransmissionAPI
 */
@WebServlet("/PowerTransmissionAPI")
public class PowerTransmissionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PowerTransmission ptObj = new PowerTransmission();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerTransmissionAPI() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = ptObj.insertItem(request.getParameter("t_ID"), 
										   request.getParameter("t_Acommercial"), 
										   request.getParameter("t_Aresidential"), 
										   request.getParameter("t_Aagriculture"),
										   request.getParameter("t_date")); 
		response.getWriter().write(output);
		
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = ptObj.updatePTdata(paras.get("hidptIDSave").toString(), 
				 							paras.get("t_ID").toString(), 
				 							paras.get("t_Acommercial").toString(), 
				 							paras.get("t_Aresidential").toString(),
				 							paras.get("t_Aagriculture").toString(),
				 							paras.get("t_date").toString()); 
		response.getWriter().write(output); 
	}
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = ptObj.deletePTdata(paras.get("ptid").toString()); 
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

}
