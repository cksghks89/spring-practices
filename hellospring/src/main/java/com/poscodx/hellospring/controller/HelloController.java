package com.poscodx.hellospring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello(HttpServletRequest request, String name) {
		System.out.println(name);
		System.out.println(request.getParameter("name") + "-=");

		return "/WEB-INF/views/hello.jsp";
	}
}
