package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	// produces 속성 =  해당 메서드가 생산하는 MIME 타입을 의미
	// 문자열로 직접 지정할 수 있고, 메서드 내의 MidiaType이라는 클래스도 이용할 수 있다
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	// 텍스트를 호출
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		// @RestController의 경우에는 순수한 데이터
		return "안녕하세요";
	}
	
		// produces 속성 =  해당 메서드가 생산하는 MIME 타입을 의미
		@GetMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					 MediaType.APPLICATION_XML_VALUE})
		// SampleVO를 호출 
		public SampleVO getSample() {
	
			return new SampleVO(112, "스타", "로드");
		}
		
		@GetMapping(value = "/getSample2") 
		public SampleVO getSample2() {
			return new SampleVO(113, "라쿤", "로켓");
		}
		
		// 내부적으로 1부터 10미만까지의 루프를 처리하면서 
		// SampleVO 객체를 만들어서 List<SampleVO>로 만들어 낸다
		@GetMapping(value = "/getList")
		public List<SampleVO> getList() {
			return IntStream.range(1,10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last"))
					// 스트림의 요소들을 모아 List 인스턴스를 반환
					.collect(Collectors.toList());
		}
		
		// 맵 사용
		@GetMapping(value = "/getMap")
		public Map<String, SampleVO> getMap() {
			
			Map<String, SampleVO> map = new HashMap<>();
			
			// 맵의 경우 ‘키’와 ’값’을 가지는 하나의 객체로 간주된다.
			map.put("First", new SampleVO(111, "그루트", "주니어"));
			
			return map;
		}
		
		// ResponseEntity는 데이터와 함께 HTTP 헤더의 상태 메세지 등을 같이 전달하는 용도
		@GetMapping(value = "/check", params = {"height", "weight"})
		public ResponseEntity<SampleVO> check(Double height, Double weight){
			
			SampleVO vo = new SampleVO(0, "" + height, "" + weight);
			
			ResponseEntity<SampleVO> result = null;
			
			// heigt값이 150보다 작다면 502(bad gateway) 상태 코드와 데이터를 전송
			// 그렇지 않으면 200(ok) 코드와 데이터를 전송한다.
			if (height < 150) {
				result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			} else {
				result = ResponseEntity.status(HttpStatus.OK).body(vo);
			}
			return result;
		}
		
		// @Requestbody가 말 그대로 요청(request)한 내용(body)을 처리하기 때문에
		// 일반적인 파라미터 전달방식을 사용할 수 없기 때문에 @PostMapping 사용 
		@PostMapping("/ticket")
		public Ticket conver(@RequestBody Ticket ticket) {
			
			log.info("conver..........ticket" + ticket);
			
			return ticket;
		}

}
