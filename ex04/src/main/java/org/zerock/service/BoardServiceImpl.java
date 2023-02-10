package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
// 비즈니스 영역을 담당하는 객체를 표시하기 위해 사용
@Service
// BoardService 인터페이스의 구현체 작성 
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	// spring 4.3 이상에서 자동 처리
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	// mapper insertSelectKey()를 이용해서 나중에 생성된 게시물의 번호를 확인할 수 잇게 작성
	@Override
	public void register(BoardVO board) {
		
		log.info("register......" + board);
		// // insert문이 실행되고 생성된 PK 값을 알아야 하는 경우
		mapper.insertSelectKey(board);
	}

	// 게시물의 번호가 파라미터이고 BoardVO의 인스턴스가 리턴
	@Override
	public BoardVO get(Long bno) {
		
		log.info("get......." + bno);
		
		// 	insert가 된 데이터를 PK를 이용하여 조회한 값을 반환
		return mapper.read(bno);
	}

	// 게시물 수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......" + board);
		
		// 정상적으로 수정 이루어지면 1이 반환되기 때문에
		// ‘==’ 연산자를 이용하여 boolean처리
		return mapper.update(board)==1;
	}

	// 게시물 삭제 
	@Override
	public boolean remove(Long bno) {
		log.info("modify......" + bno);
		
		// 정상적으로 삭제가 이루어지면 1이 반환되기 때문에
		// ‘==’ 연산자를 이용하여 boolean처리
		return mapper.delete(bno)==1;
	}
	
	// 현재 테이블에 저장된 모든 데이터를 가져오는 메소드 
//	@Override
//	public List<BoardVO> getList() {
//		
//		log.info("getList.........");
//		
//		return mapper.getList();
//	}
	
	// 현재 테이블에 저장된 모든 데이터를 가져오는 메소드 
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("get List with criteria : " + cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	// 테이블에 저장된 데이터 개수를 가져오는 메소드
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	
}
