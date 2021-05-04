package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import util.DBConnect;;
public class Fund {
	
	public String ReafFundDetails()
	{ 
		 String output = ""; 
		try
		 { 
		 Connection con = DBConnect.connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
			 } 
		 // Prepare the html table to be displayed
		//Prepare the html table to be displayed
			output = "<table border='1'><tr>"
					  + "<th>Fund ID</th>"
					  + "<th>Project ID</th>"
					  + "<th>Fund Request Date</th>" 
					  + "<th> Fund Announcement</th>" 
					  +	"<th> Fund Duration</th>" 
					  +	"<th> Instructions to Applicant</th>" 
					  +	"<th> Fund Amount</th>" 
					  +	"<th>Details Updated Date</th>" 
					  +	"<th> Update</th>" 
					  +	"<th> Delete</th>" ;
					  
					
			//Query to execute
			String query = "select * from fund";
			//Creating the prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			//getting the values to a result set
			ResultSet rs = preparedStmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String itemId = Integer.toString(rs.getInt("FundID"));
				String ProjID = (rs.getString("ProjectID"));
				Date ReqDate = rs.getDate("F_Request_Date");
				String FundAnnounce = rs.getString("Fund_Announcment");
				String Duration = rs.getString("F_Duration");
				String Instructions = rs.getString("A_Instructions");				
				Date ModifyDate = rs.getDate("D_modified_date");
				Double amount = rs.getDouble("Fund_amount");
				
				
				
				// Add into the html table
				output += "<tr><td>" + itemId + "</td>";
				output += "<td>" + ProjID + "</td>";
				output += "<td>" + ReqDate + "</td>";
				output += "<td>" + FundAnnounce + "</td>";
				output += "<td>" + Duration + "</td>";
				output += "<td>" + Instructions + "</td>";				
				output += "<td>" + amount + "</td>";
				output += "<td>" + ModifyDate + "</td>";
				
				//buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
				+ "class='btnUpdate btn btn-secondary' data-itemid='" + itemId + "'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' "
				+ "class='btnRemove btn btn-danger' data-itemid='" + itemId + "'></td></tr>"; 
				
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
	
	
	
	
	
	
	
	
	
	

}
