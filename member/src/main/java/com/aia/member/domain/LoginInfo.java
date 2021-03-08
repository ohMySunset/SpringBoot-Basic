package com.aia.member.domain;

public class LoginInfo {  // 로그인에 성공한 회원의 정보를 보여줄 빈즈

	private String memberid; // 회원의 아이디
	private String membername; // 회원의 이름
	private String memberphoto; // 회원의 프로필 사진

	public LoginInfo(String memberid, String membername, String memberphoto) {
		this.memberid = memberid;
		this.membername = membername;
		this.memberphoto = memberphoto;
	}

	public String getMemberid() {
		return memberid;
	}

	public String getMembername() {
		return membername;
	}

	public String getMemberphoto() {
		return memberphoto;
	}

	@Override
	public String toString() {
		return "LoginInfo [memberid=" + memberid + ", membername=" + membername + ", memberphoto=" + memberphoto + "]";
	}

	
}
