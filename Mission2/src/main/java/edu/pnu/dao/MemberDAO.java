package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAO {
	
	Connection con;
	
	public MemberDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2","sa","abcd");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 서비스가 아래의 메서드를 호출함
	public int addMember(MemberVO memberVO) {
		if(memberVO.getName() == null || memberVO.getPass() == null) return 0;
		try {
			String sql = "insert into member(pass, name) values (?, ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			return psmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	public List<MemberVO> getMembers(){
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
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public MemberVO getMember(int Id) {
		try {
			String sql = "select * from member where id=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, Id);
			ResultSet rs = psmt.executeQuery();
			
			MemberVO member = new MemberVO();
			while(rs.next()) {				
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setPass(rs.getString("pass"));
				member.setRegidate(rs.getDate("regidate"));
			}
			
			if(member.getId() == 0) return null;
			
			return member;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public int updateMember(MemberVO memberVO) {
		if(memberVO.getId() == 0) return -1;
		
		try {
			String sql = "update member set ";
			if(memberVO.getPass() != null && memberVO.getName()!= null) {
				sql += "pass=?, name=? where id=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, memberVO.getPass());
				psmt.setString(2, memberVO.getName());
				psmt.setInt(3, memberVO.getId());
				
				return psmt.executeUpdate();
			}
			else if(memberVO.getPass() != null) {
				sql += "pass=? where id=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, memberVO.getPass());
				psmt.setInt(2, memberVO.getId());
				
				return psmt.executeUpdate();
			}
			else if(memberVO.getName() != null) {
				sql += "name=? where id=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, memberVO.getName());
				psmt.setInt(2, memberVO.getId());
				
				return psmt.executeUpdate();
			}
			else
				return -1;
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int removeMember(Integer Id) {
		if(Id == null) return -1;
		try {
			String sql = "delete member where id="+Id;
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
