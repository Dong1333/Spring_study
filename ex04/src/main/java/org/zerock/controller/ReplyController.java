package org.zerock.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@AllArgsConstructor
@Log4j
public class ReplyController {
	
	private ReplyService service;
	
	// 댓글 추가
	// POST 방식으로만 동작하도록 설계, consumes와 produces를 이용해서 JSON 방식의 데이터만 처리
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE})
	// @RequestBody를 적용하여 JSON 데이터를 ReplyVO 타입으로 변환하도록 지정
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
	
		log.info("ReplyVO: " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT : " + insertCount);
		
		// 삼항연산자 처리. 댓글이 추가된 숫자를 확인해서 브라우저에 ‘200 OK’ 혹은’500 Internal Server Error’를 반환
		return insertCount == 1 
		? new ResponseEntity<> ("success", HttpStatus.OK)
		: new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	// 특정 게시물의 댓글 목록 확인
//	// ‘/{bno}/{page}’의 ‘page’값은 Criteria를 생성해서 직접 처리해야 한다
//	@GetMapping(value = "/pages/{bno}/{page}",
//				produces = {
//					MediaType.APPLICATION_XML_VALUE,
//					MediaType.APPLICATION_JSON_UTF8_VALUE })
//	// 게시물의 번호는 @PathVariable을 이용해서 파라미터로 처리
//	public ResponseEntity<List<ReplyVO>> getList(
//			@PathVariable("page") int page,
//			@PathVariable("bno") Long bno) {
//		
//		log.info("getList........");
//		Criteria cri = new Criteria(page, 10);
//		log.info(cri);
//		return new ResponseEntity<> (service.getList(cri, bno), HttpStatus.OK);
//	}
//	

	@GetMapping(value = "/pages/{bno}/{page}", 
			produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {

		Criteria cri = new Criteria(page, 10);
		
		log.info("get Reply List bno: " + bno);

		log.info("cri:" + cri);

		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}

	
	// 댓글 삭제/조회
	@GetMapping(value = "/{rno}",
		produces = { MediaType.APPLICATION_XML_VALUE,
				   MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get : " + rno);
		
		return new ResponseEntity<> (service.get(rno), HttpStatus.OK);
	
	}
	
	@DeleteMapping(value="/{rno}", 
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove: " + rno);
		
		return service.remove(rno) == 1 
				? new ResponseEntity<> ("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 수정
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo,
			@PathVariable("rno") Long rno) {
		vo.setBno(rno);
		
		log.info("rno : " + rno);
		log.info("modify : " + vo);
		
		return service.remove(rno) == 1 
				? new ResponseEntity<> ("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
