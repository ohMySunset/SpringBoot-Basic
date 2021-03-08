package com.aia.member.domain;

public class SearchParam {
	
	private int p; // 검색 결과 페이지 넘버
	private String searchType;  // 검색 타입
	private String keyword;     // 검색어
	
	
	
	public SearchParam(int p, String searchType, String keyword) {
		this.p = p;
		this.searchType = searchType;
		this.keyword = keyword;
		
		if(this.p<1) {
			this.p = 1;
		}
	}

	public SearchParam() {
		this.p = 1;
	}


	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchParam [p=" + p + ", searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	
	
	
	

}
