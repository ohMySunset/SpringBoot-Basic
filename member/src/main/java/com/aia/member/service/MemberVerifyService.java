package com.aia.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aia.member.dao.MemberDao;

@Service
public class MemberVerifyService {
	
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int memberVerify(int idx, String code) {
		int result = 0; // 0 -> 잘못된 요청 , 1 -> 인증완료, 3-> 이미 인증이 완료됨
		
		dao = template.getMapper(MemberDao.class);
		
		int isVerify = dao.selectMemberByIdxVerify(idx);
		
		// 결과 : 0 또는 1
	    if(isVerify==1) {
	    	result = 3;
	    } else {
	    	result = dao.updateMemberVerify(idx, code);
	    }
		
		
		return result;
		
	}

}
