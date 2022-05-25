package com.fastcampus.app;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// YoilTellerMVC 객체 생성
		Class clazz = Class.forName("com.fastcampus.app.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// Main 메서드의 정보를 가져옴
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// Model을 생성
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model = "+model);
		
		// Main 메서드를 호출
		// Reflection API를 이용한 호출
		// String viewName = obj.main(2021, 10, 1, model)  // 기존 방법
		String viewName = (String)main.invoke(obj, new Object[] { 2021, 10, 1, model });
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		// 텍스트 파일을 이용한 rendering
		render(model, viewName);
		
	}
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// View의 내용을 한줄씩 읽어서 하나의 문자열로 만듬
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		// Model을 Map으로 변환
		Map map = model.asMap();
		
		// Key를 하나씩 읽어서 template의 ${key}를 value로 바꾼다
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			
			// replace()로 key를 value로 치환함
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		// 렌더링 결과를 출력
		System.out.println(result);
	}
}
