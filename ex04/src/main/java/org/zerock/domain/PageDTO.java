package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	// 화면 페이지 번호(시작번호, 끝번호)
	private int startPage;
	private int endPage;
	// 이전, 다음으로 확인할 데이터(페이지)가 있는지 참 거짓 여부 확인변수
	private boolean prev, next;
	
	// 마지막 페이지를 알기위한 전체 데이터 수 변수와
	// 페이지 개수 기준을 잡아줄 Criteria타입 변수 선언
	// 페이지 개수는 10개라는 확정은 없으니 클래스 타입으로 하여 추후 
	// 한 페이지 당 10개, 20개, 15개 등등 변화에 용이하게끔 클래스로 선언
	private int total;
	private Criteria cri;
	
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// 끝 페이지 공식의 Math.ceil()은 소수점을 올림으로 처리 
		// 예) 12페이지( [12])의 데이터 경우 : Math.ceil(1.2) * 10 = 20(페이지에서 끝..?? *나머지 8페이지는 공백)
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		// 시작 페이지는 끝페이지에서 -9하면 된다. 
		// 20 - 9 = 11. 즉 ([11], [12]......[20]) 완성
		this.startPage = this.endPage - 9;
		
		// 진짜 끝번호 만일 끝 페이지(endPage -> 20)와 한 페이지당 출력되는 데이터 수(amount -> 10)의 곱이
		// 전체 데이터 수(total -> 120)보다 크다면(120 < 200) 끝 번호(endPage)는 다시 total을 이용해서 다시 계산
		// 또 다른 ex) 71.0(글 개수) / 10 =7.1 -> 8페이지
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount())); // 120 /10 == 12(페이지에서 끝) 
		
		// 끝 페이지 = 20, 진짜 끝 페이지 = 12면 
		// 즉 진짜 끝페이지가 더 적으면 적용
		// 만약 12 < 20
		if (realEnd < this.endPage) {
			// endpage = 12
			this.endPage = realEnd;
		}
		
		// 이전(prev)링크는 시작 번호(startPage)가 1보다 큰 경우라면 존재
		this.prev = this.startPage > 1;
		
		// 다음(next)링크는 위의 realEnd가 끝 페이지(endPage 12)보다 큰 경우에만 존재
		// ex) 총 데이터 개수가 300개면 30페이지가 realEnd가된다
		// 20 < 30 즉 12페이지의 끝번호 20 뒤에 아직 30페이지까지의 데이터가 남아있으므로
		// next(다음)링크가 존재하게된다.
		this.next = this.endPage < realEnd;
		
	
	}
}
