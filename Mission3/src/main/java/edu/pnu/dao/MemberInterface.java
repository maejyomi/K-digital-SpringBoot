package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	// 서비스가 아래의 메서드를 호출함
	int addMember(MemberVO memberVO);

	List<MemberVO> getMembers();

	MemberVO getMember(int Id);

	int updateMember(MemberVO memberVO);

	int removeMember(Integer Id);

}