package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcTemplate;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithRawJdbc;
import com.poscodx.guestbook.vo.GuestbookVo;

@Controller
@RequestMapping("/")
public class GuestbookController {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@RequestMapping("/")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteform(Model model, String no) {
		model.addAttribute("no", no);
		return "delete";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookRepository.deleteByNoAndPassword(vo.getNo(), vo.getPassword());
		return "redirect:/";
	}
	
	
}
