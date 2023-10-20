package edu.pnu.dao;

import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	// 서비스가 아래의 메서드를 호출함
	Map<String, Object> addMember(MemberVO memberVO);

	Map<String, Object> getMembers();

	Map<String, Object> getMember(Integer Id);

	Map<String, Object> updateMember(MemberVO memberVO);

	Map<String, Object> removeMember(Integer Id);

}