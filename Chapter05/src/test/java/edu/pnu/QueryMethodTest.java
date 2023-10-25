package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;

	//@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("title2");

		System.out.println("FindByTitle() 검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}

	//@Test
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("7");

		System.out.println("findByContentContaining() 검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17","17");
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("1");
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	/*
	//@Test
	public void testFindByTitleContaining2() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		// 0번째(첫번째) 페이지, 5개씩 잘랐을 때
		List<Board> boardList = boardRepo.findByTitleContaining("title", paging);
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	*/
	@Test
	public void testFindByTitleContaining3() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		
		Page<Board> pageInfo = boardRepo.findByTitleContaining("title", paging);
		
		System.out.println("PAGE SIZE : " + pageInfo.getSize()); // 한 페이지 크기
		System.out.println("TOTAL PAGES : " + pageInfo.getTotalPages()); // 전체 페이지의 수
		System.out.println("TOTAL COUNT : " + pageInfo.getTotalElements()); 
		System.out.println("NEXT : " + pageInfo.nextPageable()); // 다음 페이지 객체
		
//		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("검색 결과");
		for (Board board : pageInfo) { // page가 iterable이기 때문에 가능 
			System.out.println("---> " + board);
		}
		
	}
	
	// 쿼리 메소드 활용 실습 p.261 참고
	//@Test
	public void testFindByTitleContaining() {
		// title에 1이 포함되는 데이터 출력
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	
	//@Test
	public void testFindByTitleContainingAndCntGreaterThan() {
		// title에 1이 포함되면서 cnt가 50보다 큰 데이터 출력
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
	
	//@Test
	public void testFindByCntGreaterThanEqualAndCntLessThanEqualOrderBySeq() {
		// cnt가 10~50 사이인 데이터를 seq 오름차순으로 출력
		List<Board> boardList = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeq(10L, 50L);
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}

	//@Test
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		// title에 10이 포함되거나 content에 2가 포함되는 데이터를 seq 내림차순으로 출력
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
}
