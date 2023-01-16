package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService {

	public void register(BoardVO board);
	
	// 특정한 게시물을 가져오는 메소드 따라서 반환 값 = 게시물(BoardVO)
	public BoardVO get(Long bno);
	// 게시물 수정
	public boolean modify(BoardVO board);
	// 게시물 삭제
	public boolean remove(Long bno);
	// 전체 리스트를 구하는 메소드
	public List<BoardVO> getList();
}