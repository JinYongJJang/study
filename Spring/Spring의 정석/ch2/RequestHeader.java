package com.fastcampus.app;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeader {
	@RequestMapping("/requestHeader")
	public void main(HttpServletRequest req) {
		Enumeration<String> e = req.getHeaderNames();
		
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name + " : " + req.getHeader(name));
		}
	}
	
}
