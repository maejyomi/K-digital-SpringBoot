package edu.pnu.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class LogFileImp implements LogInterface{
	
	public int addLog(String method, String sql, boolean success) {
		Date date = new Date();
		
		try {
			FileWriter fw = new FileWriter("log.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(method+", "+ sql +", " + date +", "+success);
			bw.newLine();
			bw.flush(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

}
