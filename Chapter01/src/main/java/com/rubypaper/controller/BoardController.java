package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class Person {
	private String name;
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}

@RestController
public class BoardController {

	public BoardController() { // @RestController -> 실행하면 Boot가 자동으로 메모리에 올라가면서 인스턴스 만들게 함
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) { // http://localhost:8080/hello?name=홍길동
		return "Hello : " + name;
	}
	
	@GetMapping("/hello1")
	public String hello(String name, Integer age) { // 파라미터는 객체타입으로 적기
		if (age == null) age = 0;
		return "Hello : " + name + "(" + age+ "세)";
	}
	
	@GetMapping("/person")
	public String person(Person person) { // /person?name=홍길동&age=10 자동으로 객체를 만들어줌
		return person.toString();
	}
	
	@GetMapping("/jsonperson")
	public String jsonperson(@RequestBody Person person) { // RequestBody : 브라우저로부터 넘어오는 body 안에 있는 json데이터를 쓰겠다
		return person.toString() + " with JSON";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd) {
		return id + ", " + pwd;
	}
}
