package com.fastcampus.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTeller {

	@RequestMapping("/getYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// Ŭ������ �ѹ��� �������� ���� (�̱��� ����)
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);  // month�� 0���� ���� ( 0 = January )
		
		// 1. �Ͽ���, 2.������, 3.ȭ���� ...
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.println(year + "��" + month + "��" + day + "����");
		out.println(yoil + "�����Դϴ�.");
		
	}

}
