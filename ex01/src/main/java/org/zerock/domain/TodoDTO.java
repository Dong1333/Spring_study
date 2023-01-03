package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//SampleController에서 처리할 날짜 형태의 값을 저장할 클래스 작성
@Data
public class TodoDTO {
	
	private String title;
	// SampleController에 @InitBinder(자동호출 어노테이션)
	// 없이도 날짜 형식 적용가능한 방법
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
	
}
