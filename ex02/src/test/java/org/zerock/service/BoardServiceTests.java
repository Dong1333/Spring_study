package org.zerock.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// 이 테스트 코드 실행시 컨텍스트가 어떤 설정파일들을 읽어야 하는지 지정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// java 설정의 경우
// @ContextConfiguration(classes = {org.zerock.congig.RootConfig.class})
@Log4j
public class BoardServiceTests {
	
	// BoardService 객체생성 후 주입 
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	// BoardService 객체가 제대로 주입이 가능한지 확인하는 테스트 
	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
	}
 
	// BoardServiceImple에 register()를 테스트 하는 코드 
	@Test
	public void testRegister() {
		// BoardVO 객체 생성 후 값 저장
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("등록자 오후 7시");
		
		//  mapper insertSelectKey()를 사용한
		// 나중에 생성된 게시물의 번호를 확인할 수 있는
		// BoardService 객체 속 register 메소드에 board객체를 넣어 실행
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	//  현재 테이블에 저장된 데이터를 가져오는걸 테스트 하는 코드 
	// service.getList()에 반환 값을 하나씩 출력 
	@Test
	public void testGetList() {
		// forEach 함수 : list 값을 board에 담아서 log로 출력한다 
		//service.getList().forEach(board -> log.info(board));
		
		// 2페이지에 넣을 10개의 게시글 목록 
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
	}
	
	// bno 번호 기준으로 BoardVO의 인스턴스가 리턴되어 로그로 출력
	@Test
	public void testGet() {
		
		log.info(service.get(1L));
	}

	//  게시물이 존재하면 true를 반환
	@Test
	public void testDelete() {
		// 게시물의 번호 존재 여부를 확인하고 테스트해야 한다.
		log.info("REMOVE RESULT : " + service.remove(2L));
		
	}

	// 게시물 조회하고 title 값 수정한 이후에 없데이트 진행
	// 만약 board가 비어있다면 리턴..?
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(1L);
		
		if(board == null) {
			return; 
		}
	}
}
