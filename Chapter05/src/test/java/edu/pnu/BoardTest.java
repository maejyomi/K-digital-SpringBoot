package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardRepository boardRepo;
	// 스프링 부트가 자동으로 BoardRepository 상속받아서 클래스를 생성
	
	@DisplayName("보드 한 개 입력 테스트")
	//@Test
	public void BoardInsertOne() {
		
		Board board = new Board();
		board.setTitle("title");
		board.setWriter("writer");
		board.setContent("content");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board); // 데이터 저장은 save
	}
	
	@DisplayName("보드(builder) 입력 테스트")
	//@Test
	public void BoardInsertOneByBuilder() {
		boardRepo.save(Board.builder().title("title").writer("writer").content("content").build());
	}
	
	@DisplayName("보드 여러 개 입력 테스트")
	//@Test
	public void BoardInsertMany() {
		Random rd = new Random();
		for(int i=1; i<=10; i++) {
			Board board = new Board();
			board.setTitle("title"+i);
			board.setWriter("writer"+i);
			board.setContent("content"+i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(50));
			
			boardRepo.save(board);
		}
	}
	
	@DisplayName("보드(builder) 여러 개 입력 테스트")
	@Test
	public void BoardInsertManyByBuilder() {
		for(int i=1; i<=10; i++) {			
			boardRepo.save(Board.builder().title("title"+i).writer("writer"+i).content("content"+i).build());
		}
	}
	
}
