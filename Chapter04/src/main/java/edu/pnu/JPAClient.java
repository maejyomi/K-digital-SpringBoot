package edu.pnu;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Random rd = new Random();
		try {
			tx.begin();
			for(int i=0; i<=5; i++) {
				Board board = new Board();
				board.setTitle("title"+i);
				board.setWriter("writer"+(i%2+1));
				board.setContent("content"+i);
				board.setCreateDate(new Date());
				board.setCnt(rd.nextLong(50));
				em.persist(board);
			}
			tx.commit();
			
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();			
		}

	}

}
