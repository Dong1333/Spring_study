package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
	//  번호(tno)와 소유주(owner), 등급(grade)을 지정
	private int tno;
	private String owner;
	private String grade;
}
