package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	// 고정적으로 드라이버 로드 하기(항상 사용가능 / 공유의 개념)
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 테스트 코드	
	@Test
	public void testConnection(){
		
		// DB 접속 정보 (주소/DB이름, 유저 이름, 접속 비밀번호)
		String sUrl = "jdbc:mysql://localhost:3306/firstDB";
		String sUser = "root";
		String sPwd = "12341234";
		
		
		try(Connection con = 
			DriverManager.getConnection(sUrl, sUser, sPwd))
		{
		// Log4j를 이용한 로그 정보 출력
		log.info(con);
		} 
		catch (Exception e) {
		fail(e.getMessage());
		}
	}
}
