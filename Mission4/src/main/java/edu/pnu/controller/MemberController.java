package edu.pnu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired // Service 어노테이션이 붙은 객체를 자동으로 할당
	private MemberService memberService;
	
	public MemberController() {
		
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers(){
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}") // PathVariable
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/getMember") // QueryString
	public MemberVO getMember2(Integer id) {
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public int addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@PutMapping("/member")
	public int updateMember(MemberVO memberVO) {
		return memberService.updateMember(memberVO);
	}
	
	@DeleteMapping("/member/{id}")
	public int removeMember(@PathVariable Integer id) {
		return memberService.removeMember(id);
	}
	
	@DeleteMapping("/member")
	public int removeMember2(Integer id) {
		return memberService.removeMember(id);
	}

}
