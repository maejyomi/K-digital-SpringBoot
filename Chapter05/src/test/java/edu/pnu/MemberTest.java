package edu.pnu;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@DisplayName("멤버 입력 테스트")
	@Test
	@Order(1)
	public void testInsert() {
		for(int i=1; i<=5; i++) {
			memberRepo.save(Member.builder()
								.id("user"+i)
								.password("pass"+i)
								.name("name"+i)
								.role("user")
								.build());
		}
	}
	
	@DisplayName("멤버 조회 테스트")
	@Test
	@Order(3)
	public void testGetMember() {
		Member member = memberRepo.findById("user1").get();
		System.out.println(member.toString());
	}
	
	@DisplayName("멤버 수정 테스트")
	@Test
	@Order(2)
	public void testUpdateMember() {
		Member member = memberRepo.findById("user1").get();
		member.setName("둘리");
		memberRepo.save(member);
	}
	
	@DisplayName("멤버 삭제 테스트")
	@Test
	@Order(4)
	public void testDeleteMember() {
		memberRepo.deleteById("user1");
	}

}
