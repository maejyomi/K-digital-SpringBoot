package edu.pnu.controller;

import java.util.List;

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

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	@GetMapping("/member")
	public List<MemberVO> getMember() {
		
		return memberService.getMembers();
	}

	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		
		return memberService.getMember(id);
	}

	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		
		return memberService.addMember(memberVO);
	}

	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO memberVO) {
		
		return memberService.updateMember(memberVO);
	}

	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		
		return memberService.removeMember(id);
	}
}
