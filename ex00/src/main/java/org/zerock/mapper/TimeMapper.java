package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

//  MyBatis의 어노테이션을 이용해서 SQL을 메서드에 추가한다.
public interface TimeMapper {
	@Select("SELECT sysdate FROM dual")
	public String getTime();
}
