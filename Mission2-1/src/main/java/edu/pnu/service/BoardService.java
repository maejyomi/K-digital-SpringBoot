package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.BoardDAO;
import edu.pnu.domain.BoardVO;

public class BoardService {
	private BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	public List<BoardVO> getBoards() {
		return boardDAO.getBoards();
	}
	public BoardVO getBoard(Integer seq) {
		return boardDAO.getBoard(seq);
	}
	public int addBoard(BoardVO boardVO) {
		return boardDAO.addBoard(boardVO);
	}
	public int updateBoard(BoardVO boardVO) {
		return boardDAO.updateBoard(boardVO);
	}
	public int removeBoard(Integer seq) {
		return boardDAO.removeBoard(seq);
	}

}
