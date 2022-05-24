package com.fastcampus.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
	@RequestMapping("/rollDice")
	public static void main(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		// 랜덤한 값 생성
		int index1 = (int)(Math.random()*6)+1;
		int index2 = (int)(Math.random()*6)+1;

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src='resources/img/dice"+ index1 + ".jpg' />");
		out.println("<img src='resources/img/dice"+ index2 + ".jpg' />");
		out.println("</body>");
		out.println("</html>");
	}

}
