package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> { // 엔티티 클래스 타입, 아이디 타입
	// 서비스에서 메소드를 사용
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword); // where절에서 like 연산자 사용
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	// 쿼리 메소드 활용 실습
	List<Board> findByTitleContaining(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long cnt);
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeq(Long cnt1, Long cnt2);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	// 쿼리 어노테이션
	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	@Query("select b.seq, b.title, b.writer, b.createDate "
			+ "from Board b "
			+ "where b.title like %?1% "
			+ "order by b.seq desc")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query(value="select seq, title, writer, create_date from board where title like '%'||?1||'%' order by seq desc", nativeQuery=true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	// 페이징
	@Query("select b from Board b order by b.seq desc")
	List<Board> queryAnnotationTest5(Pageable paging);
}
