package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

// ServletContext를 이용하기 위해서 사용
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
// java 설정의 경우
// @ContextConfiguration(classes = {
//	org.zerock.congig.RootConfig.class,
//	org.zerock.congig.ServletConfig.class})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	

	// 말 그대로 ‘가짜 mvc’
	// 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것처럼 만들어서
	// Controller를 실행해 볼 수 있다
	private MockMvc mockMvc;
	
	// 모든 테스트 시작 전 실행
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// BoardController의 list() 테스트 하는 메소드 
	// 게시물의 목록을 전달 
	// MockMvcRequestBuilders라는 존재를 이용해서 GET 방식의 호출
	@Test
	public void testList() throws Exception {
		// BoardController의 getList()에서 반환된 결과를 이용해서
		// Model에 어떤 데이터 들이 담겨있는지 확인한다.
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	// BoardController의 register() 테스트 하는 메소드 
	// 등록 작업이 끝나면 다시 목록화면으로 이동
	@Test
	public void testRegister() throws Exception{
		// POST 방식으로 데이터를 전달 / param()을 이용해서 전달할 파라미터들을 지정
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
				
	}
	
	// 조회, BoardController의 GET() 테스트 하는 메소드
	@Test
	public void tetGet() throws Exception {
		
		// 특정 게시물을 조회할 때 반드시 ‘bno’라는 파라미터가 필요하므로 param()을 추가.
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "1"))
				.andReturn()
				.getModelAndView().getModelMap());
	}
	
	// 수정, BoardController의 modify() 테스트 하는 메소드
	@Test
	public void testModify() throws Exception {
		// POST 방식으로 데이터를 전달 / param()을 이용해서 전달할 파라미터들을 지정
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "1")
				.param("title", "수정된 새글 제목")
				.param("content", "수정된 새글 내용")
				.param("writer", "user00"))
			.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
	} 
	// 삭제, BoardController의 remove() 테스트 하는 메소드
	@Test
	public void testRemove() throws Exception {
		// 삭제 전 데이터베이스에 게시물 번호 확인할 
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "25")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
	}
}
