package com.aia.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aia.member.domain.MemberRegRequest;
import com.aia.member.service.MemberRegService;

@Controller
@RequestMapping("/member/reg")
public class MemberRegController {
	
	@Autowired
	private MemberRegService regService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getRegForm() {
		return "member/memberRegForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String memberReg(@ModelAttribute("regData") MemberRegRequest regRequest,
			                HttpServletRequest request,
			                Model model) {
		
		System.out.println(request);
		
		//가입 여부 숫자로 반환받아 저장 -> view에서 확인하기 위해 
		int result = regService.memberReg(regRequest, request);
		
		model.addAttribute("result", result);
		
		return "member/memberRegView";
	}
	
	
	
	
}