package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 내부적으로 스프링 컨테이너를 생성해주는 
// SpringJUnit4ClassRunner.class를 실행하는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
// 생성된 스프링 컨테이너에 스프링 빈을 추가하기 위해
// 설정 파일을 읽어야 하는데, 이런 설정파일을 로드하는 어노테이션
@WebAppConfiguration 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Java 설정을 사용하는 경우
// @ContextConfiguration(classes={RootConfig.class})
@Log4j
public class DataSourceTests {
	
	@Setter(onMethod_ = { @Autowired })
		private DataSource dataSource;
		
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;
	
//	// HikiraCP 테스트
//	@Test
//		public void testConnection() {
//			try (Connection con = dataSource.getConnection()){
//				log.info(con);
//			} catch (Exception e) {
//				fail(e.getMessage());
//			}
//		}
//	
	// Mybatis Test
	@Test
		public void testMyBatis() {
			try (SqlSession session  = sqlSessionFactory.openSession();
					Connection con = session.getConnection();
					){
				log.info(session);
				log.info(con);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
}
