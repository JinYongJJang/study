package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

class Card{
	String instance_username;
	static String class_username;

	/* ****** 인스턴스 메서드 *******/
	void instance_setUserName(String name){
		instance_username = name;
	}
	String instance_getUserName() {
		return instance_username;
	}
	/* ************************ */
	
	
	/* ****** 클래스 메서드 *******/
	static void class_setUserName(String name){
		class_username = name;
	}
	static String class_getUserName() {
		return class_username;
	}
	/* ************************ */
};


@Controller  // 1. 원격 호출 가능한 프로그램으로 등록
public class Hello {
	@RequestMapping("/hello")  // 2. URL과 메서드를 연결
	private void main() {
		
		Card a = new Card();
		a.instance_setUserName("이진용");
		System.out.println(a.instance_getUserName());
		
		Card.class_setUserName("진용짱짱");
		System.out.println(Card.class_getUserName());
	}
}



