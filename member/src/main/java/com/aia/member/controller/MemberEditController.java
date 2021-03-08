package com.aia.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aia.member.domain.Member;
import com.aia.member.domain.MemberEditRequest;
import com.aia.member.service.MemberEditService;

@Controller
@RequestMapping("/member/edit")
public class MemberEditController {
	
	@Autowired
	private MemberEditService editService;
	
	@RequestMapping(method =RequestMethod.GET)
     public String editForm(
    		 @RequestParam("idx") int idx,
    		  Model model
    		                 ) {
		
		// Service -> MemberDao -> mapper -> Member
		
		Member member = editService.getMember(idx);
		model.addAttribute("member", editService.getMember(idx));
		System.out.println(member);
		
    	 return "member/editForm";
     }
	
	@RequestMapping(method = RequestMethod.POST)
	public String editMember(
			MemberEditRequest editRequest,
			HttpServletRequest request,
			Model model) {
		
		//Service -> MemberDao : update -> member -> int
		model.addAttribute("result", editService.editMember(editRequest, request));
		
		return "member/edit";
	}
	

}
