package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImp implements MemberInterface{
	private List<MemberVO> list;
	
	public MemberDaoListImp() {
		list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(MemberVO.builder().id(i).pass("1234").name("name"+i).regidate(new Date()).build());
		}
	}
	
	private MemberVO findMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) return m;
		}
		
		return null;
	}
	
	private Integer findNextId() {
		int newId = 0;
		for(MemberVO m : list) {
			newId = m.getId();
		}
		
		return newId + 1;
	}
	
	// Get
	public List<MemberVO> getMembers(){
		return list;
	}
	// Get	
	public MemberVO getMember(int id) {
		MemberVO m = findMember(id);
		if (m == null) return null;
		
		return m;
	}
	
	// Post
	public int addMember(MemberVO memberVO) {
		if(memberVO.getName()==null || memberVO.getPass() == null) return 0;
		
		memberVO.setId(findNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		
		return 1;
	}
	// Put
	public int updateMember(MemberVO memberVO) {
		MemberVO m = findMember(memberVO.getId());
		if(m == null) return 0;
		
		if(m.getName()!=null) m.setName(memberVO.getName());
		if(m.getPass()!=null) m.setPass(memberVO.getPass());
		
		return 1;
	}
	// Delete
	public int removeMember(Integer id) {
		MemberVO m = findMember(id);
		if(m == null) return 0;
		
		list.remove(m);
		return 1;
	}

	
}
