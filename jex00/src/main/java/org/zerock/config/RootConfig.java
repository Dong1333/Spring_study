package org.zerock.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


// java 설정을 이용하는 의존성 주입 
@Configuration
// XML의 '컴포넌트 스캔' 과 같은 역할
// 지정된 패키지 속 클래스들을 모두 스캔 후 관리 대상들을 관리한다. 
@ComponentScan(basePackages= {"org.zerock.sample"})
// Mapper 설정을 java 코드로 설정하기 
@MapperScan(basePackages= { "org.zerock.mapper"})
public class RootConfig {
	// HikariCP 이용하기 위한 java로 bean 등록
	@Bean
	public DataSource dataSource() {
		// HikariConfig 객체 생성
		HikariConfig hikariConfig = new HikariConfig();
		
		// 	HikariConfig객체에 DB 드라이버, 주소(파라미터 값 포함) 을 저장한다.
//		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
//		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/firstDB?useSSL=false");
		
		//java로 log4jdbc사용 
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/firstDB?useSSL=false");
		
		// HikariConfig 객체에 사용자 정보를 저장해 준다.
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("12341234");
		
		// HikariDataSource 객체를 생성하고 위에 세팅했던 정보가 담긴 hikariConfig를 전달해 준다.
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
		}
	
	// Mybatis java설정으로 빈 등록
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory)sqlSessionFactory.getObject();
		
	}
	

}
