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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardVO {
	private Integer seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default
	private Date regidate = new Date();
	@Builder.Default
	private int cnt = 0;

}
