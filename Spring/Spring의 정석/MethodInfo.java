package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// YoilTeller 클래스의 객체 생성
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 클래스에 있는 모든 메서드 정보를 가져와서 배열에 저장
		Method[] methodArr = clazz.getDeclaredMethods();

		for(Method m : methodArr) {
			String name = m.getName();
			Parameter[] paramArr = m.getParameters();
			Class returnType = m.getReturnType();
			
			System.out.println(name);  // main
			System.out.println(returnType);  // void

			// 구분자, 접두사, 접미사
			StringJoiner paramList = new StringJoiner(", ", "(", ")");


			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class paramType = param.getType();
				
				System.out.println(paramName);  // arg0, arg1   컴파일러에게 매개변수 이름은 중요하지 않음
				System.out.println(paramType);  // HttpServletRequest, HttpServletResponse
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	}

}
