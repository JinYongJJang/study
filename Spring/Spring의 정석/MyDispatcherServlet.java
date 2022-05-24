package com.fastcampus.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

// @Controller + @RequestMapping
@WebServlet("/myDispatcherServlet")  // http://localhost:8080/app/myDispatcherServlet?year=2021&month=10&day=1
public class MyDispatcherServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = request.getParameterMap(); // year, month, day�� Map ���·� ������
		Model model = null;
		String viewName = "";
		
		try {
			// Main �޼����� ������ ����
			Class clazz = Class.forName("com.fastcampus.app.YoilTellerMVC");
			Object obj = clazz.newInstance();
			
			Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

			// Main �޼����� �Ű����� ���(paramArr)�� �о �޼��� ȣ�⿡ ����� ���� ���(argArr)�� �����
			Parameter[] paramArr = main.getParameters();
			Object[] argArr = new Object[main.getParameterCount()];
			
			for(int i=0; i<paramArr.length; i++) {
				String paramName = paramArr[i].getName();
				Class paramType = paramArr[i].getType();
				Object value = map.get(paramName);
				
				// paramType �߿� Model�� ������, ���� & ����
				if(paramType == Model.class) {
					argArr[i] = model = new BindingAwareModelMap();
				}
				else if(paramType == HttpServletResponse.class) {
					argArr[i] = request;
				}
				else if(paramType == HttpServletRequest.class) {
					argArr[i] = response;
				}
				else if(value != null) { // Map�� paramName�� ������, value�� parameter�� Ÿ���� ���ؼ� �ٸ��� ��ȯ�ؼ� ����
					String strValue = ((String[])value)[0];  // �迭�� �޴� ������ year=2021&year=2022�� �� �� �ֱ� ����
					argArr[i] = convertTo(strValue, paramType);
				}
			}
			
			viewName = (String)main.invoke(obj, argArr);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		render(model, viewName, response);
	}
	
	private Object convertTo(Object value, Class type) {
		if(type==null || value==null || type.isInstance(value)) // Ÿ���� ������ �״�� ��ȯ 
			return value;
		
		// Ÿ���� �ٸ���, ��ȯ�ؼ� ��ȯ
		if(String.class.isInstance(value) && type==int.class) { // String -> int
			return Integer.valueOf((String)value);
		} else if(String.class.isInstance(value) && type==double.class) { // String -> double
			return Double.valueOf((String)value);
		}
			
		return value;
	}
	private String getResolvedViewName(String viewName) {
		return getServletContext().getRealPath("/WEB-INF/views") + "/" + viewName + ".jsp";
	}
	
	private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
		String result = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		Map map = model.asMap();
		
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()�� key�� value ġȯ�Ѵ�.
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 5.������ ����� ����Ѵ�.
		out.println(result);
	}

}
