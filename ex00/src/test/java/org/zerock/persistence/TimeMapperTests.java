package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//내부적으로 스프링 컨테이너를 생성해주는 
//SpringJUnit4ClassRunner.class를 실행하는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
//생성된 스프링 컨테이너에 스프링 빈을 추가하기 위해
//설정 파일을 읽어야 하는데, 이런 설정파일을 로드하는 어노테이션
@WebAppConfiguration 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Java 설정을 사용하는 경우
//@ContextConfiguration(classes={RootConfig.class})
@Log4j
public class TimeMapperTests {

	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	// TimeMapper가 정상적으로 사용이 가능한지 알아보기위한 테스트 코드
	@Test
	public void testGetTime() {
		// 실제 동작하는 클래스의 이름을 확인
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
	
	// 테스트
	@Test
	public void testGetTime2() {
		// 실제 동작하는 클래스의 이름을 확인
		log.info("getTime2()");
		log.info(timeMapper.getTime2());
	}
	
}
