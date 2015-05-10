package com.sp.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class OraclConnector {
	public static void main(String[] args) throws SQLException {
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "system",
					"Sumit30");
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (connection != null) {
			/*String sql = "SELECT * FROM USER_DETAILS";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("USER_NAME"));
			}
			
			System.out.println("You made it, take control your database now!");*/
			CallableStatement callableStatement = null;
			String getDBUSERByUserIdSql = "{call test(?,?,?)}";
			callableStatement = connection.prepareCall(getDBUSERByUserIdSql);
			
			callableStatement.setString(1, "Sumit");
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			
			callableStatement.executeUpdate();
			String userName = callableStatement.getString(2);
			String address = callableStatement.getString(3);
			System.out.println(userName +" "+ address);
			
			
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}
