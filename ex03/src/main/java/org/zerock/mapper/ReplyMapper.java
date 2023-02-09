package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

// 댓글 
public interface ReplyMapper {
	
	// 등록
	public int insert(ReplyVO vo);
	
	// 조회
	public ReplyVO read(Long rno);
	
	// 삭제
	public int delete (Long rno);
	
	// 수정
	public int update(ReplyVO vo);
	
	// 댓글 목록 페이징 처리
	// 페이징 처리는 기존과 동일하게 Criteria를 이용
	// 특정한 게시물의 댓글만을 대상으로 하기 때문에 추가로 게시물 번호 이용
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	// 댓글의 숫자 파악
	public int getCountByBno(Long bno);
}
