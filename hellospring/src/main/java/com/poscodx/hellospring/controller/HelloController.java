package com.poscodx.hellospring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}

	@RequestMapping("/hello2")
	public String hello2(String name) {
		System.out.println("name: " + name);
		return "/WEB-INF/views/hello.jsp";
	}

	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("/WEB-INF/views/hello.jsp");
		return mav;
	}

	@RequestMapping("/hello4")
	public String hello4(String name, Model model) {
		model.addAttribute("name1", name);
		ModelAndView mav = new ModelAndView();
		mav.addObject("name2", name);
		return "/WEB-INF/views/hello4.jsp";
	}

	@ResponseBody
	@RequestMapping("/hello5")
	public String hello5(String name) {
		return "<h1>안녕하세요</h1>";
	}

	@RequestMapping("/hello6")
	public String hello6(String name) {
		return "redirect:/hello";
	}

	// 안 좋은 예시 (프레임워크를 이용하는 것을 권장)
	@RequestMapping("/hello7")
	public void hello7(HttpServletResponse response) throws IOException {
		response.getWriter().print("<h1>Hello World</h1>");
	}

}
