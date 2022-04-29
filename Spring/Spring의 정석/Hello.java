package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

class Card{
	String instance_username;
	static String class_username;

	/* ****** �ν��Ͻ� �޼��� *******/
	void instance_setUserName(String name){
		instance_username = name;
	}
	String instance_getUserName() {
		return instance_username;
	}
	/* ************************ */
	
	
	/* ****** Ŭ���� �޼��� *******/
	static void class_setUserName(String name){
		class_username = name;
	}
	static String class_getUserName() {
		return class_username;
	}
	/* ************************ */
};


@Controller  // 1. ���� ȣ�� ������ ���α׷����� ���
public class Hello {
	@RequestMapping("/hello")  // 2. URL�� �޼��带 ����
	private void main() {
		
		Card a = new Card();
		a.instance_setUserName("������");
		System.out.println(a.instance_getUserName());
		
		Card.class_setUserName("����¯¯");
		System.out.println(Card.class_getUserName());
	}
}



