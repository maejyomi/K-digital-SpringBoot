package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor // 모든 생성자 자동 생성
@NoArgsConstructor // 기본 생성자
public class MemberVO {
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;
	
}
