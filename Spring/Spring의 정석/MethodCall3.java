package com.fastcampus.app;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// 요청할 때 제공된 값
		Map map = new HashMap();
		map.put("year", "2021");
		map.put("month", "10");
		map.put("day", "1");
		
		Model model = null;
		Class clazz = Class.forName("com.fastcampus.app.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		//main 메서드의 매게변수 목록을 가져옴 
		Parameter[] paramArr = main.getParameters();
		
		// 매개변수 갯수와 같은 길이의 Object 배열 생성
		Object[] argArr = new Object[main.getParameterCount()];
		
		for(int i=0; i<paramArr.length; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);   // map에서 못 찾으면 value = null
			
			// paramType 중에 Model이 있으면, 생성 & 저장
			if(paramType == Model.class) {
				argArr[i] = model = new BindingAwareModelMap();
			}
			else if(value != null) {  // map에 paramName이 있으면
				// value와 paramType의 타입을 비교해서, 다르면 변환해서 저장   // "2021" => Int
				argArr[i] = convertTo(value, paramType);
			}
		}
		System.out.println("paramArr = " + Arrays.toString(paramArr));
		System.out.println("argArr = " + Arrays.toString(argArr));
		
		String viewName = (String)main.invoke(obj, argArr);
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);

		render(model, viewName);			

	}

	private static Object convertTo(Object value, Class type) {
		// TODO Auto-generated method stub
		
		// 타입이 같으면 그대로 반환
		if(type == null || value == null || type.isInstance(value)) {
			return value;
		}
		
		// 타입이 다르면, 변환해서 반환
		if(String.class.isInstance(value) && type == int.class) {  // string -> int
			return Integer.valueOf((String)value);
		}
		else if(String.class.isInstance(value) && type == double.class) {  // string -> double
			return Double.valueOf((String)value);
		}
		
		return null;
	}
	
	private static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. model을 map으로 변환 
		Map map = model.asMap();
		
		// 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 5.렌더링 결과를 출력한다.
		System.out.println(result);
	}

}
