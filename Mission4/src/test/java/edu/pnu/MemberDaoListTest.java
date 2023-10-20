package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDaoListImp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberDaoListTest {
	
//	@DisplayName("SelectAll Test")
//	//@Test
//	@Order(2)	
//	public void testSelectAll() {
//		MemberInterface test = new MemberDaoListImp();
//		List<MemberVO> list = test.getMembers();
//		for(MemberVO m : list) {
//			System.out.println("[Select All] : "+m);
//		}
//		
//	}
//	
//	@DisplayName("Select Test")
//	//@Test
//	public void testSelect() {
//		MemberInterface test = new MemberDaoListImp();
//		MemberVO member = test.getMember(1);
//		System.out.println("[Select] : " + member);
//		
//	}
//	
//	@DisplayName("Insert Test")
//	//@Test
//	@Order(1)
//	public void testInsert() {
//		MemberInterface test = new MemberDaoListImp();
//		int result = test.addMember(MemberVO.builder().name("홍길동").pass("1234").build());
//		System.out.println("[Insert] : "+ result);
//		
//		/*
//		 * List<MemberVO> list = test.getMembers(); for(MemberVO m : list) {
//		 * System.out.println("[Select All] : "+m); }
//		 */	
//	}
//	
//	@DisplayName("Update Test")
//	@Test
//	public void testUpdate() {
//		MemberInterface test = new MemberDaoListImp();
//		int result = test.updateMember(MemberVO.builder().id(1).name("test").pass("test").build());
//		System.out.println("[Update] : " + result);
//		
//		/*
//		 * List<MemberVO> list = test.getMembers(); for(MemberVO m : list) {
//		 * System.out.println("[Select All] : "+m); }
//		 */	
//	}
//	
//	@DisplayName("Remove Test")
//	@Test
//	public void testDelete() {
//		MemberInterface test = new MemberDaoListImp();
//		int result = test.removeMember(1);
//		System.out.println("[Remove] : " + result);
//		
//		/*
//		 * List<MemberVO> list = test.getMembers(); for(MemberVO m : list) {
//		 * System.out.println("[Select All] : "+m); }
//		 */
//	}

}
