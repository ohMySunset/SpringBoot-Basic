package com.aia.member.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aia.member.dao.MemberDao;
import com.aia.member.domain.Member;
import com.aia.member.domain.MemberRegRequest;


@Service
public class MemberRegService {

	private MemberDao dao;

	@Autowired
	SqlSessionTemplate template;
	
	//@Autowired
	//private MailSenderService mailSenderService;

	// 파일을 업로드, 데이터 베이스 저장
	@Transactional
	public int memberReg(MemberRegRequest regRequest, HttpServletRequest request) {
		int result = 0;

		File newFile = null;

		String newFileName = null;

		if (regRequest.getUserPhoto() !=null && !regRequest.getUserPhoto().isEmpty()) {
			// 웹 경로
			String uploadPath = "/fileupload/member";
			// 시스템의 실제 경로
			String saveDirPath = request.getSession().getServletContext().getRealPath(uploadPath);
			// 새로운 파일 이름
			newFileName = regRequest.getUserid() + System.currentTimeMillis();

			newFile = new File(saveDirPath, newFileName);

			// userPhoto -> 파일객체로 저장
			try {
				regRequest.getUserPhoto().transferTo(newFile);
				
				FileOutputStream thumbnail = new FileOutputStream(new File(saveDirPath, "s_"+ newFileName));
				// 썸네일 저장 50X50
				//Thumbnailator.createThumbnail(regRequest.getUserPhoto().getInputStream(), thumbnail, 50, 50);
				
				
				thumbnail.close();
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// toMember() : MemberRegRequest -> Member
		Member member = regRequest.toMember();
		if(newFileName != null) {
		member.setMemberphoto(newFileName);
		}
		
		
		// 파일 저장		
		//try {
			// 데이터 베이스 입력
			dao = template.getMapper(MemberDao.class);
			
			// member_count테이블 > membercount컬럼 : membercount+1(update)
			dao.memberCountUpdate();
			
			//int num1 = 10/0;
		
			//회원 DB에 insert			
			result = dao.insertMember(member);
			
			
			// 메일 발송 : 인증처리를 하는 페이지 /op/member/verify?id=40&code=난수
			//int mailSendCnt = mailSenderService.send(member);
			//System.out.println("메일 발송  처리 횟수 : "+ mailSendCnt);

	//	} catch (Exception e) { // DB관련 Exception
	//		e.printStackTrace();

			// 만약 DB오류가 났는데 현재 파일이 저장이 되버린다면? -> 삭제
			if (newFile!=null && newFile.exists()) {
				newFile.delete();
			}
	//	}

		return result;
	}

}
