package org.zerock.config;

import javax.sql.DataSource;

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
public class RootConfig {
	// 
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/firstDB");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("12341234");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
		}

}
