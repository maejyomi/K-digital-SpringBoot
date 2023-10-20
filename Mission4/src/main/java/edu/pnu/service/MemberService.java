package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogFileImp;
import edu.pnu.dao.LogH2Impl;
import edu.pnu.dao.LogInterface;
import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.dao.MemberDaoListImp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	MemberInterface memberDAO;
	LogInterface logDAO;

//	@Autowired
//	private Environment env; // application properies에 있는 데이터 읽을 때 사용

	public MemberService(Environment env) {
		String type = env.getProperty("mywebservice.data.type");

		if (type.equals("h2")) {
			System.out.println("H2 서비스");
			memberDAO = new MemberDAOH2Impl();
			logDAO = new LogH2Impl();
			//logDAO = new LogFileImp();
		} else {
			System.out.println("List 서비스");
			memberDAO = new MemberDaoListImp();
		}

	}

	public int addMember(MemberVO memberVO) {
		Map<String, Object> map = memberDAO.addMember(memberVO);
		int result = (int) map.get("data");
		String sql = (String) map.get("sql");
		logDAO.addLog("addMember", sql, result == 1 ? true : false);
		return result;
	}

	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDAO.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		String sql = (String) map.get("sql");
		logDAO.addLog("getMembers", sql, list != null ? true : false);
		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDAO.getMember(id); 
		MemberVO member = (MemberVO) map.get("data");
		String sql = (String) map.get("sql");
		logDAO.addLog("getMember", sql, (member != null) ? true : false);

		return member;
	}

	public int updateMember(MemberVO memberVO) {
		Map<String, Object> map = memberDAO.updateMember(memberVO);
		int result = (int) map.get("data");
		String sql = (String) map.get("sql");
		logDAO.addLog("updateMember", sql, result == 1 ? true : false);
		return result;
	}

	public int removeMember(Integer id) {
		Map<String, Object> map = memberDAO.removeMember(id);
		int result = (int) map.get("data");
		String sql = (String) map.get("sql");
		logDAO.addLog("removeMember", sql, result == 1 ? true : false);
		return result;
	}

}
