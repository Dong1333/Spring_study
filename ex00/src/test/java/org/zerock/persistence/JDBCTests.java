package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	// 드라이버 연결
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
		
		String sUrl = "jdbc:mysql://localhost:3306/firstDB";
		String sUser = "root";
		String sPwd = "12341234";
		
		
		try(Connection con = 
			DriverManager.getConnection(sUrl, sUser, sPwd))
		{
		log.info(con);
		} 
		catch (Exception e) {
		fail(e.getMessage());
		}
	}
}
