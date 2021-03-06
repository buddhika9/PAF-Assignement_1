package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.DBConnect;;
public class Fund {
	
	public String ReadFundDetails()
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
			output = "<table border='1' style = 'table'><tr>"
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
						output = "Error while reading the Fund Details."; 
						System.err.println(e.getMessage()); 
					} 
				return output; 
	}
	
	
	
	public String InsertFund(String ProjectID,String Fund_announcement , String Fund_duration,String instructions , String Amount ) 
	 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = DBConnect.connect(); 
				 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for inserting."; 
				 } 
				 // create a prepared statement
				 String query = "insert into fund (FundID ,ProjectID, F_Duration ,Fund_amount)"
					  		+ " values(?,?,?,?)";
				// create a prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query); 
											
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setInt(2,Integer.parseInt((ProjectID)));			
				preparedStmt.setString(3,(Fund_duration));			
				preparedStmt.setDouble(4,Double.parseDouble(Amount));
	
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newFund = ReadFundDetails(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";  
									 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund details.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		} 




	public String UpdateFund(String fundid , String Fund_announcement , String Fund_duration,String instructions , String Amount) 
	 { 
		String output = ""; 
		 try
		 { 
			 Connection con = DBConnect.connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
				String query = "UPDATE fund SET Fund_Announcment = ? , F_Duration = ? , A_Instructions = ? ,D_modified_date = ? ,Fund_amount = ? Where FundID = ? ";
			     
			 	// create a prepared statement
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 	
			 	//Getting the local date 
			 	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 	java.util.Date date =  new Date();
			 	String d1 = dateFormat.format(date);
			 	
			 	// binding values
			 	preparedStmt.setString(1, Fund_announcement);
			 	preparedStmt.setString(2, Fund_duration);
			 	preparedStmt.setString(3,instructions);
			 	preparedStmt.setString(4,d1);	
			 	preparedStmt.setDouble(5,Double.parseDouble(Amount));	
			 	preparedStmt.setInt(6,Integer.parseInt(fundid ));
				// execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newFund = ReadFundDetails(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";  
				
				 
		 	} 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the fund details.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 } 
	
	public String DeleteFund(String FundID) 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = DBConnect.connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting  to the database for deleting."; 
		 } 
			 // create a prepared statement
			 String query = "delete from fund where FundID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(FundID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newFund =ReadFundDetails (); 
			 output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";  
					
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	
	
	
	

}
