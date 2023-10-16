package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그를 조금더 있어보이게 쓰고싶을 때
@RestController
public class BoardController {
	
	public BoardController() {
		log.info("BoardController() 생성");
//		System.out.println("BoardController() 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		log.info("Hello 호출 : param="+name);
		return "Hello : " + name;
	}

}
