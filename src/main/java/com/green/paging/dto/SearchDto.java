package com.green.paging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SearchDto {
	private int    pageNo;           // 현재 페이지 번호 : nowpage pageNo
	private int    numOfRows;        // 페이지가 출력할 데이터 갯수 : numOfRows
	private int    pageSize;         // 화면하단에 출력할 페이지번호 갯수 : 10
	
	private String keyword;          // 검색 키워드
	private String searchType;       // 검색 유형 : title, content, writer
	 
//	constructor : 초기값을 설정
	public SearchDto() {
		this.pageNo    = 1;
		this.numOfRows = 10;
		this.pageSize  = 10;
	}
	
//	페이징된 검색결과
	private Pagination Pagination; // 페이지네이션 정보
	
//	매소드 추가
	public int getOffset() {
		return (this.pageNo - 1) * this.numOfRows;
	}
	
	 
}
