package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LogH2Impl implements LogInterface{
	Connection con;
	
	public LogH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2","sa","abcd");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addLog(String method, String sql, boolean success) {
		String query = "insert into dblog(method, sqlstring, success) values (?,?,?)";
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, method);
			psmt.setString(2, sql);
			psmt.setBoolean(3, success);
			
			return psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
 		return 0;
	}
	 
}
