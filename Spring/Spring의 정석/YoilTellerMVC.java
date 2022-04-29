package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC {

	@RequestMapping("/getYoilMVC")
	public ModelAndView main(int year, int month, int day) throws IOException {
		// TODO Auto-generated method stub
		
		ModelAndView mv = new ModelAndView();
		
		char yoil = getYoil(year, month, day);
		
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		mv.setViewName("yoil");
		
		return mv;
		
		
	}

	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		// TODO Auto-generated method stub
		// 클래스를 한번만 동적으로 생성 (싱글톤 패턴)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);  // month는 0부터 시작 ( 0 = January )
		
		// 1. 일요일, 2.월요일, 3.화요일 ...
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		return yoil;
	}
}
