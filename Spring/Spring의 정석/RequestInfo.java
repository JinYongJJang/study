package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestInfo {
	@RequestMapping("/requestInfo")
	public static void main(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
		System.out.println("request.getContentLength() = " + request.getContentLength());
		System.out.println("request.getContentType() = " + request.getContentType());
		
		System.out.println("request.getMethod() = " + request.getMethod());
		System.out.println("request.getProtocol() = " + request.getProtocol());
		System.out.println("request.getScheme() = " + request.getScheme());
		
		
		System.out.println("request.getServerName() = " + request.getServerName());
		System.out.println("request.getServerPort() = " + request.getServerPort());
		System.out.println("request.getRequestURL() = " + request.getRequestURL());
		System.out.println("request.getRequestURI() = " + request.getRequestURI());
		
		System.out.println("request.getContextPath() = " + request.getContextPath());
		System.out.println("request.getServletPath() = " + request.getServletPath());
		System.out.println("request.getQueryString() = " + request.getQueryString());
		
		System.out.println("request.getLocalName() = " + request.getLocalName());
		System.out.println("request.getLocalPort() = " + request.getLocalPort());
		
		System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
		System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
		System.out.println("request.getRemotePort() = " + request.getRemotePort());
		
	}

}
