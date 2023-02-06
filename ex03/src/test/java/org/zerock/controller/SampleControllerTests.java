package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 스프링 테스트를 Junit으로 진행.
@RunWith(SpringJUnit4ClassRunner.class)

// 테스트를 위해 부트 스트랩 된 ApplicationContext 가 WebApplicationContext 의 인스턴스여야 함 을 나타낸다.
// Controller및 web환경에 사용되는 빈들을 자동으로 생성하여 등록
@WebAppConfiguration

// spring context의 빈 설정 파일을 아래와 파일들로 사용한다는 의미.
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class SampleControllerTests {
	
	// WebApplicationContext에 의존적이니 자동주입 시켜줘~
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	// 
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//  SamplerController에 작성된 convert() 메서드를 테스트
	@Test
	public void testConvert() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		//  Gson 라이브러리는 Java의 객체를 JSON 문자열로 변환하기 위해서 사용
		String jsonStr = new Gson().toJson(ticket);
		
		log.info(jsonStr);
		
		// MockMvc는 contentType()을 이용해서 전달하는 데이터가 무엇인지 알려줄 수 있다.
		mockMvc.perform(post("/sample/ticket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().is(200));
	}
	
	
}
