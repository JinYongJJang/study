package com.fastcampus.ch2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class requestMessage {
	@RequestMapping("/requestMessage")
	public void main(HttpServletRequest req) throws Exception {
		String requestLine = req.getMethod();  // GET
		requestLine += " " + req.getRequestURI();  // /ch2/requestMesaage
		
		String queryString = req.getQueryString();
		requestLine += queryString == null ? "" : "?"+queryString;     //    ?year=2022
		requestLine += " " + req.getProtocol();  // HTTP/1.1
		System.out.println(requestLine);
		
		
		Enumeration<String> e = req.getHeaderNames();
		
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name + ":" + req.getHeader(name));
		}
		
		final int CONTENT_LENGTH = req.getContentLength();
		
		if(CONTENT_LENGTH > 0) {
			byte[] content = new byte[CONTENT_LENGTH];
			
			InputStream in = req.getInputStream();
			in.read(content, 0, CONTENT_LENGTH);
			
			System.out.println();
			System.out.println(new String(content, "utf-8"));
			
		}
	}
}
