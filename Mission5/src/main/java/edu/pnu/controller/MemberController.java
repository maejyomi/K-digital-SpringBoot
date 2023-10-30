package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
		
	/*	
	@Autowired // Service 어노테이션이 붙은 객체를 자동으로 할당
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Autowired
	public void setMemberController(MemberService memberService) {
		this.memberService = memberService;
	}	
	 */	
	final private MemberService memberService;
	
	@GetMapping("/member")
	public List<Member> getMembers(){
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}") // PathVariable
	public ResponseEntity<?> getMember(@PathVariable Integer id) {
		Member member = memberService.getMember(id);
		if(member != null)
			return ResponseEntity.ok(member);
		return ResponseEntity.badRequest().body("Not Found User");
	}
	
	@GetMapping("/getMember") // QueryString
	public Member getMember2(Integer id) {
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public Member addMember(Member memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> updateMember(Member memberVO) {
		Member member =  memberService.updateMember(memberVO);
		if(member != null) {
			return ResponseEntity.ok(member);
		}
		
		return ResponseEntity.badRequest().body("Not Found User");
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
