package org.zerock.domain;

import java.util.Date;

import lombok.Data;

// 값 자체를 표현하는 객체인 VO 객체
// VO 클래스 생성
@Data
public class BoardVO {
	private Long bno; // 테이블 고유 번호
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private Date regdate; // 생성시간
	private Date updateDate; // 최종수정시간 
}