package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public  Map<String, Object> getMembers(){
		Map<String, Object> map = new HashMap<>();
		map.put("sql", "X");
		map.put("data", list);
		return map;
	}
	// Get	
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		MemberVO m = findMember(id);
		if (m == null) {
			map.put("sql", "X");
			map.put("data", null);
		}
		
		map.put("sql", "X");
		map.put("data", m);
		
		return map;
	}
	
	// Post
	public Map<String, Object> addMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		if(memberVO.getName()==null || memberVO.getPass() == null) {
			map.put("sql", "X");
			map.put("data", 0);
			return map;
		}
		
		memberVO.setId(findNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		
		map.put("sql", "X");
		map.put("data", 1);
		
		return map;
	}
	// Put
	public Map<String, Object> updateMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		MemberVO m = findMember(memberVO.getId());
		if(m == null) {
			map.put("sql", "X");
			map.put("data", 0);
			return map;
		}
		
		if(m.getName()!=null) m.setName(memberVO.getName());
		if(m.getPass()!=null) m.setPass(memberVO.getPass());
		
		map.put("sql", "X");
		map.put("data", 1);
		
		return map;
	}
	// Delete
	public Map<String, Object> removeMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		MemberVO m = findMember(id);
		if(m == null) {
			map.put("sql", "X");
			map.put("data", 0);
			return map;
		}
		list.remove(m);
		map.put("sql", "X");
		map.put("data", 1);
		
		return map;
	}

	
}
