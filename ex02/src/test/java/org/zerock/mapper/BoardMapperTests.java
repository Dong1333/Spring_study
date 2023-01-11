package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// BoardMapper 인터페이스를 테스트 할 수 있는 테스트 클래스 작성
// 인터페이스 이지만 아래 의존성 주입을 통하여 구현객체를 생성하여 테스트 할 수 있다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests { 
	
	// 의존성 주입을 통한 mapper 객체 자동 생성
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	// BoardMapper의 insert()의 테스트 코드
	@Test
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 ");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	// BoardMapper의 InsertSelectKey()의 테스트 코드
	@Test
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 slelect key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	// BoardMapper의 read()의 테스트 코드
	@Test
	public void testRead() {
		
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(3L);
		
		log.info(board);
	}
	
	// BoardMapper의 delete()의 테스트 코드
	@Test
	public void testDelete() {
	
		log.info("DELETE COUNT : "+ mapper.delete(3L));
	}
	
	// BoardMapper의 testUpdate()의 테스트 코드
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		// 실행전 존재하는 번호인지 확인할 것
		board.setBno(27L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		
		log.info("UPDATE COUNT : " + count);
	}
	
	
}
