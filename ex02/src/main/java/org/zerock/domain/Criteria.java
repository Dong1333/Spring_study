package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 검색의 기준을 정하는 Criteria 클래스 작성
// pageNum, amount 값을 전달하는 용도
public class Criteria {
	// '페이지 번호', '페이지당 보여줄 글 개수' 변수 선언
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

}
