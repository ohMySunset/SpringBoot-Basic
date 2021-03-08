package com.aia.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aia.member.dao.MemberDao;
import com.aia.member.domain.Member;

@Service
public class MemberLoginService {
	
	private MemberDao dao;
	
	@Autowired
	SqlSessionTemplate template;
	
	public boolean login(HttpServletRequest request,
			             HttpServletResponse response ) { 
		
		String id = request.getParameter("userid");
		String pw = request.getParameter("pw");
		String chk = request.getParameter("chk");
		
		dao = template.getMapper(MemberDao.class);
			
		boolean loginCheck = false; //로그인 되지 않은 상태
		
		// 1. Dao -> id와 pw 데이터로 검색질의 => 존재하면 로그인 처리		
		Member member = dao.selectLogin(id, pw);		
		System.out.println(member);
		
		if(member != null) {
			
			if(member.getVerify() == 'Y') {
				
				// 현재 세션의 속성에 LoginInfo 인스턴스를 저장
				request.getSession().setAttribute("loginInfo", member.toLoginInfo());
				loginCheck = true;
				
				// 2. uid의 쿠키처리
				if(chk != null && chk.equals("on")) {
					// 쿠키 생성
					Cookie cookie = new Cookie("uid", id);
					cookie.setMaxAge(60*60*24*365);
					response.addCookie(cookie);
				} else {
					// 쿠키 소멸
					Cookie cookie = new Cookie("uid", id);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			} else {
				loginCheck = true;
				request.setAttribute("msg","미인증 이메일입니다. 인증 후 로그인 해주세요.");
				
			}
			
			
		}		
		
		return loginCheck;
	}
	
	

}
