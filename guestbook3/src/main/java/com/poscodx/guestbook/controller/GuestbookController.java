package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithRawJdbc;
import com.poscodx.guestbook.vo.GuestbookVo;

@Controller
@RequestMapping("/")
public class GuestbookController {
	@Autowired
	private GuestbookRepositoryWithRawJdbc guestbookRepository;
	
	@Autowired
	private GuestbookRepositoryWithJdbcContext guestbookRepository2;
	
//	@Autowired
//	private GuestbookRepositoryWithJdbcTemplate guestbookRepository3;
	
	@RequestMapping("/")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookRepository2.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteform(Model model, String no) {
		model.addAttribute("no", no);
		return "delete";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookRepository2.deleteByNoAndPassword(vo.getNo(), vo.getPassword());
		return "redirect:/";
	}
	
	
}
