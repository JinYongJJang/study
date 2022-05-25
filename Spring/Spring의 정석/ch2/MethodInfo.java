package com.fastcampus.app;

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
		// YoilTeller �겢�옒�뒪�쓽 媛앹껜 �깮�꽦
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// �겢�옒�뒪�뿉 �엳�뒗 紐⑤뱺 硫붿꽌�뱶 �젙蹂대�� 媛��졇���꽌 諛곗뿴�뿉 ���옣
		Method[] methodArr = clazz.getDeclaredMethods();

		for(Method m : methodArr) {
			String name = m.getName();
			Parameter[] paramArr = m.getParameters();
			Class returnType = m.getReturnType();
			
			System.out.println(name);  // main
			System.out.println(returnType);  // void

			// 援щ텇�옄, �젒�몢�궗, �젒誘몄궗
			StringJoiner paramList = new StringJoiner(", ", "(", ")");


			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class paramType = param.getType();
				
				System.out.println(paramName);  // arg0, arg1   而댄뙆�씪�윭�뿉寃� 留ㅺ컻蹂��닔 �씠由꾩� 以묒슂�븯吏� �븡�쓬
				System.out.println(paramType);  // HttpServletRequest, HttpServletResponse
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	}

}
