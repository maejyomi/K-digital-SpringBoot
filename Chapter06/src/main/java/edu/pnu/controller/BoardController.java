package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 처음 구동을 하자마자 세션에 빈객체를 넣는 코드
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프.");
	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "getBoardList"; // Controller는 view의 이름이라고 이해함(viewResolve가 설정되어 있는 경우)
	}
	
	@PostMapping("/insertBoard") // 입력이 끝나면 getBoardList로 redirect 
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()== null) {
			return "redirect:login";
		}
		
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/insertBoard") // 실제로 데이터를 입력하는 곳
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if(member.getId()== null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		if(member.getId()== null) {
			return "redirect:login";
		}
		
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()== null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member,Board board) {
		if(member.getId()== null) {
			return "redirect:login";
		}
		
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	

}
