package edu.pnu.dao;

public interface LogInterface {
	int addLog(String method, String sql, boolean success);
}
