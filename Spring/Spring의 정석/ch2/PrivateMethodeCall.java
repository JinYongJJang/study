package com.fastcampus.app;

public class PrivateMethodeCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main();  // private�� �ܺ� ȣ�� �Ұ�
		
		
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();
		helloClass.getDeclaredMethod("main");
		
	}
}
