package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PowerTransmission {
	//A common method to connect to the DB
		private Connection connect()
		{
			//Create database connection
			//Provide the correct details: dbURL, dbUser, dbPass
			 String dbURL = "jdbc:mysql://localhost:3306/power_transmission";
		     String dbUser = "root";
		     String dbPass = "12345";
		     
			Connection con = null;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(dbURL, dbUser, dbPass);
			}
			catch (Exception e)
			{
				e.printStackTrace();}
			return con;
		    }
			
			public String insertItem( String t_ID, String t_Acommercial, String t_Aresidential, String t_Aagriculture, String t_date ){ 
				
						String output = ""; 
						
						try
						{ 
							Connection con = connect(); 
							
							if (con == null) 
							{
								return "Error while connecting to the database for inserting."; 
								
							} 
							// create a prepared statement
							String query = " insert into p_transmission (`ptid`,`t_ID`, `t_Acommercial`,`t_Aresidential`,`t_Aagriculture`, `t_date`)" + " values (?, ?, ?, ?, ?, ?)"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
							preparedStmt.setInt(1, 0); 
							preparedStmt.setString(2, t_ID); 
							preparedStmt.setString(3, t_Acommercial); 
							preparedStmt.setString(4, t_Aresidential); 
							preparedStmt.setString(5, t_Aagriculture); 
							preparedStmt.setString(6, t_date);
							// execute the statement
	 
							preparedStmt.execute(); 
							con.close(); 
							
							String newPowerTransmission = readPTdata(); 
							output = "{\"status\":\"success\",\"data\":\""+newPowerTransmission+"\"}"; 
						} 
						
						catch (Exception e) 
						{ 
							output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
							System.err.println(e.getMessage()); 
						} 
						return output; 
				} 
		
			
			public String readPTdata() 
			{ 
				String output = ""; 
				try
				{ 
					Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border=\"1\" class=\"table\"><tr><th>Power Transmission ID</th>"
			 		+ "<th>Commercial Area Units</th><th>Residential Area Units</th>"
			 		+ "<th>Agriculture Area Units</th>"
			 		+ "<th>Transmitted Date</th>"
			 		+ "<th>Update</th>"
			 		+ "<th>Remove</th></tr>"; 
			
			 String query = "select * from p_transmission"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String ptid = Integer.toString(rs.getInt("ptid")); 
			 String t_ID = rs.getString("t_ID"); 
			 String t_Acommercial = rs.getString("t_Acommercial"); 
			 String t_Aresidential = rs.getString("t_Aresidential"); 
			 String t_Aagriculture = rs.getString("t_Aagriculture"); 
			 String t_date = rs.getString("t_date"); 
			 // Add into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+ptid+"'>"+t_ID+"</td>"; 
			 output += "<td>" + t_Acommercial + "</td>"; 
			 output += "<td>" + t_Aresidential + "</td>"; 
			 output += "<td>" + t_Aagriculture + "</td>"; 
			 output += "<td>" + t_date + "</td>"; 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-ptid='" + ptid + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-ptid='" + ptid + "'></td></tr>"; 
			 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 
			catch (Exception e) 
			 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
			}
			
			public String deletePTdata(String ptid){ 
				
				String output = ""; 
				
				try{ 
					Connection con = connect(); 
					
					if (con == null){
						return "Error while connecting to the database for deleting."; 
						} 
					// create a prepared statement
					String query = "delete from p_transmission where ptid=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ptid)); 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					String newPowerTransmission = readPTdata(); 
					 output = "{\"status\":\"success\",\"data\":\""+newPowerTransmission+"\"}"; 

				} 
				
				catch (Exception e){ 
					output = "{\"status\":\"error\",\"data\":\"Error while deleting the item.\"}";
					System.err.println(e.getMessage()); 
				} 
				return output; 
		} 
			
			//
			public String updatePTdata(String ptid, String t_ID, String t_Acommercial, String t_Aresidential, String t_Aagriculture, String t_date){ 
				
				String output = ""; 
				
				try{ 
						Connection con = connect(); 
						if (con == null){
							return "Error while connecting to the database for updating.";
							} 
						// create a prepared statement
						String query = "UPDATE p_transmission SET t_ID=?,t_Acommercial=?,t_Aresidential=?,t_Aagriculture=?,t_date=? WHERE ptid=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setString(1, t_ID); 
						preparedStmt.setString(2, t_Acommercial); 
						preparedStmt.setString(3, t_Aresidential); 
						preparedStmt.setString(4, t_Aagriculture);
						preparedStmt.setString(5, t_date);
						preparedStmt.setInt(6, Integer.parseInt(ptid)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newPowerTransmission = readPTdata(); 
						output = "{\"status\":\"success\",\"data\":\""+newPowerTransmission+"\"}"; 

				} 
				
				catch (Exception e){ 
					
					output = "{\"status\":\"error\",\"data\":\"Error while updating the item.\"}"; 

					System.err.println(e.getMessage()); 
					
				} 
				
				return output; 
		} 
}
