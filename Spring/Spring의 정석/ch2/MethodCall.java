package com.fastcampus.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MethodCall {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		HashMap map = new HashMap();
		System.out.println("before: " + map);  // {}
		
		ModelController mc = new ModelController();
		String viewName = mc.main(map);  // txtView2
		
		System.out.println("after : " + map);  // {id=asdf, pwd=1111}
		
		render(map, viewName);
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		Scanner sc = new Scanner(new File(viewName + ".txt"));  // txtView2.txt

		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			result = result.replace("${" + key + "}", (String)map.get(key));   // id:${id}  -> id:asdf 蹂��솚
		}
		
		System.out.println(result); // id:asdf
		                            // pwd:1111
	}

}

class ModelController {
	public String main(HashMap map) {
		map.put("id", "asdf");
		map.put("pwd", "1111");
		
		return "txtView2";
	}
}
