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
		
		// YoilTellerMVC ��ü ����
		Class clazz = Class.forName("com.fastcampus.app.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// Main �޼����� ������ ������
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// Model�� ����
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model = "+model);
		
		// Main �޼��带 ȣ��
		// Reflection API�� �̿��� ȣ��
		// String viewName = obj.main(2021, 10, 1, model)  // ���� ���
		String viewName = (String)main.invoke(obj, new Object[] { 2021, 10, 1, model });
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		// �ؽ�Ʈ ������ �̿��� rendering
		render(model, viewName);
		
	}
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// View�� ������ ���پ� �о �ϳ��� ���ڿ��� ����
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		// Model�� Map���� ��ȯ
		Map map = model.asMap();
		
		// Key�� �ϳ��� �о template�� ${key}�� value�� �ٲ۴�
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			
			// replace()�� key�� value�� ġȯ��
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		// ������ ����� ���
		System.out.println(result);
	}
}
