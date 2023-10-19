package edu.pnu.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.dao.MemberDaoListImp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	MemberInterface memberDAO;
	
//	@Autowired
//	private Environment env; // application properies에 있는 데이터 읽을 때 사용
	
	public MemberService(Environment env) {
		String type = env.getProperty("mywebservice.data.type");
		
		if(type.equals("h2")) {
			System.out.println("H2 서비스");
			memberDAO = new MemberDAOH2Impl();
		} else {
			System.out.println("List 서비스");
			memberDAO = new MemberDaoListImp();
		}
		  
	}
	
	public int addMember(MemberVO memberVO) {
		return memberDAO.addMember(memberVO);
	}

	public List<MemberVO> getMembers() {
		return memberDAO.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDAO.getMember(id);
	}

	public int updateMember(MemberVO memberVO) {
		return memberDAO.updateMember(memberVO);
	}

	public int removeMember(Integer id) {
		return memberDAO.removeMember(id);
	}

}
