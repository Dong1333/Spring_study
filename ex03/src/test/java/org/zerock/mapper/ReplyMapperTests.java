package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	// 현재 실제 있는 데이터의 bno 값인지 확인필수
	private Long[] bnoArr = {450L, 449L, 448L, 447L, 446L, 445L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	// 댓글 등록 테스트
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1,10).
		forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
		
	}
	
	// 댓글 조회 테스트 
	@Test
	public void testRead() {
		
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
		
	// 댓글 삭제 테스트
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		
		mapper.delete(targetRno);
	}
	
	// 댓글 수정 테스트
	@Test
	public void testUpdate() {
		
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("update Reply");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT " + count);
		
	}
	
	// 댓글 목록 페이징 테스트
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		//450L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	
	@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
}
