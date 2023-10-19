package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트 순서 지정하고 싶을 때 사용
public class MemberDAOTest {

	@DisplayName("MemberDAO Insert Test")
	//@Test
	@Order(0) // 작은 숫자부터 우선순위가 할당되어서 실행됨
	public void testInsert() {
		MemberInterface dao = new MemberDAOH2Impl();
		int result = dao.addMember(MemberVO.builder().pass("pass7").name("user8").build());

		System.out.println("[insert] : " + result);
	}

	@DisplayName("MemberDAO Select All Test")
	@Test
	@Order(1)
	public void testSelectAll() {
		MemberInterface dao = new MemberDAOH2Impl();

		List<MemberVO> list = dao.getMembers();
		for (MemberVO m : list) {
			System.out.println("[select all] : " + m);
		}

	}

	@DisplayName("MemberDAO Select Test")
	@Test
	@Order(3)
	public void testSelect() {
		MemberInterface dao = new MemberDAOH2Impl();

		MemberVO m = dao.getMember(1);
		System.out.println("[select] : " + m);
	}

	@DisplayName("MemberDAO Update Test")
	//@Test
	@Order(2)
	public void testUpdate() {
		MemberInterface dao = new MemberDAOH2Impl();
		MemberVO m = MemberVO.builder().id(7).name("user7").build();

		System.out.println("[update] : " + dao.updateMember(m));

	}

	@DisplayName("MemberDAO Remove Test")
	//@Test
	@Order(4)
	public void testRemove() {
		MemberInterface dao = new MemberDAOH2Impl();
		System.out.println("[remove] : " + dao.removeMember(1));

	}

}
