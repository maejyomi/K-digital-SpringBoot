package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.BoardVO;

public class BoardDAO {
	Connection con;

	public BoardDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2", "sa", "abcd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> getBoards() {
		List<BoardVO> list = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from board;");

			while (rs.next()) {
				list.add(BoardVO.builder().seq(rs.getInt("seq")).title(rs.getString("title"))
						.writer(rs.getString("writer")).content(rs.getString("content"))
						.regidate(rs.getDate("regidate")).cnt(rs.getInt("cnt")).build());
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public BoardVO getBoard(Integer seq) {
		try {
			PreparedStatement psmt = con.prepareStatement("select * from board where seq=?");
			psmt.setInt(1, seq);
			ResultSet rs = psmt.executeQuery();

			BoardVO boardVO = new BoardVO();
			while (rs.next()) {
				boardVO.setSeq(rs.getInt("seq"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setWriter(rs.getString("writer"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setRegidate(rs.getDate("regidate"));
				boardVO.setCnt(rs.getInt("cnt"));
			}

			if (boardVO.getSeq() == null)
				return null;

			return boardVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int addBoard(BoardVO boardVO) {
		if (boardVO.getTitle() == null || boardVO.getWriter() == null || boardVO.getContent() == null)
			return -1;
		try {
			PreparedStatement psmt = con.prepareStatement("insert into board(title, writer, content) values(?,?,?);");
			psmt.setString(1, boardVO.getTitle());
			psmt.setString(2, boardVO.getWriter());
			psmt.setString(3, boardVO.getContent());

			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
	
	
	public int updateBoard(BoardVO boardVO) {
		if(boardVO.getSeq() == null) return 0;
		
		BoardVO board = getBoard(boardVO.getSeq());
		if(board== null) return 0;
		
		String sql = "update board set ";
		if(boardVO.getTitle() == null)	sql += "title='" + board.getTitle()+"'";
		else 							sql += "title='" + boardVO.getTitle()+"'";
		
		if(boardVO.getWriter() == null)	sql += ",writer='" + board.getWriter()+"'";
		else 							sql += ",writer='" + boardVO.getWriter()+"'";
		
		if(boardVO.getContent() == null)sql += ",content='" + board.getContent()+"'";
		else 							sql += ",content='" + boardVO.getContent()+"'";
		
		sql += " where seq="+boardVO.getSeq();
		
		try {
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	public int updateBoard2(BoardVO boardVO) {
		// update board set title=123 where seq=1
		String sql = null;
		try {			
			if(boardVO.getTitle()!=null) {
				if(sql == null) sql = "update board set ";
				sql += ("title='"+boardVO.getTitle()+"'");
			}
			if(boardVO.getWriter()!=null) {
				if(sql == null) {
					sql = "update board set ";
				}
				else {
					sql += ", ";
				}
				sql += ("writer='"+boardVO.getWriter()+"'");
			}
			if(boardVO.getContent()!=null) {
				if(sql == null) {
					sql = "update board set ";
				}
				else {
					sql += ", ";
				}
				sql += ("content='"+boardVO.getContent()+"'");
			}
			sql += (" where seq="+boardVO.getSeq());
			
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateBoard1(BoardVO boardVO) {
		if (boardVO.getSeq() == null)
			return -1;

		try {
			String sql = "update board set ";
			if (boardVO.getTitle() != null && boardVO.getWriter() != null && boardVO.getContent() != null) {
				sql += "title=?, writer=?, content=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getTitle());
				psmt.setString(2, boardVO.getWriter());
				psmt.setString(3, boardVO.getContent());
				psmt.setInt(4, boardVO.getSeq());

				return psmt.executeUpdate();
			} else if (boardVO.getTitle() != null && boardVO.getWriter() != null) {
				sql += "title=?, writer=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getTitle());
				psmt.setString(2, boardVO.getWriter());
				psmt.setInt(3, boardVO.getSeq());

				return psmt.executeUpdate();

			} else if (boardVO.getTitle() != null && boardVO.getContent() != null) {
				sql += "title=?, content=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getTitle());
				psmt.setString(2, boardVO.getContent());
				psmt.setInt(3, boardVO.getSeq());

				return psmt.executeUpdate();

			} else if (boardVO.getWriter() != null && boardVO.getContent() != null) {
				sql += "writer=?, content=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getWriter());
				psmt.setString(2, boardVO.getContent());
				psmt.setInt(3, boardVO.getSeq());

				return psmt.executeUpdate();

			} else if (boardVO.getTitle() != null) {
				sql += "title=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getTitle());
				psmt.setInt(2, boardVO.getSeq());

				return psmt.executeUpdate();

			} else if (boardVO.getWriter() != null) {
				sql += "writer=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getWriter());
				psmt.setInt(2, boardVO.getSeq());

				return psmt.executeUpdate();

			} else if (boardVO.getContent() != null) {
				sql += "content=? where seq=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, boardVO.getContent());
				psmt.setInt(2, boardVO.getSeq());

				return psmt.executeUpdate();

			} else
				return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int removeBoard(Integer seq) {
		if (seq == null) return -1;
		try {
			String sql = "delete board where seq="+seq;
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}
