package edu.pnu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientSearch {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("--->" + searchBoard.toString());
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();			
		}

	}

}
