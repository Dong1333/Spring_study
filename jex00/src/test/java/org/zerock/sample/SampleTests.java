package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


// Java를 이용하는 경우의 테스트 설정
// 스프링을 실행하는 역할
@RunWith(SpringJUnit4ClassRunner.class)

// 스프링 빈 등록
@ContextConfiguration(classes= {RootConfig.class})

// Loombok을 이용하여 기록하는 Logger를 변수로 생성
@Log4j
public class SampleTests {
	
	@Setter(onMethod_ = {@Autowired})
	private Restaurant restaurant;
	
	// JUnit에서 테스트 대상입니다.
	@Test
	public void textExist(){
		// restaurant가 null이 아니어야만 테스트가 성공합니다!
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("--------------------------");
		log.info(restaurant.getChef());
	}
}
