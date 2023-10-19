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
	@Override
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
	@Override
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
	@Override
	public int updateMember(MemberVO memberVO) {
		if(memberVO.getId() == 0) return -1;
		
		MemberVO member = getMember(memberVO.getId());
		if(member == null) return 0;
		
		String sql = "update member set ";
		if(memberVO.getName() == null)	sql += "name='"+member.getName()+"'";
		else							sql += "name='"+memberVO.getName()+"'";
		
		if(memberVO.getPass() == null)	sql += ",pass='"+member.getPass()+"'";
		else							sql += ",pass='"+memberVO.getPass()+"'";
		
		sql += " where id="+memberVO.getId();
		
		try {
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	@Override
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
