package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDAOH2Impl implements MemberInterface {
	
	Connection con;
	
	public MemberDAOH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2","sa","abcd");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 서비스가 아래의 메서드를 호출함
	@Override
	public Map<String, Object> addMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		if(memberVO.getName() == null || memberVO.getPass() == null) {
			map.put("sql", "insert into member(pass, name) values ('%s', '%s')");
			map.put("data", 0);
			
			return map;
		}
		try {
			String sql = String.format("insert into member(pass, name) values ('%s', '%s')", memberVO.getPass(), memberVO.getName());
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);
			map.put("sql", sql);
			map.put("data", result);
			
			return map;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public Map<String, Object> getMembers(){
		List<MemberVO> list = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from member";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getInt("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setRegidate(rs.getDate("regidate"));
				
				list.add(member);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", list);
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public Map<String, Object> getMember(Integer Id) {
		Map<String, Object> map = new HashMap<>();
		
		if(Id == null) {
			map.put("sql", "");
			map.put("data", null);
			return map;
		}

		try {
			String sql = String.format("select * from member where id=%d", Id);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			MemberVO member = new MemberVO();
			while(rs.next()) {				
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setPass(rs.getString("pass"));
				member.setRegidate(rs.getDate("regidate"));
			}
			
			map.put("sql", sql);
			map.put("data", member);
			
			if(member.getId() == null) {
				map.put("sql", sql);
				map.put("data", null);
				
			}
			
			return map;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public Map<String, Object> updateMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		Map<String, Object> map2 = getMember(memberVO.getId());
		MemberVO member = (MemberVO) map2.get("data");
		if(member == null) {
			map.put("sql", "update member set name=?, pass=? where id=?");
			map.put("data", 0);
			return map;
		}
		
		String sql = "update member set ";
		if(memberVO.getName() == null)	sql += "name='"+member.getName()+"'";
		else							sql += "name='"+memberVO.getName()+"'";
		
		if(memberVO.getPass() == null)	sql += ",pass='"+member.getPass()+"'";
		else							sql += ",pass='"+memberVO.getPass()+"'";
		
		sql += " where id="+memberVO.getId();
		
		try {
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);
			map.put("sql", sql);
			map.put("data", result);
			return map;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Map<String, Object> removeMember(Integer Id) {
		Map<String, Object> map = new HashMap<>();
		
		if(Id == null) {
			map.put("sql", "delete member where id=?");
			map.put("data", 0);
			
			return map;
		}
		
		try {
			String sql = "delete member where id="+Id;
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);
			map.put("sql", sql);
			map.put("data", result);
			return map;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
