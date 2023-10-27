package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@Service
public class BoardService {
	@Autowired
	private DynamicBoardRepository boardRepo;

	public Iterable<Board> getBoard(String title) {
		String searchKeyword = title;

		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		builder.and(qboard.title.like("%" + searchKeyword + "%"));
		Iterable<Board> boardList = boardRepo.findAll(builder);

		return boardList;
	}


}
