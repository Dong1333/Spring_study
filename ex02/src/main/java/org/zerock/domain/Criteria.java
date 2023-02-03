package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// MyBatis는 값을 얻어올때 get으로 얻어와서 getter/setter가 필요
@Getter
@Setter
@ToString
// 검색의 기준을 정하는 Criteria 클래스 작성
// pageNum, amount 값을 전달하는 용도
public class Criteria {
	// '페이지 번호', '페이지당 보여줄 글 개수' 변수 선언
	private int pageNum;
	private int amount;
	
	// 검색시 필요한 타입(게시판, 제목, 제목and 게시판, 내용 등등)과 키워드
	private String type; // w, t, tw, w 들을 모두 처리하려면 배열이 필요
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}


	  // BoardMapper.xml 속 검색 조건이 각 글자(T, W, C)로 구성되어 있으므로
	  // 검색 조건을 배열로 만들어 한 번에 처리
	  public String[] getTypeArr() {
		    
	 // 만약 타입이 널이면 빈 문자 배열, 아니라면 글자를 하나씩 다 쪼갠다. (twc -> t, w, c)
	  return type == null? new String[] {}: type.split("");
	}
	 
	// UriComponentsBuilder를 이용하여 여러 개의 파라미터들을 연결해서 URL의 형태로 만들어주는 기능
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.getAmount())
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
