package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

// MyBatisd은 간단한 SQL을 처리하는데 어노테이션을 사용한다. 
public interface BoardMapper {
	// @Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();

}
