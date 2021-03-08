package com.aia.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aia.member.service.MemberLoginService;

@Controller
@RequestMapping("/member/login")
public class MemberLoginController {
	
	@Autowired
	private MemberLoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm() {
		return "member/loginForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest request, // id,pw 받고, session 불러오기
			            HttpServletResponse response,
			            Model model) { // cookie 저장하기
		
		model.addAttribute("loginCheck",loginService.login(request, response));
		
		return "member/loginView";
	}
	

}
