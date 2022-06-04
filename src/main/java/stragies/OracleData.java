package stragies;

import java.io.*;  
import java.sql.*;  

public class OracleData {
	
	public ResultSet LoadDataFromOracleDB(String host,String username,String password, String query) throws Exception
	{
		//step1 load the driver class 
		Class.forName("oracle.jdbc.driver.OracleDriver");  

		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(host,username,password);  

		//step3 create the statement object  
		Statement stmt=con.createStatement();  

		//step4 execute query  
		ResultSet rs=stmt.executeQuery(query);  
	
		//step5 close the connection object  
		con.close();
		
		return rs;
	}

}
