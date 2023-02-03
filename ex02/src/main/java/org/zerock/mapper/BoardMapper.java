package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

// MyBatis는 간단한 SQL을 처리하는데 어노테이션을 사용한다.
// 하지만 xml을 사용하도록 한다(org.zerock.mapper.BoardMapper.XML)
public interface BoardMapper {
	// @Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	// 페이징 처리 
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// insert만 처리되고 생성된 PK 값을 알 필요가 없는 경우
	public void insert(BoardVO board);
	
	// insert문이 실행되고 생성된 PK 값을 알아야 하는 경우
	public void insertSelectKey(BoardVO board);
	
	// insert가 된 데이터를 PK를 이용하여 조회하는 작업 처리
	public BoardVO read(Long bno);
	
	// PK값을 이용하여 데이터 삭제 작업 (in : Long / out : int)
	public int delete(Long bno);
	
	// 업데이트 메소드( 최종 수정시간은 데이터베이스 내 현재 시간)
	// (in : BoardVO / out : int)
	public int update(BoardVO board);
	
	// 모든 게시물의 개수 조회
	public int getTotalCount(Criteria cri);

}
