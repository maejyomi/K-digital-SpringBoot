package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepo; // DAO대신 Repository 사용

	public Member addMember(Member memberVO) {
		Member member = memberRepo.save(Member.builder()
									.name(memberVO.getName())
									.pass(memberVO.getPass())
									.build());
		
		return member;
	}

	public List<Member> getMembers() {
		List<Member> list = memberRepo.findAll();
		
		return list;
	}
	
	public Member getMember(Integer id) {
		Optional<Member> opt = memberRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
		
	}

	public Member updateMember(Member memberVO) {
		if(memberVO.getId() == null) return null;
		
		Optional<Member> opt = memberRepo.findById(memberVO.getId());
		if(opt.isPresent()) {
			Member member = opt.get();
			if (memberVO.getName() != null) member.setName(memberVO.getName());
			if (memberVO.getPass() != null) member.setPass(memberVO.getPass());
			Member result = memberRepo.save(member);
			return result;
		}
		
		return null;
	}

	public int removeMember(Integer id) {
		memberRepo.deleteById(id);
		return 1;
	}

}
