package com.aia.member.dao;

import java.util.List;
import java.util.Map;

import com.aia.member.domain.Member;

public interface MemberDao {
	
	// public abstract
	// 회원 등록을 위한 메서드
	int insertMember(Member member);
	
	// 전체 회원의 수
	int memberCountUpdate();
	
	// 회원 로그인을 위한 메서드
	Member selectLogin(String id, String pw);
	
	// 회원의 총 수를 구할 메서드
	int selectTotalCount();
	
	// 회원 정보를 구하는 메서드 (페이지 번호에 맞는)
	//List<Member> selectMemberList(int startRow, int cntPerPage);
	List<Member> selectMemberList(Map<String, Object> param);
	
	//2021.1.28 : REST API -> GET
	List<Member> selectAllMemberList();
		
	// 검색한 회원의 수를 구하는 메서드
	int selectSearchMemberCount(Map<String, Object> listMap);
	
    // 회원 정보 삭제
	int deleteMemberByIdx(int idx);
	
	// idx로 회원 정보 구하는 메서드
	Member selectMemberByIdx(int idx);
	
    // 회원정보를 수정하는 메서
	int updateMember(Member member);
	
	// id 존재 유무 확인하는 메서드
	int selectMemberByIdCount(String id);
	
	// idx, verify 조건으로 조회하는 메서드
	int selectMemberByIdxVerify(int index);
	
	// 인증처리가 안되어있을  verify -> Y
	int updateMemberVerify(int idx, String code);  // param이 여러개일 때 param1, param2 이런식으로 들어옴.
	
}

