package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController // 객체를 리턴하면 json 파일로 자동으로 바뀌어서 넘어감
public class MemberController {
	/*
	 * C: insert Post
	 * R: select Get
	 * U: update Put
	 * D: delete Delete
	 * 
	 * */
	List<MemberVO> list = new ArrayList<>();
	
	public MemberController() {
		for(int i=1; i<5; i++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setName("name"+i);
			m.setPass("pass"+i);
			m.setRegidate(new Date());
			list.add(m);
		}
	}
	
	private MemberVO findMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) return m;
		}
		return null;
	}
	
	// 현재 입력되어 있는 객체들의 id를 조사해서 최대값에 1을 더해서
	// 다음 추가되는 데이터의 id를 만들어서 넘겨준다.
	private int getNextId() {
		int mid = -1;
		for(MemberVO m : list) {
			if(mid < m.getId()) mid = m.getId();
		}
		
		return mid + 1;
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMemberse(){
		
		return list;
	}
	
	// restful API 개발한다 -> 이 방식 적절
	// MemberVO에서 id가 {id}인 데이터를 찾아서 리턴
	// http://localhost:8080/member/5 ==> id가 5인 데이터를 찾아서 돌려 달라
	@GetMapping("/member/{id}") 
	public MemberVO getMember(@PathVariable Integer id) { // 경로상에 있는 변수다 = PathVariable
		return findMember(id);
	}
	
	// MemberVO에서 id가 id인 데이터를 찾아서 리턴
	// http://localhost:8080/member?id=5 ==> id가 5인 데이터를 찾아서 돌려 달라
	@GetMapping("/getMember") 
	public MemberVO getMember1(Integer id) { // 경로상에 있는 변수 = PathVariable
		return findMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		memberVO.setId(getNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
		
		/*
		 * id 값을 입력했을 때 동일한 id가 있으면 실패
		if(memberVO == null) return null;
		
		if(findMember(memberVO.getId()) == null) {
			memberVO.setRegidate(new Date());
			list.add(memberVO);
			return memberVO;
		}
		
		return null;
		*/
	}
	
	@PostMapping("/member1")
	public ResponseEntity<?> addMember1(MemberVO memberVO) {
		if(memberVO.getName()==null || memberVO.getPass()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberVO);
		}
		memberVO.setId(getNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		
		// return ResponseEntity.status(HttpStatus.OK).body(memberVO); // OK, NOT_FOUND
		return ResponseEntity.ok(memberVO); // ok만 넘길 때
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO memberVO) {
		if(memberVO == null) return null;
		
		MemberVO m = findMember(memberVO.getId());
		if(memberVO.getName()==null || memberVO.getPass()==null) return null;
		
		if (memberVO.getName() != null)
			m.setName(memberVO.getName());
		if(memberVO.getPass() != null)
			m.setPass(memberVO.getPass());
		
		return m;
		
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		MemberVO m = findMember(id);
		if(m == null) return null;
		list.remove(m);
		
		return m;
	}
	
}
